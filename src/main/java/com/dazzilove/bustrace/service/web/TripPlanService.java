package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.TripPlan;

import java.util.List;

public interface TripPlanService {
	public void addTripPlan(TripPlan tripPlan) throws Exception;
	public List<TripPlan> findByRouteId(String routeId) throws Exception;
	public TripPlan getTripPlan(String tripPlanId) throws Exception;
	public void editTripPlan(TripPlan tripPlan) throws Exception;
    void updateTripRecord(String routeId) throws Exception;
	void deleteTripPlan(TripPlan tripPlan) throws Exception;
}
