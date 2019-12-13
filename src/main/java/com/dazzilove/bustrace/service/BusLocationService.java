package com.dazzilove.bustrace.service;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.BusLocationParam;
import com.dazzilove.bustrace.service.ws.BusLocationList;

import java.util.List;

public interface BusLocationService {

    public List<BusLocationList> getBusLocation(String routeId) throws Exception;

    public void addBusLocationList(String routeId) throws Exception;

    public List<BusLocation> getBusLoactions(BusLocationParam busLocationParam) throws Exception;

}
