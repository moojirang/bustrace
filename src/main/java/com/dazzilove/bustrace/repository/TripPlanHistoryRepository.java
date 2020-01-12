package com.dazzilove.bustrace.repository;

import com.dazzilove.bustrace.domain.TripPlanHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TripPlanHistoryRepository extends MongoRepository<TripPlanHistory, UUID> {
}
