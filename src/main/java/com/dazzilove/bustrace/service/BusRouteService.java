package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.service.wsdl.BusRoute;

import java.util.List;

public interface BusRouteService {

    public List<BusRoute> getBusRoutes(String keyword);
}
