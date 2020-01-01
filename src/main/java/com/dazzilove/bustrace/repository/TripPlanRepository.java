package com.dazzilove.bustrace.repository;

import com.dazzilove.bustrace.domain.TripPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface TripPlanRepository extends MongoRepository<TripPlan, UUID> {
	List<TripPlan> findByRouteId(String routeId);
}
