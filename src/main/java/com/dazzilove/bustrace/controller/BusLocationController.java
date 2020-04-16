package com.dazzilove.bustrace.controller;

import com.dazzilove.bustrace.service.ws.BusLocationList;
import com.dazzilove.bustrace.service.ws.BusLocationListResponse;
import com.dazzilove.bustrace.service.BusLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 한글테스트
 * ABCDEFG
 */
@RestController
@RequestMapping("/bus")
public class BusLocationController {

    @Autowired
    BusLocationService busLocationService;

    @GetMapping(value = "/location/{routeId}")
    public ResponseEntity<List<BusLocationList>> getBusLocation(@PathVariable(value = "routeId") String routeId) throws Exception {
        return new ResponseEntity<>(busLocationService.getBusLocation(routeId), HttpStatus.OK);
    }

    @PostMapping(value = "/location/{routeId}")
    public ResponseEntity<?> addBusLocation(@PathVariable(value = "routeId") String routeId) throws Exception {
        busLocationService.addBusLocationList(routeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
