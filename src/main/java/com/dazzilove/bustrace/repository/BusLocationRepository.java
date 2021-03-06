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


    @Query("{'routeId':?0, 'plateNo':?1 'createdAt' : { '$gt' : ?2, '$lt':?3 }}")
    List<BusLocation> findByRouteIdAndPlateNoAndCreatedAtRange(String routeId, String plateNo, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt);

    @Query("{}")
    Stream<BusLocation> findAllByCustomQueryWithStream();

    @Query("{'createdAt' : { '$gt' : ?0, '$lt':?1 }}")
    List<BusLocation> getBusLoactionsByCreatedAt(LocalDateTime startDate, LocalDateTime endDate);
}
