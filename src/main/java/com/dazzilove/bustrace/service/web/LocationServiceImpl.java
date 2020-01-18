package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.Location;
import com.dazzilove.bustrace.repository.BusLocationRepository;
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
public class LocationServiceImpl implements LocationService {

    @Autowired
    BusLocationRepository busLocationRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void deduplicationLocation(String routeId, LocalDateTime baseDateTime) throws Exception {
        LocalDateTime startCreatedAt = LocalDateTime.of(baseDateTime.getYear(), baseDateTime.getMonth(), baseDateTime.getDayOfMonth(), 0, 0, 0);
        LocalDateTime endCreatedAt = LocalDateTime.of(baseDateTime.getYear(), baseDateTime.getMonth(), baseDateTime.getDayOfMonth(), 23, 59, 59);

        List<String> plateNos = getPlateNosByRouteIdFromBusLocation(routeId, startCreatedAt, endCreatedAt);
        if (plateNos.isEmpty()) {
            return;
        }

        plateNos.stream().forEach(plateNo -> {
            List<Location> locations = getLocationsByRouteIdAndPlateNoFromBusLocation(routeId, plateNo, startCreatedAt, endCreatedAt);

            List<Location> lastLocations = new ArrayList<>();
            String stationIdBefore = "";
            LocalDateTime creatdAtBefore = LocalDateTime.now();
            for(Location location: locations) {
                boolean isNotDup = false;
                String stationId = location.getStationId();
                LocalDateTime createdAt = location.getCreatedAt();

                if (!stationIdBefore.equals(stationId)) {
                    isNotDup = true;
                } else {
                    final long baseSecondsDiff = 15*60;
                    long secondsDiff = Duration.between(createdAt, creatdAtBefore).getSeconds();
                    if (secondsDiff >= baseSecondsDiff) {
                        isNotDup = true;
                    }
                }

                if (isNotDup)
                    lastLocations.add(location);

                stationIdBefore = stationId;
                creatdAtBefore = createdAt;
            }
            locationRepository.saveAll(lastLocations);
        });
    }

    private List<String> getPlateNosByRouteIdFromBusLocation(String routeId, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt) {

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


    private List<Location> getLocationsByRouteIdAndPlateNoFromBusLocation(String routeId, String plateNo, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt) {

        List<AggregationOperation> list = new ArrayList<AggregationOperation>();
        list.add(Aggregation.match(Criteria.where("routeId").is(routeId)));
        list.add(Aggregation.match(Criteria.where("plateNo").is(plateNo)));
        list.add(Aggregation.match(Criteria.where("createdAt").gte(startCreatedAt).lte(endCreatedAt)));
        list.add(Aggregation.sort(Sort.Direction.DESC, "stationId", "createdAt"));
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
