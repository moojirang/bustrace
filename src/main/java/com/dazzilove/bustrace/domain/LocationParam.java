package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class LocationParam {

	private String routeId;
	private String plateNo;
	private LocalDateTime startCreatedAt;
	private LocalDateTime endCreatedAt;

}
