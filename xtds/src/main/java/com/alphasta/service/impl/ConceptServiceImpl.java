package com.alphasta.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.mapper.ConceptMapper;
import com.alphasta.model.Concept;
import com.alphasta.service.ConceptService;

@Service
public class ConceptServiceImpl implements ConceptService {
	@Autowired
	private ConceptMapper conceptMapper;

	@Override
	public List<Concept> getUseing(Map<String, String> map) {
		// TODO Auto-generated method stub
		return conceptMapper.getUseing(map);
	}

	@Override
	public void updateUseing(Concept concept) {
		conceptMapper.updateUseing(concept);

	}

	@Override
	public void addUseing(Concept concept) {
		conceptMapper.addUseing(concept);
	}

}
