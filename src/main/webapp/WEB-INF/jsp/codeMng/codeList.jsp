<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dazzilove.bustrace.domain.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<Code> codeList = (List<Code>) request.getAttribute("codeList");
    if (codeList == null) {
        codeList = new ArrayList<>();
    }
%>
<!doctype html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/jsp/include/basicHeaderInfo.jsp"%>
<script>
    $(function () {
    });

    function addCode() {
        location.href = "/codeMng/viewAddCode";
    }
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/navBar.jsp" flush="true">
    <jsp:param name="currentMenu" value="7"/>
</jsp:include>
<div class="container bottomPadding">
    <div class="rounded-lg alert-secondary doc-title" role="alert" style="margin-top:15px;">
        <div class="d-flex mb-3 justify-content-center">
            <div class="p-2">코드관리</div>
            <div class="ml-auto p-2"></div>
        </div>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Code</th>
            <th scope="col">Name</th>
            <th scope="col">사용여부</th>
            <th scope="col">관리</th>
        </tr>
        </thead>
        <tbody>
        <% int number = 1; %>
        <% for(Code Code : codeList) { %>
        <tr>
            <th scope="row"><%= number++ %></th>
            <td><a href="/codeMng/codeInfo?_id=<%= Code.getId().toString() %>"><%= Code.getCode() %></a></td>
            <td><%= Code.getName() %></td>
            <td><%= Code.getUseYn() %></td>
            <td>삭제</td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <div class="contentAlignRight bottomMargin topMargin">
        <button type="button" class="btn btn-sm btn-primary" onclick="addCode()">코드추가</button>
    </div>
</div>
</body>
</html>