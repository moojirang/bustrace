package com.dazzilove.bustrace.service.ws;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class BusStation {

	private String centerYn;
	private String districtCd;
	private String mobileNo;
	private String regionName;
	private String stationId;
	private String stationName;
	private double x;
	private double y;

	private Map<String, String> startStationIdMap;

	public BusStation() {
		startStationIdMap = new HashMap<>();
		startStationIdMap.put("224000142", "224000142");
		startStationIdMap.put("224000141", "224000141");
		startStationIdMap.put("224000137", "224000137");
		startStationIdMap.put("224000059", "224000059");
		startStationIdMap.put("224000703", "224000703");
		startStationIdMap.put("224000050", "224000050");
		startStationIdMap.put("224000171", "224000171");
		startStationIdMap.put("224000783", "224000783");
		startStationIdMap.put("277103206", "277103206");
	}

}
