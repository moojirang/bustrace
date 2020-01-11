package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
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
	private String weekendOperationYn;
	private String spareYn;
	private String schoolBreakReductionYn;
	private LocalDateTime schoolBreakReductionStartAt;
	private String yesterdayTripRecordYn;
	private String todayTripRecordYn;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public String getTripPlanId() {
		return this.id.toString();
	}

	public String getShortPlateNo() {
		String shortPlateNo = this.plateNo;
		shortPlateNo = shortPlateNo.substring(shortPlateNo.length() - 4, shortPlateNo.length());
		return shortPlateNo;
	}

	public String getFormatedSchoolBreakReductionStartAt() {
		if (this.schoolBreakReductionStartAt == null) {
			return "";
		}
		return String.format("%s/%s/%s"
				, formatTwoLength(String.valueOf(schoolBreakReductionStartAt.getYear()))
				, formatTwoLength(String.valueOf(schoolBreakReductionStartAt.getMonthValue()))
				, formatTwoLength(String.valueOf(schoolBreakReductionStartAt.getDayOfMonth())));
	}

	public boolean isAddValidate() {
		if("".equals(this.routeId))
			return false;
		if("".equals(this.plateNo))
			return false;
		if("".equals(this.plateType))
			return false;
		if("".equals(this.weekendOperationYn))
			return false;
		if("".equals(this.spareYn))
			return false;
		if("".equals(this.schoolBreakReductionYn))
			return false;

		return true;
	}

	public boolean isEditValidate() {
		if("".equals(this.id))
			return false;
		if(!isAddValidate())
			return false;

		return true;
	}

	private String formatTwoLength(String string) {
		String returnValue = string;
		returnValue = (returnValue == null) ? "" : returnValue;
		returnValue = returnValue.trim();
		returnValue = (returnValue.length() == 1) ? "0" + returnValue : returnValue;
		return returnValue;
	}

}
