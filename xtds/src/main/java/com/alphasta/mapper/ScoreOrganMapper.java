package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Score_organization;

public interface ScoreOrganMapper {
   List<Score_organization> getScoreOrgan(Map<String,Object> map);
   void insertOne(Score_organization scoreorgan);
}
