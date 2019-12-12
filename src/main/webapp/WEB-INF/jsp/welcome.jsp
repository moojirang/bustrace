<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
</head>
<body>
<%
    String id = (String) request.getAttribute("id");
%>
Welcome <%= id %>!
</body>
</html>