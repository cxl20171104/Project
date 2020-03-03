package com.alphasta.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.mapper.ScoreMapper;
import com.alphasta.mapper.ScoreOrganMapper;
import com.alphasta.model.Score_organization;
import com.alphasta.service.ScoreOrganService;
@Service
public class ScoreOrganServiceImpl implements ScoreOrganService {
	@Autowired
    public ScoreOrganMapper scoreOrganMapper;
	@Override
	public void addOrganScore(Score_organization scoreorgan) {
		// TODO Auto-generated method stub
		scoreOrganMapper.insertOne(scoreorgan);
	}

	@Override
	public List<Score_organization> getOrganscores(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return scoreOrganMapper.getScoreOrgan(map);
	}

}
