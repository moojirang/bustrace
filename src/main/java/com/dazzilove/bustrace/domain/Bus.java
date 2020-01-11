package com.dazzilove.bustrace.domain;

import com.dazzilove.bustrace.utils.CodeUtil;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Bus {

    private String routeName;
    private String routeId;
    private String plateNo;
    private String plateType;
    private String plateTypeName;
    private int totalTripPlanCount;
    private int yesterdayTripRecordCount;
    private int todayTripRecordCount;

    public Bus(){

    }

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
        return CodeUtil.getPlateType(this.plateType).getName();
    }

    public int plateNoDiff(Bus bus) {
        return this.plateNo.compareTo(bus.getPlateNo());
    }

    public List<Bus> getBusList() {
        List<Bus> busList = new ArrayList<>();
        Bus bus = new Bus("3200", "224000019");
        busList.add(bus);
        bus = new Bus("3300", "224000047");
        busList.add(bus);
        bus = new Bus("3400", "224000050");
        busList.add(bus);
        bus = new Bus("3500", "224000054");
        busList.add(bus);
        bus = new Bus("5602", "216000047");
        busList.add(bus);
        bus = new Bus("5604", "224000040");
        busList.add(bus);
        bus = new Bus("32", "217000009");
        busList.add(bus);
        bus = new Bus("81", "208000009");
        busList.add(bus);
        bus = new Bus("30-2", "224000014");
        busList.add(bus);

        return busList;
    }
}
