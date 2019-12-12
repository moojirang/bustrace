package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Bus {

    String routeName;
    String routeId;

    public Bus(String routeName, String routeId) {
        this.routeName = routeName;
        this.routeId = routeId;
    }
}
