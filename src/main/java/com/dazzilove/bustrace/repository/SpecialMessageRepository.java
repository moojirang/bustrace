package com.dazzilove.bustrace.repository;

import com.dazzilove.bustrace.domain.SpecialMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpecialMessageRepository extends MongoRepository<SpecialMessage, UUID> {

    @Query("{'routeId':?0}")
    List<SpecialMessage> findByRouteId(String routeId);
}
