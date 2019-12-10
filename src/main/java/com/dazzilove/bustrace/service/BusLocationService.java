package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.BusLocationResult;

import java.util.List;

public interface BusLocationService {

    public BusLocationResult getBusLocation(String routeId) throws Exception;

}
