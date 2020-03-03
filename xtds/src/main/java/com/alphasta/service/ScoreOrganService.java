package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Score_organization;

public interface ScoreOrganService {
	
	void addOrganScore(Score_organization scororgan);
	List<Score_organization> getOrganscores(Map<String,Object> map);

}
