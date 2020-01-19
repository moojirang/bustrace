package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.Location;
import com.dazzilove.bustrace.domain.LocationParam;
import org.apache.commons.lang.StringUtils;
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
public class LocationServiceImpl implements LocationService {
	@Autowired
	MongoOperations mongoOperations;

	public List<Location> getLocations(LocationParam locationParam) throws Exception {
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

		List<Location> locations = mongoOperations.aggregate(agg, Location.class, Location.class).getMappedResults();
		if (locations == null)
			locations = new ArrayList<>();

		return locations;
	}
}
