package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.service.wsdl.BusRoute;
import com.dazzilove.bustrace.service.wsdl.BusRouteListResponse;
import com.dazzilove.bustrace.service.wsdl.GetBusRouteList;
import com.dazzilove.bustrace.service.wsdl.GetBusRouteListResponse;
import org.apache.catalina.util.ToStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.style.DefaultValueStyler;
import org.springframework.core.style.ToStringCreator;
import org.springframework.core.style.ValueStyler;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;

public class BusRouterClient extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(BusRouterClient.class);

    public GetBusRouteListResponse getBusRouteList(String routeName, String routeTypeCd) {
        GetBusRouteList request = new GetBusRouteList();
        request.setArg0(routeName);
        request.setArg1(routeTypeCd);
        Object response =  getWebServiceTemplate().marshalSendAndReceive(
                "http://openapi.gbis.go.kr/ws/busrouteservice",
                new JAXBElement<GetBusRouteList>(new QName("http://ws.api.javaweb/", "getBusRouteList"),
                        GetBusRouteList.class, request),
                new SoapActionCallback("getBusRouteList"));

        return (GetBusRouteListResponse) JAXBIntrospector.getValue(response);
    }
}
