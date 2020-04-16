package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.domain.Batch;
import com.dazzilove.bustrace.domain.BatchParam;
import com.dazzilove.bustrace.task.BusLocationTask;
import com.dazzilove.bustrace.task.PlateRunHistoryTask;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class BatchController {

    @Autowired
    BusLocationTask busLocationTask;

    @Autowired
    PlateRunHistoryTask plateRunHistoryTask;

    @RequestMapping("/batchMng/list")
    public ModelAndView getBusList() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("batchMng/batchList");

        mav.addObject("batchList", getBatchList());

        return mav;
    }

    @RequestMapping("/batchMng/scheduleStationSaveTask")
    @ResponseBody
    public String runScheduleStationSaveTask() {
        busLocationTask.scheduleStationSaveTask();
        return "ScheduleStationSaveTask is done";
    }

    @RequestMapping("/batchMng/scheduleLocationSaveTask")
    @ResponseBody
    public String runScheduleLocationSaveTask(ServletRequest request) throws Exception {
        String routeId = StringUtils.defaultString(request.getParameter("routeId"), "").trim();
        String startDate = StringUtils.defaultString(request.getParameter("startDate"), "").trim();
        String endDate = StringUtils.defaultString(request.getParameter("endDate"), "").trim();

        if ("".equals(routeId) && "".equals(startDate) && "".equals(endDate)) {
            return "ScheduleStationSaveTask is failed. Check Parameters.";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("ScheduleStationSaveTask is done.");
        sb.append("Params - routeId=" + routeId + ", startDate=" + startDate + ", endDate="+ endDate);
        sb.append(", start : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        busLocationTask.scheduleLocationSave(routeId, startDate, endDate);

        sb.append(", end : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return sb.toString();
    }

    @RequestMapping("/batchMng/schedulePlateRunHistorySaveTask")
    @ResponseBody
    public String runSchedulePlateRunHistorySaveTask(ServletRequest request) throws Exception {
        String startDate = StringUtils.defaultString(request.getParameter("startDate"), "").trim();
        String endDate = StringUtils.defaultString(request.getParameter("endDate"), "").trim();

        if ("".equals(startDate) && "".equals(endDate)) {
            return "SchedulePlateRunHistorySaveTask is failed. Check Parameters.";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("SchedulePlateRunHistorySaveTask is done.");
        sb.append("Params - startDate=" + startDate + ", endDate="+ endDate);
        sb.append(", start : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        plateRunHistoryTask.schedulePlateRunHistorySave(startDate, endDate);

        sb.append(", end : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return sb.toString();
    }

    private Object getBatchList() {
        GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();

        List<Batch> list = new ArrayList<>();
        addScheduleStationSaveTask(list, now);
        addScheduleLocationSaveTask(list, now);
        addSchedulePlateRunHistorySaveTaskTest(list, now);
        return list;
    }

    private void addScheduleStationSaveTask(List<Batch> list, GregorianCalendar now) {
        Batch batch = new Batch();
        batch.setName("노선별 정거장 정보 수집 배치");
        batch.setSchedule("0 1 * * * ?");
        batch.setMethod("BusLocationTask.scheduleStationSaveTask");
        batch.setUrl("/batchMng/scheduleStationSaveTask");
        list.add(batch);
    }

    private void addScheduleLocationSaveTask(List<Batch> list, GregorianCalendar now) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Batch batch = new Batch();
        batch.setName("노선별 과거 데이터 정리 배치");
        batch.setSchedule("0 1 0 * * ?");
        batch.setMethod("BusLocationTask.scheduleLocationSaveTask");
        batch.setUrl("/batchMng/scheduleLocationSaveTask");

        List<BatchParam> batchParams = new ArrayList<>();
        BatchParam batchParam = new BatchParam();
        batchParam.setName("startDate");
        batchParam.setInputType(BatchParam.INPUT_TYPE_TEXT);
        batchParam.setDefaultValue(sdf.format(now.getTime()));
        batchParams.add(batchParam);
        batchParam = new BatchParam();
        batchParam.setName("endDate");
        batchParam.setDefaultValue(sdf.format(now.getTime()));
        batchParam.setInputType(BatchParam.INPUT_TYPE_TEXT);
        batchParams.add(batchParam);
        batchParam = new BatchParam();
        batchParam.setName("routeId");
        batchParam.setDefaultValue("224000047");
        batchParam.setInputType(BatchParam.INPUT_TYPE_TEXT);
        batchParams.add(batchParam);

        batch.setBatchParams(batchParams);

        list.add(batch);
    }

    private void addSchedulePlateRunHistorySaveTaskTest(List<Batch> list, GregorianCalendar now) {
        Batch batch = new Batch();
        batch.setName("일별 Plate 운행 순서별 운행 리스트 저장 배치");
        batch.setSchedule("0 10 2 * * ?");
        batch.setMethod("PlateRunHistoryTask.schedulePlateRunHistorySaveTask");
        batch.setUrl("/batchMng/schedulePlateRunHistorySaveTask");
        list.add(batch);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        List<BatchParam> batchParams = new ArrayList<>();
        batch.setBatchParams(batchParams);

        BatchParam batchParam = new BatchParam();
        batchParam.setName("startDate");
        batchParam.setInputType(BatchParam.INPUT_TYPE_TEXT);
        batchParam.setDefaultValue(sdf.format(now.getTime()));
        batchParams.add(batchParam);

        batchParam = new BatchParam();
        batchParam.setName("endDate");
        batchParam.setDefaultValue(sdf.format(now.getTime()));
        batchParam.setInputType(BatchParam.INPUT_TYPE_TEXT);
        batchParams.add(batchParam);
    }
}
