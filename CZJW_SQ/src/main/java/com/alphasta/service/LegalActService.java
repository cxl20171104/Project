package com.alphasta.service;

import java.util.List;

import com.alphasta.model.LegalAct;

public interface LegalActService {
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
