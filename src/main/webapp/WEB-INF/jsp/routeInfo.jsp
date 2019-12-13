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
    String routeName = "";
    if (busRouteInfo != null) {
        routeName = busRouteInfo.getRouteName();
    }

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
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/css/bootstrap-4.4.1/bootstrap.css" rel="stylesheet"/>
    <link href="/css/base.css" rel="stylesheet"/>

    <script src="/js/jquery-3.4.1.slim.min.js"></script>
    <script src="/js/bootstrap-4.4.1/bootstrap.js"></script>

    <script language="javascript">
        function changeTab(tabId) {
            $("a").each(function() {
                var group = $(this).attr("group");
                var id = $(this).attr("id");
                if (group == "subTab") {
                    $(this).removeClass("active");
                    $("#" + id + "Area").css("display", "none");
                }
            });
            $("#" + tabId).addClass("active");
            $("#" + tabId + "Area").css("display", "block");
        }

        function toggleStationDetailInfo(obj) {
            var nowDisplay = $(obj).children(".stationDetailInfo").css("display");
            var nextDisplay = "none";
            if (nowDisplay == "none") {
                nextDisplay = "block";
            }
            $(obj).children(".stationDetailInfo").css("display", nextDisplay);
        }

        function showAllStationDetailInfo() {
            $(".stationDetailInfo").each(function() {
                $(this).css("display", "block");
            });
        }

        function closeAllStationDetailInfo() {
            $(".stationDetailInfo").each(function() {
                $(this).css("display", "none");
            });
        }
    </script>
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="#">BusTrace</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/busList">노선목록</a>
            </li>
            <li class="nav-item active">
                <span class="nav-link">> 노선정보 <span class="sr-only">(current)</span></span>
            </li>
        </ul>
    </div>
</nav>
<div class="container bottomPadding">

    <div class="alert alert-secondary doc-title topMargin" role="alert">
        <div class="align-content-md-start"><%= routeName %>번 노선정보</div>
    </div>

    <ul class="nav nav-tabs" id="usbTab">
        <li class="nav-item">
            <a class="nav-link" href="#" group="subTab" id="defaultInfoTab" onclick="changeTab('defaultInfoTab')">기본정보</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="#" group="subTab" id="stationsTab" onclick="changeTab('stationsTab')">정거장 정보</a>
        </li>
    </ul>


    <div id="defaultInfoTabArea" style="display: none;">
        <% if (busRouteInfo != null) { %>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">노선번호 = <%= busRouteInfo.getRouteName() %> (<%= busRouteInfo.getRouteId() %>)</li>
            <li class="list-group-item">운수업체 = <%= busRouteInfo.getCompanyName() %></li>
            <li class="list-group-item">평일최소 배차시간 = <%= busRouteInfo.getPeekAlloc() %></li>
            <li class="list-group-item">평일최대 배차시간 = <%= busRouteInfo.getNPeekAlloc() %></li>
            <li class="list-group-item">기점
                <div>기점정류소 = <%= busRouteInfo.getStartStationName() %> (<%= busRouteInfo.getStartStationId() %>)</div>
                <div>평일기점 첫차시간 = <%= busRouteInfo.getUpFirstTime() %></div>
                <div>평일기점 막차시간 = <%= busRouteInfo.getUpLastTime() %></div>
            </li>
            <li class="list-group-item">종점
                <div>종점정류소 = <%= busRouteInfo.getEndStationName() %> (<%= busRouteInfo.getEndStationId() %>)</div>
                <div>평일종점 첫차시간 = <%= busRouteInfo.getDownFirstTime() %></div>
                <div>평일종점 막차시간 = <%= busRouteInfo.getDownLastTime() %></div>
            </li>
        </ul>
        <% } %>
    </div>
    <div id="stationsTabArea">
        <form>
            <div class="form-row topPadding">
                <div class="form-group">
                    <input class="form-control form-control-sm" type="text" value="20190912" style="width:100px;">
                </div>
                <div class="form-group" style="width: 14px; text-align: center">
                    -
                </div>
                <div class="form-group">
                    <input class="form-control form-control-sm" type="text" value="20190912" style="width:100px;">
                </div>
                <div class="form-group" style="width: 4px">
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-primary btn-sm" onclick="showAllStationDetailInfo()">Search</button>
                </div>
            </div>
            <div class="contentAlignRight">
                <div>
                    <button type="button" class="btn btn-warning btn-sm" onclick="showAllStationDetailInfo()">Expand Stars</button>
                    <button type="button" class="btn btn-secondary btn-sm" onclick="showAllStationDetailInfo()">Expand All</button>
                    <button type="button" class="btn btn-secondary btn-sm" onclick="closeAllStationDetailInfo()">Close All</button>
                    <%--<button type="button" class="btn btn-info btn-sm" onclick="refresh()">Refresh</button>--%>

                </div>
            </div>
        </form>
        <ul class="list-group list-group-flush">
            <% for(BusRouteStation busRouteStation : busRouteStationList) { %>
                <% String stationId = busRouteStation.getStationId(); %>
            <li class="list-group-item">
                <img src="/img/star.png" class="icon-size-small icon-no-check" />
                <span onclick="toggleStationDetailInfo(this)">
                    <%= busRouteStation.getStationName() %> <span class="font-smaller font-gray">(<span class="stationId"><%= stationId %></span>)</span>
                    <%--<span class="badge badge-primary badge-pill">14</span>--%>
                    <div class="stationDetailInfo">
                        <ul class="list-group list-group-flush font-smaller">
                            <li class="list-group-item bg-gray">2019.12.23 23:45 / 잔여좌석 0석</li>
                            <li class="list-group-item bg-gray">2019.12.23 23:45 / 잔여좌석 0석</li>
                            <li class="list-group-item bg-gray">2019.12.23 23:45 / 잔여좌석 0석</li>
                            <li class="list-group-item bg-gray">2019.12.23 23:45 / 잔여좌석 0석</li>
                        </ul>
                    </div>
                </span>
            </li>
            <% } %>
        </ul>
        <div class="topPadding contentAlignRight">
            <button type="button" class="btn btn-primary btn-sm" onclick="showAllStationDetailInfo()">All Expand</button>
            <button type="button" class="btn btn-secondary btn-sm" onclick="closeAllStationDetailInfo()">All Close</button>
        </div>
    </div>
</div>
</body>
</html>