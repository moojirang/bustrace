package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.service.web.CodeService;
import com.dazzilove.bustrace.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CodeMngController {

	@Autowired
	CodeService codeService;

	@RequestMapping("/codeMng/refresh")
	@ResponseBody
	public String refresh() throws Exception {
		String msg = "코드가 갱신되었습니다.";

		try {
			CodeUtil.init();
		} catch (Exception e) {
			msg = "코드 갱신에 실패했습니다.";
			e.printStackTrace();
		}

		return msg;
	}
}
