package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@ToString
public class DetailCode {
	@Id private String id;
	private String name;
	private int sortNumber;
	private String useYn;
	private String delYn;
	private String masterCode;
	private String img;
	private String val1, val2, val3;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	public boolean isDeleted() {
		return "Y".equals(delYn);
	}
}
