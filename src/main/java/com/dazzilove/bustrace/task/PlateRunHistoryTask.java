package com.dazzilove.bustrace.task;

import com.dazzilove.bustrace.domain.PlateRunHistory;
import com.dazzilove.bustrace.service.batch.PlateRunHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlateRunHistoryTask {

    private transient static final Logger logger = LoggerFactory.getLogger(PlateRunHistoryTask.class);

    @Autowired
    PlateRunHistoryService plateRunHistoryService;

    @Scheduled(cron = "0 10 2 * * ?")
    public void schedulePlateRunHistorySaveTask() throws Exception {
        List<String> routes = new ArrayList<>();
        routes.add("224000019"); // 3200
        routes.add("224000047"); // 3300
        routes.add("224000050"); // 3400
        routes.add("224000054"); // 3500

        LocalDateTime now = LocalDateTime.now().minusDays(1);

        for (String routeId: routes) {
            PlateRunHistory param = new PlateRunHistory();
            param.setRouteId(routeId);
            param.setStartDate(now);
            param.setEndDate(now);
            plateRunHistoryService.savePlateRunHistory(param);
        }

    }
}
