package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Concept;

public interface ConceptMapper {

	public List<Concept> getUseing(Map<String, String> map);

	public void updateUseing(Concept concept);

	public void addUseing(Concept concept);

}
