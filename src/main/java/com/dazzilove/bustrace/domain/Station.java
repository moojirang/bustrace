package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Station {
	private String stationId;
	private String stationSeq;
	private String stationName;
	private String centerYn;
	private String districtCd;
	private String mobileNo;
	private String regionName;
	private String turnYn;
	private int remainSeatCntZeroCnt;

	private List<Location> locations = new ArrayList<>();
}
