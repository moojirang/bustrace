package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlateType {
	private String code;
	private String name;
	private String imageSrc;
	private int typeCnt;
}


