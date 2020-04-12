package com.dazzilove.bustrace.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RealtimeLocationTaskTest {

    @Autowired
    RealtimeLocationTask realtimeLocationTask;

    @Test
    void scheduleBusLocationToRealtimeLocationTaskTest() throws Exception {
        realtimeLocationTask.scheduleBusLocationToRealtimeLocationTask();
    }

    @Test
    void deletRealTimeLocationBeforeOneWeekTest() throws Exception {
        realtimeLocationTask.scheduleDeletBusLocationBeforeOneWeekTask();
    }
}
