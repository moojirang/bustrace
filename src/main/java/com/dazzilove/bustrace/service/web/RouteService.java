package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.Bus;
import com.dazzilove.bustrace.domain.Route;
import com.dazzilove.bustrace.domain.TripPlan;

import java.util.List;

public interface RouteService {
	Route getRouteInfoByRouteId(String routeId) throws Exception;
	List<TripPlan> getTripPlans(String routeId) throws Exception;

    List<Route> getRoutes() throws Exception;

    void addRoute(Route route) throws Exception;
    void editRoute(Route route) throws Exception;
    void deleteRoute(Route route) throws Exception;

    Route getOnlyRouteInfo(String id) throws Exception;

    Route getRouteInfo(String id) throws Exception;
}
