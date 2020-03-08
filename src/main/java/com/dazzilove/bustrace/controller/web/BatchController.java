package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.task.BusLocationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BatchController {

    @Autowired
    BusLocationTask busLocationTask;

    @RequestMapping("/batch/scheduleStationSaveTask")
    @ResponseBody
    public String runScheduleStationSaveTask() {
        busLocationTask.scheduleStationSaveTask();
        return "ScheduleStationSaveTask is done";
    }
}
