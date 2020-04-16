package com.dazzilove.bustrace.service.batch;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.RealtimeLocation;
import com.dazzilove.bustrace.repository.RealtimeLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RealtimeLocationServiceImpl implements RealtimeLocationService {

    @Autowired
    RealtimeLocationRepository realtimeLocationRepository;

    @Autowired
    BusLocationBatchService busLocationBatchService;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void transFromBusLocationToRealTimeLocation() throws Exception {
        LocalDateTime startDate = LocalDateTime.now().minusMinutes(30);
        LocalDateTime endDate = LocalDateTime.now();
        List<BusLocation> busLocations = busLocationBatchService.getBusLoactionsByCreatedAt(startDate, endDate);

        busLocations.stream().forEach(busLocation -> {
            RealtimeLocation realtimeLocation = new RealtimeLocation();
            realtimeLocation.setId(busLocation.getId());
            realtimeLocation.setRouteId(busLocation.getRouteId());
            realtimeLocation.setStationId(busLocation.getStationId());
            realtimeLocation.setStationSeq(busLocation.getStationSeq());
            realtimeLocation.setEndBus(busLocation.getEndBus());
            realtimeLocation.setLowPlate(busLocation.getLowPlate());
            realtimeLocation.setPlateNo(busLocation.getPlateNo());
            realtimeLocation.setPlateType(busLocation.getPlateType());
            realtimeLocation.setRemainSeatCnt(busLocation.getRemainSeatCnt());
            realtimeLocation.setCreatedAt(busLocation.getCreatedAt());
            realtimeLocationRepository.save(realtimeLocation);
        });
    }

    @Override
    public void deletRealTimeLocationBeforeOneWeek() {
        LocalDateTime createdAt = LocalDateTime.now().minusDays(8);
        realtimeLocationRepository.deletRealTimeLocationBeforeOneWeek(createdAt);
    }

    @Override
    public List<RealtimeLocation> getLocationsForPlateAndSortByCreatedAtAsc(RealtimeLocation param) throws Exception {
        String routeId = param.getRouteId().trim();
        String plateNo = param.getPlateNo().trim();
        LocalDateTime startCreatedAt = param.getStartCreatedAt();
        LocalDateTime endCreatedAt = param.getEndCreatedAt();

        List<AggregationOperation> list = new ArrayList<AggregationOperation>();
        if (routeId.length() > 0)
            list.add(Aggregation.match(Criteria.where("routeId").is(routeId)));
        if (plateNo.length() > 0)
            list.add(Aggregation.match(Criteria.where("plateNo").is(plateNo)));
        if (startCreatedAt != null)
            list.add(Aggregation.match(Criteria.where("createdAt").gte(startCreatedAt)));
        if (endCreatedAt != null)
            list.add(Aggregation.match(Criteria.where("createdAt").lte(endCreatedAt)));
        list.add(Aggregation.sort(Sort.Direction.ASC, "routeId", "plateNo", "createdAt"));
        TypedAggregation<BusLocation> agg = Aggregation.newAggregation(BusLocation.class, list);

        List<RealtimeLocation> locations = mongoOperations.aggregate(agg, RealtimeLocation.class, RealtimeLocation.class).getMappedResults();
        if (locations == null)
            locations = new ArrayList<>();

        return locations;
    }
}
