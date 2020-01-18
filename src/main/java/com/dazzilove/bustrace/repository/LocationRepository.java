package com.dazzilove.bustrace.repository;

import com.dazzilove.bustrace.domain.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface LocationRepository extends MongoRepository<Location, UUID> {
}
