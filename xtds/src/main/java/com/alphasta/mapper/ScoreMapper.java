package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Score;

public interface ScoreMapper {

	public String getMyScore(String userId);

	public List<Score> getScoreList(PageInfo pageInfo);

	public void addScore(Score score);

	public List<String> getAllHasSeenArt(Map map);

	public List<Score> findScoreByParam(Map<String, Object> param);

	public List<Score> getScoresByMonth(Map<String, Object> map);
}
