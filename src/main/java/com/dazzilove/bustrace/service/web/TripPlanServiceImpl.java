package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.Bus;
import com.dazzilove.bustrace.domain.TripPlan;
import com.dazzilove.bustrace.repository.TripPlanRepository;
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
		updateTarget.setWeekendOperationYN(tripPlan.getWeekendOperationYN());
		updateTarget.setSpareYN(tripPlan.getSpareYN());
		updateTarget.setSchoolBreakReductionYN(tripPlan.getSchoolBreakReductionYN());
		updateTarget.setSchoolBreakReductionStartAt(tripPlan.getSchoolBreakReductionStartAt());
		updateTarget.setUpdatedAt(LocalDateTime.now());
		tripPlanRepository.save(updateTarget);

	}
}
