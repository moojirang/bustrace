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
    String plateTypeValue = StringUtils.defaultString(tripPlan.getPlateType(), "");
    String weekendOperationYNValue = StringUtils.defaultString(tripPlan.getWeekendOperationYN(), "N");
    String spareYNValue = StringUtils.defaultString(tripPlan.getSpareYN(), "N");
    String schoolBreakReductionYNValue = StringUtils.defaultString(tripPlan.getSchoolBreakReductionYN(), "N");
    String schoolBreakReductionStartAtValue = StringUtils.defaultString(tripPlan.getFormatedSchoolBreakReductionStartAt(), "");
    schoolBreakReductionStartAtValue = schoolBreakReductionStartAtValue.replace("/", "-");

    List<PlateType> plateTypeList = new ArrayList();
    Map<String, PlateType> plateTypeCodeMap = CodeUtil.getPlateTypes();
    plateTypeCodeMap.forEach((key, value) -> {
        plateTypeList.add(value);
    });
%>
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
            var plateType = $(':radio[name="plateTypes"]:checked').val();
            var weekendOperationYN = $(':radio[name="weekendOperationYN"]:checked').val();
            var spareYN = $(':radio[name="spareYN"]:checked').val();
            var schoolBreakReductionYN = $(':radio[name="schoolBreakReductionYN"]:checked').val();

            if (plateNo == "" || plateNo.length < 4) {
                alert("차량번호를 입력해주세요.");
                return;
            }
            if (plateType == undefined) {
                alert("차량타입을 선택해주세요.");
                return;
            }
            if (weekendOperationYN == undefined) {
                alert("주말운행여부를 선택해주세요.");
                return;
            }
            if (spareYN == undefined) {
                alert("예비차여부를 선택해주세요.");
                return;
            }
            if (schoolBreakReductionYN == undefined) {
                alert("방학감차여부를 선택해주세요.");
                return;
            }

            if (schoolBreakReductionYN == "Y") {
                var schoolBreakReductionStartAt = $("#schoolBreakReductionStartAt").val();
                schoolBreakReductionStartAt = $.trim(schoolBreakReductionStartAt);

                if (schoolBreakReductionStartAt == "") {
                    alert("방학감차 시작일을 입력해주세요.");
                    return;
                }
            }

            $.ajax({
              method: "POST",
              url: "/busMng/addTripPlanProc",
              data: {
                  routeId : routeId
                  , plateNo: plateNo
                  , plateType: plateType
                  , weekendOperationYN: weekendOperationYN
                  , spareYN: spareYN
                  , schoolBreakReductionYN: schoolBreakReductionYN
                  , schoolBreakReductionStartAt: schoolBreakReductionStartAt
              }
            })
            .done(function(msg) {
                var confirmResult = confirm(msg + "\n추가로 등록하시겠습니까?");
                if (confirmResult) {
                    location.href = "/busMng/addTripPlan?routeId=" + routeId;
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
            var plateType = $(':radio[name="plateTypes"]:checked').val();
            var weekendOperationYN = $(':radio[name="weekendOperationYN"]:checked').val();
            var spareYN = $(':radio[name="spareYN"]:checked').val();
            var schoolBreakReductionYN = $(':radio[name="schoolBreakReductionYN"]:checked').val();

            if (plateNo == "" || plateNo.length < 4) {
                alert("차량번호를 입력해주세요.");
                return;
            }
            if (plateType == undefined) {
                alert("차량타입을 선택해주세요.");
                return;
            }
            if (weekendOperationYN == undefined) {
                alert("주말운행여부를 선택해주세요.");
                return;
            }
            if (spareYN == undefined) {
                alert("예비차여부를 선택해주세요.");
                return;
            }
            if (schoolBreakReductionYN == undefined) {
                alert("방학감차여부를 선택해주세요.");
                return;
            }

            if (schoolBreakReductionYN == "Y") {
                var schoolBreakReductionStartAt = $("#schoolBreakReductionStartAt").val();
                schoolBreakReductionStartAt = $.trim(schoolBreakReductionStartAt);

                if (schoolBreakReductionStartAt == "") {
                    alert("방학감차 시작일을 입력해주세요.");
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
                  , plateType: plateType
                  , weekendOperationYN: weekendOperationYN
                  , spareYN: spareYN
                  , schoolBreakReductionYN: schoolBreakReductionYN
                  , schoolBreakReductionStartAt: schoolBreakReductionStartAt
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
    <div class="alert alert-secondary doc-title" role="alert" style="margin-top:15px;">
        <%= routeName %>번 차량 추가
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
                <input class="form-check-input" type="radio" name="weekendOperationYN" id="weekendOperation_Y" value="Y" data-input-value="<%= weekendOperationYNValue %>">
                <label class="form-check-label" for="weekendOperation_Y">주말운행 O</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="weekendOperationYN" id="weekendOperation_N" value="N" data-input-value="<%= weekendOperationYNValue %>">
                <label class="form-check-label" for="weekendOperation_N">주말운행 X</label>
            </div>
        </div>
        <div class="form-group">
            <label>예비차여부</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="spareYN" id="spare_Y" value="Y" data-input-value="<%= spareYNValue %>">
                <label class="form-check-label" for="spare_Y">예비차 O</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="spareYN" id="spare_N" value="N" data-input-value="<%= spareYNValue %>">
                <label class="form-check-label" for="spare_N">예비차 X</label>
            </div>
        </div>
        <div class="form-group">
            <label>방학감차여부</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="schoolBreakReductionYN" id="schoolBreakReduction_Y" value="Y" data-input-value="<%= schoolBreakReductionYNValue %>">
                <label class="form-check-label" for="schoolBreakReduction_Y">방학감차 O</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="schoolBreakReductionYN" id="schoolBreakReduction_N" value="N" data-input-value="<%= schoolBreakReductionYNValue %>">
                <label class="form-check-label" for="schoolBreakReduction_N">방학감차 X</label>
            </div>
        </div>
        <div class="form-group">
            <label>방학감차 시작일</label>
            <input class="form-control form-control-sm" type="date" id="schoolBreakReductionStartAt" name="schoolBreakReductionStartAt" data-input-value="<%= schoolBreakReductionStartAtValue %>">
        </div>

        <div class="contentAlignRight bottomMargin topMargin">
            <button type="button" class="btn btn-sm btn-secondary" onclick="goBack()">뒤로</button>
            <% if("ADD".equals(pageMode)) { %>
                <button type="button" class="btn btn-sm btn-primary" onclick="addBus()">추가</button>
            <% } else { %>
                <button type="button" class="btn btn-sm btn-primary" onclick="editBus()">수정</button>
            <% } %>
        </div>
    </form>
</div>
</body>
</html>