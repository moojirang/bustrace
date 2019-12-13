package com.dazzilove.bustrace.repository;

import com.dazzilove.bustrace.domain.BusLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface BusLocationRepository extends MongoRepository<BusLocation, UUID> {

    List<BusLocation> findAll();

    List<BusLocation> findByRouteId(String routeId);


    @Query("{'routeId':?0, 'createdAt' : { '$gt' : ?1, '$lt':?2 }}")
    List<BusLocation> findByRouteIdAndCreatedAtRange(String routeId, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt);

    @Query("{}")
    Stream<BusLocation> findAllByCustomQueryWithStream();
}
