package com.dazzilove.bustrace.service.batch;

import com.dazzilove.bustrace.domain.PlateRunHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class PlateRunHistoryServiceTest {

    @Autowired
    PlateRunHistoryService plateRunHistoryService;

    @Test
    void savePlateRunHistoryTest() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        PlateRunHistory plateRunHistoryParam = new PlateRunHistory();
        plateRunHistoryParam.setRouteId("224000047"); // 3300
        plateRunHistoryParam.setStartDate(now.minusDays(8));
        plateRunHistoryParam.setEndDate(now.minusDays(1));

        plateRunHistoryService.savePlateRunHistory(plateRunHistoryParam);
    }
}
