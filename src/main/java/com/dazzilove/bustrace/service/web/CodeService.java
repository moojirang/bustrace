package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.Code;
import com.dazzilove.bustrace.domain.DetailCode;

import java.util.List;
import java.util.UUID;

public interface CodeService {
	public void refresh() throws Exception;
	public Code getCode(UUID id) throws Exception;
	public List<Code> getCodeList() throws Exception;
	public Code getCode(String id) throws Exception;
	public void addCode(Code code) throws Exception;
	public void editCode(Code code) throws Exception;
	public void deleteCode(Code code) throws Exception;
	public void editDetailCode(String codeId, DetailCode detailCode) throws Exception;
	public void deleteDetailCode(String codeId, DetailCode editedDetailCode) throws Exception;
}
