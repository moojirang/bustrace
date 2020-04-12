<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dazzilove.bustrace.domain.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<Batch> batchList = (List<Batch>) request.getAttribute("batchList");
    if (batchList == null) {
        batchList = new ArrayList<>();
    }
%>
<!doctype html>
<html lang="ko">
<head>
    <%@include file="/WEB-INF/jsp/include/basicHeaderInfo.jsp"%>
    <script>
        $(function () {
        });
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/navBar.jsp" flush="true">
    <jsp:param name="currentMenu" value="7"/>
</jsp:include>
<div class="container bottomPadding">
    <div class="rounded-lg alert-secondary doc-title" role="alert" style="margin-top:15px;">
        <div class="d-flex mb-3 justify-content-center">
            <div class="p-2">배치리스트</div>
            <div class="ml-auto p-2"></div>
        </div>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Schedule</th>
            <th scope="col">URL</th>
        </tr>
        </thead>
        <tbody>
        <% int number = 1; %>
        <% for(Batch batch : batchList) { %>
        <tr>
            <th scope="row"><%= number++ %></th>
            <td><%= batch.getName() %></td>
            <td><%= batch.getSchedule() %></td>
            <td><%= batch.getFullUrl() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>