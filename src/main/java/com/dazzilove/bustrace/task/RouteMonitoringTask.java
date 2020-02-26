package com.dazzilove.bustrace.task;

import com.dazzilove.bustrace.service.batch.RouteMonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RouteMonitoringTask {

    private transient static final Logger logger = LoggerFactory.getLogger(RouteMonitoringTask.class);

    @Autowired
    private RouteMonitoringService routeMonitoringService;

    // cron = 초 분 시 일 월 주 (년)
    // 5분간격 6:00 ~ 9:55 까지 매일
    @Scheduled(cron = "0 0 9 * * ?")
    public void scheduleMorningTripPlanCountTask() throws Exception {
        routeMonitoringService.weekdayMorningTripPlanHistory();
    }
}
