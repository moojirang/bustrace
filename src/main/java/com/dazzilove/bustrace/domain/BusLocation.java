package com.dazzilove.bustrace.domain;

import com.dazzilove.bustrace.utils.CodeUtil;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@ToString
public class BusLocation {
    @Id
    private UUID id;

    /** 노선ID */
    private String routeId;
    /** 정류소ID */
    private String stationId;
    /** 정류소 순서 */
    private String stationSeq;
    /** 막차여부 (0:해당사항없음, 1:막차) */
    private String endBus;
    /** 저상버스여부 (0:일반버스, 1:저상버스) */
    private String lowPlate;
    /** 차량번호 */
    private String plateNo;
    /** 차종 (0:정보없음, 1:소형승합차, 2:중형승합차, 3:대형승합차, 4:2층버스) */
    private String plateType;
    /** 차량 빈자리수 (-1:정보없음, 0~:빈자리 수) */
    private String remainSeatCnt;
    /** 생성시간 */
    private LocalDateTime createdAt;

    public String getLowPlateName() {
        String lowPlatName = "";
        switch (lowPlate) {
            case "0":
                lowPlatName = "일반버스";
                break;
            case "1":
                lowPlatName = "저상버스";
                break;
        }
        return lowPlatName;
    }

    public String getPlateTypeName() {
        return CodeUtil.getPlateType(this.plateType).getName();
    }

    private String formatTwoLength(String string) {
        String returnValue = string;
        returnValue = (returnValue == null) ? "" : returnValue;
        returnValue = returnValue.trim();
        returnValue = (returnValue.length() == 1) ? "0" + returnValue : returnValue;
        return returnValue;
    }

    public String getFormatedCreatedAt() {
        return String.format("%s/%s/%s %s:%s"
                                , formatTwoLength(String.valueOf(createdAt.getYear()))
                                , formatTwoLength(String.valueOf(createdAt.getMonthValue()))
                                , formatTwoLength(String.valueOf(createdAt.getDayOfMonth()))
                                , formatTwoLength(String.valueOf(createdAt.getHour()))
                                , formatTwoLength(String.valueOf(createdAt.getMinute())));
    }

    public String getFormatedCreatedAtByHalftime() {
        int minute = createdAt.getMinute();
        minute = (minute <= 30) ? 0 : 31;
        return String.format("%s/%s/%s %s:%s"
                , formatTwoLength(String.valueOf(createdAt.getYear()))
                , formatTwoLength(String.valueOf(createdAt.getMonthValue()))
                , formatTwoLength(String.valueOf(createdAt.getDayOfMonth()))
                , formatTwoLength(String.valueOf(createdAt.getHour()))
                , formatTwoLength(String.valueOf(minute)));
    }

    public int createdAtDiff(final BusLocation busLocation) {
        return this.createdAt.compareTo(busLocation.getCreatedAt());
    }
}
