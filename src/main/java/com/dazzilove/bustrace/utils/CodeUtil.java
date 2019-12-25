package com.dazzilove.bustrace.utils;

import com.dazzilove.bustrace.domain.PlateType;

import java.util.HashMap;
import java.util.Map;

public class CodeUtil {

	static Map<String, PlateType> plateTypes = new HashMap<>();

	public static String getPlateTypeName(String code) {
		return plateTypes.get(code).getName();
	}

	public static Map<String, PlateType> getPlateTypes() {
		Map<String, PlateType> newPlateTypes = new HashMap<>();
		PlateType plateType;

		plateType = new PlateType();
		plateType.setCode("0");
		plateType.setName("정보없음");
		plateType.setImageSrc("/img/bus1f.png");
		newPlateTypes.put(plateType.getCode(), plateType);

		plateType = new PlateType();
		plateType.setCode("1");
		plateType.setName("소형승합차");
		plateType.setImageSrc("/img/bus1f_0.png");
		newPlateTypes.put(plateType.getCode(), plateType);

		plateType = new PlateType();
		plateType.setCode("2");
		plateType.setName("중형승합차");
		plateType.setImageSrc("/img/bus1f_1.png");
		newPlateTypes.put(plateType.getCode(), plateType);

		plateType = new PlateType();
		plateType.setCode("3");
		plateType.setName("대형승합차");
		plateType.setImageSrc("/img/bus1f_2.png");
		newPlateTypes.put(plateType.getCode(), plateType);

		plateType = new PlateType();
		plateType.setCode("4");
		plateType.setName("2층버스");
		plateType.setImageSrc("/img/bus2f_2.png");
		newPlateTypes.put(plateType.getCode(), plateType);

		plateType = new PlateType();
		plateType.setCode("99");
		plateType.setName("전체");
		plateType.setImageSrc("/img/bus.png");
		newPlateTypes.put(plateType.getCode(), plateType);

		if (plateTypes.isEmpty()) {
			plateTypes = newPlateTypes;
		}

		return newPlateTypes;
	}

}
