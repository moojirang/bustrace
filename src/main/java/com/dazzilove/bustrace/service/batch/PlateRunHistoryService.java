package com.dazzilove.bustrace.service.batch;

import com.dazzilove.bustrace.domain.PlateRunHistory;

public interface PlateRunHistoryService {
    void savePlateRunHistory(PlateRunHistory plateRunHistory) throws Exception;
}
