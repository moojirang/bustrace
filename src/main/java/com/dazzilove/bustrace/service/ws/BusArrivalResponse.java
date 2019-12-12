package com.dazzilove.bustrace.service.ws;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name = "response")
public class BusArrivalResponse extends MsgResponse {

    @XmlElementWrapper(name =  "msgBody")
    @XmlElement(name = "busArrivalItem")
    private List<BusArrivalItem> busArrivalItem;
}
