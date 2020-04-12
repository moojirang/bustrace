<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <jsp:param name="currentMenu" value="1"/>
</jsp:include>
<div class="container bottomPadding topPadding">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">BusTrace Backend</h5>
            <p class="card-text">
                <div>BusTrace Backend System 입니다.</div>
                <div>버스 목록 확인을 위해서는 아래의 페이지로 이동하세요.</div>
            </p>
            <a href="http://bustrace-app.dazzilove.com/" target="_blank" class="card-link">BusTrace-app</a>
        </div>
    </div>
</div>
</body>
</html>