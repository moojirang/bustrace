package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.domain.BusLocationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class BusLocationServiceImpl implements BusLocationService {

    private static final Logger logger = LoggerFactory.getLogger(BusLocationServiceImpl.class);

    @Value("${bustrace.serviceKey}")
    private String serviceKey;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public BusLocationResult getBusLocation(String routeId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("http://openapi.gbis.go.kr/ws/rest/buslocationservice");
        sb.append("?serviceKey=" + serviceKey);
        sb.append("&routeId=" + routeId);

        return restTemplate.getForObject(sb.toString(), BusLocationResult.class);
    }
}
