package com.dazzilove.dd.repository;

import com.dazzilove.dd.domain.WordUnit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface WordUnitRepository extends MongoRepository<WordUnit, UUID> {
}
