package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.Location;
import com.dazzilove.bustrace.domain.LocationParam;

import java.util.List;

public interface LocationService {
	public List<Location> getLocations(LocationParam locationParam) throws Exception;
}
