package com.dazzilove.bustrace.task;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RouteMonitoringTaskTest {

    @Autowired
    RouteMonitoringTask routeMonitoringTask;

    @Test
    @Ignore
    public void scheduleMorningTripPlanCountTaskTest() throws Exception {
        routeMonitoringTask.scheduleMorningTripPlanCountTask();
    }

}
