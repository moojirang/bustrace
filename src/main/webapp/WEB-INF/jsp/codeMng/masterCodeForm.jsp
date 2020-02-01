<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.dazzilove.bustrace.domain.*" %>
<%
    String pageMode = StringUtils.defaultString((String)request.getAttribute("pageMode"), "ADD");

    Code codeInfo = (Code) request.getAttribute("code");
    codeInfo = (codeInfo == null) ? new Code() : codeInfo;

    String _id = "";
    if (codeInfo.getId() != null) {
        _id = ("ADD".equals(pageMode)) ? "" : codeInfo.getId().toString();
    }

    String code = StringUtils.defaultString(codeInfo.getCode(), "");
    String name = StringUtils.defaultString(codeInfo.getName(), "");
    String useYn = StringUtils.defaultString(codeInfo.getUseYn(), "Y");
    String delYn = StringUtils.defaultString(codeInfo.getDelYn(), "N");
%>
<% if("Y".equals(delYn)) { %>
<script>
    alert("삭제된 정보 입니다.");
    history.back();
</script>
<% } %>
<!doctype html>
<html lang="ko">
<head>
    <%@include file="/WEB-INF/jsp/include/basicHeaderInfo.jsp"%>

    <script>
        var _id = "<%= _id %>";

        $(function () {
            setInputCheckedByValue();
        });

        function addCode() {
            var code = $("#code").val();
            var name = $("#name").val();
            var useYn = $(':radio[name="useYn"]:checked').val();


            if (code == "") {
                alert("코드를 입력해주세요.");
                return;
            }
            if (name == "") {
                alert("코드명을 입력해주세요.");
                return;
            }
            if (useYn == undefined) {
                alert("사용여부를 선택해주세요.");
                return;
            }

            $.ajax({
              method: "POST",
              url: "/codeMng/addCode",
              data: {
                  code : code
                  , name: name
                  , useYn: useYn
              }
            })
            .done(function(msg) {
                var confirmResult = confirm(msg + "\n추가로 등록하시겠습니까?");
                if (confirmResult) {
                    location.href = "/codeMng/viewAddCode";
                } else {
                    location.href = "/codeMng/codeList";
                }
            })
            .fail(function() {
                alert("error");
            });
        }

        function editCode() {
            var code = $("#code").val();
            var name = $("#name").val();
            var useYn = $(':radio[name="useYn"]:checked').val();


            if (code == "") {
                alert("코드를 입력해주세요.");
                return;
            }
            if (name == "") {
                alert("코드명을 입력해주세요.");
                return;
            }
            if (useYn == undefined) {
                alert("사용여부를 선택해주세요.");
                return;
            }

            $.ajax({
              method: "POST",
              url: "/codeMng/editCode",
              data: {
                    _id: _id
                  , code : code
                  , name: name
                  , useYn: useYn
              }
            })
            .done(function(msg) {
                alert(msg);
                location.href = "/codeMng/codeInfo?_id=" + _id;
            })
            .fail(function() {
                alert("error");
            });
        }

        function delCode() {
            $.ajax({
              method: "POST",
              url: "/codeMng/delCode",
              data: {
                  _id : _id
              }
            })
            .done(function(msg) {
                alert(msg);
                location.href = "/codeMng/codeInfo?_id=" + _id;
            })
            .fail(function() {
                alert("error");
            });
        }

        function goList() {
            location.href="/codeMng/codeList";
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
            <div class="p-2">코드 추가</div>
            <div class="ml-auto p-2"><button type="button" class="btn btn-sm btn-secondary" onclick="goList()">목록</button></div>
        </div>
    </div>
    <form id="form1" name="form1">
        <input type="hidden" id="pageMode" value="<%= pageMode %>" />
        <% if (!"".equals(_id)) { %>
        <div class="form-group">
            <label>관리번호</label>
            <input type="text" class="form-control" id="_id" data-input-value="<%= _id %>" disabled="disabled">
        </div>
        <% } %>
        <div class="form-group">
            <label>Code</label>
            <input type="text" class="form-control" id="code" placeholder="" data-input-value="<%= code %>">
        </div>
        <div class="form-group">
            <label>Name</label>
            <input type="text" class="form-control" id="name" placeholder="" data-input-value="<%= name %>">
        </div>
        <div class="form-group">
            <label>사용여부</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="useYn" id="use_Y" value="Y" data-input-value="<%= useYn %>">
                <label class="form-check-label" for="use_Y">사용함</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="useYn" id="use_N" value="N" data-input-value="<%= useYn %>">
                <label class="form-check-label" for="use_N">사용안함</label>
            </div>
        </div>

        <div class="contentAlignRight bottomMargin topMargin">
            <% if("ADD".equals(pageMode)) { %>
            <button type="button" class="btn btn-sm btn-primary" onclick="addCode()">추가</button>
            <% } else { %>
            <button type="button" class="btn btn-sm btn-danger" onclick="delCode()">삭제</button>
            <button type="button" class="btn btn-sm btn-primary" onclick="editCode()">수정</button>
            <% } %>
        </div>
    </form>
</div>
</body>
</html>