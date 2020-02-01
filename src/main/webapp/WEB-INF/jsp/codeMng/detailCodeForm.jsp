<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.dazzilove.bustrace.domain.*" %>
<%
    String pageMode = StringUtils.defaultString((String)request.getAttribute("pageMode"), "ADD");
    String codeId = StringUtils.defaultString((String)request.getAttribute("codeId"), "").trim();
    String detailCodeId = StringUtils.defaultString((String)request.getAttribute("detailCodeId"), "").trim();    
%>
<% if("".equals(codeId) || (!"ADD".equals(pageMode) && "".equals(detailCodeId))) { %>
<script>
    alert("정보가 올바르지 않습니다.");
    history.back();
</script>
<% } %>
<%
    Code codeInfo = (Code) request.getAttribute("code");
    codeInfo = (codeInfo == null) ? new Code() : codeInfo;

    boolean isDeletedCode = codeInfo.isDeleted();

    String code = "";
    String name = "";
    int sortNumber = 0;
    String val1 = "";
    String val2 = "";
    String val3 = "";
    String useYn = "N";
    boolean isDeletedDetailCode = true;

    DetailCode detailCodeInfo = codeInfo.getDetailCode(detailCodeId);
    if (detailCodeInfo != null) {
        code = StringUtils.defaultString(detailCodeInfo.getCode());
        name = StringUtils.defaultString(detailCodeInfo.getName());
        sortNumber = detailCodeInfo.getSortNumber();
        val1 = StringUtils.defaultString(detailCodeInfo.getVal1());
        val2 = StringUtils.defaultString(detailCodeInfo.getVal2());
        val3 = StringUtils.defaultString(detailCodeInfo.getVal2());
        useYn = StringUtils.defaultString(detailCodeInfo.getUseYn(), "Y");
        isDeletedDetailCode = detailCodeInfo.isDeleted();
    }
%>
<% if(isDeletedCode || (!"ADD".equals(pageMode) && isDeletedDetailCode)) { %>
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
        var codeId = "<%= codeId %>";
        var detailCodeId = "<%= detailCodeId %>";

        $(function () {
            setInputCheckedByValue();
        });

        function addDetailCode() {
            var code = $("#code").val();
            var name = $("#name").val();
            var sortNumber = $("#sortNumber").val();
            var val1 = $("#val1").val();
            var val2 = $("#val2").val();
            var val3 = $("#val3").val();
            var useYn = $(':radio[name="useYn"]:checked').val();

            if (code == "") {
                alert("코드를 입력해주세요.");
                return;
            }
            if (name == "") {
                alert("코드명을 입력해주세요.");
                return;
            }
            if (sortNumber == "") {
                alert("정렬순서를 입력해주세요.");
                return;
            }
            if (useYn == undefined) {
                alert("사용여부를 선택해주세요.");
                return;
            }

            $.ajax({
              method: "POST",
              url: "/codeMng/addDetailCode",
              data: {
                  codeId: codeId
                  , code: code
                  , name: name
                  , sortNumber: sortNumber
                  , val1: val1
                  , val2: val2
                  , val3: val3
                  , useYn: useYn
              }
            })
            .done(function(msg) {
                var confirmResult = confirm(msg + "\n추가로 등록하시겠습니까?");
                if (confirmResult) {
                    location.href = "/codeMng/viewAddDetailCode?codeId=" + codeId;
                } else {
                    location.href = "/codeMng/codeInfo?_id=" + codeId;
                }
            })
            .fail(function() {
                alert("error");
            });
        }

        function editDetailCode() {
            var code = $("#code").val();
            var name = $("#name").val();
            var sortNumber = $("#sortNumber").val();
            var val1 = $("#val1").val();
            var val2 = $("#val2").val();
            var val3 = $("#val3").val();
            var useYn = $(':radio[name="useYn"]:checked').val();

            if (code == "") {
                alert("코드를 입력해주세요.");
                return;
            }
            if (name == "") {
                alert("코드명을 입력해주세요.");
                return;
            }
            if (sortNumber == "") {
                alert("정렬순서를 입력해주세요.");
                return;
            }
            if (useYn == undefined) {
                alert("사용여부를 선택해주세요.");
                return;
            }

            $.ajax({
              method: "POST",
              url: "/codeMng/editDetailCode",
              data: {
                    codeId: codeId
                  , detailCodeId : detailCodeId
                  , code : code
                  , name: name
                  , sortNumber: sortNumber
                  , val1: val1
                  , val2: val2
                  , val3: val3
                  , useYn: useYn
              }
            })
            .done(function(msg) {
                alert(msg);
                location.href = "/codeMng/codeInfo?_id=" + codeId;
            })
            .fail(function() {
                alert("error");
            });
        }

        function delDetailCode() {
            $.ajax({
              method: "POST",
              url: "/codeMng/delDetailCode",
              data: {
                    codeId: codeId
                  , detailCodeId : detailCodeId
              }
            })
            .done(function(msg) {
                alert(msg);
                location.href = "/codeMng/codeInfo?_id=" + codeId;
            })
            .fail(function() {
                alert("error");
            });
        }

        function goBack() {
            history.back();
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
            <div class="p-2">상세코드 추가</div>
            <div class="ml-auto p-2"><button type="button" class="btn btn-sm btn-secondary" onclick="goBack()">뒤로</button></div>
        </div>
    </div>
    <form id="form1" name="form1">
        <input type="hidden" id="pageMode" value="<%= pageMode %>" />
        <% if (!"".equals(detailCodeId)) { %>
        <div class="form-group">
            <label>관리번호</label>
            <input type="text" class="form-control" id="codeId" data-input-value="<%= detailCodeId %>" disabled="disabled">
        </div>
        <% } %>
        <div class="form-group">
            <label>상세코드</label>
            <input type="text" class="form-control" id="code" placeholder="" data-input-value="<%= code %>">
        </div>
        <div class="form-group">
            <label>상세코드명</label>
            <input type="text" class="form-control" id="name" placeholder="" data-input-value="<%= name %>">
        </div>
        <div class="form-group">
            <label>정렬순서</label>
            <input type="text" class="form-control" id="sortNumber" placeholder="" data-input-value="<%= sortNumber %>">
        </div>
        <div class="form-group">
            <label>코드정보1</label>
            <input type="text" class="form-control" id="val1" placeholder="" data-input-value="<%= val1 %>">
        </div>
        <div class="form-group">
            <label>코드정보2</label>
            <input type="text" class="form-control" id="val2" placeholder="" data-input-value="<%= val2 %>">
        </div>
        <div class="form-group">
            <label>코드정보3</label>
            <input type="text" class="form-control" id="val3" placeholder="" data-input-value="<%= val3 %>">
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
            <button type="button" class="btn btn-sm btn-primary" onclick="addDetailCode()">추가</button>
            <% } else { %>
            <button type="button" class="btn btn-sm btn-danger" onclick="delDetailCode()">삭제</button>
            <button type="button" class="btn btn-sm btn-primary" onclick="editDetailCode()">수정</button>
            <% } %>
        </div>
    </form>
</div>
</body>
</html>