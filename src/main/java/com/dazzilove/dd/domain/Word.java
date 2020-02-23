package com.dazzilove.dd.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@ToString
public class Word {
	@Id private int id;
	private int no;
	private String english;
	private String korean;
	private String question;
	private String answer;
}
