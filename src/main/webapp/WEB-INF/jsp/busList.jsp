<%@ page import="java.util.List" %>
<%@ page import="com.dazzilove.bustrace.domain.Bus" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dazzilove.bustrace.domain.Route" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<Route> routeList = (List<Route>) request.getAttribute("routeList");
    if (routeList == null) {
        routeList = new ArrayList<>();
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
    <jsp:param name="currentMenu" value="1"/>
</jsp:include>
<div class="container bottomPadding">
    <div class="rounded-lg alert-secondary doc-title" role="alert" style="margin-top:15px;">
        <div class="d-flex mb-3 justify-content-center">
            <div class="p-2">노선 리스트</div>
            <div class="ml-auto p-2"></div>
        </div>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">번호</th>
            <th scope="col">전체운행</th>
            <th scope="col">전일운행</th>
            <th scope="col">당일운행</th>
        </tr>
        </thead>
        <tbody>
        <% int number = 1; %>
        <% for(Route route : routeList) { %>
        <tr>
            <th scope="row"><%= number++ %></th>
            <td><a href="/routeInfo?_id=<%= route.getId().toString() %>"><%= route.getRouteName() %></a></td>
            <td><span class="badge badge-light tripplan-tot-count"><%= route.getTotalTripPlanCount() %></span></td>
            <td><span class="badge badge-light tripplan-div-count"><%= route.getYesterdayTripRecordCount() %></span></td>
            <td><span class="badge badge-light tripplan-div-count" ><%= route.getTodayTripRecordCount() %></span></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <ul>
    </ul>
</div>
</body>
</html>