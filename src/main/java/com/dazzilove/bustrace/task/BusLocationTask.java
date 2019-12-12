package com.dazzilove.bustrace.task;

import com.dazzilove.bustrace.service.BusLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BusLocationTask {

    private transient static final Logger logger = LoggerFactory.getLogger(BusLocationTask.class);

    @Value("${bustrace.routeId}")
    private String routeId;

    @Autowired
    private BusLocationService busLocationService;

    // cron = 초 분 시 일 월 주 (년)
    // 5분간격 6:00 ~ 9:55 까지 매일
    @Scheduled(cron = "0 0/5 6-9 * * ?")
    public void scheduleBusLocationSaveTask() {
        try {
            busLocationService.addBusLocationList(routeId);
        }
        catch ( Exception e ) {
            logger.error("[SCHEDULER] busLocation service error, " + e.getMessage());
        }
    }
}
