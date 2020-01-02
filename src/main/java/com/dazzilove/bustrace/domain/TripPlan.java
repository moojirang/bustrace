package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@ToString
public class TripPlan {

	@Id
	private UUID id;
	private String routeName;
	private String routeId;
	private String plateNo;
	private String plateType;
	private String plateTypeName;
	private String weekendOperationYN;
	private String schoolBreakReductionYN;
	private LocalDateTime schoolBreakReductionStartAt;
	private LocalDateTime createdAt;

	public String getShortPlateNo() {
		String shortPlateNo = this.plateNo;
		shortPlateNo = shortPlateNo.substring(shortPlateNo.length() - 4, shortPlateNo.length());
		return shortPlateNo;
	}
}
