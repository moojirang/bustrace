package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.service.wsdl.*;

import java.util.List;

public interface BusRouteService {

    /**
     * 버스노선 목록 조회
     * @param routeName 노선 번호
     * @return
     */
    public List<BusRoute> getBusRouteList(String routeName);

    /**
     * 운행지역별 버스 노선번호 목록 조회
     * @param areaId 운행지역ID
     * @param routeName 노선번호
     * @return
     */
    public List<BusRoute> getAreaBusRouteList(String areaId, String routeName);

    /**
     * 경유 정류소 목록 조회
     * @param routeId 노선ID
     * @return
     */
    public List<BusRouteStation> getBusRouteStationList(String routeId);

    /**
     * 노선 정보 항목 조회
     * @param routeId 노선ID
     * @return
     */
    public BusRouteInfo getBusRouteInfoItem(String routeId);

    /**
     * 노선 형상 정보 목록 조회
     * @param routeId 노선ID
     * @return
     */
    public List<BusRouteLine> getBusRouteLineList(String routeId);
}
