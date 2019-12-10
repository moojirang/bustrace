package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.service.wsdl.GetBusRouteList;
import com.dazzilove.bustrace.service.wsdl.GetBusRouteListResponse;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;

@Component
public class BusRouteClient extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(BusRouteClient.class);

    public BusRouteClient(@Autowired Jaxb2Marshaller marshaller) {
        this.setDefaultUri("http://openapi.gbis.go.kr/ws/busrouteservice");
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public GetBusRouteListResponse getBusRouteList(String serviceKey, String routeName) {
        GetBusRouteList request = new GetBusRouteList();
        request.setArg0(serviceKey);
        request.setArg1(routeName);

        Object response =  getWebServiceTemplate().marshalSendAndReceive(
                new JAXBElement<GetBusRouteList>(new QName("http://ws.api.javaweb/", "getBusRouteList"), GetBusRouteList.class, request),
                new SoapActionCallback("http://openapi.gbis.go.kr/ws/busrouteservice"));

        logger.info("response = " + ToStringBuilder.reflectionToString(response));

        return (GetBusRouteListResponse) JAXBIntrospector.getValue(response);
    }
}
