package com.dazzilove.bustrace.repository;

import com.dazzilove.bustrace.domain.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RouteRepository extends MongoRepository<Route, UUID> {
}
