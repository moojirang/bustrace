package com.dazzilove.bustrace.service.ws;

import lombok.Data;
import lombok.ToString;

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

}
