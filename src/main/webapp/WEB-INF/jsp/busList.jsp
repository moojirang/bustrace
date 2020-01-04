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
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/navBar.jsp" flush="true">
    <jsp:param name="currentMenu" value="1"/>
</jsp:include>
<div class="container bottomPadding">
    <div class="alert alert-secondary doc-title" role="alert" style="margin-top:15px;">
        노선 리스트
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">노선번호</th>
        </tr>
        </thead>
        <tbody>
        <% int number = 1; %>
        <% for(Bus bus : busList) { %>
        <tr>
            <th scope="row"><%= number++ %></th>
            <td>
                <a href="/routeInfo?routeId=<%= bus.getRouteId() %>"><%= bus.getRouteName() %></a>
                (<%= bus.getRouteId() %>)
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <ul>
    </ul>
</div>
</body>
</html>