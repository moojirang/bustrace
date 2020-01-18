package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.LocationParam;
import com.dazzilove.bustrace.domain.TripPlan;
import com.dazzilove.bustrace.domain.TripPlanHistory;
import com.dazzilove.bustrace.repository.TripPlanHistoryRepository;
import com.dazzilove.bustrace.repository.TripPlanRepository;
import com.dazzilove.bustrace.service.BusLocationService;
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
    TripPlanHistoryRepository tripPlanHistoryRepository;

	@Autowired
	BusLocationService busLocationService;

	@Override
	public void addTripPlan(TripPlan tripPlan) throws Exception {
		tripPlan.setId(UUID.randomUUID());
		tripPlan.setCreatedAt(LocalDateTime.now());
		tripPlanRepository.insert(tripPlan);
        addTripPlanHistory(tripPlan);
	}

	@Override
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
		updateTarget.setTurnNumber(tripPlan.getTurnNumber());
		updateTarget.setPlateType(tripPlan.getPlateType());
		updateTarget.setWeekendOperationYn(tripPlan.getWeekendOperationYn());
		updateTarget.setSpareYn(tripPlan.getSpareYn());
		updateTarget.setSchoolBreakReductionYn(tripPlan.getSchoolBreakReductionYn());
		updateTarget.setSchoolBreakReductionStartedAt(tripPlan.getSchoolBreakReductionStartedAt());
		updateTarget.setTripStopYn(tripPlan.getTripStopYn());
		updateTarget.setTripStopStartedAt(tripPlan.getTripStopStartedAt());
		updateTarget.setUpdatedAt(LocalDateTime.now());
		tripPlanRepository.save(updateTarget);
        addTripPlanHistory(tripPlan);
	}

	@Override
	public void deleteTripPlan(TripPlan tripPlan) throws Exception {
		TripPlan updateTarget = getTripPlan(tripPlan.getTripPlanId());
		if("".equals(updateTarget.getTripPlanId()))
			throw new Exception("정보가 올바르지 않습니다.");
		updateTarget.setDeleteYn("Y");
		updateTarget.setDeletedAt(LocalDateTime.now());
		updateTarget.setUpdatedAt(LocalDateTime.now());
		tripPlanRepository.save(updateTarget);
        addTripPlanHistory(tripPlan);
	}

	@Override
	public void updateTripRecord(String routeId) throws Exception {
		List<TripPlan> tripPlans = tripPlanRepository.findByRouteId(routeId);
		if(tripPlans.isEmpty()) {
			return;
		}

		for(TripPlan tripPlan: tripPlans) {

			TripPlan updateTarget = getTripPlan(tripPlan.getTripPlanId());
			if (updateTarget == null) {
				return;
			}

			LocalDateTime basicDateTime = LocalDateTime.now();
			updateTarget.setTodayTripRecordYn(getTripRecordYnByDay(tripPlan, basicDateTime));
			tripPlanRepository.save(updateTarget);

			basicDateTime = basicDateTime.minusDays(1);
			getTripRecordYnByDay(tripPlan, basicDateTime);
			updateTarget.setYesterdayTripRecordYn(getTripRecordYnByDay(tripPlan, basicDateTime));
			tripPlanRepository.save(updateTarget);
		}
	}

	private void addTripPlanHistory(TripPlan tripPlan) {
        TripPlanHistory tripPlanHistory = new TripPlanHistory();
        tripPlanHistory.setHistoryId(UUID.randomUUID());
        tripPlanHistory.setHistoryCreatedAt(LocalDateTime.now());
        tripPlanHistory.setTripPlan(tripPlan);
        tripPlanHistoryRepository.save(tripPlanHistory);
    }

	private String getTripRecordYnByDay(TripPlan tripPlan, LocalDateTime basicDateTime) throws Exception {
		LocalDateTime startDateTime = LocalDateTime.of(basicDateTime.getYear(), basicDateTime.getMonth(), basicDateTime.getDayOfMonth(), 0, 0, 0);
		LocalDateTime endDateTime = LocalDateTime.of(basicDateTime.getYear(), basicDateTime.getMonth(), basicDateTime.getDayOfMonth(), 23, 59, 59);

		LocationParam locationParam = new LocationParam();
		locationParam.setRouteId(tripPlan.getRouteId());
		locationParam.setPlateNo(tripPlan.getPlateNo());
		locationParam.setStartCreatedAt(startDateTime);
		locationParam.setEndCreatedAt(endDateTime);
		List<BusLocation> locations = busLocationService.getBusLoactionsByPlateNo(locationParam);

		if (!locations.isEmpty() && locations.size() > 0) {
			return "Y";
		}

		return "N";
	}
}
