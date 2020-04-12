package com.dazzilove.bustrace.task;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RealtimeLocationTaskTest {

    @Autowired
    RealtimeLocationTask realtimeLocationTask;

    @Test
    @Ignore
    void scheduleBusLocationToRealtimeLocationTaskTest() throws Exception {
        realtimeLocationTask.scheduleBusLocationToRealtimeLocationTask();
    }

    @Test
    @Ignore
    void deletRealTimeLocationBeforeOneWeekTest() throws Exception {
        realtimeLocationTask.scheduleDeletBusLocationBeforeOneWeekTask();
    }
}
