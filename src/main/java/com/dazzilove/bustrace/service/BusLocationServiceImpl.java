package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.LocationParam;
import com.dazzilove.bustrace.domain.mapper.BusLocationMapper;
import com.dazzilove.bustrace.repository.BusLocationRepository;
import com.dazzilove.bustrace.service.ws.BusLocationList;
import com.dazzilove.bustrace.service.ws.BusLocationListResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class BusLocationServiceImpl implements BusLocationService {

    private static final Logger logger = LoggerFactory.getLogger(BusLocationServiceImpl.class);

    @Value("${bustrace.serviceKey}")
    private String serviceKey;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private BusLocationMapper busLocationMapper;

    @Autowired
    private BusLocationRepository busLocationRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<BusLocationList> getBusLocation(String routeId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("http://openapi.gbis.go.kr/ws/rest/buslocationservice");
        sb.append("?serviceKey=" + serviceKey);
        sb.append("&routeId=" + routeId);

        BusLocationListResponse response = restTemplate.getForObject(sb.toString(), BusLocationListResponse.class);

        if (response != null && response.getMsgBody() != null) {
            return response.getMsgBody().getBusLocationList();
        }

        return null;
    }

    @Override
    public void addBusLocationList(String routeId) throws Exception {
        List<BusLocationList> list = this.getBusLocation(routeId);

        if (list != null) {
            list.stream()
                    .map(vo -> busLocationMapper.toEntity(vo))
                    .forEach( entity -> {
                        entity.setId(UUID.randomUUID());
                        entity.setCreatedAt(LocalDateTime.now());
                        busLocationRepository.insert(entity);
                    });

        }
    }

    @Override
    public List<BusLocation> getBusLoactions(LocationParam locationParam) throws Exception {
        String routeid = StringUtils.defaultString(locationParam.getRouteId(), "").trim();
        String plateNo = StringUtils.defaultString(locationParam.getPlateNo(), "").trim();
        String stationId = StringUtils.defaultString(locationParam.getStationId(), "").trim();
        LocalDateTime startCreatedAt = locationParam.getStartCreatedAt();
        LocalDateTime endCreatedAt = locationParam.getEndCreatedAt();

        List<AggregationOperation> list = new ArrayList<AggregationOperation>();
        if (routeid.length() > 0)
            list.add(Aggregation.match(Criteria.where("routeId").is(routeid)));
        if (plateNo.length() > 0)
            list.add(Aggregation.match(Criteria.where("plateNo").is(plateNo)));
        if (stationId.length() > 0)
            list.add(Aggregation.match(Criteria.where("stationId").is(stationId)));
        if (startCreatedAt != null)
            list.add(Aggregation.match(Criteria.where("createdAt").gte(startCreatedAt)));
        if (endCreatedAt != null)
            list.add(Aggregation.match(Criteria.where("createdAt").lte(endCreatedAt)));
        list.add(Aggregation.sort(Sort.Direction.ASC, "routeId", "createdAt", "plateNo", "stationId"));
        TypedAggregation<BusLocation> agg = Aggregation.newAggregation(BusLocation.class, list);

        List<BusLocation> locations = mongoOperations.aggregate(agg, BusLocation.class, BusLocation.class).getMappedResults();
        if (locations == null)
            locations = new ArrayList<>();

        return locations;
    }

    @Override
    public BusLocation getLastBusLocation(LocationParam locationParam) throws Exception {
        String routeid = StringUtils.defaultString(locationParam.getRouteId(), "").trim();
        String plateNo = StringUtils.defaultString(locationParam.getPlateNo(), "").trim();
        String stationId = StringUtils.defaultString(locationParam.getStationId(), "").trim();
        LocalDateTime startCreatedAt = locationParam.getStartCreatedAt();
        LocalDateTime endCreatedAt = locationParam.getEndCreatedAt();

        List<AggregationOperation> list = new ArrayList<AggregationOperation>();
        if (routeid.length() > 0)
            list.add(Aggregation.match(Criteria.where("routeId").is(routeid)));
        if (plateNo.length() > 0)
            list.add(Aggregation.match(Criteria.where("plateNo").is(plateNo)));
        if (stationId.length() > 0)
            list.add(Aggregation.match(Criteria.where("stationId").is(stationId)));
        if (startCreatedAt != null)
            list.add(Aggregation.match(Criteria.where("createdAt").gte(startCreatedAt)));
        if (endCreatedAt != null)
            list.add(Aggregation.match(Criteria.where("createdAt").lte(endCreatedAt)));
        list.add(Aggregation.sort(Sort.Direction.ASC, "routeId", "plateNo", "stationId", "createdAt"));
        list.add(Aggregation.limit(1));
        TypedAggregation<BusLocation> agg = Aggregation.newAggregation(BusLocation.class, list);

        List<BusLocation> locations = mongoOperations.aggregate(agg, BusLocation.class, BusLocation.class).getMappedResults();
        if (locations == null || locations.size() == 0) {
            return new BusLocation();
        } {
            return locations.get(0);
        }
    }

    @Override
    public List<BusLocation> getBusLoactionsByPlateNo(LocationParam locationParam) throws Exception {
        return busLocationRepository.findByRouteIdAndPlateNoAndCreatedAtRange(
                locationParam.getRouteId()
                , locationParam.getPlateNo()
                , locationParam.getStartCreatedAt()
                , locationParam.getEndCreatedAt());
    }
}
