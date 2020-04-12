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
            <li class="nav-item" data-menu="6">
                <a class="nav-link">시스템관리</a>
            </li>
            <li class="nav-item" data-menu="7">
                <a class="nav-link" href="/batchMng/list">- 배치리스트</a>
            </li>
        </ul>
    </div>
</nav>