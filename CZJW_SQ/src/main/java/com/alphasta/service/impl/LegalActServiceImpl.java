package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alphasta.mapper.LegalActMapper;
import com.alphasta.model.LegalAct;
import com.alphasta.service.LegalActService;

public class LegalActServiceImpl implements LegalActService{
    @Autowired 
    LegalActMapper legalActMapper;
	@Override
	public int addLegalAct(LegalAct legalAct) {
		int p=legalActMapper.addLegalAct(legalAct);
		return p;
	}

	@Override
	public LegalAct deleteLegalAct(String id) {
		// TODO Auto-generated method stub
		LegalAct deleteLegalAct = legalActMapper.deleteLegalAct(id);
		return deleteLegalAct;
	}

	@Override
	public LegalAct updateLegalAct(LegalAct legalAct) {
		// TODO Auto-generated method stub
		LegalAct updateLegalAct = legalActMapper.updateLegalAct(legalAct);
		return updateLegalAct;
	}

	@Override
	public LegalAct findLegalActById(String id) {
		// TODO Auto-generated method stub
		LegalAct findLegalActById = legalActMapper.findLegalActById(id);
		return findLegalActById;
	}

	@Override
	public List<LegalAct> findLegalActByRid(String rid) {
		// TODO Auto-generated method stub
		List<LegalAct> findLegalActByCid = legalActMapper.findLegalActByRid(rid);
		return findLegalActByCid;
	}

	@Override
	public LegalAct findLegalAct(LegalAct legalAct) {
		LegalAct findLegalAct = legalActMapper.findLegalAct(legalAct);
		return findLegalAct;
	}

   
}
