package com.dazzilove.bustrace.repository;

import com.dazzilove.bustrace.domain.PlateRunHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PlateRunHistoryRepository extends MongoRepository<PlateRunHistory, UUID> {

    @Query("{'routeId':?0, 'runDay':?1 }")
    List<PlateRunHistory> findByRunDay(String routeId, String runDay);
}
