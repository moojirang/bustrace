package com.dazzilove.bustrace.task;

import com.dazzilove.bustrace.service.batch.RealtimeLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RealtimeLocationTask {

    private transient static final Logger logger = LoggerFactory.getLogger(RealtimeLocationTask.class);

    @Autowired
    RealtimeLocationService realtimeLocationService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void scheduleBusLocationToRealtimeLocationTask() throws Exception {
        realtimeLocationService.transFromBusLocationToRealTimeLocation();
    }

    @Scheduled(cron = "0 20 1 * * ?")
    public void scheduleDeletBusLocationBeforeOneWeekTask() throws Exception {
        realtimeLocationService.deletRealTimeLocationBeforeOneWeek();
    }


}
