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

	public String getFormatedSchoolBreakReductionStartAt() {
		if (this.schoolBreakReductionStartAt == null) {
			return "";
		}
		return String.format("%s/%s/%s %s:%s"
				, formatTwoLength(String.valueOf(schoolBreakReductionStartAt.getYear()))
				, formatTwoLength(String.valueOf(schoolBreakReductionStartAt.getMonthValue()))
				, formatTwoLength(String.valueOf(schoolBreakReductionStartAt.getDayOfMonth()))
				, formatTwoLength(String.valueOf(schoolBreakReductionStartAt.getHour()))
				, formatTwoLength(String.valueOf(schoolBreakReductionStartAt.getMinute())));
	}

	private String formatTwoLength(String string) {
		String returnValue = string;
		returnValue = (returnValue == null) ? "" : returnValue;
		returnValue = returnValue.trim();
		returnValue = (returnValue.length() == 1) ? "0" + returnValue : returnValue;
		return returnValue;
	}
}
