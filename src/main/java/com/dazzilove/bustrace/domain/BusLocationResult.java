package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@ToString
@XmlRootElement(name = "response")
public class BusLocationResult {

    private MsgBody msgBody;

    @XmlRootElement(name = "msgBody")
    public static class MsgBody {

        @XmlElementWrapper(name = "busLocationList")
        private List<BusLocation> busLocationList;
    }

}
