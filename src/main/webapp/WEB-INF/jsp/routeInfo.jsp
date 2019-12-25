<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.dazzilove.bustrace.service.ws.BusRouteStation" %>
<%@ page import="com.dazzilove.bustrace.service.wsdl.BusRouteInfo" %>
<%@ page import="com.dazzilove.bustrace.domain.*" %>
<%@ page import="com.dazzilove.bustrace.service.ws.BusStation" %>
<%@ page import="com.dazzilove.bustrace.utils.CodeUtil" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    BusRouteInfo busRouteInfo = (BusRouteInfo) request.getAttribute("busRouteInfo");
    String routeId = "";
    String routeName = "";
    if (busRouteInfo != null) {
        routeId = busRouteInfo.getRouteId();
        routeName = busRouteInfo.getRouteName();
    }

    List<String> plateNoList = (List<String>) request.getAttribute("plateNoList");
    if (plateNoList.isEmpty()) {
        plateNoList = new ArrayList();
    }

    List<BusRouteStation> busRouteStationList = (List<BusRouteStation>) request.getAttribute("busRouteStationList");
    if (busRouteStationList == null) {
        busRouteStationList = new ArrayList<>();
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String nowDay = sdf.format(new Date());

    String startCreatedAt = StringUtils.defaultString(request.getParameter("startCreatedAt"), "").trim();
    String endCreatedAt = StringUtils.defaultString(request.getParameter("endCreatedAt"), "").trim();

    startCreatedAt = ("".equals(startCreatedAt.trim())) ? nowDay : startCreatedAt;
    endCreatedAt = ("".equals(endCreatedAt.trim())) ? nowDay : endCreatedAt;

%>
<!doctype html>
<html lang="ko">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/css/bootstrap-4.4.1/bootstrap.css" rel="stylesheet"/>
    <link href="/css/base.css" rel="stylesheet"/>

    <script src="/js/jquery-3.4.1.slim.min.js"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/bootstrap-4.4.1/bootstrap.js"></script>

    <script language="javascript">
        $(function () {
          $('[data-toggle="tooltip"]').tooltip()
        });

        function changeTab(obj) {
            $(".subTab li a").each(function() {
                var id = $(this).attr("id");
                $(this).removeClass("active");
                $("#" + id + "Area").css("display", "none");
            });

            var tabId = $(obj).attr("id");
            $("#" + tabId).addClass("active");
            $("#" + tabId + "Area").css("display", "block");
        }

        function toggleStationDetailInfo(obj) {
            $(obj).nextAll().each(function() {
                if ($(this).hasClass("stationDetailInfo") && $(this).hasClass("timeline")) {
                    var nowDisplay = $(this).css("display");
                    var nextDisplay = "none";
                    if (nowDisplay == "none") {
                        nextDisplay = "block";
                    }
                    $(this).css("display", nextDisplay);
                }
            });
        }

        function showOnlyStarts(target) {
            var btnTextShowOnly = "Only Stars";
            var btnTextShowAll = "Show All";

            var btnText = "";

            if (target == null) {
                btnText = $("#toggleStarShowClseBtn").text();
            } else {
                btnText = target;
            }

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

        function showAllStarts() {
            showOnlyStarts("Show All");
        }

        function resetWayGroup(obj) {
            $(".way-group").each(function() {
                $(this).removeClass("active");
            });
            $(obj).addClass("active");

            showAllStarts();

            var displayTurnClass = "";
            if($(obj).hasClass("ascend-way")) {
                displayTurnClass = "trunN";
            } else if ($(obj).hasClass("descend-way")) {
                displayTurnClass = "trunY";
            }
            $(".busLocationLiItem").each(function() {
                $(this).css("display", "block");
                if (displayTurnClass != "" && !$(this).hasClass(displayTurnClass)) {
                    $(this).css("display", "none");
                }
            });
        }

        function showAllStationDetailInfo(obj) {
            $(".stationDetailInfo").each(function() {
                $(this).css("display", "block");

                if ($(this).hasClass("bus")) {
                    $(this).css("display", "none");
                }
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
                if (createdAt == $(this).attr("data-createdat")) {
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

        function toggleStationDetailInfoTab(obj) {
            toggleBadgeStationDetailInfo(obj);

            var defaultClass = "timeline";
            if ($(obj).hasClass("bus")) {
                defaultClass = "bus";
            }

            $(obj).nextAll(".stationDetailInfo").each(function() {
                if ($(this).hasClass(defaultClass)) {
                    $(this).css("display", "block");
                } else {
                    $(this).css("display", "none");
                }
            });
        }

        function toggleBadgeStationDetailInfo(obj) {
            var checkClass = "badge-stationDetailInfo";
            var prevObj = $(obj).prev();
            if(prevObj.hasClass(checkClass)) {
                checkAndRemoveClass(prevObj, "badge-secondary");
                checkAndRemoveClass(prevObj, "badge-light");
                prevObj.addClass("badge-light");
            }
            var nextObj = $(obj).next();
            if(nextObj.hasClass(checkClass)) {
                checkAndRemoveClass(nextObj, "badge-secondary");
                checkAndRemoveClass(nextObj, "badge-light");
                nextObj.addClass("badge-light");
            }
            checkAndRemoveClass(obj, "badge-light");
            $(obj).addClass("badge-secondary");
        }

        function checkAndRemoveClass(obj, className) {
            if ($(obj).hasClass(className)) {
                $(obj).removeClass(className);
            }
        }

        function changePlateNoSelect(obj) {
            var selectedPlateNo = $(obj).val();
            $(".createdAtLi").each(function() {
                var nowPlateNo = $(this).attr("data-plateno");
                if (selectedPlateNo == "All") {
                    $(this).css("display", "block");
                } else if (selectedPlateNo == nowPlateNo) {
                    $(this).css("display", "block");
                } else {
                    $(this).css("display", "none");
                }
            });
            showAllStationDetailInfo();
        }

        function changeTimeArangeSelect(obj) {
            var startTimeArange = "0";
            var endTimeArange = "23";

            var selectedArange = $(obj).val();
            if (selectedArange == "00") {
                return;
            } else if (selectedArange == "01") {
                startTimeArange = "0";
                endTimeArange = "23";
            } else if (selectedArange == "02") {
                startTimeArange = "5";
                endTimeArange = "9";
            } else if (selectedArange == "03") {
                startTimeArange = "10";
                endTimeArange = "16";
            } else if (selectedArange == "04") {
                startTimeArange = "17";
                endTimeArange = "22";
            }

            $("#startTimeArange").val(startTimeArange);
            $("#endTimeArange").val(endTimeArange);

            changeTimeArange();
        }

        function changeTimeArange() {
            var startTimeArange = parseInt($("#startTimeArange").val());
            var endTimeArange = parseInt($("#endTimeArange").val());

            $(".createdAtLi").each(function() {
                var createdAt = $(this).attr("data-createdat");
                var hour = createdAt.substring(11, 13);
                hour = (hour.indexOf("0") == 0) ? hour.substring(1,2) : hour;
                var hourInt = parseInt(hour);

                var isDisplay = false;
                if (hourInt >= startTimeArange && hourInt <= endTimeArange) {
                    isDisplay = true;
                }

                if (isDisplay) {
                    $(this).css("display", "block");
                } else {
                    $(this).css("display", "none");
                }
            });

            showAllStationDetailInfo();
        }

        function showSpecialPlateTypeList(plateTypeCode) {
            $(".createdAtLi").each(function() {
                var selectedPlateTypeCode = $(this).attr("data-platetype");
                if (plateTypeCode == "99") {
                    $(this).css("display", "block");
                } else if (selectedPlateTypeCode == plateTypeCode) {
                    $(this).css("display", "block");
                } else {
                    $(this).css("display", "none");
                }
                showAllStationDetailInfo();
            });
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

    <ul class="nav nav-tabs subTab">
        <li class="nav-item">
            <a class="nav-link" href="#" id="defaultInfoTab" onclick="changeTab(this)">기본정보</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="#" id="stationsTab" onclick="changeTab(this)">정거장 정보</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#" id="specialInfoTab" onclick="changeTab(this)">특이사항</a>
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
                    <input class="form-control form-control-sm" type="date" id="startCreatedAt" name="startCreatedAt" value="<%= startCreatedAt %>" >
                </div>
                <div class="form-group" style="width: 14px; text-align: center">
                    -
                </div>
                <div class="form-group">
                    <input class="form-control form-control-sm" type="date" id="endCreatedAt" name="endCreatedAt" value="<%= endCreatedAt %>" >
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
                        <button type="button" class="btn btn-info btn-sm way-group ascend-way" onclick="resetWayGroup(this)">상행</button>
                        <button type="button" class="btn btn-info btn-sm way-group descend-way" onclick="resetWayGroup(this)">하행</button>
                        <button type="button" class="btn btn-info btn-sm way-group all-way active" onclick="resetWayGroup(this)">전체</button>
                    </div>
                </div>
            </div>
            <div class="contentAlignRight bottomMargin">
                <div class="btn">
                    <% List<PlateType> plateTypeList = CodeUtil.getPlateTypes(); %>
                    <% for(PlateType plateType: plateTypeList) { %>
                        <img src="<%= plateType.getImageSrc() %>" style="width:25px"
                            data-toggle="tooltip"
                            data-placement="bottom"
                            title="<%= plateType.getName() %>"
                            onclick="showSpecialPlateTypeList('<%= plateType.getCode() %>');" />
                    <% } %>
                </div>
                <div class="btn-group stationDetailInfoBtnGroup" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary btn-sm" onclick="showAllStationDetailInfo(this)">Expand All</button>
                    <button type="button" class="btn btn-secondary btn-sm" onclick="closeAllStationDetailInfo(this)">Close All</button>
                </div>
            </div>
        </form>

        <div class="form-group">
            <div class="input-group input-group-sm">
                <div class="input-group-prepend">
                    <span class="input-group-text form-control-sm">시간대</span>
                </div>
                <select class="custom-select" id="startTimeArange" onchange="changeTimeArange()">
                    <option>All</option>
                    <% for(int i=0; i<24; i++) { %>
                    <option value="<%=i%>" <%= (i==0) ? "selected" : "" %>><%= (i < 10) ? "0"+i : ""+i %>:00</option>
                    <% } %>
                </select>
                <select class="custom-select" id="endTimeArange" onchange="changeTimeArange()">
                    <option>All</option>
                    <% for(int i=0; i<24; i++) { %>
                    <option value="<%=i%>" <%= (i==23) ? "selected" : "" %>><%= (i < 10) ? "0"+i : ""+i %>:59</option>
                    <% } %>
                </select>
                <select class="custom-select" onchange="changeTimeArangeSelect(this);">
                    <option value="00">선택</option>
                    <option value="01">전체</option>
                    <option value="02">출근시간</option>
                    <option value="03">근무시간</option>
                    <option value="04">퇴근시간</option>
                </select>
            </div>
            <div class="input-group input-group-sm">
                <div class="input-group-prepend">
                    <span class="input-group-text form-control-sm">차량번호</span>
                </div>
                <select class="custom-select" onchange="changePlateNoSelect(this);">
                    <option>All</option>
                    <% for(String plateNo : plateNoList) { %>
                    <option><%= plateNo %></option>
                    <% } %>
                </select>
            </div>
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

                    int remainSeatCntZeroCnt = busRouteStation.getRemainSeatCntZeroCnt();
                    String remainSeatCntZeroClass = (remainSeatCntZeroCnt > 0) ? "remainSeatCntZero" : "";

                %>
            <li class="list-group-item busLocationLiItem trun<%= turnYn %> <%= remainSeatCntZeroClass %>">
                <img src="/img/star.png" class="icon-size-small <%= (isStarStation) ? "icon-check" : "icon-no-check" %>" />
                <span>
                    <span onclick="toggleStationDetailInfo(this)">
                        <%= busRouteStation.getStationName() %>
                        <span class="font-smaller font-gray">(<span class="stationId"><%= stationId %></span>)</span>
                    </span>
                    <%--<span class="badge badge-secondary badge-stationDetailInfo timeline" onclick="toggleStationDetailInfoTab(this)">Timeline</span>--%>
                    <%--<span class="badge badge-light badge-stationDetailInfo bus" onclick="toggleStationDetailInfoTab(this)">Bus</span>--%>

                    <% List<BusLocation> busLocationList = busRouteStation.getBusLocationList(); %>
                    <% busLocationList = (busLocationList.isEmpty()) ? new ArrayList<>() : busLocationList; %>
                    <div class="stationDetailInfo timeline">
                        <ul class="list-group list-group-flush font-smaller">
                            <% for(BusLocation busLocation: busLocationList) { %>
                            <%
                                String formatedCreatedAt = busLocation.getFormatedCreatedAt();
                                String plateNo = busLocation.getPlateNo();
                                String plateType = busLocation.getPlateType();
                                String plateTypeName = busLocation.getPlateTypeName();
                                String remainSeatCnt = StringUtils.defaultString(busLocation.getRemainSeatCnt(), "0");

                                int platNoLen = plateNo.length();
                                String shortPlateNo = plateNo.substring(platNoLen -4, platNoLen);

                                String busImgSrc = "/img/";
                                switch (plateTypeName) {
                                    case "2층버스":
                                        busImgSrc = busImgSrc + "bus2f_2.png";
                                        break;
                                    case "대형승합차":
                                        busImgSrc = busImgSrc + "bus1f_2.png";
                                        break;
                                    case "중형승합차":
                                        busImgSrc = busImgSrc + "bus1f_1.png";
                                        break;
                                    default:
                                        busImgSrc = busImgSrc + "bus1f_0.png";
                                        break;
                                }

                                String remainSeatCntZeroYn = ("0".equals(remainSeatCnt)) ? "Y" : "N";
                                int remainSeatCntInt = Integer.parseInt(remainSeatCnt);
                                String remainSeatCntBgClass = "";
                                if (remainSeatCntInt == 0) {
                                    remainSeatCntBgClass = "bg-green-depth1";
                                } else if (remainSeatCntInt <= 5) {
                                    remainSeatCntBgClass = "bg-green-depth2";
                                } else if (remainSeatCntInt <= 10) {
                                    remainSeatCntBgClass = "bg-green-depth3";
                                } else if (remainSeatCntInt <= 15) {
                                    remainSeatCntBgClass = "bg-green-depth4";
                                }
                            %>
                            <li class="list-group-item bg-gray createdAtLi remainSeatCntZero<%=remainSeatCntZeroYn%> <%= remainSeatCntBgClass %>"
                                data-createdat="<%= formatedCreatedAt %>"
                                data-plateno="<%= plateNo %>"
                                data-platetype="<%= plateType %>">
                                <%= formatedCreatedAt %>
                                | <img src="<%= busImgSrc%>" style="width:25px;" alt="<%= plateTypeName %>" title="<%= plateTypeName %>" /> <%= shortPlateNo %>
                                <% if (!"-1".equals(remainSeatCnt)) { %>
                                | 잔여좌석 <%= remainSeatCnt %>석
                                <% } %>
                                </li>
                            <% } %>
                        </ul>
                    </div>
                    <div class="stationDetailInfo bus">
                        bus icon viewer
                    </div>

                </span>
            </li>
            <% } %>
        </ul>
    </div>

    <div id="specialInfoTabArea" style="display: none;">
        <div style="text-align: center">특이사항 없습니다.</div>
    </div>
</div>
</body>
</html>