package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Concept;

public interface ConceptService {

	public List<Concept> getUseing(Map<String, String> map);

	public void updateUseing(Concept concept);

	public void addUseing(Concept concept);

}
