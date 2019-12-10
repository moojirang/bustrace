package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@Data
@ToString
@XmlAccessorType(value = XmlAccessType.FIELD)
public class MsgResponse implements Serializable {

    @XmlElement(name = "msgHeader")
    private MsgHeader msgHeader;
}
