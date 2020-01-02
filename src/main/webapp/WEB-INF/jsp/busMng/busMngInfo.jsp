<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dazzilove.bustrace.utils.CodeUtil" %>
<%@ page import="com.dazzilove.bustrace.domain.*" %>
<%
    String routeId = (String) request.getParameter("routeId");
    Route route = (Route) request.getAttribute("route");
    route = (route == null)? new Route() : route;

    Map<String, PlateType> plateTypeCodeMap = CodeUtil.getPlateTypes();
    List<PlateType> plateTypeList = new ArrayList();
    plateTypeCodeMap.forEach((key, value) -> {
        plateTypeList.add(value);
    });
%>
<!doctype html>
<html lang="ko">
<head>

    <%@include file="/WEB-INF/jsp/include/basicHeaderInfo.jsp"%>

    <script>
        function addTripPlan(routeId) {
            location.href = '/busMng/addTripPlan?routeId=' + routeId;
        }
    </script>
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="/busList">BusTrace</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/busList">노선목록</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/busMng/busMngList">노선관리</a>
            </li>
            <li class="nav-item active">
                <span class="nav-link">> 노선관리상세 <span class="sr-only">(current)</span></span>
            </li>
        </ul>
    </div>
</nav>
<div class="container bottomPadding">
    <div class="alert alert-secondary doc-title" role="alert" style="margin-top:15px;">
        노선관리상세 (<%= route.getRouteName() %>번)
    </div>

    <ul class="nav nav-tabs subTab">
        <li class="nav-item">
            <a class="nav-link" href="#" id="defaultInfoTab" onclick="changeTab(this)">기본정보</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="#" id="tripPlanTab" onclick="changeTab(this)">버스운행계획</a>
        </li>
    </ul>

    <div id="defaultInfoTabArea" style="display: none;">
        <ul class="list-group list-group-flush">
            <li class="list-group-item">라우트ID = <%= route.getRouteId() %></li>
            <li class="list-group-item">노선번호 = <%= route.getRouteName() %></li>
            <li class="list-group-item">운수업체 = <%= route.getCompanyName() %></li>
            <li class="list-group-item">평일최소 배차시간 = <%= route.getPeekAlloc() %></li>
            <li class="list-group-item">평일최대 배차시간 = <%= route.getNPeekAlloc() %></li>
            <li class="list-group-item">기점
                <div>기점정류소 = <%= route.getStartStationName() %> (<%= route.getStartStationId() %>)</div>
                <div>평일기점 첫차시간 = <%= route.getUpFirstTime() %></div>
                <div>평일기점 막차시간 = <%= route.getUpLastTime() %></div>
            </li>
            <li class="list-group-item">종점
                <div>종점정류소 = <%= route.getEndStationName() %> (<%= route.getEndStationId() %>)</div>
                <div>평일종점 첫차시간 = <%= route.getDownFirstTime() %></div>
                <div>평일종점 막차시간 = <%= route.getDownLastTime() %></div>
            </li>
        </ul>
    </div>

    <div id="tripPlanTabArea" style="display: block;">
         <% List<TripPlan> tripPlanList = route.getTripPlanList(); %>
         <% tripPlanList = (tripPlanList == null) ? new ArrayList() : tripPlanList; %>
         <% int index = 1; %>

        <div class="topMargin bottomMargin">
            <span class="badge badge-secondary">!</span>
            <% for(PlateType plateType: plateTypeList) { %>
                <%
                    String codeValueAll = "99";
                    int typeCnt = plateType.getTypeCnt();
                    String code = plateType.getCode();

                    if (!codeValueAll.equals(plateType.getCode())) {
                %>
                    <img src="<%= plateType.getImageSrc() %>"
                        style="width:25px"
                        title="<%= plateType.getName() %>"
                        data-platetype="<%= plateType.getCode() %>" />
                        <span class="badge bus-select-group badge-light" data-platetype="<%= plateType.getCode() %>"><%= plateType.getName() %></span>
                 <% } %>
            <% } %>
        </div>

         <table class="table table-sm">
             <thead>
             <tr>
                 <th scope="col">#</th>
                 <th scope="col">타입</th>
                 <th scope="col">차량번호</th>
                 <th scope="col">주말운행</th>
             </tr>
             </thead>
             <tbody>
             <% for (TripPlan tripPlan: tripPlanList) { %>
             <%
                String plateType = tripPlan.getPlateType();
                String plateNo = tripPlan.getShortPlateNo();
                PlateType plateTypeCodeInfo = plateTypeCodeMap.get(plateType);
                String busImgSrc = plateTypeCodeInfo.getImageSrc();
                String plateTypeName = plateTypeCodeInfo.getName();
             %>
                 <tr>
                     <td scope="row"><%= index++ %></td>
                     <td><img src="<%= busImgSrc%>" style="width:25px;" alt="<%= plateTypeName %>" title="<%= plateTypeName %>" /></td>
                     <td><%= plateNo %></td>
                     <td><%= tripPlan.getWeekendOperationYN() %></td>
                 </tr>
             <% } %>
             </tbody>
         </table>

         <div class="contentAlignRight bottomMargin topMargin">
            <button type="button" class="btn btn-sm btn-primary" onclick="addTripPlan('<%= routeId %>')">차량추가</button>
         </div>
    </div>
</div>
</body>
</html>