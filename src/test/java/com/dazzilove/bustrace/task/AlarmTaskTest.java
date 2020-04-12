package com.dazzilove.bustrace.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AlarmTaskTest {
    @Autowired
    AlarmTask alarmTask;

    @Test
    void alarmTest() throws Exception {
        alarmTask.scheduleRouteIngAlarmTask();
    }
}
