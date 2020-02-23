package com.dazzilove.dd.service;

import com.dazzilove.dd.domain.Word;
import com.dazzilove.dd.domain.WordUnit;
import com.dazzilove.dd.repository.WordUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WordTestServiceImpl implements WordTestService {

	@Autowired
	WordUnitRepository wordUnitRepository;

	@Override
	public void addTempWordUnit(WordUnit wordUnit){
		wordUnitRepository.save(wordUnit);
	}

	@Override
	public WordUnit getWordUnit(UUID id) {
		Optional<WordUnit> wordUnit = wordUnitRepository.findById(id);
		return wordUnit.orElse(new WordUnit());
	}

	@Override
	public List<WordUnit> getWordUnitList() {
		return wordUnitRepository.findAll();
	}
}
