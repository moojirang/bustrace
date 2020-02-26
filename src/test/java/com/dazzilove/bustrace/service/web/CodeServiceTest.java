package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.Code;
import com.dazzilove.bustrace.domain.DetailCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class CodeServiceTest {

    @Autowired
    CodeService codeService;

    @Test
    public void defaultCodeInsertTest() {
        insertPlateType();
    }

    private void insertPlateType() {
        List<DetailCode> detailCode = new ArrayList<>();
        DetailCode plateTypeDetailCode = new DetailCode();
        plateTypeDetailCode.setId(UUID.randomUUID());
        plateTypeDetailCode.setCode("1");
        plateTypeDetailCode.setName("소형승합차");
        plateTypeDetailCode.setImg("/img/bus1f_0.png");
        plateTypeDetailCode.setSortNumber(1);
        plateTypeDetailCode.setUseYn("Y");
        plateTypeDetailCode.setDelYn("N");
        plateTypeDetailCode.setCreatedAt(LocalDateTime.now());
        plateTypeDetailCode.setUpdatedAt(LocalDateTime.now());
        detailCode.add(plateTypeDetailCode);

        plateTypeDetailCode = new DetailCode();
        plateTypeDetailCode.setId(UUID.randomUUID());
        plateTypeDetailCode.setCode("2");
        plateTypeDetailCode.setName("중형승합차");
        plateTypeDetailCode.setImg("/img/bus1f_1.png");
        plateTypeDetailCode.setSortNumber(2);
        detailCode.add(plateTypeDetailCode);

        plateTypeDetailCode = new DetailCode();
        plateTypeDetailCode.setId(UUID.randomUUID());
        plateTypeDetailCode.setCode("3");
        plateTypeDetailCode.setName("대형승합차");
        plateTypeDetailCode.setImg("/img/bus1f_2.png");
        plateTypeDetailCode.setSortNumber(3);
        detailCode.add(plateTypeDetailCode);

        plateTypeDetailCode = new DetailCode();
        plateTypeDetailCode.setId(UUID.randomUUID());
        plateTypeDetailCode.setCode("4");
        plateTypeDetailCode.setName("2층버스");
        plateTypeDetailCode.setImg("/img/bus2f_2.png");
        plateTypeDetailCode.setSortNumber(4);
        detailCode.add(plateTypeDetailCode);

        plateTypeDetailCode = new DetailCode();
        plateTypeDetailCode.setId(UUID.randomUUID());
        plateTypeDetailCode.setCode("99");
        plateTypeDetailCode.setName("전체");
        plateTypeDetailCode.setImg("/img/bus.png");
        plateTypeDetailCode.setSortNumber(99);
        detailCode.add(plateTypeDetailCode);

        Code plateType = new Code();
        plateType.setId(UUID.randomUUID());
        plateType.setCode("PLATE_TYPE");
        plateType.setName("차량타입");
        plateType.setUseYn("Y");
        plateType.setDelYn("N");
        plateType.setCreatedAt(LocalDateTime.now());
        plateType.setUpdatedAt(LocalDateTime.now());
        plateType.setDetailCodes(detailCode);
    }
}
