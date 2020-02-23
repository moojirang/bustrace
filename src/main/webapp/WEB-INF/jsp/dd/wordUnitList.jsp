<%@ page import="java.util.*" %>
<%@ page import="com.dazzilove.dd.domain.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<WordUnit> wordUnitList = (List<WordUnit>) request.getAttribute("wordUnitList");
%>
<!doctype html>
<html lang="ko">
<head>
</head>
<body>
<% for(WordUnit wordUnit: wordUnitList) { %>
    <a href="/dd/wordUnit?id=<%= wordUnit.getId() %>"><%= wordUnit.getUnitCode() %> / <%= wordUnit.getUnitName() %></a>
<% } %>
</body>
</html>