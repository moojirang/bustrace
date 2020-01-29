<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.dazzilove.bustrace.utils.CodeUtil" %>
<%@ page import="com.dazzilove.bustrace.domain.*" %>
<%
    String pageMode = StringUtils.defaultString((String)request.getAttribute("pageMode"), "");

    Route route = (Route) request.getAttribute("route");
    route = (route == null) ? new Route() : route;

    String _id = "";
    if (route.getId() != null) {
        _id = ("ADD".equals(pageMode)) ? "" : route.getId().toString();
    }

    String routeId = StringUtils.defaultString(route.getRouteId());
    String routeName = StringUtils.defaultString(route.getRouteName());
    String weekdayCount = String.valueOf(route.getWeekdayCount());
    String weekendCount = String.valueOf(route.getWeekendCount());
    String doubleDeckerCount = String.valueOf(route.getDoubleDeckerCount());
    String companyName = StringUtils.defaultString(route.getCompanyName());
    String peekAlloc = StringUtils.defaultString(route.getPeekAlloc());
    String nPeekAlloc = StringUtils.defaultString(route.getNPeekAlloc());
    String startStationId = StringUtils.defaultString(route.getStartStationId());
    String startStationName = StringUtils.defaultString(route.getStartStationName());
    String upFirstTime = StringUtils.defaultString(route.getUpFirstTime());
    String upLastTime = StringUtils.defaultString(route.getUpLastTime());
    String endStationId = StringUtils.defaultString(route.getEndStationId());
    String endStationName = StringUtils.defaultString(route.getEndStationName());
    String downFirstTime = StringUtils.defaultString(route.getDownFirstTime());
    String downLastTime = StringUtils.defaultString(route.getDownLastTime());

    String dataGatherBatchUseYnValue = "N";
    String dataGatherBatchScheduleValue = "";
    DataGatherScheduler dataGatherScheduler = route.getDataGatherScheduler();
    if (dataGatherScheduler != null) {
        dataGatherBatchUseYnValue = (dataGatherScheduler.isEnabled()) ? "Y" : "N";
        dataGatherBatchScheduleValue = dataGatherScheduler.getSchedule();
    }

%>
<% if("Y".equals(route.getDeleteYn())) { %>
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

        function addRoute() {
            var routeId = $("#routeId").val();
            var routeName = $("#routeName").val();
            var weekdayCount = $("#weekdayCount").val();
            var weekendCount = $("#weekendCount").val();
            var doubleDeckerCount = $("#doubleDeckerCount").val();
            var companyName = $("#companyName").val();
            var peekAlloc = $("#peekAlloc").val();
            var nPeekAlloc = $("#nPeekAlloc").val();
            var startStationId = $("#startStationId").val();
            var startStationName = $("#startStationName").val();
            var upFirstTime = $("#upFirstTime").val();
            var upLastTime = $("#upLastTime").val();
            var endStationId = $("#endStationId").val();
            var endStationName = $("#endStationName").val();
            var downFirstTime = $("#downFirstTime").val();
            var downLastTime = $("#downLastTime").val();
            var dataGatherBatchEnabled = $("input:checkbox[id='dataGatherBatchEnabled']").is(":checked");
            var dataGatherBatchSchedule = $("#dataGatherBatchSchedule").val();

            if (routeId == "") {
                alert("라우트ID를 입력해주세요.");
                return;
            }
            if (routeName == "") {
                alert("노선번호를 입력해주세요.");
                return;
            }
            if (weekdayCount == "") {
                alert("평일 운행 대수를 입력해주세요.");
                return;
            }
            if (weekendCount == "") {
                alert("주말 운행 대수를 입력해주세요.");
                return;
            }
            if (doubleDeckerCount == "") {
                alert("2층버스 운행 대수를 입력해주세요.");
                return;
            }
            if (companyName == "") {
                alert("운수업체를 입력해주세요.");
                return;
            }
            if (peekAlloc == "") {
                alert("평일최소 배차시간을 입력해주세요.");
                return;
            }
            if (nPeekAlloc == "") {
                alert("평일최대 배차시간을 입력해주세요.");
                return;
            }
            if (startStationId == "" || startStationName == "") {
                alert("기점정류소를 입력해주세요.");
                return;
            }
            if (upFirstTime == "") {
                alert("평일기점 첫차시간을 입력해주세요.");
                return;
            }
            if (upLastTime == "") {
                alert("평일기점 막차시간을 입력해주세요.");
                return;
            }
            if (endStationId == "" || endStationName == "") {
                alert("종점정류소를 입력해주세요.");
                return;
            }
            if (downFirstTime == "") {
                alert("평일종점 첫차시간을 입력해주세요.");
                return;
            }
            if (downLastTime == "") {
                alert("평일종점 막차시간을 입력해주세요.");
                return;
            }
            if (dataGatherBatchEnabled) {
                if (dataGatherBatchSchedule == "") {
                    alert("배치실행주기를 선택하세요.");
                    return;
                }
            }

            $.ajax({
                method: "POST",
                url: "/busMng/addRoute",
                data: {
                    routeId : routeId
                    , routeName: routeName
                    , weekdayCount: weekdayCount
                    , weekendCount: weekendCount
                    , doubleDeckerCount: doubleDeckerCount
                    , companyName: companyName
                    , peekAlloc: peekAlloc
                    , nPeekAlloc: nPeekAlloc
                    , startStationId: startStationId
                    , startStationName: startStationName
                    , upFirstTime: upFirstTime
                    , upLastTime: upLastTime
                    , endStationId: endStationId
                    , endStationName: endStationName
                    , downFirstTime: downFirstTime
                    , downLastTime: downLastTime
                    , dataGatherBatchEnabled: (dataGatherBatchEnabled) ? "Y" : "N"
                    , dataGatherBatchSchedule: dataGatherBatchSchedule
                }
            })
            .done(function(msg) {
                var confirmResult = confirm(msg + "\n추가로 등록하시겠습니까?");
                if (confirmResult) {
                    location.href = "/busMng/viewAddRoute";
                } else {
                    location.href = "/busMng/busMngList";
                }
            })
            .fail(function() {
                alert("error");
            });
        }

        function editRoute() {
            var routeId = $("#routeId").val();
            var routeName = $("#routeName").val();
            var weekdayCount = $("#weekdayCount").val();
            var weekendCount = $("#weekendCount").val();
            var doubleDeckerCount = $("#doubleDeckerCount").val();
            var companyName = $("#companyName").val();
            var peekAlloc = $("#peekAlloc").val();
            var nPeekAlloc = $("#nPeekAlloc").val();
            var startStationId = $("#startStationId").val();
            var startStationName = $("#startStationName").val();
            var upFirstTime = $("#upFirstTime").val();
            var upLastTime = $("#upLastTime").val();
            var endStationId = $("#endStationId").val();
            var endStationName = $("#endStationName").val();
            var downFirstTime = $("#downFirstTime").val();
            var downLastTime = $("#downLastTime").val();
            var dataGatherBatchEnabled = $("input:checkbox[id='dataGatherBatchEnabled']").is(":checked");
            var dataGatherBatchSchedule = $("#dataGatherBatchSchedule").val();

            if (routeId == "") {
                alert("라우트ID를 입력해주세요.");
                return;
            }
            if (routeName == "") {
                alert("노선번호를 입력해주세요.");
                return;
            }
            if (weekdayCount == "") {
                alert("평일 운행 대수를 입력해주세요.");
                return;
            }
            if (weekendCount == "") {
                alert("주말 운행 대수를 입력해주세요.");
                return;
            }
            if (doubleDeckerCount == "") {
                alert("2층버스 운행 대수를 입력해주세요.");
                return;
            }
            if (companyName == "") {
                alert("운수업체를 입력해주세요.");
                return;
            }
            if (peekAlloc == "") {
                alert("평일최소 배차시간을 입력해주세요.");
                return;
            }
            if (nPeekAlloc == "") {
                alert("평일최대 배차시간을 입력해주세요.");
                return;
            }
            if (startStationId == "" || startStationName == "") {
                alert("기점정류소를 입력해주세요.");
                return;
            }
            if (upFirstTime == "") {
                alert("평일기점 첫차시간을 입력해주세요.");
                return;
            }
            if (upLastTime == "") {
                alert("평일기점 막차시간을 입력해주세요.");
                return;
            }
            if (endStationId == "" || endStationName == "") {
                alert("종점정류소를 입력해주세요.");
                return;
            }
            if (downFirstTime == "") {
                alert("평일종점 첫차시간을 입력해주세요.");
                return;
            }
            if (downLastTime == "") {
                alert("평일종점 막차시간을 입력해주세요.");
                return;
            }
            if (dataGatherBatchEnabled) {
                if (dataGatherBatchSchedule == "") {
                    alert("배치실행주기를 선택하세요.");
                    return;
                }
            }

            $.ajax({
                method: "POST",
                url: "/busMng/editRoute",
                data: {
                    _id : _id
                    , routeId : routeId
                    , routeName: routeName
                    , weekdayCount: weekdayCount
                    , weekendCount: weekendCount
                    , doubleDeckerCount: doubleDeckerCount
                    , companyName: companyName
                    , peekAlloc: peekAlloc
                    , nPeekAlloc: nPeekAlloc
                    , startStationId: startStationId
                    , startStationName: startStationName
                    , upFirstTime: upFirstTime
                    , upLastTime: upLastTime
                    , endStationId: endStationId
                    , endStationName: endStationName
                    , downFirstTime: downFirstTime
                    , downLastTime: downLastTime
                    , dataGatherBatchEnabled: (dataGatherBatchEnabled) ? "Y" : "N"
                    , dataGatherBatchSchedule: dataGatherBatchSchedule
                }
            })
            .done(function(msg) {
                alert(msg);
                location.href = "/busMng/busMngInfo?_id=" + _id;
            })
            .fail(function() {
                alert("error");
            });
        }

        function delRoute() {
            $.ajax({
                method: "POST",
                url: "/busMng/delRoute",
                data: {_id : _id}
            })
            .done(function(msg) {
                alert(msg);
                location.href = "/busMng/busMngList";
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
    <jsp:param name="currentMenu" value="5"/>
</jsp:include>
<div class="container bottomPadding">
    <div class="rounded-lg alert-secondary doc-title" role="alert" style="margin-top:15px;">
        <div class="d-flex mb-3 justify-content-center">
            <div class="p-2">노선 추가</div>
            <div class="ml-auto p-2"><button type="button" class="btn btn-sm btn-secondary" onclick="goBack()">뒤로</button></div>
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
            <label>라우트ID</label>
            <input type="text" class="form-control" id="routeId" placeholder="" data-input-value="<%= routeId %>">
        </div>
        <div class="form-group">
            <label>노선번호</label>
            <input type="text" class="form-control" id="routeName" placeholder="" data-input-value="<%= routeName %>">
        </div>
        <div class="form-group">
            <label>평일 운행 대수</label>
            <input type="text" class="form-control" id="weekdayCount" placeholder="" data-input-value="<%= weekdayCount %>">
        </div>
        <div class="form-group">
            <label>주말 운행 대수</label>
            <input type="text" class="form-control" id="weekendCount" placeholder="" data-input-value="<%= weekendCount %>">
        </div>
        <div class="form-group">
            <label>2층버스 운행 대수</label>
            <input type="text" class="form-control" id="doubleDeckerCount" placeholder="" data-input-value="<%= doubleDeckerCount %>">
        </div>
        <div class="form-group">
            <label>운수업체</label>
            <input type="text" class="form-control" id="companyName" placeholder="" data-input-value="<%= companyName %>">
        </div>
        <div class="form-group">
            <label>평일최소 배차시간</label>
            <input type="text" class="form-control" id="peekAlloc" placeholder="" data-input-value="<%= peekAlloc %>">
        </div>
        <div class="form-group">
            <label>평일최대 배차시간</label>
            <input type="text" class="form-control" id="nPeekAlloc" placeholder="" data-input-value="<%= nPeekAlloc %>">
        </div>
        <div class="form-group">
            <label>기점정류소</label>
            <input type="text" class="form-control" id="startStationId" placeholder="ID" data-input-value="<%= startStationId %>">
            <input type="text" class="form-control" id="startStationName" placeholder="NAME" data-input-value="<%= startStationName %>">
        </div>
        <div class="form-group">
            <label>평일기점 첫차시간</label>
            <input type="text" class="form-control" id="upFirstTime" placeholder="00:00" data-input-value="<%= upFirstTime %>">
        </div>
        <div class="form-group">
            <label>평일기점 막차시간</label>
            <input type="text" class="form-control" id="upLastTime" placeholder="00:00" data-input-value="<%= upLastTime %>">
        </div>
        <div class="form-group">
            <label>종점정류소</label>
            <input type="text" class="form-control" id="endStationId" placeholder="ID" data-input-value="<%= endStationId %>">
            <input type="text" class="form-control" id="endStationName" placeholder="NAME" data-input-value="<%= endStationName %>">
        </div>
        <div class="form-group">
            <label>평일종점 첫차시간</label>
            <input type="text" class="form-control" id="downFirstTime" placeholder="00:00" data-input-value="<%= downFirstTime %>">
        </div>
        <div class="form-group">
            <label>평일종점 막차시간</label>
            <input type="text" class="form-control" id="downLastTime" placeholder="00:00" data-input-value="<%= downLastTime %>">
        </div>
        <div class="form-group">
            <label>데이터 수집 배치</label>
            <div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <input type="checkbox" id="dataGatherBatchEnabled" data-checked-YN="<%= dataGatherBatchUseYnValue %>" aria-label="데이터 수집 배치를 실행하려면 체크하세요.">
                        </div>
                    </div>
                    <select class="custom-select" id="dataGatherBatchSchedule" data-input-value="<%= dataGatherBatchScheduleValue %>">
                        <option value="">선택</option>
                        <option value="1">1분</option>
                        <option value="2">5분</option>
                        <option value="3">15분</option>
                        <option value="4">1시간</option>
                    </select>
                </div>
        </div>

        <div class="contentAlignRight bottomMargin topMargin">
            <% if("ADD".equals(pageMode)) { %>
            <button type="button" class="btn btn-sm btn-primary" onclick="addRoute()">추가</button>
            <% } else { %>
            <button type="button" class="btn btn-sm btn-danger" onclick="delRoute()">삭제</button>
            <button type="button" class="btn btn-sm btn-primary" onclick="editRoute()">수정</button>
            <% } %>
        </div>
    </form>
</div>
</body>
</html>