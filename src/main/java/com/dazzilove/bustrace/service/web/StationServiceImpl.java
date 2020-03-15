package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.Station;
import com.dazzilove.bustrace.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    StationRepository stationRepository;

    @Override
    public Station getStation(String stationId, String stationSeq) {
        List<AggregationOperation> list = new ArrayList<AggregationOperation>();
        if (stationId.length() > 0)
            list.add(Aggregation.match(Criteria.where("stationId").is(stationId)));
        if (stationSeq.length() > 0)
            list.add(Aggregation.match(Criteria.where("stationSeq").is(stationSeq)));
        TypedAggregation<Station> agg = Aggregation.newAggregation(Station.class, list);

        List<Station> stations = mongoOperations.aggregate(agg, Station.class, Station.class).getMappedResults();
        if (stations == null || stations.size() == 0)
            return new Station();
        else
            return stations.get(0);
    }
}
