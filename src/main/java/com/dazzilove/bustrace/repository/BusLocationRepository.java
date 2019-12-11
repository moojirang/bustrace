package com.dazzilove.bustrace.repository;

import com.dazzilove.bustrace.domain.BusLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface BusLocationRepository extends MongoRepository<BusLocation, UUID> {

    List<BusLocation> findAll();

    @Query("{}")
    Stream<BusLocation> findAllByCustomQueryWithStream();
}
