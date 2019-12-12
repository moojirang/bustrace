package com.dazzilove.bustrace.task;

import com.dazzilove.bustrace.service.BusLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BusLocationTask {

    private transient static final Logger logger = LoggerFactory.getLogger(BusLocationTask.class);

    @Autowired
    private BusLocationService busLocationService;

    // cron = 초 분 시 일 월 주 (년)
    // 5분간격 6:00 ~ 9:55 까지 매일
    @Scheduled(cron = "0 0/5 6-9 * * ?")
    public void scheduleBusLocationMorningSaveTask() {
        busLocationMorningAndNight();
    }

    @Scheduled(cron = "0 0/5 17-21 * * ?")
    public void scheduleBusLocationNightSaveTask() {
        busLocationMorningAndNight();
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void scheduleBusLocationAllDaySaveTask() {
        busLocationAllDay();
    }

    private void busLocationAllDay() {
        List<String> routes = new ArrayList<>();
        routes.add("216000047"); // 5602
        routes.add("224000040"); // 5604
        routes.add("224000019"); // 32
        routes.add("208000009"); // 81
        routes.add("224000014"); // 30-2

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
}
