package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.domain.Code;
import com.dazzilove.bustrace.domain.DetailCode;
import com.dazzilove.bustrace.service.web.CodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class CodeMngController {

	@Autowired
	CodeService codeService;

	@RequestMapping("/codeMng/codeList")
	public ModelAndView viewCodeList(ServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("codeMng/codeList");
		mav.addObject("codeList", codeService.getCodeList());
		return mav;
	}

	@RequestMapping("/codeMng/codeInfo")
	public ModelAndView viewCodeInfo(ServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("codeMng/codeInfo");

		String _id = StringUtils.defaultString(request.getParameter("_id"), "");
		Code code = codeService.getCode(_id);
		mav.addObject("codeInfo", code);

		return mav;
	}

	@RequestMapping("/codeMng/viewAddCode")
	public ModelAndView viewAddCode(ServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("codeMng/masterCodeForm");
		return mav;
	}

	@RequestMapping("/codeMng/addCode")
	@ResponseBody
	public String addCode(ServletRequest request) throws Exception {
		Code code = convertCodeByRequest(request);
		codeService.addCode(code);
		return "등록이 완료 되었습니다.";
	}

	@RequestMapping("/codeMng/viewEditCode")
	public ModelAndView viewEditCode(ServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("codeMng/masterCodeForm");
		mav.addObject("pageMode", "EDIT");

		String _id = StringUtils.defaultString(request.getParameter("_id"), "");
		Code code = codeService.getCode(_id);
		mav.addObject("code", code);

		return mav;
	}

	@RequestMapping("/codeMng/editCode")
	@ResponseBody
	public String editCode(ServletRequest request) throws Exception {
		Code code = convertCodeByRequest(request);
		codeService.editCode(code);
		return "수정이 완료 되었습니다.";
	}

	@RequestMapping("/codeMng/delCode")
	@ResponseBody
	public String delCode(ServletRequest request) throws Exception {
		Code code = convertCodeByRequest(request);
		code.setDelYn("Y");
		codeService.editCode(code);
		return "삭제가 완료 되었습니다.";
	}

	@RequestMapping("/codeMng/viewAddDetailCode")
	public ModelAndView viewAddDetailCode(ServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("codeMng/detailCodeForm");

		String codeId = StringUtils.defaultString(request.getParameter("codeId"), "");
		String detailCodeId = StringUtils.defaultString(request.getParameter("detailCodeId"), "");

		Code code = codeService.getCode(UUID.fromString(codeId));
		mav.addObject("code", code);

		mav.addObject("codeId", codeId);
		mav.addObject("detailCodeId", detailCodeId);

		return mav;
	}

	@RequestMapping("/codeMng/addDetailCode")
	@ResponseBody
	public String addDetailCode(ServletRequest request) throws Exception {
		String codeId = StringUtils.defaultString(request.getParameter("codeId"), "");
		DetailCode detailCode = convertDetailCodeByRequest(request);

		Code code = codeService.getCode(UUID.fromString(codeId));
		code.getDetailCodes().add(detailCode);
		codeService.editCode(code);

		return "등록이 완료 되었습니다.";
	}

	@RequestMapping("/codeMng/viewEditDetailCode")
	public ModelAndView viewEditDetailCode(ServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("codeMng/detailCodeForm");

		mav.addObject("pageMode", "EDIT");

		String codeId = StringUtils.defaultString(request.getParameter("codeId"), "");
		String detailCodeId = StringUtils.defaultString(request.getParameter("detailCodeId"), "");
		mav.addObject("codeId", codeId);
		mav.addObject("detailCodeId", detailCodeId);

		mav.addObject("code", codeService.getCode(codeId));

		return mav;
	}

	@RequestMapping("/codeMng/editDetailCode")
	@ResponseBody
	public String editDetailCode(ServletRequest request) throws Exception {

		String codeId = StringUtils.defaultString(request.getParameter("codeId"), "");

		DetailCode detailCode = convertDetailCodeByRequest(request);
		codeService.editDetailCode(codeId, detailCode);

		return "수정이 완료 되었습니다.";
	}

	@RequestMapping("/codeMng/delDetailCode")
	@ResponseBody
	public String delDetailCode(ServletRequest request) throws Exception {
		String codeId = StringUtils.defaultString(request.getParameter("codeId"), "");

		DetailCode detailCode = convertDetailCodeByRequest(request);
		codeService.deleteDetailCode(codeId, detailCode);

		return "삭제가 완료 되었습니다.";
	}

	private Code convertCodeByRequest(ServletRequest request) throws Exception {
		String _id = StringUtils.defaultString(request.getParameter("_id"), "");
		String paramCode = StringUtils.defaultString(request.getParameter("code"), "");
		String paramName = StringUtils.defaultString(request.getParameter("name"), "");
		String paramUseYn = StringUtils.defaultString(request.getParameter("useYn"), "N");

		Code code = new Code();
		if (_id.trim().length() > 0) {
			code = codeService.getCode(_id);
		}
		code.setCode(paramCode);
		code.setName(paramName);
		code.setUseYn(paramUseYn);
		return code;
	}

	private DetailCode convertDetailCodeByRequest(ServletRequest request) throws Exception {
		String codeId = StringUtils.defaultString(request.getParameter("codeId"), "");
		String detailCodeId = StringUtils.defaultString(request.getParameter("detailCodeId"), "").trim();
		String paramCode = StringUtils.defaultString(request.getParameter("code"), "");
		String paramName = StringUtils.defaultString(request.getParameter("name"), "");
		int paramSortNumber = Integer.parseInt(StringUtils.defaultString(request.getParameter("sortNumber"), "99"));
		String paramVal1 = StringUtils.defaultString(request.getParameter("val1"), "");
		String paramVal2 = StringUtils.defaultString(request.getParameter("val2"), "");
		String paramVal3 = StringUtils.defaultString(request.getParameter("val3"), "");
		String paramUseYn = StringUtils.defaultString(request.getParameter("useYn"), "N");

		DetailCode detailCode = new DetailCode();
		if ("".equals(detailCodeId)) {
			detailCode.setId(UUID.randomUUID());
			detailCode.setCreatedAt(LocalDateTime.now());
		} else {
			detailCode.setId(UUID.fromString(detailCodeId));
		}
		detailCode.setCode(paramCode);
		detailCode.setName(paramName);
		detailCode.setSortNumber(paramSortNumber);
		detailCode.setVal1(paramVal1);
		detailCode.setVal2(paramVal2);
		detailCode.setVal3(paramVal3);
		detailCode.setUseYn(paramUseYn);

		return detailCode;
	}
}
