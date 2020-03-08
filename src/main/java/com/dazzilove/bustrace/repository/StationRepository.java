package com.dazzilove.bustrace.repository;

import com.dazzilove.bustrace.domain.Station;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface StationRepository extends MongoRepository<Station, UUID> {
}
