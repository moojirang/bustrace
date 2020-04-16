package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
public class PlateRunHistory implements Comparable<PlateRunHistory> {

    @Id private UUID id;

    private String routeId;
    private String plateNo;
    private String runDay;
    private int sortNumber;
    private LocalDateTime createdAt;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public int compareTo(PlateRunHistory plate2) {
        if (this.createdAt.isBefore(plate2.getCreatedAt())) {
            return -1;
        }
        if (this.createdAt.isAfter(plate2.getCreatedAt())) {
            return 1;
        }
        return 0;
    }
}
