package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.domain.Bus;
import com.dazzilove.bustrace.service.BusLocationService;
import com.dazzilove.bustrace.service.BusRouteService;
import com.dazzilove.bustrace.service.wsdl.BusRouteInfo;
import com.dazzilove.bustrace.service.wsdl.BusRouteStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;

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

        BusRouteInfo busRouteInfo = busRouteService.getBusRouteInfoItem(routeId);
        mav.addObject("busRouteInfo", busRouteInfo);

        List<BusRouteStation> busRouteStationList = busRouteService.getBusRouteStationList(routeId);
        mav.addObject("busRouteStationList", busRouteStationList);

        return mav;
    }

}
