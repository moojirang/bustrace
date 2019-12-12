<%@ page import="java.util.List" %>
<%@ page import="com.dazzilove.bustrace.domain.Bus" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dazzilove.bustrace.service.wsdl.BusRouteStation" %>
<%@ page import="com.dazzilove.bustrace.service.wsdl.BusRoute" %>
<%@ page import="com.dazzilove.bustrace.service.wsdl.BusRouteInfo" %>
<%@ page import="com.dazzilove.bustrace.domain.BusLocation" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    BusRouteInfo busRouteInfo = (BusRouteInfo) request.getAttribute("busRouteInfo");

    List<BusRouteStation> busRouteStationList = (List<BusRouteStation>) request.getAttribute("busRouteStationList");
    if (busRouteStationList == null) {
        busRouteStationList = new ArrayList<>();
    }

    List<BusLocation> busLocationList = (List<BusLocation>) request.getAttribute("busLocationList");
    if (busLocationList == null) {
        busLocationList = new ArrayList<>();
    }
%>
<!doctype html>
<html lang="ko">
<head>
</head>
<body>
<div>
    <div>노선 기본정보</div>
    <div>
        <% if (busRouteInfo != null) { %>
        <ul>
            <li>노선번호 = <%= busRouteInfo.getRouteName() %> (<%= busRouteInfo.getRouteId() %>)</li>
            <li>운수업체 = <%= busRouteInfo.getCompanyName() %></li>
            <li>평일최소 배차시간 = <%= busRouteInfo.getPeekAlloc() %></li>
            <li>평일최대 배차시간 = <%= busRouteInfo.getNPeekAlloc() %></li>
            <li>기점
                <div>기점정류소 = <%= busRouteInfo.getStartStationName() %> (<%= busRouteInfo.getStartStationId() %>)</div>
                <div>평일기점 첫차시간 = <%= busRouteInfo.getUpFirstTime() %></div>
                <div>평일기점 막차시간 = <%= busRouteInfo.getUpLastTime() %></div>
            </li>
            <li>종점
                <div>종점정류소 = <%= busRouteInfo.getEndStationName() %> (<%= busRouteInfo.getEndStationId() %>)</div>
                <div>평일종점 첫차시간 = <%= busRouteInfo.getDownFirstTime() %></div>
                <div>평일종점 막차시간 = <%= busRouteInfo.getDownLastTime() %></div>
            </li>
        </ul>
        <% } %>
    </div>
</div>
<div>
    <div>정거장 정보</div>
    <div>
        <ul>
            <% for(BusRouteStation busRouteStation : busRouteStationList) { %>
            <li><%= busRouteStation.getStationName() %> (<%= busRouteStation.getStationId() %>)</li>
            <% } %>
        </ul>
    </div>
</div>
<div>
    <div>운행정보</div>
    <div>
        <ul>
            <% for(BusLocation busLocation : busLocationList) { %>
            <li>노선 아이디 = <%= busLocation.getRouteId() %></li>
            <li>정류소 아이디 = <%= busLocation.getStationId() %></li>
            <li>차종 = <%= busLocation.getPlateType() %></li>
            <li>차량 빈자리 수 = <%= busLocation.getRemainSeatCnt() %></li>
            <% } %>
        </ul>
    </div>
</div>
</body>
</html>