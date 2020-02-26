package com.dazzilove.bustrace.service.batch;

import com.dazzilove.bustrace.domain.*;
import com.dazzilove.bustrace.service.web.LocationService;
import com.dazzilove.bustrace.service.web.RouteService;
import com.dazzilove.bustrace.service.web.SpecialMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RouteMonitoringServiceImpl implements RouteMonitoringService {

    public static final String PLAT_TYPE_DOUBLE_DECKER = "4";
    @Autowired
    private RouteService routeService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private SpecialMessageService specialMessageService;

    @Override
    public void weekdayMorningTripPlanHistory() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startCreatedAt = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
        LocalDateTime endCreatedAt = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 8, 59, 59);

        List<Route> routes = routeService.getRoutes();
        for (Route route: routes) {
            String routeId = route.getRouteId();
            int weekdayCount = route.getWeekdayCount();
            int weekdayDoubleDeckerCount = route.getWeekdayDoubleDeckerCount();

            int recordedWeekdayCount = 0;
            int recordedWeekdayDoubleDeckerCount = 0;
            List<TripPlan> tripPlans = route.getTripPlanList();
            for(TripPlan tripPlan: tripPlans) {
                LocationParam locationParam = new LocationParam();
                locationParam.setRouteId(routeId);
                locationParam.setPlateNo(tripPlan.getPlateNo());
                locationParam.setStartCreatedAt(startCreatedAt);
                locationParam.setEndCreatedAt(endCreatedAt);

                List<Location> locations = locationService.getLocations(locationParam);
                if (!locations.isEmpty() && locations.size() > 0) {
                    recordedWeekdayCount++;
                    if (PLAT_TYPE_DOUBLE_DECKER.equals(tripPlan.getPlateType())) {
                        recordedWeekdayDoubleDeckerCount++;
                    }
                }
            }

            if (recordedWeekdayCount < weekdayCount ) {
                addSpecialMessage(routeId,
                        SpecialMessageDivCd.MONITORING_TOTAL_TRIP_RECORD,
                        "평일 오전 9시, 총 운행대수가 부족합니다. (" + recordedWeekdayCount + "/" + weekdayCount + ")");
            }

            if (recordedWeekdayDoubleDeckerCount < weekdayDoubleDeckerCount) {
                addSpecialMessage(routeId,
                        SpecialMessageDivCd.MONITORING_DOUBLE_DECKER_TRIP_RECORD,
                        "평일 오전 9시, 2층버스 총 운행대수가 부족합니다. (" + recordedWeekdayDoubleDeckerCount + "/" + weekdayDoubleDeckerCount + ")");
            }
        }
    }

    private void addSpecialMessage(String routeId, SpecialMessageDivCd divCd, String message) {
        SpecialMessage specialMessage = new SpecialMessage();
        specialMessage.setRouteId(routeId);
        specialMessage.setDivCd(divCd.name());
        specialMessage.setMessage(message);
        specialMessageService.add(specialMessage);
    }
}
