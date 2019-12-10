package com.dazzilove.bustrace.controller;

import com.dazzilove.bustrace.service.BusRouteService;
import com.dazzilove.bustrace.service.wsdl.BusRoute;
import com.dazzilove.bustrace.service.wsdl.BusRouteInfo;
import com.dazzilove.bustrace.service.wsdl.BusRouteLine;
import com.dazzilove.bustrace.service.wsdl.BusRouteStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RequestMapping("/bus")
@RestController
public class BusRouteController {

    @Autowired
    private BusRouteService busRouteService;

    /**
     * 노선정보 목록 조회
     * @param routeName 노선번호
     * @return
     */
    @GetMapping(value = "/routes")
    public ResponseEntity<List<BusRoute>> getBusRouteList(@RequestParam(value = "routeName", required = false) String routeName) {
        return new ResponseEntity<>(busRouteService.getBusRouteList(routeName), HttpStatus.OK);
    }

    /**
     * 운행지역별 노선 정보 목록 조회
     * @param areaId    운행지역ID
     * @param routeName 노선번호
     * @return
     */
    @GetMapping(value = "/routes/area/{areaId}")
    public ResponseEntity<List<BusRoute>> getAreaBusRouteList(
            @PathVariable(value = "areaId") String areaId,
            @RequestParam(value = "routeName", required =  false) String routeName) {
        return new ResponseEntity<>(busRouteService.getAreaBusRouteList(areaId, routeName), HttpStatus.OK);
    }

    /**
     * 경유 정류소 목록 조회
     * @param routeId   노선ID
     * @return
     */
    @GetMapping(value = "/routes/{routeId}/stations")
    public ResponseEntity<List<BusRouteStation>> getBusRouteStationList(@PathVariable(value = "routeId") String routeId) {
        return new ResponseEntity<>(busRouteService.getBusRouteStationList(routeId), HttpStatus.OK);
    }

    /**
     * 노선 정보 항목 조회
     * @param routeId   노선ID
     * @return
     */
    @GetMapping(value = "/routes/{routeId}/infos")
    public ResponseEntity<BusRouteInfo> getBusRouteInfoItem(@PathVariable(value = "routeId") String routeId) {
        return new ResponseEntity<>(busRouteService.getBusRouteInfoItem(routeId), HttpStatus.OK);
    }

    /**
     * 노선 형상 정보 목록 조회
     * @param routeId   노선ID
     * @return
     */
    @GetMapping(value = "/routes/{routeId}/lines")
    public ResponseEntity<List<BusRouteLine>> getBusRouteLineList(@PathVariable(value = "routeId") String routeId) {
        return new ResponseEntity<>(busRouteService.getBusRouteLineList(routeId), HttpStatus.OK);
    }
}
