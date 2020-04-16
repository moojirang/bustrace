package com.dazzilove.bustrace.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlateRunHistoryTaskTest {
    @Autowired
    PlateRunHistoryTask plateRunHistoryTask;

    @Test
    void schedulePlateRunHistorySaveTaskTest() throws Exception {
        plateRunHistoryTask.schedulePlateRunHistorySaveTask();
    }
}
