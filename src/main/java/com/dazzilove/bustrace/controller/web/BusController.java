package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.domain.*;
import com.dazzilove.bustrace.service.BusLocationService;
import com.dazzilove.bustrace.service.BusRouteService;
import com.dazzilove.bustrace.service.web.LocationService;
import com.dazzilove.bustrace.service.web.RouteService;
import com.dazzilove.bustrace.service.web.SpecialMessageService;
import com.dazzilove.bustrace.service.wsdl.BusRouteStation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BusController {

    @Autowired
    private BusRouteService busRouteService;

    @Autowired
    private BusLocationService busLocationService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private SpecialMessageService specialMessageService;

//    @RequestMapping("/busList")
    public ModelAndView getBusList() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busList");

        List<Route> routeList = routeService.getRoutes();
        mav.addObject("routeList", routeList);

        return mav;
    }

//    @RequestMapping("/routeInfo")
    public ModelAndView getRouteInfo(ServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("routeInfo");

        String _id = request.getParameter("_id");
        String createdAt = StringUtils.defaultString(request.getParameter("createdAt"), "").trim().replace("-", "");

        LocalDateTime startCreatedAtLdt = LocalDateTime.now();
        LocalDateTime endCreatedAtLdt = LocalDateTime.now();
        if ("".equals(createdAt)) {
            LocalDate localDate = LocalDate.now();
            startCreatedAtLdt = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 0, 0, 0);
            endCreatedAtLdt = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 23, 59, 59);
        } else {
            String year = createdAt.substring(0, 4);
            String month = createdAt.substring(4, 6);
            String day = createdAt.substring(6, 8);
            month = (month.indexOf("0") == 0) ? month.substring(1,2) : month;
            day = (day.indexOf("0") == 0) ? day.substring(1,2) : day;
            startCreatedAtLdt = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
            endCreatedAtLdt = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 23, 59, 59);
        }

        Route route = routeService.getOnlyRouteInfo(_id);
        mav.addObject("route", route);

        LocationParam locationParam = new LocationParam();
        locationParam.setRouteId(route.getRouteId());
        locationParam.setStartCreatedAt(startCreatedAtLdt);
        locationParam.setEndCreatedAt(endCreatedAtLdt);

        List<Station> stationList = getStationListIncludeLocations(locationParam);
        mav.addObject("stationList", stationList);

        mav.addObject("plateNoList", getPlateNoList(stationList));

        mav.addObject("specialMessageList", getSpecialMessageList(route.getRouteId()));

        return mav;
    }

    private List<SpecialMessage> getSpecialMessageList(String routeId) {
        return specialMessageService.getSpecialMessageList(routeId);
    }

    private List<Station> getStationListIncludeLocations(LocationParam locationParam) throws Exception {
        List<BusRouteStation> busRouteStations = busRouteService.getBusRouteStationList(locationParam.getRouteId());
        if (busRouteStations == null)
            busRouteStations = new ArrayList<>();

        List<Station> stations = new ArrayList<>();
        for (BusRouteStation busRouteStation: busRouteStations) {
            Station station = new Station();
            station.setStationId(busRouteStation.getStationId());
            station.setStationName(busRouteStation.getStationName());
            station.setStationSeq(busRouteStation.getStationSeq());
            station.setTurnYn(busRouteStation.getTurnYn());
            station.setCenterYn(busRouteStation.getCenterYn());
            station.setDistrictCd(busRouteStation.getDistrictCd());
            station.setMobileNo(busRouteStation.getMobileNo());
            station.setRegionName(busRouteStation.getRegionName());
            stations.add(station);
        }

        if (stations == null)
            stations = new ArrayList<>();
        stations.stream()
                .forEach(station -> {
                    LocationParam tempLocationParam = new LocationParam();
                    tempLocationParam.setRouteId(locationParam.getRouteId());
                    tempLocationParam.setStationId(station.getStationId().toString());
                    tempLocationParam.setStartCreatedAt(locationParam.getStartCreatedAt());
                    tempLocationParam.setEndCreatedAt(locationParam.getEndCreatedAt());
                    try {
                        LocalDateTime createdAtDate = locationParam.getStartCreatedAt();
                        LocalDateTime nowDate = LocalDateTime.now();
                        String createdAt = String.valueOf(createdAtDate.getYear()) + String.valueOf(createdAtDate.getMonthValue()) + String.valueOf(createdAtDate.getDayOfMonth());
                        String now = String.valueOf(nowDate.getYear()) + String.valueOf(nowDate.getMonthValue()) + String.valueOf(nowDate.getDayOfMonth());
                        if (createdAt.equals(now)) {
//                            station.getLocations().addAll(getTodayLocations(tempLocationParam));
                            station.getLocations().add(getTodayLocation(tempLocationParam));
                        } else {
//                            station.getLocations().addAll(getPreviousPeriodLocations(tempLocationParam));
                            station.getLocations().add(getPreviousPeriodLastLocation(tempLocationParam));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        return stations;
    }

    private Location getTodayLocation(LocationParam locationParam) throws Exception {
        BusLocation busLocation = busLocationService.getLastBusLocation(locationParam);
        if (busLocation == null) {
            return new Location();
        }

        Location location = new Location();
        location.setRouteId(busLocation.getRouteId());
        location.setStationId(busLocation.getStationId());
        location.setStationSeq(busLocation.getStationSeq());
        location.setEndBus(busLocation.getEndBus());
        location.setLowPlate(busLocation.getLowPlate());
        location.setPlateNo(busLocation.getPlateNo());
        location.setPlateType(busLocation.getPlateType());
        location.setRemainSeatCnt(busLocation.getRemainSeatCnt());
        location.setCreatedAt(busLocation.getCreatedAt());
        return location;
    }

    private Location getPreviousPeriodLastLocation(LocationParam locationParam) throws Exception {
        return locationService.getLastLocation(locationParam);
    }

//    private List<Location> getTodayLocations(LocationParam locationParam) throws Exception {
//        List<Location> locations = new ArrayList<>();
//
//        List<BusLocation> busLocations = busLocationService.getBusLoactions(locationParam);
//        busLocations.stream().sorted((busLocation1, busLocation2) -> busLocation2.createdAtDiff(busLocation1));    // 시간 역순으로 정렬
//
//        final long baseSecondsDiff = 15*60;
//        LocalDateTime creatdAtBefore = LocalDateTime.now();
//        for(BusLocation busLocation: busLocations) {
//            LocalDateTime createdAt = busLocation.getCreatedAt();
//            long secondsDiff = Duration.between(createdAt, creatdAtBefore).getSeconds();
//            if (secondsDiff >= baseSecondsDiff) {
//                Location location = new Location();
//                location.setRouteId(busLocation.getRouteId());
//                location.setStationId(busLocation.getStationId());
//                location.setStationSeq(busLocation.getStationSeq());
//                location.setEndBus(busLocation.getEndBus());
//                location.setLowPlate(busLocation.getLowPlate());
//                location.setPlateNo(busLocation.getPlateNo());
//                location.setPlateType(busLocation.getPlateType());
//                location.setRemainSeatCnt(busLocation.getRemainSeatCnt());
//                location.setCreatedAt(busLocation.getCreatedAt());
//                locations.add(location);
//            }
//            creatdAtBefore = createdAt;
//        }
//
//        return locations.stream().sorted((location1, location2) -> location1.createdAtDiff(location2)).collect(Collectors.toList());
//    }
//
//    private List<Location> getPreviousPeriodLocations(LocationParam locationParam) throws Exception {
//        return locationService.getLocations(locationParam);
//    }

    private List<Bus> getPlateNoList(List<Station> stationList) {
        List<Bus> plateNoList = new ArrayList<>();

        Map<String, Bus> plateNoMap = new HashMap<>();
        stationList.stream()
                .forEach(station -> {
                    List<Location> locations = station.getLocations();
                    locations.stream()
                            .forEach(location -> {
                                String plateNo = location.getPlateNo();
                                Bus bus = new Bus("", "", plateNo, location.getPlateType());
                                plateNoMap.put(plateNo, bus);
                            });
                });

        plateNoMap.forEach((key, value) -> {
            plateNoList.add(value);
        });

        if (plateNoList != null && plateNoList.size() > 1)
            plateNoList.sort((bus1, bus2) -> bus1.plateNoDiff(bus2));

        return plateNoList;
    }

//    private List<BusRouteStation> getBusRouteStationList(LocationParam locationParam) {
//        String routeId = locationParam.getRouteId();
//
//        List<com.dazzilove.bustrace.service.wsdl.BusRouteStation> busRouteStationListTemp = busRouteService.getBusRouteStationList(routeId);
//        List<BusRouteStation> busRouteStationList = new ArrayList<>();
//        if (busRouteStationListTemp != null && !busRouteStationListTemp.isEmpty()) {
//            busRouteStationListTemp
//                    .stream()
//                    .forEach(busRouteStation -> {
//                        BusRouteStation temp = new BusRouteStation();
//                        temp.setCenterYn(busRouteStation.getCenterYn());
//                        temp.setDistrictCd(busRouteStation.getDistrictCd());
//                        temp.setMobileNo(busRouteStation.getMobileNo());
//                        temp.setRegionName(busRouteStation.getRegionName());
//                        temp.setStationId(busRouteStation.getStationId());
//                        temp.setStationName(busRouteStation.getStationName());
//                        temp.setStationSeq(busRouteStation.getStationSeq());
//                        temp.setTurnYn(busRouteStation.getTurnYn());
//
//                        busRouteStationList.add(temp);
//                    });
//            List<BusLocation> busLocationList = busLocationService.getBusLoactions(locationParam);
//            if (!busLocationList.isEmpty()) {
//                busLocationList.stream()
//                        .forEach(busLocation -> {
//                            String stationId = busLocation.getStationId();
//
//                            busRouteStationList
//                                    .stream()
//                                    .filter(busRouteStation -> stationId.equals(busRouteStation.getStationId()))
//                                    .forEach(busRouteStation -> {
//                                        busRouteStation.getBusLocationList().add(busLocation);
//
//                                        String plateNo = busLocation.getPlateNo();
//                                        Bus bus = new Bus("", "", plateNo, busLocation.getPlateType());
//                                        plateNoMap.put(plateNo, bus);
//                                    });
//
//                        });
//            }
//            // TODO = 정거장별로 시간순으로 정렬 후 가장 마지막 시간의 중복데이터를 merge하도록 수정 할 것
//            for(BusRouteStation busRouteStation: busRouteStationList) {
//                List<BusLocation> busLocations = busRouteStation.getBusLocationList();
//                if (!busLocations.isEmpty()) {
//                    Map<String, BusLocation> busLocationMap = new HashMap<>();
//
//                    busLocations.forEach(busLocation -> {
//                        busLocationMap.put(busLocation.getFormatedCreatedAtByHalftime() + "/" + busLocation.getPlateNo(), busLocation);
//                    });
//                    List<BusLocation> mergedBusLocatios = new ArrayList<>();
//                    List<BusLocation> zeroRemainSeatBusLocatios = new ArrayList<>();
//                    busLocationMap.forEach((key, value) -> {
//                        mergedBusLocatios.add(value);
//                        String remainSeatCnt = StringUtils.defaultString(value.getRemainSeatCnt(), "0");
//                        if ("0".equals(remainSeatCnt)) {
//                            zeroRemainSeatBusLocatios.add(value);
//                        }
//                    });
//                    mergedBusLocatios.sort((busLocation1, busLocation2) -> busLocation1.createdAtDiff(busLocation2));
//                    busRouteStation.setBusLocationList(mergedBusLocatios);
//                    busRouteStation.setRemainSeatCntZeroCnt(zeroRemainSeatBusLocatios.size());
//                }
//
//            }
//
//
//
//        }
//
//        return busRouteStationList;
//    }

}
