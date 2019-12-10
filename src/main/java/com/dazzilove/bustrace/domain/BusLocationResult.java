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


/*
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<response>
	<comMsgHeader/>
	<msgHeader>
		<queryTime>2019-12-10 21:02:16.027</queryTime>
		<resultCode>0</resultCode>
		<resultMessage>정상적으로 처리되었습니다.</resultMessage>
	</msgHeader>
	<msgBody>
		<busLocationList>
			<endBus>0</endBus>
			<lowPlate>0</lowPlate>
			<plateNo>경기75바3116</plateNo>
			<plateType>3</plateType>
			<remainSeatCnt>37</remainSeatCnt>
			<routeId>224000047</routeId>
			<stationId>121000060</stationId>
			<stationSeq>39</stationSeq>
		</busLocationList>
		<busLocationList>
			<endBus>0</endBus>
			<lowPlate>0</lowPlate>
			<plateNo>경기75바3114</plateNo>
			<plateType>3</plateType>
			<remainSeatCnt>39</remainSeatCnt>
			<routeId>224000047</routeId>
			<stationId>277104350</stationId>
			<stationSeq>27</stationSeq>
		</busLocationList>
		<busLocationList>
			<endBus>0</endBus>
			<lowPlate>0</lowPlate>
			<plateNo>경기75바3112</plateNo>
			<plateType>3</plateType>
			<remainSeatCnt>37</remainSeatCnt>
			<routeId>224000047</routeId>
			<stationId>224000141</stationId>
			<stationSeq>68</stationSeq>
		</busLocationList>
		<busLocationList>
			<endBus>0</endBus>
			<lowPlate>0</lowPlate>
			<plateNo>경기75바3125</plateNo>
			<plateType>4</plateType>
			<remainSeatCnt>71</remainSeatCnt>
			<routeId>224000047</routeId>
			<stationId>224000816</stationId>
			<stationSeq>3</stationSeq>
		</busLocationList>
		<busLocationList>
			<endBus>0</endBus>
			<lowPlate>0</lowPlate>
			<plateNo>경기75바3138</plateNo>
			<plateType>4</plateType>
			<remainSeatCnt>68</remainSeatCnt>
			<routeId>224000047</routeId>
			<stationId>224000059</stationId>
			<stationSeq>18</stationSeq>
		</busLocationList>
		<busLocationList>
			<endBus>0</endBus>
			<lowPlate>0</lowPlate>
			<plateNo>경기75바3124</plateNo>
			<plateType>4</plateType>
			<remainSeatCnt>62</remainSeatCnt>
			<routeId>224000047</routeId>
			<stationId>224000483</stationId>
			<stationSeq>61</stationSeq>
		</busLocationList>
		<busLocationList>
			<endBus>0</endBus>
			<lowPlate>0</lowPlate>
			<plateNo>경기75바3115</plateNo>
			<plateType>3</plateType>
			<remainSeatCnt>15</remainSeatCnt>
			<routeId>224000047</routeId>
			<stationId>121000076</stationId>
			<stationSeq>49</stationSeq>
		</busLocationList>
		<busLocationList>
			<endBus>0</endBus>
			<lowPlate>0</lowPlate>
			<plateNo>경기75바3113</plateNo>
			<plateType>3</plateType>
			<remainSeatCnt>3</remainSeatCnt>
			<routeId>224000047</routeId>
			<stationId>277102910</stationId>
			<stationSeq>52</stationSeq>
		</busLocationList>
	</msgBody>
</response>
* */