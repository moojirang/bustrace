package com.dazzilove.bustrace.service.ws;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name = "busArrivalItem")
public class BusArrivalItem implements Serializable {
    /** 상태구분 (RUN:운행중, PASS:운행중, STOP:운행종료, WAIT:회차지대기) */
    @XmlElement(name = "flag")
    private String flag;
    /** 첫번째차량 위치 정보 */
    @XmlElement(name = "locationNo1")
    private String locationNo1;
    /** 두번째차량 위치 정보 */
    @XmlElement(name = "locationNo2")
    private String locationNo2;
    /** 첫번째차량 저상버스여부 (0:일반버스, 1:저상버스) */
    @XmlElement(name = "lowPlate1")
    private String lowPlate1;
    /** 두번째차량 저상버스여부 (0:일반버스, 1:저상버스) */
    @XmlElement(name = "lowPlate2")
    private String lowPlate2;
    /** 첫번째차량 차량번호 */
    @XmlElement(name = "plateNo1")
    private String plateNo1;
    /** 두번째차량 차량번호 */
    @XmlElement(name = "plateNo2")
    private String plateNo2;
    /** 첫번째차량 버스도착예정시간 (몇분후 도착예정) */
    @XmlElement(name = "predictTime1")
    private String predictTime1;
    /** 두번째차량 버스도착예정시간 (몇분후 도착예정) */
    @XmlElement(name = "predictTime2")
    private String predictTime2;
    /** 첫번째차량 빈자리수 (-1:정보없음, 0~:빈자리 수) */
    @XmlElement(name = "remainSeatCnt1")
    private String remainSeatCnt1;
    /** 두번째차량 빈자리수 (-1:정보없음, 0~:빈자리 수) */
    @XmlElement(name = "remainSeatCnt2")
    private String remainSeatCnt2;
    /** 노선ID */
    @XmlElement(name = "routeId")
    private String routeId;
    /** 노선의 정류소 순번 */
    @XmlElement(name = "staOrder")
    private String staOrder;
    /** 정류소ID */
    @XmlElement(name = "stationId")
    private String stationId;
}
