<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.dazzilove.bustrace.service.ws.BusRouteStation" %>
<%@ page import="com.dazzilove.bustrace.service.wsdl.BusRouteInfo" %>
<%@ page import="com.dazzilove.bustrace.domain.BusLocation" %>
<%@ page import="com.dazzilove.bustrace.service.ws.BusStation" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    BusRouteInfo busRouteInfo = (BusRouteInfo) request.getAttribute("busRouteInfo");
    String routeId = "";
    String routeName = "";
    if (busRouteInfo != null) {
        routeId = busRouteInfo.getRouteId();
        routeName = busRouteInfo.getRouteName();
    }

    List<String> createdAtList = (List<String>) request.getAttribute("createdAtList");
    if (createdAtList == null) {
        createdAtList = new ArrayList();
    }

    List<BusRouteStation> busRouteStationList = (List<BusRouteStation>) request.getAttribute("busRouteStationList");
    if (busRouteStationList == null) {
        busRouteStationList = new ArrayList<>();
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String nowDay = sdf.format(new Date());

    String startCreatedAt = request.getParameter("startCreatedAt");
    String endCreatedAt = request.getParameter("endCreatedAt");

    startCreatedAt = (startCreatedAt == null || "".equals(startCreatedAt.trim())) ? nowDay : startCreatedAt;
    endCreatedAt = (endCreatedAt == null || "".equals(endCreatedAt.trim())) ? nowDay : endCreatedAt;

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

        function showOnlyStarts() {
            var btnTextShowOnly = "Only Stars";
            var btnTextShowAll = "Show All";

            var btnText = $("#toggleStarShowClseBtn").text();
            console.log(btnText);
            if (btnText == btnTextShowOnly) {
                closeAllStationDetailInfo();
                $(".busLocationLiItem").each(function() {
                    $(this).css("display", "none");
                });
                $(".busLocationLiItem").each(function() {
                    if($(this).children("img").hasClass("icon-check")) {
                        $(this).css("display", "block");
                    }
                });
                $("#toggleStarShowClseBtn").text(btnTextShowAll);
            } else {
                closeAllStationDetailInfo();
                $(".busLocationLiItem").each(function() {
                    $(this).css("display", "block");
                });
                $("#toggleStarShowClseBtn").text(btnTextShowOnly);
            }
        }

        function showAllStationDetailInfo(obj) {
            $(".stationDetailInfo").each(function() {
                $(this).css("display", "block");
            });
        }

        function closeAllStationDetailInfo(obj) {
            $(".stationDetailInfo").each(function() {
                $(this).css("display", "none");
            });
        }

        function changeCreatedAtSelect(obj) {
            var createdAt = $(obj).val();
            $(".createdAtLi").each(function() {
                $(this).css("display", "none");
                if (createdAt == $(this).attr("createdAt")) {
                    $(this).css("display", "block");
                }
                if (createdAt == "All Time") {
                    $(this).css("display", "block");
                }
            });
        }

        function goSearch() {
            var frm = document.searchForm;
            frm.action = "/routeInfo";
            frm.submit();
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
        <form name="searchForm" id="searchForm">
            <input type="hidden" id="routeId" name="routeId" value="<%= routeId %>" />
            <div class="form-row topPadding">
                <div class="form-group">
                    <input class="form-control form-control-sm" type="text" id="startCreatedAt" name="startCreatedAt" value="<%= startCreatedAt %>" style="width:100px;">
                </div>
                <div class="form-group" style="width: 14px; text-align: center">
                    -
                </div>
                <div class="form-group">
                    <input class="form-control form-control-sm" type="text" id="endCreatedAt" name="endCreatedAt" value="<%= endCreatedAt %>" style="width:100px;">
                </div>
                <div class="form-group" style="width: 4px">
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-primary btn-sm" onclick="goSearch()">Search</button>
                </div>
            </div>
            <div class="contentAlignRight bottomMargin">
                <div>
                    <button type="button" class="btn btn-warning btn-sm" onclick="showOnlyStarts()" id="toggleStarShowClseBtn">Only Stars</button>

                    <div class="btn-group" role="group" aria-label="Basic example">
                        <button type="button" class="btn btn-info btn-sm">상행</button>
                        <button type="button" class="btn btn-info btn-sm">하행</button>
                        <button type="button" class="btn btn-info btn-sm active">전체</button>
                    </div>
                </div>
            </div>
            <div class="contentAlignRight bottomMargin">
                <div class="btn-group stationDetailInfoBtnGroup" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary btn-sm" onclick="showAllStationDetailInfo(this)">Expand All</button>
                    <button type="button" class="btn btn-secondary btn-sm" onclick="closeAllStationDetailInfo(this)">Close All</button>
                </div>
            </div>
        </form>

        <div class="form-group">
            <select class="form-control form-control-sm" onchange="changeCreatedAtSelect(this);">
                <option>All Time</option>
                <% for(String time: createdAtList) { %>
                <option><%= time %></option>
                <% } %>
            </select>
        </div>

        <ul class="list-group list-group-flush">
            <%
                String turnYn = "N";
                BusStation busStation = new BusStation();
            %>
            <% for(BusRouteStation busRouteStation : busRouteStationList) { %>
                <%
                    String stationId = busRouteStation.getStationId();
                    if ("N".equals(turnYn) && "Y".equals(busRouteStation.getTurnYn())) {
                        turnYn = "Y";
                    }
                    boolean isStarStation = false;
                    String starStationId = busStation.getStartStationIdMap().get(stationId);
                    if (starStationId != null) {
                        isStarStation = true;
                    }
                %>
            <li class="list-group-item busLocationLiItem" class="trun<%= turnYn %>">
                <img src="/img/star.png" class="icon-size-small <%= (isStarStation) ? "icon-check" : "icon-no-check" %> />
                <span onclick="toggleStationDetailInfo(this)">
                    <%= busRouteStation.getStationName() %>
                    <span class="font-smaller font-gray">(<span class="stationId"><%= stationId %></span>)</span>
                    <%--<span class="badge badge-primary badge-pill">14</span>--%>
                    <div class="stationDetailInfo">
                        <% List<BusLocation> busLocationList = busRouteStation.getBusLocationList(); %>
                        <% if (!busLocationList.isEmpty()) { %>
                            <ul class="list-group list-group-flush font-smaller">
                                <% for(BusLocation busLocation: busLocationList) { %>
                                <%
                                    String formatedCreatedAt = busLocation.getFormatedCreatedAt();
                                    String plateNo = busLocation.getPlateNo();
                                    String plateTypeName = busLocation.getPlateTypeName();
                                    String remainSeatCnt = busLocation.getRemainSeatCnt();

                                    String remainSeatCntZeroYn = ("0".equals(remainSeatCnt)) ? "Y" : "N";
                                %>
                                <li class="list-group-item bg-gray createdAtLi remainSeatCntZero<%=remainSeatCntZeroYn%>" createdAt="<%= formatedCreatedAt %>">
                                    <%= formatedCreatedAt %>
                                    | <%= plateNo %>(<%= plateTypeName %>)
                                    <% if (!"-1".equals(remainSeatCnt)) { %>
                                    | 잔여좌석 <%= remainSeatCnt %>석
                                    <% } %>
                                    </li>
                                <% } %>
                            </ul>
                        <% } %>
                    </div>
                </span>
            </li>
            <% } %>
        </ul>
    </div>
</div>
</body>
</html>