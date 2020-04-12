package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Code {
	@Id private String id;
	private String name;
	private String useYn;
	private String delYn;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	private List<DetailCode> detailCodes = new ArrayList<>();

	public List<DetailCode> getDetailCodes() {
		return this.detailCodes;
	}

	public DetailCode getDetailCode(String detailCodeId) {
		for (DetailCode detailCodeTemp: detailCodes) {
			if (detailCodeTemp.getId().toString().equals(detailCodeId)) {
				return detailCodeTemp;
			}
		}
		return new DetailCode();
	}

	public boolean isDeleted() {
		return "Y".equals(delYn);
	}


}
