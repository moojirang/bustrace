<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dazzilove.bustrace.utils.CodeUtil" %>
<%@ page import="com.dazzilove.bustrace.domain.*" %>
<%
    Route route = (Route) request.getAttribute("route");
    route = (route == null) ? new Route() : route;

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
        function addBus() {
            var frm = document.form1;

            var routeId = $("#routeId").val();
            var plateNo = $("#plateNo").val();
            var plateType = $(':radio[name="plateTypes"]:checked').val();
            var weekendOperationYN = $(':radio[name="weekendOperationYN"]:checked').val();

            if (plateNo == "") {
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

            console.log("routeId = " + routeId);
            console.log("plateNo = " + plateNo);
            console.log("plateType = " + plateType);
            console.log("weekendOperationYN = " + weekendOperationYN);

            $.ajax({
              method: "POST",
              url: "/busMng/addTripPlanProc",
              data: { routeId : routeId, plateNo: plateNo, plateType: plateType, weekendOperationYN: weekendOperationYN }
            })
            .done(function(msg) {
                alert(msg);
                location.href = "/busMng/busMngInfo?routeId=" + routeId;
            })
            .fail(function() {
                alert("error");
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
                <a class="nav-link" href="/busMng/busMngList">노선관리<span class="sr-only">(current)</span></a>
            </li>
        </ul>
    </div>
</nav>
<div class="container bottomPadding">
    <div class="alert alert-secondary doc-title" role="alert" style="margin-top:15px;">
        <%= route.getRouteName() %>번 차량 추가
    </div>
    <form id="form1" name="form1">
        <input type="hidden" id="routeId" value="<%= route.getRouteId() %>" />
        <div class="form-group">
            <label>차량번호</label>
            <input type="text" class="form-control" id="plateNo" placeholder="경기75바3160">
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
                        <input class="form-check-input" type="radio" name="plateTypes" id="plateType_<%= code %>" value="<%= code %>">
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
                <input class="form-check-input" type="radio" name="weekendOperationYN" id="weekendOperation_Y" value="Y">
                <label class="form-check-label" for="weekendOperation_Y">주말운행 O</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="weekendOperationYN" id="weekendOperation_N" value="N">
                <label class="form-check-label" for="weekendOperation_N">주말운행 X</label>
            </div>
        </div>

        <div class="contentAlignRight bottomMargin topMargin">
            <button type="button" class="btn btn-sm btn-primary" onclick="addBus()">추가</button>
        </div>
    </form>
</div>
</body>
</html>