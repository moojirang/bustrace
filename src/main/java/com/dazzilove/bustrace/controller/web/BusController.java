package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.domain.Bus;
import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.LocationParam;
import com.dazzilove.bustrace.domain.Route;
import com.dazzilove.bustrace.service.BusLocationService;
import com.dazzilove.bustrace.service.BusRouteService;
import com.dazzilove.bustrace.service.web.RouteService;
import com.dazzilove.bustrace.service.ws.BusRouteStation;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private RouteService routeService;

    @RequestMapping("/busList")
    public ModelAndView getBusList() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busList");

        List<Route> routeList = routeService.getRoutes();
        mav.addObject("routeList", routeList);

        return mav;
    }

    @RequestMapping("/routeInfo")
    public ModelAndView getRouteInfo(ServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("routeInfo");

        String _id = request.getParameter("_id");
        String startCreatedAt = StringUtils.defaultString(request.getParameter("startCreatedAt"), "").trim();
        String endCreatedAt = StringUtils.defaultString(request.getParameter("endCreatedAt"), "").trim();

        startCreatedAt = startCreatedAt.replace("-", "");
        endCreatedAt = endCreatedAt.replace("-", "");

        LocalDateTime startCreatedAtLdt = LocalDateTime.now();
        LocalDateTime endCreatedAtLdt = LocalDateTime.now();
        if ("".equals(startCreatedAt) || "".equals(endCreatedAt)) {
            LocalDate localDate = LocalDate.now();
            startCreatedAtLdt = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 0, 0, 0);
            endCreatedAtLdt = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 23, 59, 59);
        } else {
            String year = startCreatedAt.substring(0, 4);
            String month = startCreatedAt.substring(4, 6);
            String day = startCreatedAt.substring(6, 8);
            month = (month.indexOf("0") == 0) ? month.substring(1,2) : month;
            day = (day.indexOf("0") == 0) ? day.substring(1,2) : day;
            startCreatedAtLdt = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);

            year = endCreatedAt.substring(0, 4);
            month = endCreatedAt.substring(4, 6);
            day = endCreatedAt.substring(6, 8);
            month = (month.indexOf("0") == 0) ? month.substring(1,2) : month;
            day = (day.indexOf("0") == 0) ? day.substring(1,2) : day;
            endCreatedAtLdt = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 23, 59, 59);
        }

        Route route = routeService.getOnlyRouteInfo(_id);
        mav.addObject("route", route);

        String routeId = route.getRouteId();

        LocationParam locationParam = new LocationParam();
        locationParam.setRouteId(routeId);
        locationParam.setStartCreatedAt(startCreatedAtLdt);
        locationParam.setEndCreatedAt(endCreatedAtLdt);

        List<com.dazzilove.bustrace.service.wsdl.BusRouteStation> busRouteStationListTemp = busRouteService.getBusRouteStationList(routeId);
        List<BusRouteStation> busRouteStationList = new ArrayList<>();
        if (busRouteStationListTemp != null && !busRouteStationListTemp.isEmpty()) {
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
            Map<String, Bus> plateNoMap = new HashMap<>();
            List<BusLocation> busLocationList = busLocationService.getBusLoactions(locationParam);
            if (!busLocationList.isEmpty()) {
                busLocationList.stream()
                        .forEach(busLocation -> {
                            String stationId = busLocation.getStationId();

                            busRouteStationList
                                    .stream()
                                    .filter(busRouteStation -> stationId.equals(busRouteStation.getStationId()))
                                    .forEach(busRouteStation -> {
                                        busRouteStation.getBusLocationList().add(busLocation);

                                        String plateNo = busLocation.getPlateNo();
                                        Bus bus = new Bus("", "", plateNo, busLocation.getPlateType());
                                        plateNoMap.put(plateNo, bus);
                                    });

                        });
            }
            // TODO = 정거장별로 시간순으로 정렬 후 가장 마지막 시간의 중복데이터를 merge하도록 수정 할 것
            for(BusRouteStation busRouteStation: busRouteStationList) {
                List<BusLocation> busLocations = busRouteStation.getBusLocationList();
                if (!busLocations.isEmpty()) {
                    Map<String, BusLocation> busLocationMap = new HashMap<>();

                    busLocations.forEach(busLocation -> {
                        busLocationMap.put(busLocation.getFormatedCreatedAtByHalftime() + "/" + busLocation.getPlateNo(), busLocation);
                    });
                    List<BusLocation> mergedBusLocatios = new ArrayList<>();
                    List<BusLocation> zeroRemainSeatBusLocatios = new ArrayList<>();
                    busLocationMap.forEach((key, value) -> {
                        mergedBusLocatios.add(value);
                        String remainSeatCnt = StringUtils.defaultString(value.getRemainSeatCnt(), "0");
                        if ("0".equals(remainSeatCnt)) {
                            zeroRemainSeatBusLocatios.add(value);
                        }
                    });
                    mergedBusLocatios.sort((busLocation1, busLocation2) -> busLocation1.createdAtDiff(busLocation2));
                    busRouteStation.setBusLocationList(mergedBusLocatios);
                    busRouteStation.setRemainSeatCntZeroCnt(zeroRemainSeatBusLocatios.size());
                }

            }
            mav.addObject("busRouteStationList", busRouteStationList);





            List<Bus> plateNoList = new ArrayList<>();
            plateNoMap.forEach((key, value) -> {
                plateNoList.add(value);
            });
            plateNoList.sort((bus1, bus2) -> bus1.plateNoDiff(bus2));
            mav.addObject("plateNoList", plateNoList);
        }


        return mav;
    }

}
