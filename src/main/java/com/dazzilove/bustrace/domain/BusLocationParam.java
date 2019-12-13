package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class BusLocationParam {

	private String routeId;
	private LocalDateTime startCreatedAt;
	private LocalDateTime endCreatedAt;

}
