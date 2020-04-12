<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.dazzilove.bustrace.domain.*" %>
<%@ page import="com.dazzilove.bustrace.utils.CodeUtil" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Route route = (Route) request.getAttribute("route");
    String _id = "";
    String routeId = "";
    String routeName = "";
    if (route != null) {
        _id = route.getId().toString();
        routeId = route.getRouteId();
        routeName = route.getRouteName();
    }

    List<Bus> plateNoList = (List<Bus>) request.getAttribute("plateNoList");
    if (plateNoList == null || plateNoList.isEmpty()) {
        plateNoList = new ArrayList();
    }

    List<SpecialMessage> specialMessageList = (List<SpecialMessage>) request.getAttribute("specialMessageList");
    if (specialMessageList == null || specialMessageList.isEmpty()) {
        specialMessageList = new ArrayList<>();
    }

    List<PlateType> plateTypeList = new ArrayList();
    Map<String, PlateType> plateTypeCodeMap = CodeUtil.getPlateTypes();
    plateTypeCodeMap.forEach((key, value) -> {
        plateTypeList.add(value);
    });
    for(PlateType plateType: plateTypeList) {
        for(Bus bus: plateNoList) {
            String plateTypeTemp = StringUtils.defaultString(bus.getPlateType(), "").trim();
            if(plateTypeTemp.equals(plateType.getCode())) {
                plateType.setTypeCnt(plateType.getTypeCnt() + 1);
            }
        }
    }

/*
    List<BusRouteStatioan> busRouteStatioanList = (List<BusRouteStatioan>) request.getAttribute("busRouteStatioanList");
    if (busRouteStatioanList == null) {
        busRouteStatioanList = new ArrayList<>();
    }
*/
    
    List<Station> stations = (List<Station>) request.getAttribute("stationList");
    if (stations == null)
        stations = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String nowDay = sdf.format(new Date());

    String createdAt = StringUtils.defaultString(request.getParameter("createdAt"), "").trim();
    createdAt = ("".equals(createdAt.trim())) ? nowDay : createdAt;

%>
<!doctype html>
<html lang="ko">
<head>
    <%@include file="/WEB-INF/jsp/include/basicHeaderInfo.jsp"%>

    <script language="javascript">

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

        function resetStarGroupBtn(obj) {
            $(".btn-star-group").each(function() {
                $(this).removeClass("active");
            });
            $(obj).addClass("active");
            locationItemFilter();
        }

        function resetWayGroupBtn(obj) {
            $(".btn-way-group").each(function() {
                $(this).removeClass("active");
            });
            $(obj).addClass("active");
            locationItemFilter();
        }

        function showAllStationDetailInfo(obj) {
            $(".stationDetailInfo").each(function() {
                $(this).css("display", "block");

                if ($(this).hasClass("bus")) {
                    $(this).css("display", "none");
                }
            });
            setAtciveClassForGroup("btn-expand-group", "expand-all");
        }

        function closeAllStationDetailInfo(obj) {
            $(".stationDetailInfo").each(function() {
                $(this).css("display", "none");
            });
            setAtciveClassForGroup("btn-expand-group", "close-all");
        }

        function setAtciveClassForGroup(targetGroupClass, targetClass) {
            $("." + targetGroupClass).each(function() {
                checkAndRemoveClass(this, "active");
                if($(this).hasClass(targetClass)) {
                    $(this).addClass("active");
                }
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
            showSpecialPlateTypeList();
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
            showSpecialPlateTypeList();
        }

        function showSpecialPlateTypeList(plateTypeCode) {
            if (plateTypeCode == null || plateTypeCode == undefined) {
                plateTypeCode = "00";
            }

            $(".bus-select-group").each(function() {
                var badge = $(this);
                checkAndRemoveClass(badge, "badge-dark");
                badge.addClass("badge-light");
                var thisPlateTypeCode = $(this).attr("data-platetype");
                if (thisPlateTypeCode == plateTypeCode) {
                    checkAndRemoveClass(badge, "badge-light");
                    badge.addClass("badge-dark");
                }
            });

            if (plateTypeCode == "00") {
                return;
            }

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
    <script>
        function locationItemFilter() {
            $(".locationLiItem").each(function() {
                $(this).css("display", "none");

                var isDisplay = false;
                if (isActiveStarGroup($(this)) && isActiveWayGroup($(this))) {
                    isDisplay = true;
                }

                if (isDisplay) {
                    $(this).css("display", "block");
                }
            });
        }

        function isActiveStarGroup(obj) {
            var attrName = "data-star-yn";
            var nowData = "starAll";
            $(".btn-star-group").each(function() {
                if($(this).hasClass("active")) {
                    nowData = $(this).attr(attrName);
                }
            });

            var returnVal = false;
            if($(obj).attr(attrName) == nowData || nowData == "all") {
                returnVal = true;
            }

            return returnVal;
        }

        function isActiveWayGroup(obj) {
            var attrName = "data-way-info";
            var nowData = "";
            $(".btn-way-group").each(function() {
                if($(this).hasClass("active")) {
                    nowData = $(this).attr(attrName);
                }
            });

            var returnVal = false;
            if($(obj).attr(attrName) == nowData || nowData == "all") {
                returnVal = true;
            }

            return returnVal;
        }

        function goList() {
            location.href = "/busList";
        }
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/navBar.jsp" flush="true">
    <jsp:param name="currentMenu" value="2"/>
</jsp:include>
<div class="container bottomPadding">
    <div class="rounded-lg alert-secondary doc-title" role="alert" style="margin-top:15px;">
        <div class="d-flex mb-3 justify-content-center">
            <div class="p-2"><%= routeName %>번 노선정보</div>
            <div class="ml-auto p-2"><button type="button" class="btn btn-sm btn-secondary" onclick="goList();">목록</button></div>
        </div>
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
        <% if (route != null) { %>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">라우트ID = <%= route.getRouteId() %></li>
            <li class="list-group-item">노선번호 = <%= route.getRouteName() %></li>
            <li class="list-group-item">운행정보
                <div>평일 운행 대수 = <%= route.getWeekdayCount() %></div>
                <div>주말 운행 대수 = <%= route.getWeekendCount() %></div>
                <div>2층버스 평일 운행 대수 = <%= route.getWeekdayDoubleDeckerCount() %></div>
                <div>2층버스 주말 운행 대수 = <%= route.getWeekendDoubleDeckerCount() %></div>
            </li>
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
        <% } %>
    </div>

    <div id="stationsTabArea">
        <form name="searchForm" id="searchForm">
            <input type="hidden" id="_id" name="_id" value="<%= _id %>" />
            <input type="hidden" id="routeId" name="routeId" value="<%= routeId %>" />
            <div class="form-row topPadding">
                <div class="form-group">
                    <input class="form-control form-control-sm" type="date" id="createdAt" name="createdAt" value="<%= createdAt %>" >
                </div>
                <div class="form-group" style="width: 4px">
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-primary btn-sm" onclick="goSearch()">Search</button>
                </div>
            </div>
            <div class="contentAlignRight bottomMargin">
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-warning btn-sm btn-star-group" data-star-yn="Y" onclick="resetStarGroupBtn(this)">Only Stars</button>
                    <button type="button" class="btn btn-warning btn-sm btn-star-group active" data-star-yn="all" onclick="resetStarGroupBtn(this)">All</button>
                </div>

                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-info btn-sm btn-way-group" data-way-info="ascend-way" onclick="resetWayGroupBtn(this)">상행</button>
                    <button type="button" class="btn btn-info btn-sm btn-way-group" data-way-info="descend-way" onclick="resetWayGroupBtn(this)">하행</button>
                    <button type="button" class="btn btn-info btn-sm btn-way-group active" data-way-info="all" onclick="resetWayGroupBtn(this)">전체</button>
                </div>

                <div class="btn-group stationDetailInfoBtnGroup" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary btn-sm btn-expand-group expand-all" onclick="showAllStationDetailInfo(this)">전체 열기</button>
                    <button type="button" class="btn btn-secondary btn-sm btn-expand-group close-all active" onclick="closeAllStationDetailInfo(this)">전체 닫기</button>
                </div>
            </div>
            <div class="contentAlignRight">
                <div class="btn">
                    <% for(PlateType plateType: plateTypeList) { %>
                        <%
                            String codeValueAll = "99";
                            int typeCnt = plateType.getTypeCnt();
                            String code = plateType.getCode();
                        %>
                        <% if(typeCnt > 0 || codeValueAll.equals(code)) { %>
                            <span onclick="showSpecialPlateTypeList('<%= plateType.getCode() %>');">
                                <img src="<%= plateType.getImageSrc() %>"
                                    style="width:25px"
                                    data-toggle="tooltip"
                                    data-placement="bottom"
                                    title="<%= plateType.getName() %>"
                                    data-platetype="<%= plateType.getCode() %>" />
                                <% if (codeValueAll.equals(code)) { %>
                                    <span class="badge bus-select-group badge-dark" data-platetype="<%= plateType.getCode() %>">ALL</span>
                                <% } else { %>
                                    <span class="badge bus-select-group badge-light" data-platetype="<%= plateType.getCode() %>"><%= typeCnt %></span>
                                <% }  %>
                            </span>
                        <% } %>
                    <% } %>
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
                    <% for(Bus bus : plateNoList) { %>
                    <option value="<%= bus.getPlateNo() %>"><%= bus.getPlateNo() %> (<%= bus.getPlateTypeName() %>)</option>
                    <% } %>
                </select>
            </div>
        </div>

        <ul class="list-group list-group-flush">
            <%
                String wayInfo = "";
                //BusStation busStation = new BusStation();
            %>
            <% for(Station station : stations) { %>
                <%
                    String stationId = station.getStationId();
                    if ("descend-way".equals(wayInfo) || "Y".equals(station.getTurnYn())) {
                        wayInfo = "descend-way";
                    } else {
                        wayInfo = "ascend-way";
                    }
                    boolean isStarStation = false;
                    String starStationId = ""; //busStation.getStartStationIdMap().get(stationId);
                    if (starStationId != null) {
                        isStarStation = true;
                    }
                    String starYn = (isStarStation) ? "Y" : "N";

                    int remainSeatCntZeroCnt = station.getRemainSeatCntZeroCnt();
                    String remainSeatCntZeroClass = (remainSeatCntZeroCnt > 0) ? "remainSeatCntZero" : "";

                %>
            <li class="list-group-item locationLiItem <%= remainSeatCntZeroClass %>"
                data-way-info="<%= wayInfo %>"
                data-star-yn="<%= starYn %>" >
                <img src="/img/star.png" class="icon-size-small <%= (isStarStation) ? "icon-check" : "icon-no-check" %>" />
                <span>
                    <span onclick="toggleStationDetailInfo(this)">
                        <%= station.getStationName() %>
                        <span class="font-smaller font-gray">(<span class="stationId"><%= stationId %></span>)</span>
                    </span>
                    <%--<span class="badge badge-secondary badge-stationDetailInfo timeline" onclick="toggleStationDetailInfoTab(this)">Timeline</span>--%>
                    <%--<span class="badge badge-light badge-stationDetailInfo bus" onclick="toggleStationDetailInfoTab(this)">Bus</span>--%>

                    <% List<Location> locationList = station.getLocations(); %>
                    <% locationList = (locationList.isEmpty()) ? new ArrayList<>() : locationList; %>
                    <div class="stationDetailInfo timeline">
                        <ul class="list-group list-group-flush font-smaller">
                            <% for(Location location: locationList) { %>
                            <%
                                String formatedCreatedAt = location.getFormatedCreatedAt();
                                String plateNo = location.getPlateNo();
                                String plateType = location.getPlateType();
                                String plateTypeName = location.getPlateTypeName();
                                String remainSeatCnt = StringUtils.defaultString(location.getRemainSeatCnt(), "0");

                                int platNoLen = plateNo.length();
                                String shortPlateNo = plateNo.substring(platNoLen -4, platNoLen);

                                String busImgSrc = CodeUtil.getPlateType(plateType).getImageSrc();

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
        <% if(specialMessageList.size() == 0) { %>
        <div style="text-align: center">특이사항 없습니다.</div>
        <% } else { %>
            <% for(SpecialMessage specialMessage: specialMessageList) { %>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <%= specialMessage.getFormatedCreatedAt() %> |
                        <%= specialMessage.getMessage() %>
                    </li>
                </ul>
            <% } %>
        <% } %>
    </div>
</div>
</body>
</html>