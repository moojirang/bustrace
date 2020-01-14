package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.*;
import com.dazzilove.bustrace.repository.RouteRepository;
import com.dazzilove.bustrace.utils.CodeUtil;
import org.apache.commons.lang.StringUtils;
import org.bson.types.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	TripPlanService tripPlanService;

	@Autowired
    RouteRepository routeRepository;

    @Override
    public Route getRouteInfo(String _id) throws Exception {
        Route route = getOnlyRouteInfo(_id);

//        DataGatherScheduler dataGatherScheduler = new DataGatherScheduler();
//        dataGatherScheduler.setEnabled(true);
//        dataGatherScheduler.setSchedule(DataGatherScheduler.SCHEDULE_1_MINUTE);
//        route.setDataGatherScheduler(dataGatherScheduler);

        String routeId = route.getRouteId();
        Map<String, PlateType> plateTypeMap = CodeUtil.getPlateTypes();

        List<TripPlan> tripPlans = tripPlanService.findByRouteId(routeId);
        route.setTripPlanList(getTripPlans(routeId));

        List<TripPlan> tripedPlans = new ArrayList<>();
        route.setTripPlanListTheDayBefore(tripedPlans);

        return route;
    }

	@Override
    public Route getOnlyRouteInfo(String _id) throws Exception {
	    return getRoute(_id);
    }

    public List<TripPlan> getTripPlans(String routeId) throws Exception {
		return tripPlanService.findByRouteId(routeId).stream()
				.filter((TripPlan tempTripPlan) -> ("N".equals(tempTripPlan.getDeleteYn())))
				.sorted((TripPlan a, TripPlan b) -> ((a.getTurnNumber() > b.getTurnNumber()) ? 1 : -1))
				.collect(Collectors.toList());
	}

    @Override
    public List<Route> getRoutes() throws Exception {
	    List<Route> routeList = routeRepository.findAll().stream()
                .filter(tempRoute -> "N".equals(tempRoute.getDeleteYn())).collect(Collectors.toList());
        routeList.stream()
                .forEach(tempRoute -> {
                    List<TripPlan> tripPlans = null;
                    try {
                        tripPlans = getTripPlans(tempRoute.getRouteId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!tripPlans.isEmpty()) {
                        int yesterdayTripRecordCount = 0;
                        int todayTripRecordCount = 0;

                        for(TripPlan tempTripPlan: tripPlans) {
                            if ("Y".equals(tempTripPlan.getYesterdayTripRecordYn())) {
                                yesterdayTripRecordCount++;
                            }
                            if ("Y".equals(tempTripPlan.getTodayTripRecordYn())) {
                                todayTripRecordCount++;
                            }
                        }

                        tempRoute.setTotalTripPlanCount(tripPlans.size());
                        tempRoute.setYesterdayTripRecordCount(yesterdayTripRecordCount);
                        tempRoute.setTodayTripRecordCount(todayTripRecordCount);
                    }
                });
        return routeList;
    }

    @Override
    public void addRoute(Route route) throws Exception {
        route.setId(UUID.randomUUID());
        route.setCreatedAt(LocalDateTime.now());
        routeRepository.insert(route);
    }

    @Override
    public void editRoute(Route route) throws Exception {
        Route updateTarget = getRoute(route.getId().toString());
        updateTarget.setCompanyId(route.getCompanyId());
        updateTarget.setCompanyName(route.getCompanyName());
        updateTarget.setCompanyTel(route.getCompanyTel());
        updateTarget.setDistrictCd(route.getDistrictCd());
        updateTarget.setDownFirstTime(route.getDownFirstTime());
        updateTarget.setDownLastTime(route.getDownLastTime());
        updateTarget.setEndMobileNo(route.getEndMobileNo());
        updateTarget.setEndStationId(route.getEndStationId());
        updateTarget.setEndStationName(route.getEndStationName());
        updateTarget.setPeekAlloc(route.getPeekAlloc());
        updateTarget.setRegionName(route.getRegionName());
        updateTarget.setRouteId(route.getRouteId());
        updateTarget.setRouteName(route.getRouteName());
        updateTarget.setRouteTypeCd(route.getRouteTypeCd());
        updateTarget.setRouteTypeName(route.getRouteTypeName());
        updateTarget.setStartMobileNo(route.getStartMobileNo());
        updateTarget.setStartStationId(route.getStartStationId());
        updateTarget.setStartStationName(route.getStartStationName());
        updateTarget.setUpFirstTime(route.getUpFirstTime());
        updateTarget.setUpLastTime(route.getUpLastTime());
        updateTarget.setNPeekAlloc(route.getNPeekAlloc());
        updateTarget.setUpdatedAt(LocalDateTime.now());
        updateTarget.setDataGatherScheduler(route.getDataGatherScheduler());
        routeRepository.save(updateTarget);
    }

    @Override
    public void deleteRoute(Route route) throws Exception {
        Route updateTarget = getRoute(route.getId().toString());
        if("".equals(updateTarget.getRouteId()))
            throw new Exception("정보가 올바르지 않습니다.");
        updateTarget.setDeleteYn("Y");
        updateTarget.setDeletedAt(LocalDateTime.now());
        updateTarget.setUpdatedAt(LocalDateTime.now());
        routeRepository.save(updateTarget);
    }

    private Route getRoute(String _id) {
        Optional<Route> tripPlan = routeRepository.findById(UUID.fromString(_id));
        return tripPlan.orElse(new Route());
    }
}
