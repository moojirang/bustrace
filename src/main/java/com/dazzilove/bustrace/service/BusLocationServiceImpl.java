package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.BusLocationParam;
import com.dazzilove.bustrace.domain.mapper.BusLocationMapper;
import com.dazzilove.bustrace.repository.BusLocationRepository;
import com.dazzilove.bustrace.service.ws.BusLocationList;
import com.dazzilove.bustrace.service.ws.BusLocationListResponse;
import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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
    public List<BusLocation> getBusLoactions(BusLocationParam busLocationParam) throws Exception {
        return busLocationRepository.findByRouteIdAndCreatedAtRange(
                  busLocationParam.getRouteId()
                , busLocationParam.getStartCreatedAt()
                , busLocationParam.getEndCreatedAt());
    }

    @Override
    public List<BusLocation> getBusLoactionsByPlateNo(BusLocationParam busLocationParam) throws Exception {
        return busLocationRepository.findByRouteIdAndPlateNoAndCreatedAtRange(
                busLocationParam.getRouteId()
                , busLocationParam.getPlateNo()
                , busLocationParam.getStartCreatedAt()
                , busLocationParam.getEndCreatedAt());
    }
}
