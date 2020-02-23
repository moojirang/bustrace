package com.dazzilove.dd.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@ToString
public class WordUnit {
	@Id private UUID id;
	private String unitCode;
	private String unitName;
	private String testDt;
	private List<Word> words = new ArrayList<>();

	public List<Word> getWords() {
		if (words == null) {
			return new ArrayList<>();
		}
		return words;
	}
}
