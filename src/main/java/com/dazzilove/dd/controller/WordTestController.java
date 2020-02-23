package com.dazzilove.dd.controller;

import com.dazzilove.dd.domain.WordUnit;
import com.dazzilove.dd.service.WordTestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
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

}
