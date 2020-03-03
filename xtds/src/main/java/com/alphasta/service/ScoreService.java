package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Organization;
import com.alphasta.model.Score;
import com.alphasta.model.ScoreTj;
import com.alphasta.model.Statistics;

public interface ScoreService {

	public String getMyScore(String userId);

	public List<Score> getScoreList(PageInfo pageInfo);

	public void addScore(Score score);

	public List<Statistics> getAllStatis(Map<String, String> map);

	public List<Score> findScoreList(String ctime, Long userid);

	public List<Statistics> getStateByType(Map<String, Object> map);

	public List<Organization> getListByOrgan(Map<String, Object> map);

	public List<Score> getUserScoresByMonth(Map<String, Object> map);
	
	public Map<String,List<Score>> scoreTj(ScoreTj scoreTj);

}
