<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.dazzilove.bustrace.utils.CodeUtil" %>
<%@ page import="com.dazzilove.bustrace.domain.*" %>
<%
    String pageMode = StringUtils.defaultString((String)request.getAttribute("pageMode"), "");

    Route route = (Route) request.getAttribute("route");
    route = (route == null) ? new Route() : route;

    TripPlan tripPlan = (TripPlan) request.getAttribute("tripPlan");
    tripPlan = (tripPlan == null) ? new TripPlan() : tripPlan;

    String tripPlanId = "";
    tripPlanId = ("ADD".equals(pageMode)) ? "" : tripPlan.getId().toString();
    String routeId = tripPlan.getRouteId();
    String routeName = tripPlan.getRouteName();

    String plateNoValue = StringUtils.defaultString(tripPlan.getPlateNo(), "");
    int turnNumberValue = tripPlan.getTurnNumber();
    String plateTypeValue = StringUtils.defaultString(tripPlan.getPlateType(), "");
    String weekendOperationYnValue = StringUtils.defaultString(tripPlan.getWeekendOperationYn(), "N");
    String spareYnValue = StringUtils.defaultString(tripPlan.getSpareYn(), "N");
    String schoolBreakReductionYnValue = StringUtils.defaultString(tripPlan.getSchoolBreakReductionYn(), "N");
    String schoolBreakReductionStartedAtValue = StringUtils.defaultString(tripPlan.getFormatedSchoolBreakReductionStartedAt(), "");
    String tripStopYnValue = StringUtils.defaultString(tripPlan.getTripStopYn(), "N");
    String tripStopStartedAtValue = StringUtils.defaultString(tripPlan.getFormatedTripStopStartedAt(), "");
    String deleteYn = StringUtils.defaultString(tripPlan.getDeleteYn(), "N");

    schoolBreakReductionStartedAtValue = schoolBreakReductionStartedAtValue.replace("/", "-");
    tripStopStartedAtValue = tripStopStartedAtValue.replace("/", "-");

    List<PlateType> plateTypeList = new ArrayList();
    Map<String, PlateType> plateTypeCodeMap = CodeUtil.getPlateTypes();
    plateTypeCodeMap.forEach((key, value) -> {
        plateTypeList.add(value);
    });
%>
<% if("Y".equals(deleteYn)) { %>
<script>
    alert("삭제된 정보 입니다.");
    history.back();
</script>
<% } %>
<!doctype html>
<html lang="ko">
<head>
    <%@include file="/WEB-INF/jsp/include/basicHeaderInfo.jsp"%>

    <script>
        var routeId = "<%= routeId %>";

        $(function () {
            setInputCheckedByValue();
        });

        function addBus() {

            var routeId = $("#routeId").val();
            var plateNo = $("#plateNo").val();
            var turnNumber = $("#turnNumber").val();
            var plateType = $(':radio[name="plateTypes"]:checked').val();
            var weekendOperationYn = $(':radio[name="weekendOperationYn"]:checked').val();
            var spareYn = $(':radio[name="spareYn"]:checked').val();
            var schoolBreakReductionYn = $(':radio[name="schoolBreakReductionYn"]:checked').val();
            var tripStopYn = $(':radio[name="tripStopYn"]:checked').val();

            if (plateNo == "" || plateNo.length < 4) {
                alert("차량번호를 입력해주세요.");
                return;
            }
            if (turnNumber == "") {
                alert("순번을 입력해주세요.");
                return;
            }
            if (plateType == undefined) {
                alert("차량타입을 선택해주세요.");
                return;
            }
            if (weekendOperationYn == undefined) {
                alert("주말운행여부를 선택해주세요.");
                return;
            }
            if (spareYn == undefined) {
                alert("예비차여부를 선택해주세요.");
                return;
            }
            if (schoolBreakReductionYn == undefined) {
                alert("방학감차여부를 선택해주세요.");
                return;
            }

            if (schoolBreakReductionYn == "Y") {
                var schoolBreakReductionStartedAt = $("#schoolBreakReductionStartedAt").val();
                schoolBreakReductionStartedAt = $.trim(schoolBreakReductionStartedAt);

                if (schoolBreakReductionStartedAt == "") {
                    alert("방학감차 시작일을 입력해주세요.");
                    return;
                }
            }

            if (tripStopYn == "Y") {
                var tripStopStartedAt = $("#tripStopStartedAt").val();
                tripStopStartedAt = $.trim(tripStopStartedAt);

                if (tripStopStartedAt == "") {
                    alert("운행중단일자를 입력해주세요.");
                    return;
                }
            }

            $.ajax({
              method: "POST",
              url: "/busMng/addTripPlanProc",
              data: {
                  routeId : routeId
                  , plateNo: plateNo
                  , turnNumber: turnNumber
                  , plateType: plateType
                  , weekendOperationYn: weekendOperationYn
                  , spareYn: spareYn
                  , schoolBreakReductionYn: schoolBreakReductionYn
                  , schoolBreakReductionStartedAt: schoolBreakReductionStartedAt
                  , tripStopYn: tripStopYn
                  , tripStopStartedAt: tripStopStartedAt
              }
            })
            .done(function(msg) {
                var confirmResult = confirm(msg + "\n추가로 등록하시겠습니까?");
                if (confirmResult) {
                    location.href = "/busMng/viewAddTripPlan?routeId=" + routeId;
                } else {
                    location.href = "/busMng/busMngInfo?routeId=" + routeId;
                }
            })
            .fail(function() {
                alert("error");
            });
        }

        function editBus() {

            var tripPlanId = $("#tripPlanId").val();
            var routeId = $("#routeId").val();
            var plateNo = $("#plateNo").val();
            var turnNumber = $("#turnNumber").val();
            var plateType = $(':radio[name="plateTypes"]:checked').val();
            var weekendOperationYn = $(':radio[name="weekendOperationYn"]:checked').val();
            var spareYn = $(':radio[name="spareYn"]:checked').val();
            var schoolBreakReductionYn = $(':radio[name="schoolBreakReductionYn"]:checked').val();
            var tripStopYn = $(':radio[name="tripStopYn"]:checked').val();

            if (plateNo == "" || plateNo.length < 4) {
                alert("차량번호를 입력해주세요.");
                return;
            }
            if (turnNumber == "") {
                alert("순번을 입력해주세요.");
                return;
            }
            if (plateType == undefined) {
                alert("차량타입을 선택해주세요.");
                return;
            }
            if (weekendOperationYn == undefined) {
                alert("주말운행여부를 선택해주세요.");
                return;
            }
            if (spareYn == undefined) {
                alert("예비차여부를 선택해주세요.");
                return;
            }
            if (schoolBreakReductionYn == undefined) {
                alert("방학감차여부를 선택해주세요.");
                return;
            }

            if (schoolBreakReductionYn == "Y") {
                var schoolBreakReductionStartedAt = $("#schoolBreakReductionStartedAt").val();
                schoolBreakReductionStartedAt = $.trim(schoolBreakReductionStartedAt);

                if (schoolBreakReductionStartedAt == "") {
                    alert("방학감차 시작일을 입력해주세요.");
                    return;
                }
            }

            if (tripStopYn == "Y") {
                var tripStopStartedAt = $("#tripStopStartedAt").val();
                tripStopStartedAt = $.trim(tripStopStartedAt);

                if (tripStopStartedAt == "") {
                    alert("운행중단일자를 입력해주세요.");
                    return;
                }
            }

            $.ajax({
              method: "POST",
              url: "/busMng/editTripPlanProc",
              data: {
                  tripPlanId : tripPlanId
                  , routeId : routeId
                  , plateNo: plateNo
                  , turnNumber: turnNumber
                  , plateType: plateType
                  , weekendOperationYn: weekendOperationYn
                  , spareYn: spareYn
                  , schoolBreakReductionYn: schoolBreakReductionYn
                  , schoolBreakReductionStartedAt: schoolBreakReductionStartedAt
                  , tripStopYn: tripStopYn
                  , tripStopStartedAt: tripStopStartedAt
              }
            })
            .done(function(msg) {
                alert(msg);
                location.href = "/busMng/busMngInfo?routeId=" + routeId;
            })
            .fail(function() {
                alert("error");
            });
        }

        function delBus() {
            var tripPlanId = $("#tripPlanId").val();
            $.ajax({
              method: "POST",
              url: "/busMng/delTripPlanProc",
              data: {
                  tripPlanId : tripPlanId
              }
            })
            .done(function(msg) {
                alert(msg);
                location.href = "/busMng/busMngInfo?routeId=" + routeId;
            })
            .fail(function() {
                alert("error");
            });
        }

        function goBack() {
            history.back();
        }
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/navBar.jsp" flush="true">
    <jsp:param name="currentMenu" value="4"/>
</jsp:include>
<div class="container bottomPadding">
    <div class="rounded-lg alert-secondary doc-title" role="alert" style="margin-top:15px;">
        <div class="d-flex mb-3 justify-content-center">
            <div class="p-2"><%= routeName %>번 차량 추가</div>
            <div class="ml-auto p-2"><button type="button" class="btn btn-sm btn-secondary" onclick="goBack()">뒤로</button></div>
        </div>
    </div>
    <form id="form1" name="form1">
        <input type="hidden" id="pageMode" value="<%= pageMode %>" />
        <input type="hidden" id="routeId" value="<%= routeId %>" />
        <input type="hidden" id="tripPlanId" value="<%= tripPlanId %>" />
        <% if (!"".equals(tripPlanId)) { %>
        <div class="form-group">
            <label>관리번호</label>
            <input type="text" class="form-control" id="tripPlanId" data-input-value="<%= tripPlanId %>" disabled="disabled">
        </div>
        <% } %>
        <div class="form-group">
            <label>차량번호</label>
            <input type="text" class="form-control" id="plateNo" placeholder="경기75바3160" data-input-value="<%= plateNoValue %>">
        </div>
        <div class="form-group">
            <label>순번</label>
            <input type="text" class="form-control" id="turnNumber" placeholder="1" data-input-value="<%= turnNumberValue %>">
        </div>
        <div class="form-group">
            <label>차량타입</label>
            <% for(PlateType plateType : plateTypeList) {%>
                <%
                    String code = plateType.getCode();
                    String name = plateType.getName();
                    String busImgSrc = plateType.getImageSrc();
                %>
                <% if(!"99".equals(code)) { %>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="plateTypes" id="plateType_<%= code %>" value="<%= code %>" data-input-value="<%= plateTypeValue %>">
                        <label class="form-check-label" for="plateType_<%= code %>">
                            <img src="<%= busImgSrc%>" style="width:25px;" alt="<%= name %>" title="<%= name %>" />
                            <%= name %>
                        </label>
                    </div>
                <% } %>
            <% } %>
        </div>
        <div class="form-group">
            <label>주말운행여부</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="weekendOperationYn" id="weekendOperation_Y" value="Y" data-input-value="<%= weekendOperationYnValue %>">
                <label class="form-check-label" for="weekendOperation_Y">주말운행 O</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="weekendOperationYn" id="weekendOperation_N" value="N" data-input-value="<%= weekendOperationYnValue %>">
                <label class="form-check-label" for="weekendOperation_N">주말운행 X</label>
            </div>
        </div>
        <div class="form-group">
            <label>예비차여부</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="spareYn" id="spare_Y" value="Y" data-input-value="<%= spareYnValue %>">
                <label class="form-check-label" for="spare_Y">예비차 O</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="spareYn" id="spare_N" value="N" data-input-value="<%= spareYnValue %>">
                <label class="form-check-label" for="spare_N">예비차 X</label>
            </div>
        </div>
        <div class="form-group">
            <label>방학감차여부</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="schoolBreakReductionYn" id="schoolBreakReduction_Y" value="Y" data-input-value="<%= schoolBreakReductionYnValue %>">
                <label class="form-check-label" for="schoolBreakReduction_Y">방학감차 O</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="schoolBreakReductionYn" id="schoolBreakReduction_N" value="N" data-input-value="<%= schoolBreakReductionYnValue %>">
                <label class="form-check-label" for="schoolBreakReduction_N">방학감차 X</label>
            </div>
        </div>
        <div class="form-group">
            <label>방학감차 시작일</label>
            <input class="form-control form-control-sm" type="date" id="schoolBreakReductionStartedAt" name="schoolBreakReductionStartedAt" data-input-value="<%= schoolBreakReductionStartedAtValue %>">
        </div>
        <div class="form-group">
            <label>운행중단여부</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="tripStopYn" id="tripStop_Y" value="Y" data-input-value="<%= tripStopYnValue %>">
                <label class="form-check-label" for="tripStop_Y">운행중단 O</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="tripStopYn" id="tripStop_N" value="N" data-input-value="<%= tripStopYnValue %>">
                <label class="form-check-label" for="tripStop_N">운행중단 X</label>
            </div>
        </div>
        <div class="form-group">
            <label>운행중단일자</label>
            <input class="form-control form-control-sm" type="date" id="tripStopStartedAt" name="tripStopStartedAt" data-input-value="<%= tripStopStartedAtValue %>">
        </div>

        <div class="contentAlignRight bottomMargin topMargin">
            <% if("ADD".equals(pageMode)) { %>
                <button type="button" class="btn btn-sm btn-primary" onclick="addBus()">추가</button>
            <% } else { %>
                <button type="button" class="btn btn-sm btn-danger" onclick="delBus()">삭제</button>
                <button type="button" class="btn btn-sm btn-primary" onclick="editBus()">수정</button>
            <% } %>
        </div>
    </form>
</div>
</body>
</html>