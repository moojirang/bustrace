package com.dazzilove.dd.controller;

import com.dazzilove.dd.domain.Word;
import com.dazzilove.dd.domain.WordUnit;
import com.dazzilove.dd.domain.WordUnitExam;
import com.dazzilove.dd.service.WordTestService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class WordTestController {

	@Autowired
	WordTestService wordTestService;

	@RequestMapping("/dd/wordUnitList")
	public ModelAndView viewWordUnitList() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dd/wordUnitList");
		mav.addObject(wordTestService.getWordUnitList());
		return mav;
	}

	@RequestMapping("/dd/wordUnit")
	public ModelAndView viewWordUnit(ServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dd/wordUnit");

		String id = StringUtils.defaultString(request.getParameter("id"), "");

		WordUnit wordUnit = wordTestService.getWordUnit(UUID.fromString(id));
		mav.addObject("wordUnit", wordUnit);

		return mav;
	}

	@RequestMapping("/dd/getNewExamInfo")
	@ResponseBody
	public WordUnit getExamWords(ServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String suffleYn = request.getParameter("suffleYn");
		String suffleEngKorRate = request.getParameter("suffleEngKor");
		return wordTestService.getExamWords(id, suffleYn, suffleEngKorRate);
	}

	@RequestMapping("/dd/saveWordUnitExam")
	@ResponseBody
	public String saveWordUnitExam(ServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String wordUnitExamSeq = request.getParameter("wordUnitExamSeq");
		String examWords = request.getParameter("examWords");

		Gson gson = new Gson();
		Map<String, Object> jsonObject = gson.fromJson(examWords, new TypeToken<Map<String, Object>>(){}.getType());

		List<Word> saveWords = new ArrayList<>();
		int i=1;
		List<Map<String, Object>> wordMapList = (List<Map<String, Object>>) jsonObject.get("words");
		for(Map<String, Object> map: wordMapList) {
			int no = i++;
			Word word = new Word();
			word.setId(no);
			word.setNo(no);
			word.setEnglish(String.valueOf(map.get("english")));
			word.setKorean(String.valueOf(map.get("korean")));
			word.setQuestion(String.valueOf(map.get("question")));
			word.setAnswer(String.valueOf(map.get("answer")));

			saveWords.add(word);
		}

		WordUnitExam wordUnitExam = new WordUnitExam();
		wordUnitExam.setWordUnitExamSeq(Integer.parseInt(StringUtils.defaultString(wordUnitExamSeq, "0")));
		wordUnitExam.setWordUnitId(UUID.fromString(id));
		wordUnitExam.getWords().addAll(saveWords);

		String message = "저장했습니다.";
		try {
			wordTestService.saveWordUnitExam(wordUnitExam);
		} catch (Exception e) {
			message = "저장에 실패 했습니다.";
		}

		return message;
	}


}
