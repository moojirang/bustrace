package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BusRoute {

    String routeId;
    String routeName;
    String routeTypeCd;
    String routeTypeName;
    String regionName;
    String districtCd;

}
