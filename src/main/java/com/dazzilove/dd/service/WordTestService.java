package com.dazzilove.dd.service;

import com.dazzilove.dd.domain.WordUnit;

import java.util.List;
import java.util.UUID;

public interface WordTestService {
	void addTempWordUnit(WordUnit wordUnit);

	WordUnit getWordUnit(UUID id);

	List<WordUnit> getWordUnitList();
}
