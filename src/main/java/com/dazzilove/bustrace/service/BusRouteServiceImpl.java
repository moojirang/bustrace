package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.service.wsdl.*;
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

    @Override
    public List<BusRoute> getBusRouteList(String routeName) {
        GetBusRouteListResponse response = client.getBusRouteList(routeName);

        logger.info("response=" + ToStringBuilder.reflectionToString(response));

        if (response != null && response.getReturn() != null) {
            logger.info("return=" + ToStringBuilder.reflectionToString(response.getReturn()));

            ResMsgHeader header = response.getReturn().getMsgHeader();
            logger.info("msgHeader=" + ToStringBuilder.reflectionToString(response.getReturn().getMsgHeader()));

            if (header != null && header.getResultCode() == 0) {
                logger.info("comMsgHeader=" + ToStringBuilder.reflectionToString(response.getReturn().getComMsgHeader()));
                logger.info("msgBody=" + ToStringBuilder.reflectionToString(response.getReturn().getMsgBody()));

                return response.getReturn().getMsgBody().getBusRouteList();
            }
        }
        return null;
    }

    @Override
    public List<BusRoute> getAreaBusRouteList(String areaId, String routeName) {
        GetAreaBusRouteListResponse response = client.getAreaBusRouteList(areaId, routeName);

        if (response != null && response.getReturn() != null) {
            ResMsgHeader header = response.getReturn().getMsgHeader();
            if (header != null && header.getResultCode() == 0) {
                return response.getReturn().getMsgBody().getBusRouteList();
            }
        }
        return null;
    }

    @Override
    public BusRouteInfo getBusRouteInfoItem(String routeId) {
        GetBusRouteInfoItemResponse response = client.getBusRouteInfoItem(routeId);
        if (response != null && response.getReturn() != null) {
            ResMsgHeader header = response.getReturn().getMsgHeader();
            if (header != null && header.getResultCode() == 0) {
                return response.getReturn().getMsgBody().getBusRouteInfoItem();
            }
        }
        return null;
    }

    @Override
    public List<BusRouteLine> getBusRouteLineList(String routeId) {
        GetBusRouteLineListResponse response = client.getBusRouteLineList(routeId);
        if (response != null && response.getReturn() != null) {
            ResMsgHeader header = response.getReturn().getMsgHeader();
            if (header != null && header.getResultCode() == 0) {
                return response.getReturn().getMsgBody().getBusRouteLineList();
            }
        }
        return null;
    }

    @Override
    public List<BusRouteStation> getBusRouteStationList(String routeId) {
        GetBusRouteStationListResponse response = client.getBusRouteStationList(routeId);
        if (response != null && response.getReturn() != null) {
            ResMsgHeader header = response.getReturn().getMsgHeader();
            if (header != null && header.getResultCode() == 0) {
                return response.getReturn().getMsgBody().getBusRouteStationList();
            }
        }
        return null;
    }
}
