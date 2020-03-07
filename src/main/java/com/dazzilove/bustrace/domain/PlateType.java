package com.dazzilove.bustrace.domain;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlateType {
	private String code;
	private String name;
	private String imageSrc;
	private int typeCnt;
}


