package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
public class RealtimeLocation {
    @Id private UUID id;
    private String routeId;
    private String stationId;
    private String stationSeq;
    private String endBus;
    private String lowPlate;
    private String plateNo;
    private String plateType;
    private String remainSeatCnt;
    private LocalDateTime createdAt;

    private LocalDateTime startCreatedAt;
    private LocalDateTime endCreatedAt;

}
