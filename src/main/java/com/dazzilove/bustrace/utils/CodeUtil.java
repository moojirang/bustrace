package com.dazzilove.bustrace.utils;

import com.dazzilove.bustrace.domain.PlateType;

import java.util.ArrayList;
import java.util.List;

public class CodeUtil {

	static List<PlateType> plateTypes = new ArrayList<>();

	public static List<PlateType> getPlateTypes() {
		if (plateTypes.isEmpty()) {
			plateTypes = new ArrayList<>();
			PlateType plateType;

			plateType = new PlateType();
			plateType.setCode("0");
			plateType.setName("정보없음");
			plateType.setImageSrc("/img/bus1f.png");
			plateTypes.add(plateType);

			plateType = new PlateType();
			plateType.setCode("1");
			plateType.setName("소형승합차");
			plateType.setImageSrc("/img/bus1f_0.png");
			plateTypes.add(plateType);

			plateType = new PlateType();
			plateType.setCode("2");
			plateType.setName("중형승합차");
			plateType.setImageSrc("/img/bus1f_1.png");
			plateTypes.add(plateType);

			plateType = new PlateType();
			plateType.setCode("3");
			plateType.setName("대형승합차");
			plateType.setImageSrc("/img/bus1f_2.png");
			plateTypes.add(plateType);

			plateType = new PlateType();
			plateType.setCode("4");
			plateType.setName("2층버스");
			plateType.setImageSrc("/img/bus2f_2.png");
			plateTypes.add(plateType);

			plateType = new PlateType();
			plateType.setCode("99");
			plateType.setName("전체");
			plateType.setImageSrc("/img/bus.png");
			plateTypes.add(plateType);
		}

		return plateTypes;
	}

}
