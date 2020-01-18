package com.dazzilove.bustrace.service.batch;

import java.time.LocalDateTime;

public interface DeduplicationLocationService {
    public void deduplicationLocation(String routeId, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt) throws Exception;
}
