package com.dazzilove.bustrace.service.ws;

import com.dazzilove.bustrace.domain.BusLocation;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class BusRouteStation extends BusStation {
	private String stationSeq;
	private String turnYn;
	private int remainSeatCntZeroCnt;

	private List<BusLocation> busLocationList = new ArrayList<>();
}
