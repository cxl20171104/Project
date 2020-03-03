package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.LegalAct;

public interface LegalActMapper {
	//
	int addLegalAct(LegalAct legalAct);
	//
	LegalAct deleteLegalAct (String id);
	//
	LegalAct updateLegalAct(LegalAct legalAct);
	//
	LegalAct findLegalActById(String id);
	//
	List<LegalAct> findLegalActByRid(String rid);
	//
	LegalAct findLegalAct(LegalAct legalAct);
}
