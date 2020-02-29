package com.dazzilove.bustrace.utils;

import com.dazzilove.bustrace.domain.Code;
import com.dazzilove.bustrace.domain.DetailCode;
import com.dazzilove.bustrace.domain.PlateType;
import com.dazzilove.bustrace.service.web.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CodeUtil {

	CodeService codeService;

	public static Map<String, Code> codes = new HashMap<>();
	public static Map<String, PlateType> plateTypes = new HashMap<>();

	public CodeUtil(@Autowired CodeService codeService) {
		this.codeService = codeService;
		init();
	}

	public void init() {
		List<Code> codeList = new ArrayList<>();
		try {
			codeList = codeService.getCodeList();
		} catch (Exception e) { }
		for(Code code: codeList) {
			codes.put(code.getCode(), code);
		}
	}

	public static PlateType getPlateType(String code) {
		return plateTypes.get(code);
	}

	public static Map<String, PlateType> getPlateTypes() {
		Code code = codes.get("PLATE_TYPE");
		List<DetailCode> detailCodes = code.getDetailCodes();

		Map<String, PlateType> newPlateTypes = new HashMap<>();
		PlateType plateType;

		for(DetailCode detailCode: detailCodes) {
			plateType = new PlateType();
			plateType.setCode(detailCode.getCode());
			plateType.setName(detailCode.getName());
			plateType.setImageSrc(detailCode.getImg());
			newPlateTypes.put(plateType.getCode(), plateType);
		}

		if (plateTypes.isEmpty()) {
			plateTypes = newPlateTypes;
		}

		return newPlateTypes;
	}

}
