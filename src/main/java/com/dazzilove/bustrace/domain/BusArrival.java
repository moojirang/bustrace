package com.dazzilove.bustrace.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BusArrival {
    @Id
    private UUID id;
    /** 상태구분 (RUN:운행중, PASS:운행중, STOP:운행종료, WAIT:회차지대기) */
    private String flag;
    /** 첫번째차량 위치 정보 */
    private String locationNo1;
    /** 두번째차량 위치 정보 */
    private String locationNo2;
    /** 첫번째차량 저상버스여부 (0:일반버스, 1:저상버스) */
    private String lowPlate1;
    /** 두번째차량 저상버스여부 (0:일반버스, 1:저상버스) */
    private String lowPlate2;
    /** 첫번째차량 차량번호 */
    private String plateNo1;
    /** 두번째차량 차량번호 */
    private String plateNo2;
    /** 첫번째차량 버스도착예정시간 (몇분후 도착예정) */
    private String predictTime1;
    /** 두번째차량 버스도착예정시간 (몇분후 도착예정) */
    private String predictTime2;
    /** 첫번째차량 빈자리수 (-1:정보없음, 0~:빈자리 수) */
    private String remainSeatCnt1;
    /** 두번째차량 빈자리수 (-1:정보없음, 0~:빈자리 수) */
    private String remainSeatCnt2;
    /** 노선ID */
    private String routeId;
    /** 노선의 정류소 순번 */
    private String staOrder;
    /** 정류소ID */
    private String stationId;
    /** 생성일시 */
    private LocalDateTime createdAt;
}
