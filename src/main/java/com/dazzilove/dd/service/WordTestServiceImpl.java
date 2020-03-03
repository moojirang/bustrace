package com.dazzilove.dd.service;

import com.dazzilove.dd.domain.SequenceId;
import com.dazzilove.dd.domain.Word;
import com.dazzilove.dd.domain.WordUnit;
import com.dazzilove.dd.domain.WordUnitExam;
import com.dazzilove.dd.repository.WordUnitExamRepository;
import com.dazzilove.dd.repository.WordUnitRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class WordTestServiceImpl implements WordTestService {

	public static final String WORD_UNIT_EXAM_SEQ = "wordUnitExamSeq";

	@Autowired
	private MongoOperations mongoOperation;

	@Autowired
	WordUnitRepository wordUnitRepository;

	@Autowired
	WordUnitExamRepository wordUnitExamRepository;

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

	@Override
	public WordUnit getExamWords(String id, String suffleYn, String suffleEngKorRate) throws Exception {
		WordUnit wordUnit = getWordUnit(UUID.fromString(id));

		List<Word> words = wordUnit.getWords();
		for(Word word: words) {
			word.setQuestion(word.getEnglish());
			word.setAnswer(word.getKorean());
		}

		if ("Y".equals(StringUtils.defaultString(suffleYn))) {
			List<Word> getSuffledWords = getSuffledWords(wordUnit.getWords(), suffleEngKorRate);
			wordUnit.getWords().clear();
			wordUnit.getWords().addAll(getSuffledWords);
		}
		wordUnit.setWordUnitExamSeq(createSeqWordUnitExam());
		return wordUnit;
	}

	@Override
	public void saveWordUnitExam(WordUnitExam wordUnitExam) throws Exception {
		wordUnitExam.setCreatedAt(LocalDateTime.now());
		wordUnitExamRepository.save(wordUnitExam);
	}

	private int createSeqWordUnitExam() throws Exception {
		Query query = new Query(Criteria.where("_id").is(WORD_UNIT_EXAM_SEQ));
		Update update = new Update();
		update.inc("seq", 1);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		SequenceId sequenceId = mongoOperation.findAndModify(query, update, options, SequenceId.class);

		if (sequenceId == null) {
			throw new Exception("Unable to get sequennce id for key : " + WORD_UNIT_EXAM_SEQ);
		}

		return sequenceId.getSeq();
	}

	private List<Word> getSuffledWords(List<Word> words, String suffleEngKorRate) {
		int suffleEngKorRateInt = Integer.parseInt(StringUtils.defaultString(suffleEngKorRate, "10").trim());

		List<Word> suffledWords = new ArrayList<>();
		for (int i=0; i<words.size(); i++) {
			suffledWords.add(words.get(i));
		}
		Collections.shuffle(suffledWords);

		for(int i=0; i<suffledWords.size(); i++) {
			if(i % suffleEngKorRateInt == 0) {
				Word word = suffledWords.get(i);
				word.setQuestion(word.getKorean());
				word.setAnswer(word.getEnglish());
			}
		}
		Collections.shuffle(suffledWords);

		return suffledWords;
	}
}
