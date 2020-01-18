package com.dazzilove.bustrace.service.batch;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.Location;
import com.dazzilove.bustrace.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DeduplicationLocationServiceImpl implements DeduplicationLocationService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void deduplicationLocation(String routeId, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt) throws Exception {
        List<LocalDateTime> dateDurations = getDateDurations(startCreatedAt, endCreatedAt);
        for(LocalDateTime searchDate: dateDurations) {
            LocalDateTime startCreatedAtParam = LocalDateTime.of(searchDate.getYear(), searchDate.getMonth(), searchDate.getDayOfMonth(), 0, 0, 0);
            LocalDateTime endCreatedAtParam = LocalDateTime.of(startCreatedAtParam.getYear(), startCreatedAtParam.getMonth(), startCreatedAtParam.getDayOfMonth(), 23, 59, 59);

            getPlateNosFromBusLocation(routeId, startCreatedAtParam, endCreatedAtParam)
                .stream()
                .forEach(plateNo -> {
                    getStationIdsFromBusLoaction(routeId, plateNo, startCreatedAtParam, endCreatedAtParam)
                        .stream()
                        .forEach(stationId -> {
                            final long baseSecondsDiff = 15*60;
                            deleteLocations(routeId, plateNo, stationId, startCreatedAtParam, endCreatedAtParam);
                            List<Location> locations = getLocationsFromBusLocation(routeId, plateNo, stationId, startCreatedAtParam, endCreatedAtParam);
                            List<Location> lastLocations = new ArrayList<>();
                            LocalDateTime creatdAtBefore = LocalDateTime.now();
                            for(Location location: locations) {
                                LocalDateTime createdAt = location.getCreatedAt();
                                long secondsDiff = Duration.between(createdAt, creatdAtBefore).getSeconds();
                                if (secondsDiff >= baseSecondsDiff) {
                                    lastLocations.add(location);
                                }
                                creatdAtBefore = createdAt;
                            }
                            locationRepository.saveAll(lastLocations);
                        });
            });
        }
    }

    private List<LocalDateTime> getDateDurations(LocalDateTime startCreatedAt, LocalDateTime endCreatedAt) {
        List<LocalDateTime> dateDurations = new ArrayList<>();
        LocalDateTime localDateTime = startCreatedAt;
        while (localDateTime.isBefore(endCreatedAt)) {
            dateDurations.add(localDateTime);
            localDateTime = localDateTime.plusDays(1);
        }
        return dateDurations;
    }

    private void deleteLocations(String routeId, String plateNo, String stationId, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt) {
        List<AggregationOperation> list = new ArrayList<AggregationOperation>();
        list.add(Aggregation.match(Criteria.where("routeId").is(routeId)));
        list.add(Aggregation.match(Criteria.where("plateNo").is(plateNo)));
        list.add(Aggregation.match(Criteria.where("stationId").is(stationId)));
        list.add(Aggregation.match(Criteria.where("createdAt").gte(startCreatedAt)));
        list.add(Aggregation.match(Criteria.where("createdAt").lte(endCreatedAt)));
        TypedAggregation<Location> agg = Aggregation.newAggregation(Location.class, list);

        List<Location> locations = mongoOperations.aggregate(agg, Location.class, Location.class).getMappedResults();
        locationRepository.deleteAll(locations);
    }

    private List<String> getPlateNosFromBusLocation(String routeId, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt) {

        List<AggregationOperation> list = new ArrayList<AggregationOperation>();
        list.add(Aggregation.match(Criteria.where("routeId").is(routeId)));
        list.add(Aggregation.match(Criteria.where("createdAt").gte(startCreatedAt)));
        list.add(Aggregation.match(Criteria.where("createdAt").lte(endCreatedAt)));
        list.add(Aggregation.group("routeId", "plateNo").count().as("totCount"));
        list.add(Aggregation.project("routeId", "plateNo", "totCount"));
        TypedAggregation<BusLocation> agg = Aggregation.newAggregation(BusLocation.class, list);

        List<String> plateNos = new ArrayList<>();
        List<BusLocation> busLocationList = mongoOperations.aggregate(agg, BusLocation.class, BusLocation.class).getMappedResults();
        if (!busLocationList.isEmpty()) {
            busLocationList.stream().forEach(busLocation -> plateNos.add(busLocation.getPlateNo()));
        }

        return plateNos;
    }

    private List<String> getStationIdsFromBusLoaction(String routeId, String plateNo, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt) {
        List<AggregationOperation> list = new ArrayList<AggregationOperation>();
        list.add(Aggregation.match(Criteria.where("routeId").is(routeId)));
        list.add(Aggregation.match(Criteria.where("plateNo").is(plateNo)));
        list.add(Aggregation.match(Criteria.where("createdAt").gte(startCreatedAt)));
        list.add(Aggregation.match(Criteria.where("createdAt").lte(endCreatedAt)));
        list.add(Aggregation.group("routeId", "plateNo", "stationId").count().as("totCount"));
        list.add(Aggregation.project("routeId", "plateNo", "stationId", "totCount"));
        TypedAggregation<BusLocation> agg = Aggregation.newAggregation(BusLocation.class, list);

        List<String> stationIds = new ArrayList<>();
        List<BusLocation> busLocationList = mongoOperations.aggregate(agg, BusLocation.class, BusLocation.class).getMappedResults();
        if (!busLocationList.isEmpty()) {
            busLocationList.stream().forEach(busLocation -> stationIds.add(busLocation.getStationId()));
        }
        return stationIds;
    }


    private List<Location> getLocationsFromBusLocation(String routeId, String plateNo, String stationId, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt) {
        List<AggregationOperation> list = new ArrayList<AggregationOperation>();
        list.add(Aggregation.match(Criteria.where("routeId").is(routeId)));
        list.add(Aggregation.match(Criteria.where("plateNo").is(plateNo)));
        list.add(Aggregation.match(Criteria.where("stationId").is(stationId)));
        list.add(Aggregation.match(Criteria.where("createdAt").gte(startCreatedAt).lte(endCreatedAt)));
        list.add(Aggregation.sort(Sort.Direction.DESC, "createdAt"));
        TypedAggregation<BusLocation> agg = Aggregation.newAggregation(BusLocation.class, list);
        List<BusLocation> busLocationList = mongoOperations.aggregate(agg, BusLocation.class, BusLocation.class).getMappedResults();

        List<Location> locationList = new ArrayList<>();
        if (busLocationList.isEmpty()) {
            return locationList;
        }

        busLocationList.stream().forEach(busLocation -> {
            Location location = new Location();
            location.setId(UUID.randomUUID());
            location.setRouteId(busLocation.getRouteId());
            location.setStationId(busLocation.getStationId());
            location.setStationSeq(busLocation.getStationSeq());
            location.setEndBus(busLocation.getEndBus());
            location.setLowPlate(busLocation.getLowPlate());
            location.setPlateNo(busLocation.getPlateNo());
            location.setPlateType(busLocation.getPlateType());
            location.setRemainSeatCnt(busLocation.getRemainSeatCnt());
            location.setCreatedAt(busLocation.getCreatedAt());
            locationList.add(location);
        });
        return locationList;
    }
}
