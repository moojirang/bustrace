package com.dazzilove.bustrace.service.batch;

import com.dazzilove.bustrace.domain.RealtimeLocation;

import java.util.List;

public interface RealtimeLocationService {
    void transFromBusLocationToRealTimeLocation() throws Exception;

    void deletRealTimeLocationBeforeOneWeek() throws Exception;

    List<RealtimeLocation> getLocationsForPlateAndSortByCreatedAtAsc(RealtimeLocation param) throws Exception;
}
