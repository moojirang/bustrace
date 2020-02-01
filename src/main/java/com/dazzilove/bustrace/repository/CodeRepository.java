package com.dazzilove.bustrace.repository;

import com.dazzilove.bustrace.domain.Code;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CodeRepository extends MongoRepository<Code, UUID> {
}
