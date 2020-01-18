package com.dazzilove.bustrace.task;

import com.dazzilove.bustrace.service.BusLocationService;
import com.dazzilove.bustrace.service.batch.DeduplicationLocationService;
import com.dazzilove.bustrace.service.web.TripPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BusLocationTask {

    private transient static final Logger logger = LoggerFactory.getLogger(BusLocationTask.class);

    @Autowired
    private BusLocationService busLocationService;

    @Autowired
    private DeduplicationLocationService deduplicationLocationService;

    @Autowired
    private TripPlanService tripPlanService;

    // cron = 초 분 시 일 월 주 (년)
    // 5분간격 6:00 ~ 9:55 까지 매일
    @Scheduled(cron = "0 0/1 6-9 * * ?")
    public void scheduleBusLocationMorningSaveTask() {
        busLocationMorningAndNight();
    }

    @Scheduled(cron = "0 0/1 17-21 * * ?")
    public void scheduleBusLocationNightSaveTask() {
        busLocationMorningAndNight();
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void scheduleBusLocationAllDaySaveTask() {
        busLocationAllDay();
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void scheduleTripInfoUpdateTask() throws Exception {
        tripRecordUpdate();
    }

    @Scheduled(cron = "10 10 1 * * ?")
    public void scheduleLocationSaveTask() {
        List<String> routes = new ArrayList<>();
        routes.add("216000047"); // 5602
        routes.add("224000040"); // 5604
        routes.add("217000009"); // 32
        routes.add("208000009"); // 81
        routes.add("224000014"); // 30-2
        routes.add("224000019"); // 3200
        routes.add("224000047"); // 3300
        routes.add("224000050"); // 3400
        routes.add("224000054"); // 3500

        for(String routeId: routes) {
            try {
//                LocalDateTime baseDateTime = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
//                LocalDateTime startCreatedAt = LocalDateTime.of(baseDateTime.getYear(), baseDateTime.getMonth(), baseDateTime.getDayOfMonth(), 0, 0, 0);
//                LocalDateTime endCreatedAt = LocalDateTime.of(baseDateTime.getYear(), baseDateTime.getMonth(), baseDateTime.getDayOfMonth(), 23, 59, 59);

                LocalDateTime startCreatedAt = LocalDateTime.of(2019, 11, 1, 0, 0, 0);
                LocalDateTime endCreatedAt = LocalDateTime.of(2020, 1, 17, 23, 59, 59);

                deduplicationLocationService.deduplicationLocation(routeId, startCreatedAt, endCreatedAt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void busLocationAllDay() {
        List<String> routes = new ArrayList<>();
        routes.add("216000047"); // 5602
        routes.add("224000040"); // 5604
        routes.add("217000009"); // 32
        routes.add("208000009"); // 81
        routes.add("224000014"); // 30-2
        routes.add("224000019"); // 3200
        routes.add("224000047"); // 3300
        routes.add("224000050"); // 3400
        routes.add("224000054"); // 3500

        busLocationSave(routes);
    }

    private void busLocationMorningAndNight() {
        List<String> routes = new ArrayList<>();
        routes.add("224000019"); // 3200
        routes.add("224000047"); // 3300
        routes.add("224000050"); // 3400
        routes.add("224000054"); // 3500

        busLocationSave(routes);
    }

    private void busLocationSave(List<String> routes) {
        try {
            for (String route : routes) {
                busLocationService.addBusLocationList(route);
            }
        } catch (Exception e) {
            logger.error("[SCHEDULER] busLocation service error, " + e.getMessage());
        }
    }

    private void tripRecordUpdate() throws Exception {
        List<String> routes = new ArrayList<>();
        routes.add("216000047"); // 5602
        routes.add("224000040"); // 5604
        routes.add("217000009"); // 32
        routes.add("208000009"); // 81
        routes.add("224000014"); // 30-2
        routes.add("224000019"); // 3200
        routes.add("224000047"); // 3300
        routes.add("224000050"); // 3400
        routes.add("224000054"); // 3500

        for(String routeId: routes) {
            tripPlanService.updateTripRecord(routeId);
        }
    }
}
