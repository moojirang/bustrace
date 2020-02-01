<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.dazzilove.bustrace.domain.*" %>
<%
    Code codeInfo = (Code) request.getAttribute("codeInfo");
    codeInfo = (codeInfo == null)? new Code() : codeInfo;

    String _id = "";
    if (codeInfo.getId() != null) {
        _id = codeInfo.getId().toString();
    }
%>
<!doctype html>
<html lang="ko">
<head>

    <%@include file="/WEB-INF/jsp/include/basicHeaderInfo.jsp"%>

    <script>
        var _id = "<%= _id %>";

        $(function () {
            setStyleTripPlanList();
        });
        
        function goList() {
            location.href="/codeMng/codeList";
        }

        function editCode() {
            location.href="/codeMng/viewEditCode?_id=" + _id;
        }

        function delCode() {
            $.ajax({
                method: "POST",
                url: "/codeMng/delCode",
                data: {_id : _id}
            })
                .done(function(msg) {
                    alert(msg);
                    location.href = "/codeMng/codeList";
                })
                .fail(function() {
                    alert("error");
                });
        }

        function addDetailCode() {
            location.href="/codeMng/viewAddDetailCode?codeId=" + _id;
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
            <div class="p-2">코드정보</div>
            <div class="ml-auto p-2"><button type="button" class="btn btn-sm btn-secondary" onclick="goList()">목록</button></div>
        </div>
    </div>

    <ul class="nav nav-tabs subTab">
        <li class="nav-item">
            <a class="nav-link" href="#" id="defaultInfoTab" onclick="changeTab(this)">기본정보</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="#" id="detailCodeListTab" onclick="changeTab(this)">상세코드</a>
        </li>
    </ul>

    <div id="defaultInfoTabArea" style="display: none;">
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Code = <%= codeInfo.getCode() %></li>
            <li class="list-group-item">Name = <%= codeInfo.getName() %></li>
            <li class="list-group-item">사용여부 = <%= codeInfo.getUseYn() %></li>
        </ul>
        <div class="contentAlignRight bottomMargin topMargin">
            <button type="button" class="btn btn-sm btn-danger" onclick="delCode()">삭제</button>
            <button type="button" class="btn btn-sm btn-primary" onclick="editCode()">수정</button>
        </div>
    </div>

    <div id="detailCodeListTabArea" style="display: block;">
        <%
            List<DetailCode> detailCodes = codeInfo.getDetailCodes();
        %>
         <table class="table table-sm" id="detailCodeList">
             <thead>
             <tr>
                 <th scope="col">순번</th>
                 <th scope="col">상세코드</th>
                 <th scope="col">코드명</th>
                 <th scope="col">사용여부</th>
             </tr>
             </thead>
             <tbody>
             <% for (DetailCode detailCode: detailCodes) { %>
             <%
             %>
                 <tr>
                     <td scope="row"><%= detailCode.getSortNumber() %></td>
                     <td><a href="/codeMng/viewEditDetailCode?codeId=<%= codeInfo.getId() %>&detailCodeId=<%= detailCode.getId() %>"><%= detailCode.getCode() %></a></td>
                     <td><%= detailCode.getName() %></td>
                     <td><%= detailCode.getUseYn() %></td>
                 </tr>
             <% } %>
             </tbody>
         </table>

         <div class="contentAlignRight bottomMargin topMargin">
            <button type="button" class="btn btn-sm btn-primary" onclick="addDetailCode()">상세코드추가</button>
         </div>
    </div>
</div>
</body>
</html>