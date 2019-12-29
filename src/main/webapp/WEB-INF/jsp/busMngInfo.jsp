<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/css/bootstrap-4.4.1/bootstrap.css" rel="stylesheet"/>
    <link href="/css/base.css" rel="stylesheet"/>

    <script src="/js/jquery-3.4.1.slim.min.js"></script>
    <script src="/js/bootstrap-4.4.1/bootstrap.js"></script>
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
            <li class="nav-item">
                <a class="nav-link" href="/busMng">노선관리</a>
            </li>
            <li class="nav-item active">
                <span class="nav-link">> 노선관리상세 <span class="sr-only">(current)</span></span>
            </li>
        </ul>
    </div>
</nav>
<div class="container bottomPadding">
    <div class="alert alert-secondary doc-title" role="alert" style="margin-top:15px;">
        노선관리
    </div>
</div>
</body>
</html>