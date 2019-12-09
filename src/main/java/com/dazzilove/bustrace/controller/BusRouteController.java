package com.dazzilove.bustrace.controller;

import com.dazzilove.bustrace.service.BusRouteService;
import com.dazzilove.bustrace.service.wsdl.BusRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/bus")
@RestController
public class BusRouteController {

    @Autowired
    private BusRouteService busRouteService;

    @GetMapping(value = "/routes")
    public ResponseEntity<List<BusRoute>> getBusRouteList(@RequestParam(value = "keyword", required = false) String keyword) {

        List<BusRoute> list = busRouteService.getBusRoutes(keyword);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
