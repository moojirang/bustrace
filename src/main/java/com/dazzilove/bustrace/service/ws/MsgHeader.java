package com.dazzilove.bustrace.service.ws;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "msgHeader")
public class MsgHeader implements Serializable {

    @XmlElement(name = "queryTime")
    private String queryTime;

    @XmlElement(name = "resultCode")
    private int resultCode;

    @XmlElement(name = "resultMessage")
    private String resultMessage;
}
