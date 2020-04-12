package com.dazzilove.bustrace.repository;

import com.dazzilove.bustrace.domain.RealtimeLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RealtimeLocationRepository extends MongoRepository<RealtimeLocation, UUID> {
    @Query("{'createdAt' : { '$lt':?0 }}")
    void deletRealTimeLocationBeforeOneWeek(LocalDateTime createdAt);
}
