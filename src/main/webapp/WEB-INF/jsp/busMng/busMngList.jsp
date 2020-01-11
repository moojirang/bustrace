<%@ page import="java.util.List" %>
<%@ page import="com.dazzilove.bustrace.domain.Bus" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<Bus> busList = (List<Bus>) request.getAttribute("busList");
    if (busList == null) {
        busList = new ArrayList<>();
    }
%>
<!doctype html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/jsp/include/basicHeaderInfo.jsp"%>
<script>
    $(function () {
        setTripPlanCountBg();
    });

    function setTripPlanCountBg() {
        $(".tripplan-div-count").each(function() {
            var tripPlanTotalCount = $(this).parents("tr").children("td").children(".tripplan-tot-count").text();
            var tripPlanCount = $(this).text();
            if (tripPlanTotalCount == tripPlanCount) {
                return;
            }
            checkAndRemoveClass($(this), "badge-light");
            $(this).addClass("badge-dark");
        });
    }
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/navBar.jsp" flush="true">
    <jsp:param name="currentMenu" value="3"/>
</jsp:include>
<div class="container bottomPadding">
    <div class="rounded-lg alert-secondary doc-title" role="alert" style="margin-top:15px;">
        <div class="d-flex mb-3 justify-content-center">
            <div class="p-2">노선관리</div>
            <div class="ml-auto p-2"></div>
        </div>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">번호</th>
            <th scope="col">라우트ID</th>
            <th scope="col">전체운행</th>
            <th scope="col">전일운행</th>
            <th scope="col">당일운행</th>
        </tr>
        </thead>
        <tbody>
        <% int number = 1; %>
        <% for(Bus bus : busList) { %>
        <tr>
            <th scope="row"><%= number++ %></th>
            <td>
                <a href="/busMng/busMngInfo?routeId=<%= bus.getRouteId() %>"><%= bus.getRouteName() %></a>
            </td>
            <td><%= bus.getRouteId() %></td>
            <td><span class="badge badge-light tripplan-tot-count"><%= bus.getTotalTripPlanCount() %></span></td>
            <td><span class="badge badge-light tripplan-div-count"><%= bus.getYesterdayTripRecordCount() %></span></td>
            <td><span class="badge badge-light tripplan-div-count" ><%= bus.getTodayTripRecordCount() %></span></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>