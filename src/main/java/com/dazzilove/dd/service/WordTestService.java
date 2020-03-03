package com.dazzilove.dd.service;

import com.dazzilove.dd.domain.WordUnit;
import com.dazzilove.dd.domain.WordUnitExam;

import java.util.List;
import java.util.UUID;

public interface WordTestService {
	void addTempWordUnit(WordUnit wordUnit);

	WordUnit getWordUnit(UUID id);

	List<WordUnit> getWordUnitList();

	WordUnit getExamWords(String id, String suffleYn, String suffleEngKorRate) throws Exception;

	void saveWordUnitExam(WordUnitExam wordUnitExam) throws Exception;
}
