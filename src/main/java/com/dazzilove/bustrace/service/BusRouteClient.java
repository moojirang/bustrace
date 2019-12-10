package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.service.wsdl.*;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

@Component
public class BusRouteClient extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(BusRouteClient.class);

    @Value("${bustrace.serviceKey}")
    private String serviceKey;

    @Autowired
    public BusRouteClient(Jaxb2Marshaller marshaller) {
        this.setDefaultUri("http://openapi.gbis.go.kr/ws/busrouteservice");
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    /**
     * 노선 정보 목록 조회
     * @param routeName 노선 번호
     * @return
     */
    public GetBusRouteListResponse getBusRouteList(String routeName) {
        GetBusRouteList request = new GetBusRouteList();
        request.setArg0(serviceKey);
        request.setArg1(routeName);

        return getWSClientResponse(request, GetBusRouteListResponse.class);

        /**
        Object response =  getWebServiceTemplate().marshalSendAndReceive(
                new JAXBElement<GetBusRouteList>(new QName("http://ws.api.javaweb/", "getBusRouteList"), GetBusRouteList.class, request),
                new SoapActionCallback("http://openapi.gbis.go.kr/ws/busrouteservice"));

        logger.info("response = " + ToStringBuilder.reflectionToString(response));

        return (GetBusRouteListResponse) JAXBIntrospector.getValue(response);
         */
    }

    /**
     * 운행지역별 노선 정보 목록 조회
     * @param areaId    운행지역ID
     * @param routeName 노선번호
     * @return
     */
    public GetAreaBusRouteListResponse getAreaBusRouteList(String areaId, String routeName) {
        GetAreaBusRouteList request = new GetAreaBusRouteList();
        request.setArg0(serviceKey);
        request.setArg1(areaId);
        request.setArg2(routeName);

        return getWSClientResponse(request, GetAreaBusRouteListResponse.class);
    }

    /**
     * 경유 정류소 목록 조회
     * @param routeId 노선ID
     * @return
     */
    public GetBusRouteStationListResponse getBusRouteStationList(String routeId) {
        GetBusRouteStationList request = new GetBusRouteStationList();
        request.setArg0(serviceKey);
        request.setArg1(routeId);

        return getWSClientResponse(request, GetBusRouteStationListResponse.class);
    }

    /**
     * 노선 정보 항목 조회
     * @param routeId 노선ID
     * @return
     */
    public GetBusRouteInfoItemResponse getBusRouteInfoItem(String routeId) {
        GetBusRouteInfoItem request = new GetBusRouteInfoItem();
        request.setArg0(serviceKey);
        request.setArg1(routeId);

        return getWSClientResponse(request, GetBusRouteInfoItemResponse.class);
    }

    /**
     * 노선 형상 정보 목록 조회
     * @param routeId 노선ID
     * @return
     */
    public GetBusRouteLineListResponse getBusRouteLineList(String routeId) {
        GetBusRouteLineList request = new GetBusRouteLineList();
        request.setArg0(serviceKey);
        request.setArg1(routeId);

        return getWSClientResponse(request, GetBusRouteLineListResponse.class);
    }

    /**
     * SOAP client 연동
     * @param request 파라미터 정보
     * @param responseClass OUTPUT class type
     * @return
     */
    private <T> T getWSClientResponse(Object request, Class<T> responseClass) {
        Class requestClass = request.getClass();
        XmlType t = (XmlType) requestClass.getAnnotation(XmlType.class);
        String typeName = t.name();
        Object response =  getWebServiceTemplate().marshalSendAndReceive(
                new JAXBElement(new QName("http://ws.api.javaweb/", typeName), request.getClass(), request),
                new SoapActionCallback(this.getDefaultUri()));

        logger.info("response = " + ToStringBuilder.reflectionToString(response));

        return (T) JAXBIntrospector.getValue(response);
    }
}
