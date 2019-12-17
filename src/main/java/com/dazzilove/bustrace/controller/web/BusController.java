package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.domain.Bus;
import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.BusLocationParam;
import com.dazzilove.bustrace.service.BusLocationService;
import com.dazzilove.bustrace.service.BusRouteService;
import com.dazzilove.bustrace.service.wsdl.BusRouteInfo;
import com.dazzilove.bustrace.service.ws.BusRouteStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class BusController {

    @Autowired
    private BusRouteService busRouteService;

    @Autowired
    private BusLocationService busLocationService;

    @RequestMapping("/busList")
    public ModelAndView getBusList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busList");

        List<Bus> busList = new ArrayList<>();

        Bus bus = new Bus("3200", "224000019");
        busList.add(bus);
        bus = new Bus("3300", "224000047");
        busList.add(bus);
        bus = new Bus("3400", "224000050");
        busList.add(bus);
        bus = new Bus("3500", "224000054");
        busList.add(bus);
        bus = new Bus("5602", "216000047");
        busList.add(bus);
        bus = new Bus("5604", "224000040");
        busList.add(bus);
        bus = new Bus("32", "224000019");
        busList.add(bus);
        bus = new Bus("81", "208000009");
        busList.add(bus);
        bus = new Bus("30-2", "224000014");
        busList.add(bus);

        mav.addObject("busList", busList);


        return mav;
    }

    @RequestMapping("/routeInfo")
    public ModelAndView getRouteInfo(ServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("routeInfo");

        String routeId = request.getParameter("routeId");
        String startCreatedAt = request.getParameter("startCreatedAt");
        String endCreatedAt = request.getParameter("endCreatedAt");

        startCreatedAt = (startCreatedAt == null) ? "" : startCreatedAt.trim();
        endCreatedAt = (endCreatedAt == null) ? "" : endCreatedAt.trim();

        LocalDateTime startCreatedAtLdt = LocalDateTime.now();
        LocalDateTime endCreatedAtLdt = LocalDateTime.now();
        if ("".equals(startCreatedAt.trim()) || "".equals(endCreatedAt.trim())) {
            LocalDate localDate = LocalDate.now();
            startCreatedAtLdt = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 0, 0, 0);
            endCreatedAtLdt = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 23, 59, 59);
        } else {
            String year = startCreatedAt.substring(0, 4);
            String month = startCreatedAt.substring(4, 6);
            String day = startCreatedAt.substring(6, 8);
            month = (month.indexOf("0") > -1) ? month.substring(1,1) : month;
            day = (day.indexOf("0") > -1) ? day.substring(1,1) : day;
            startCreatedAtLdt = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);

            year = endCreatedAt.substring(0, 4);
            month = endCreatedAt.substring(4, 6);
            day = endCreatedAt.substring(6, 8);
            month = (month.indexOf("0") > -1) ? month.substring(1,1) : month;
            day = (day.indexOf("0") > -1) ? day.substring(1,1) : day;
            endCreatedAtLdt = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 23, 59, 59);
        }

        BusLocationParam busLocationParam = new BusLocationParam();
        busLocationParam.setRouteId(routeId);
        busLocationParam.setStartCreatedAt(startCreatedAtLdt);
        busLocationParam.setEndCreatedAt(endCreatedAtLdt);

        BusRouteInfo busRouteInfo = busRouteService.getBusRouteInfoItem(routeId);
        mav.addObject("busRouteInfo", busRouteInfo);

        List<com.dazzilove.bustrace.service.wsdl.BusRouteStation> busRouteStationListTemp = busRouteService.getBusRouteStationList(routeId);
        List<BusRouteStation> busRouteStationList = new ArrayList<>();
        if (!busRouteStationListTemp.isEmpty()) {
            busRouteStationListTemp
                    .stream()
                    .forEach(busRouteStation -> {
                        BusRouteStation temp = new BusRouteStation();
                        temp.setCenterYn(busRouteStation.getCenterYn());
                        temp.setDistrictCd(busRouteStation.getDistrictCd());
                        temp.setMobileNo(busRouteStation.getMobileNo());
                        temp.setRegionName(busRouteStation.getRegionName());
                        temp.setStationId(busRouteStation.getStationId());
                        temp.setStationName(busRouteStation.getStationName());
                        temp.setStationSeq(busRouteStation.getStationSeq());
                        temp.setTurnYn(busRouteStation.getTurnYn());

                        busRouteStationList.add(temp);
                    });
            Map<String, String> createdAtMap = new HashMap<>();
            List<BusLocation> busLocationList = busLocationService.getBusLoactions(busLocationParam);
            if (!busLocationList.isEmpty()) {
                busLocationList.stream()
                        .forEach(busLocation -> {
                            String stationId = busLocation.getStationId();

                            busRouteStationList
                                    .stream()
                                    .filter(busRouteStation -> stationId.equals(busRouteStation.getStationId()))
                                    .forEach(busRouteStation -> {
                                        busRouteStation.getBusLocationList().add(busLocation);
                                        String createdAt = busLocation.getFormatedCreatedAt();
                                        createdAtMap.put(createdAt, createdAt);
                                    });

                        });
            }
            mav.addObject("busRouteStationList", busRouteStationList);

            List<String> createdAtList = new ArrayList<>();
            createdAtMap.forEach((key, value) -> {
                createdAtList.add(key);
            });
            createdAtList.sort((a, b) -> a.compareTo(b));
            mav.addObject("createdAtList", createdAtList);
        }


        return mav;
    }

}