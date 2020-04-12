package com.dazzilove.bustrace.service.batch;

import com.dazzilove.bustrace.domain.BusLocation;

import java.time.LocalDateTime;
import java.util.List;

public interface BusLocationBatchService {
    List<BusLocation> getBusLoactionsByCreatedAt(LocalDateTime startDate, LocalDateTime endDate) throws Exception;
}
