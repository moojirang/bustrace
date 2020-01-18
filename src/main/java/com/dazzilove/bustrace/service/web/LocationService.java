package com.dazzilove.bustrace.service.web;

import java.time.LocalDateTime;

public interface LocationService {
    public void deduplicationLocation(String routeId, LocalDateTime baseDate) throws Exception;
}
