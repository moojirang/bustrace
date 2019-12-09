package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.service.wsdl.BusRoute;
import com.dazzilove.bustrace.service.wsdl.GetBusRouteListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    private static final Logger logger = LoggerFactory.getLogger(BusRouteServiceImpl.class);

    @Autowired
    private BusRouterClient client;

    @Override
    public List<BusRoute> getBusRoutes(String keyword) {
        GetBusRouteListResponse response = client.getBusRouteList(keyword, "");

        logger.info("result=", response);
        if (response != null && response.getReturn() != null && response.getReturn().getMsgBody() != null) {
            return response.getReturn().getMsgBody().getBusRouteList();
        }
        return null;
    }
}
