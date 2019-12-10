package com.dazzilove.bustrace.controller;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.BusLocationResult;
import com.dazzilove.bustrace.service.BusLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusLocationController {

    @Autowired
    BusLocationService busLocationService;

    @GetMapping(value = "/location/{routeId}")
    public BusLocationResult getBusLocation(@PathVariable(value = "routeId") String routeId) throws Exception {
        return busLocationService.getBusLocation(routeId);
    }
}
