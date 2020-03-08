package com.dazzilove.bustrace.controller.setting;

import com.dazzilove.bustrace.domain.Code;
import com.dazzilove.bustrace.domain.DetailCode;
import com.dazzilove.bustrace.repository.CodeRepository;
import com.dazzilove.bustrace.service.web.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CodeSettingController {

    @Autowired
    CodeService codeService;

    @Autowired
    CodeRepository codeRepository;

    @RequestMapping("/setting/code")
    @ResponseBody
    public String insertCode() {
        insertPlateType();
        insertWeekendOperation();
        insertSpareTripYn();
        insertSchoolBreakReductionYn();
        insertTripStopYn();

        return "Insert Code is Done";
    }

    private void insertPlateType() {
        List<DetailCode> detailCodes = new ArrayList<>();

        DetailCode detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("1");
        detailCode.setName("소형승합차");
        detailCode.setImg("/img/bus1f_0.png");
        detailCode.setSortNumber(1);
        detailCode.setUseYn("Y");
        detailCode.setDelYn("N");
        detailCode.setCreatedAt(LocalDateTime.now());
        detailCode.setUpdatedAt(LocalDateTime.now());
        detailCodes.add(detailCode);

        detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("2");
        detailCode.setName("중형승합차");
        detailCode.setImg("/img/bus1f_1.png");
        detailCode.setSortNumber(2);
        detailCodes.add(detailCode);

        detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("3");
        detailCode.setName("대형승합차");
        detailCode.setImg("/img/bus1f_2.png");
        detailCode.setSortNumber(3);
        detailCodes.add(detailCode);

        detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("4");
        detailCode.setName("2층버스");
        detailCode.setImg("/img/bus2f_2.png");
        detailCode.setSortNumber(4);
        detailCodes.add(detailCode);

        detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("99");
        detailCode.setName("전체");
        detailCode.setImg("/img/bus.png");
        detailCode.setSortNumber(99);
        detailCodes.add(detailCode);

        Code code = new Code();
        code.setId(UUID.randomUUID());
        code.setCode("PLATE_TYPE");
        code.setName("차량타입");
        code.setUseYn("Y");
        code.setDelYn("N");
        code.setCreatedAt(LocalDateTime.now());
        code.setUpdatedAt(LocalDateTime.now());
        code.setDetailCodes(detailCodes);

        codeRepository.save(code);
    }

    private void insertWeekendOperation() {
        List<DetailCode> detailCodes = new ArrayList<>();

        DetailCode detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("Y");
        detailCode.setName("주말운행 O");
        detailCode.setSortNumber(1);
        detailCode.setUseYn("Y");
        detailCode.setDelYn("N");
        detailCode.setCreatedAt(LocalDateTime.now());
        detailCode.setUpdatedAt(LocalDateTime.now());
        detailCodes.add(detailCode);

        detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("N");
        detailCode.setName("주말운행 X");
        detailCode.setSortNumber(2);
        detailCode.setUseYn("Y");
        detailCode.setDelYn("N");
        detailCode.setCreatedAt(LocalDateTime.now());
        detailCode.setUpdatedAt(LocalDateTime.now());
        detailCodes.add(detailCode);

        Code code = new Code();
        code.setId(UUID.randomUUID());
        code.setCode("WEEKEND_OPERATION_YN");
        code.setName("주말운행여부");
        code.setUseYn("Y");
        code.setDelYn("N");
        code.setCreatedAt(LocalDateTime.now());
        code.setUpdatedAt(LocalDateTime.now());
        code.setDetailCodes(detailCodes);

        codeRepository.save(code);
    }

    private void insertSpareTripYn() {
        List<DetailCode> detailCodes = new ArrayList<>();

        DetailCode detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("Y");
        detailCode.setName("예비차 O");
        detailCode.setSortNumber(1);
        detailCode.setUseYn("Y");
        detailCode.setDelYn("N");
        detailCode.setCreatedAt(LocalDateTime.now());
        detailCode.setUpdatedAt(LocalDateTime.now());
        detailCodes.add(detailCode);

        detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("N");
        detailCode.setName("예비차 X");
        detailCode.setSortNumber(2);
        detailCode.setUseYn("Y");
        detailCode.setDelYn("N");
        detailCode.setCreatedAt(LocalDateTime.now());
        detailCode.setUpdatedAt(LocalDateTime.now());
        detailCodes.add(detailCode);

        Code code = new Code();
        code.setId(UUID.randomUUID());
        code.setCode("SPARE_TRIP_YN");
        code.setName("예비차여부");
        code.setUseYn("Y");
        code.setDelYn("N");
        code.setCreatedAt(LocalDateTime.now());
        code.setUpdatedAt(LocalDateTime.now());
        code.setDetailCodes(detailCodes);

        codeRepository.save(code);
    }

    private void insertSchoolBreakReductionYn() {
        List<DetailCode> detailCodes = new ArrayList<>();

        DetailCode detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("Y");
        detailCode.setName("방학감차 O");
        detailCode.setSortNumber(1);
        detailCode.setUseYn("Y");
        detailCode.setDelYn("N");
        detailCode.setCreatedAt(LocalDateTime.now());
        detailCode.setUpdatedAt(LocalDateTime.now());
        detailCodes.add(detailCode);

        detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("N");
        detailCode.setName("방학감차 X");
        detailCode.setSortNumber(2);
        detailCode.setUseYn("Y");
        detailCode.setDelYn("N");
        detailCode.setCreatedAt(LocalDateTime.now());
        detailCode.setUpdatedAt(LocalDateTime.now());
        detailCodes.add(detailCode);

        Code code = new Code();
        code.setId(UUID.randomUUID());
        code.setCode("SCHOOL_BREAK_REDUCTION_YN");
        code.setName("방학감차여부");
        code.setUseYn("Y");
        code.setDelYn("N");
        code.setCreatedAt(LocalDateTime.now());
        code.setUpdatedAt(LocalDateTime.now());
        code.setDetailCodes(detailCodes);

        codeRepository.save(code);
    }

    private void insertTripStopYn() {
        List<DetailCode> detailCodes = new ArrayList<>();

        DetailCode detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("Y");
        detailCode.setName("운행중단 O");
        detailCode.setSortNumber(1);
        detailCode.setUseYn("Y");
        detailCode.setDelYn("N");
        detailCode.setCreatedAt(LocalDateTime.now());
        detailCode.setUpdatedAt(LocalDateTime.now());
        detailCodes.add(detailCode);

        detailCode = new DetailCode();
        detailCode.setId(UUID.randomUUID());
        detailCode.setCode("N");
        detailCode.setName("운행중단 X");
        detailCode.setSortNumber(2);
        detailCode.setUseYn("Y");
        detailCode.setDelYn("N");
        detailCode.setCreatedAt(LocalDateTime.now());
        detailCode.setUpdatedAt(LocalDateTime.now());
        detailCodes.add(detailCode);

        Code code = new Code();
        code.setId(UUID.randomUUID());
        code.setCode("TRIP_STOP_YN");
        code.setName("운행중단여부");
        code.setUseYn("Y");
        code.setDelYn("N");
        code.setCreatedAt(LocalDateTime.now());
        code.setUpdatedAt(LocalDateTime.now());
        code.setDetailCodes(detailCodes);

        codeRepository.save(code);
    }
}