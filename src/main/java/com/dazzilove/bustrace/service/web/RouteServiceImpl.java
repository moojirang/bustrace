package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.*;
import com.dazzilove.bustrace.repository.BusRepository;
import com.dazzilove.bustrace.utils.CodeUtil;
import org.bson.types.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	BusRepository busRepository;

	@Autowired
	TripPlanService tripPlanService;

	@Override
	public Route getRouteInfo(String routeId) throws Exception {
		Route route = new Route();

		DataGatherScheduler dataGatherScheduler = new DataGatherScheduler();
		dataGatherScheduler.setEnabled(true);
		dataGatherScheduler.setSchedule(DataGatherScheduler.SCHEDULE_1_MINUTE);
		route.setDataGatherScheduler(dataGatherScheduler);

		Map<String, PlateType> plateTypeMap = CodeUtil.getPlateTypes();

		List<TripPlan> tripPlans = tripPlanService.findByRouteId(routeId);
		route.setTripPlanList(tripPlans);

		List<TripPlan> tripedPlans = new ArrayList<>();
		route.setTripPlanListTheDayBefore(tripedPlans);

		return route;
	}
}
