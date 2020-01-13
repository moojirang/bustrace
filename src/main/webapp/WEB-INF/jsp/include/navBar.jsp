<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String currentMenu = request.getParameter("currentMenu");
%>
<script>
    var currentMenu = "<%= currentMenu %>";
    $(function () {
      $(".nav-item").each(function() {
        checkAndRemoveClass($(this), "active");
        if($(this).attr("data-menu") == currentMenu) {
            $(this).addClass("active");
        }
      });
    });
</script>
<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="/busList">BusTrace</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item" data-menu="1">
                <a class="nav-link" href="/busList">노선목록</a>
            </li>
            <li class="nav-item" data-menu="2">
                <span class="nav-link">- 노선정보</span></span>
            </li>
            <li class="nav-item" data-menu="3">
                <a class="nav-link" href="/busMng/busMngList">노선관리</a>
            </li>
            <li class="nav-item" data-menu="5">
                <span class="nav-link">- 노선등록/수정/삭제</span>
            </li>
            <li class="nav-item" data-menu="4">
                <span class="nav-link">- 노선관리상세</span>
            </li>
        </ul>
    </div>
</nav>