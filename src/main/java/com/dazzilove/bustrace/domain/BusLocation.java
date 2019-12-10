package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@ToString
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name = "busLocationList")
public class BusLocation implements Serializable {

    @XmlElement(name = "endBus")
    private String endBus;

    @XmlElement(name = "lowPlate")
    private String lowPlate;

    @XmlElement(name = "plateNo")
    private String plateNo;

    @XmlElement(name = "plateType")
    private String plateType;

    @XmlElement(name = "remainSeatCnt")
    private String remainSeatCnt;

    @XmlElement(name = "routeId")
    private String routeId;

    @XmlElement(name = "stationId")
    private String stationId;

    @XmlElement(name = "stationSeq")
    private String stationSeq;

}


