package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.Bus;
import com.dazzilove.bustrace.domain.Route;

public interface RouteService {
	Route getRouteInfo(String routeId) throws Exception;
}
