package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.Bus;
import com.dazzilove.bustrace.domain.Route;
import com.dazzilove.bustrace.domain.TripPlan;

import java.util.List;

public interface RouteService {
	Route getRouteInfo(String routeId) throws Exception;
	List<TripPlan> getTripPlans(String routeId) throws Exception;
	List<Bus> getRoutes() throws Exception;
}
