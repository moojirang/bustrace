package com.dazzilove.bustrace.domain;

import com.dazzilove.bustrace.utils.CodeUtil;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Bus {

    private String routeName;
    private String routeId;
    private String plateNo;
    private String plateType;
    private String plateTypeName;

    public Bus(String routeName, String routeId) {
        this.routeName = routeName;
        this.routeId = routeId;
    }

    public Bus(String routeName, String routeId, String plateNo, String plateType) {
        this.routeName = routeName;
        this.routeId = routeId;
        this.plateNo = plateNo;
        this.plateType = plateType;
    }

    public String getPlateTypeName() {
        return CodeUtil.getPlateTypeName(this.plateType);
    }
}
