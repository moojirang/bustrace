package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.*;
import com.dazzilove.bustrace.utils.CodeUtil;
import org.apache.commons.lang.StringUtils;
import org.bson.types.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

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
		route.setTripPlanList(getTripPlans(routeId));

		List<TripPlan> tripedPlans = new ArrayList<>();
		route.setTripPlanListTheDayBefore(tripedPlans);

		return route;
	}

	public List<TripPlan> getTripPlans(String routeId) throws Exception {
		return tripPlanService.findByRouteId(routeId).stream()
				.filter((TripPlan tempTripPlan) -> ("N".equals(StringUtils.defaultString(tempTripPlan.getDeleteYn(), "N"))))
				.collect(Collectors.toList());
	}

	@Override
	public List<Bus> getRoutes() throws Exception {
		List<Bus> busList = new Bus().getBusList();
		busList.stream()
				.forEach(tempBus -> {
					List<TripPlan> tripPlans = null;
					try {
						tripPlans = getTripPlans(tempBus.getRouteId());
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

						tempBus.setTotalTripPlanCount(tripPlans.size());
						tempBus.setYesterdayTripRecordCount(yesterdayTripRecordCount);
						tempBus.setTodayTripRecordCount(todayTripRecordCount);
					}
				});
		return busList;
	}
}
