package com.dazzilove.bustrace.task;

import com.dazzilove.bustrace.domain.Route;
import com.dazzilove.bustrace.domain.TripPlan;
import com.dazzilove.bustrace.service.web.RouteService;
import com.dazzilove.bustrace.service.web.TripPlanService;
import com.dazzilove.bustrace.utils.HolyDayUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlarmTask {
    private transient static final Logger logger = LoggerFactory.getLogger(AlarmTask.class);

    @Autowired
    RouteService routeService;

    @Autowired
    TripPlanService tripPlanService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduleRouteIngAlarmTask() throws Exception {
        List<Route> routes = routeService.getRoutes();
        if (routes.isEmpty()) {
            return;
        }

        for (Route route: routes) {
            if (route.isDeleted()) {
                continue;
            }

            String routeId = route.getRouteId();

            route.setTodayTripRecordCount(0);
            route.setTodayDoubleDeckerTripRecordCount(0);

            List<TripPlan> tripPlans = tripPlanService.findByRouteId(routeId);
            if (tripPlans.isEmpty()) {
                continue;
            }

            int todayTripRecordCount = 0;
            int todayDoubleDeckerTripRecordCount = 0;
            for (TripPlan tripPlan: tripPlans) {
                if(tripPlan.isDeleted()) {
                    continue;
                }

                String todayTripRecordYn = StringUtils.defaultString(tripPlan.getTodayTripRecordYn(), "N");
                String plateType = StringUtils.defaultString(tripPlan.getPlateType(), "");

                if ("Y".equals(todayTripRecordYn)) {
                    todayTripRecordCount++;
                    if ("4".equals(plateType)) {
                        todayDoubleDeckerTripRecordCount++;
                    }
                }
            }

            route.setTodayTripRecordCount(todayTripRecordCount);
            route.setTodayDoubleDeckerTripRecordCount(todayDoubleDeckerTripRecordCount);
        }

        List<String> messageList = new ArrayList<>();
        for (Route route: routes) {
            int weekdayCount = route.getWeekdayCount();
            int weekendCount = route.getWeekendCount();
            int weekdayDoubleDeckerCount = route.getWeekdayDoubleDeckerCount();
            int weekendDoubleDeckerCount = route.getWeekendDoubleDeckerCount();
            int todayTripRecordCount = route.getTodayTripRecordCount();
            int todayDoubleDeckerTripRecordCount = route.getTodayDoubleDeckerTripRecordCount();

            int routeTripCount = 0;
            int routeDoubleDeckerTripCount = 0;

            LocalDateTime now = LocalDateTime.now();
            String yyyymmdd = String.valueOf(now.getYear()) + String.valueOf(now.getMonthValue()) + String.valueOf(now.getDayOfMonth());

            if (HolyDayUtil.isHolyDay(yyyymmdd)) {
                routeTripCount = weekdayCount;
                routeDoubleDeckerTripCount = weekdayDoubleDeckerCount;
            } else {
                routeTripCount = weekendCount;
                routeDoubleDeckerTripCount = weekendDoubleDeckerCount;
            }

            boolean addComma = false;
            String msg = "";
            if (routeTripCount != todayTripRecordCount) {
                msg += String.format("대형버스 운행 차량 수 = %s (%s)", todayTripRecordCount, routeTripCount);
                addComma = true;
            }
            if (routeDoubleDeckerTripCount != todayDoubleDeckerTripRecordCount) {
                msg = (addComma) ? msg + "\n" : msg;
                msg += String.format("2층버스 운행 차량 수 = %s (%s)", todayDoubleDeckerTripRecordCount, routeDoubleDeckerTripCount);
            }

            if (!"".equals(msg)) {
                messageList.add(route.getRouteName() + "번 운행 차량 수를 확인하세요. \n" + msg);
            }
        }

        for(String message: messageList) {
            // TODO - kakao alarm
        }

    }
}
