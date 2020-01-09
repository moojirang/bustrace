package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.BusLocationParam;
import com.dazzilove.bustrace.domain.TripPlan;
import com.dazzilove.bustrace.repository.TripPlanRepository;
import com.dazzilove.bustrace.service.BusLocationService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripPlanServiceImpl implements TripPlanService {

	@Autowired
	TripPlanRepository tripPlanRepository;

	@Autowired
	BusLocationService busLocationService;

	@Override
	public void addTripPlan(TripPlan tripPlan) throws Exception {
		tripPlan.setId(UUID.randomUUID());
		tripPlan.setCreatedAt(LocalDateTime.now());
		tripPlanRepository.insert(tripPlan);
	}

	public List<TripPlan> findByRouteId(String routeId) throws Exception {
		return tripPlanRepository.findByRouteId(routeId);
	}

	@Override
	public TripPlan getTripPlan(String tripPlanId) throws Exception {
		Optional<TripPlan> tripPlan = tripPlanRepository.findById(UUID.fromString(tripPlanId));
		return tripPlan.orElse(new TripPlan());
	}

	@Override
	public void editTripPlan(TripPlan tripPlan) throws Exception {
		TripPlan updateTarget = getTripPlan(tripPlan.getTripPlanId());
		updateTarget.setRouteId(tripPlan.getRouteId());
		updateTarget.setPlateNo(tripPlan.getPlateNo());
		updateTarget.setPlateType(tripPlan.getPlateType());
		updateTarget.setWeekendOperationYn(tripPlan.getWeekendOperationYn());
		updateTarget.setSpareYn(tripPlan.getSpareYn());
		updateTarget.setSchoolBreakReductionYn(tripPlan.getSchoolBreakReductionYn());
		updateTarget.setSchoolBreakReductionStartAt(tripPlan.getSchoolBreakReductionStartAt());
		updateTarget.setUpdatedAt(LocalDateTime.now());
		tripPlanRepository.save(updateTarget);

	}

	@Override
	public void updateTripRecord(String routeId) throws Exception {
		List<TripPlan> tripPlans = tripPlanRepository.findByRouteId(routeId);
		if(tripPlans.isEmpty()) {
			return;
		}

		for(TripPlan tripPlan: tripPlans) {

			LocalDateTime basicDateTime = LocalDateTime.now();
			updateTripRecordByDay(tripPlan, basicDateTime);

			basicDateTime = basicDateTime.minusDays(1);
			updateTripRecordByDay(tripPlan, basicDateTime);


		}
	}

	private void updateTripRecordByDay(TripPlan tripPlan, LocalDateTime basicDateTime) throws Exception {
		LocalDateTime startDateTime = LocalDateTime.of(basicDateTime.getYear(), basicDateTime.getMonth(), basicDateTime.getDayOfMonth(), 0, 0, 0);
		LocalDateTime endDateTime = LocalDateTime.of(basicDateTime.getYear(), basicDateTime.getMonth(), basicDateTime.getDayOfMonth(), 23, 59, 59);

		TripPlan updateTarget = getTripPlan(tripPlan.getTripPlanId());
		if (updateTarget == null) {
			return;
		}

		BusLocationParam busLocationParam = new BusLocationParam();
		busLocationParam.setRouteId(tripPlan.getRouteId());
		busLocationParam.setPlateNo(tripPlan.getPlateNo());
		busLocationParam.setStartCreatedAt(startDateTime);
		busLocationParam.setEndCreatedAt(endDateTime);
		List<BusLocation> locations = busLocationService.getBusLoactions(busLocationParam);

		String todayTripRecordYn = "N";
		if (!locations.isEmpty() && locations.size() > 0) {
			todayTripRecordYn = "Y";
		}
		updateTarget.setTodayTripRecordYn(todayTripRecordYn);
		tripPlanRepository.save(updateTarget);
	}
}
