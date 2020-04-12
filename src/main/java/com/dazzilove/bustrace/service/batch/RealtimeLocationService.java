package com.dazzilove.bustrace.service.batch;

public interface RealtimeLocationService {
    void transFromBusLocationToRealTimeLocation() throws Exception;

    void deletRealTimeLocationBeforeOneWeek() throws Exception;
}
