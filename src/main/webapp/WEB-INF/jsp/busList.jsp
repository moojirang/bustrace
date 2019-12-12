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
</head>
<body>
<div>노선 리스트</div>
<ul>
<% for(Bus bus : busList) { %>
    <li><a href="/routeInfo?routeId=<%= bus.getRouteId() %>"><%= bus.getRouteName() %></a></li>
<% } %>
</ul>
</body>
</html>