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
        노선관리
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">노선번호</th>
            <th scope="col">라우트ID</th>
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
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>