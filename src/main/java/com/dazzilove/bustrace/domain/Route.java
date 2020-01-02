package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Route {

	private String companyId;
	private String companyName;
	private String companyTel;
	private String districtCd;
	private String downFirstTime;
	private String downLastTime;
	private String endMobileNo;
	private String endStationId;
	private String endStationName;
	private String peekAlloc;
	private String regionName;
	private String routeId;
	private String routeName;
	private String routeTypeCd;
	private String routeTypeName;
	private String startMobileNo;
	private String startStationId;
	private String startStationName;
	private String upFirstTime;
	private String upLastTime;
	private String nPeekAlloc;

	private DataGatherScheduler dataGatherScheduler;
	private List<TripPlan> tripPlanList; // 운행 계획 정보
	private List<TripPlan> tripPlanListTheDayBefore; // 전일 운행 정보

}