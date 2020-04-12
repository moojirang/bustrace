package com.dazzilove.bustrace.utils;

import com.dazzilove.bustrace.domain.Code;
import com.dazzilove.bustrace.domain.PlateType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CodeUtil implements ApplicationContextAware, InitializingBean {

	private static ApplicationContext applicationContext;

	public static Map<String, Code> codes = new HashMap<>();
	public static Map<String, PlateType> plateTypes = new HashMap<>();

	private CodeUtil() {
		//
	}

	public void setApplicationContext(ApplicationContext ctx) {
	    applicationContext = ctx;
    }

	@Override
	public void afterPropertiesSet() {
		CodeUtil.init();
	}

	public static void init() {
//		List<Code> codeList = new ArrayList<>();
//		try {
//			CodeService codeService = applicationContext.getBean(CodeService.class);
//			codeList = codeService.getCodeList();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		codes.clear();
//		codeList.forEach(code -> codes.put(code.getCode(), code));
	}

	public static Code getCode(String codeId) {
	    return codes.get(codeId);
	}

	public static PlateType getPlateType(String code) {
		return plateTypes.get(code);
	}

	public static Map<String, PlateType> getPlateTypes() {
//		Code code = codes.get("PLATE_TYPE");
//		List<DetailCode> detailCodes = code.getDetailCodes();
//
//		Map<String, PlateType> newPlateTypes = new HashMap<>();
//
//		newPlateTypes = detailCodes.stream().map(detailCode -> {
//			return PlateType.builder()
//					.code(detailCode.getCode())
//					.name(detailCode.getName())
//					.imageSrc(detailCode.getImg())
//					.build();
//		}).collect(Collectors.toMap(PlateType::getCode, p -> p));
//
//		if (plateTypes.isEmpty()) {
//			plateTypes = newPlateTypes;
//		}
//
//		return newPlateTypes;
		return null;
	}

}
