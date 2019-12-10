package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.service.wsdl.BusRoute;
import com.dazzilove.bustrace.service.wsdl.GetBusRouteListResponse;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    private static final Logger logger = LoggerFactory.getLogger(BusRouteServiceImpl.class);

    @Autowired
    private BusRouteClient client;

    @Value("${bustrace.serviceKey}")
    private String serviceKey;

    @Override
    public List<BusRoute> getBusRoutes(String keyword) {
        GetBusRouteListResponse response = client.getBusRouteList(serviceKey, keyword);

        logger.info("response=" + ToStringBuilder.reflectionToString(response));
        logger.info("return=" + ToStringBuilder.reflectionToString(response.getReturn()));
        logger.info("msgHeader=" + ToStringBuilder.reflectionToString(response.getReturn().getMsgHeader()));
        logger.info("comMsgHeader=" + ToStringBuilder.reflectionToString(response.getReturn().getComMsgHeader()));
        logger.info("msgBody=" + ToStringBuilder.reflectionToString(response.getReturn().getMsgBody()));

        if (response != null && response.getReturn() != null && response.getReturn().getMsgBody() != null) {
            return response.getReturn().getMsgBody().getBusRouteList();
        }
        return null;
    }
}
