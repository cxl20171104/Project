package com.alphasta.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.ScoreMapper;
import com.alphasta.mapper.StatisticsMapper;
import com.alphasta.mapper.UserMapper;
import com.alphasta.model.Organization;
import com.alphasta.model.Score;
import com.alphasta.model.ScoreTj;
import com.alphasta.model.Statistics;
import com.alphasta.model.User;
import com.alphasta.service.ScoreService;

/**
 * 积分
 * 
 * @author sjb
 * 
 */
@Service
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	private ScoreMapper scoreMapper;

	@Autowired
	private StatisticsMapper statisticsMapper;
	@Autowired
	private UserMapper userMapper;
	@Override
	public String getMyScore(String userId) {

		return scoreMapper.getMyScore(userId);
	}

	@Override
	public List<Score> getScoreList(PageInfo pageInfo) {

		return scoreMapper.getScoreList(pageInfo);
	}

	@Override
	public void addScore(Score score) {

		scoreMapper.addScore(score);
	}

	/**
	 * 获取积分汇总表
	 */
	public List<Statistics> getAllStatis(Map<String, String> map) {

		return statisticsMapper.getStatList(map);
	}

	/**
	 * 积分详情
	 */
	@Override
	public List<Score> findScoreList(String ctime, Long userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("ctime", ctime);
		return scoreMapper.findScoreByParam(map);
	}

	@Override
	public List<Statistics> getStateByType(Map<String, Object> map) {

		return statisticsMapper.getStatResult(map);
	}

	@Override
	public List<Organization> getListByOrgan(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return statisticsMapper.getlistByOrg(map);
	}

	@Override
	public List<Score> getUserScoresByMonth(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return scoreMapper.getScoresByMonth(param);
	}

	@Override
	public Map<String,List<Score>> scoreTj(ScoreTj scoreTj) {
		String userids=scoreTj.getUsers();
		String[] ids=userids.split(",");
		String startTime=scoreTj.getStartTime();
		String endTime=scoreTj.getEndTime();
		Map<String,Object>param=new HashMap<String,Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		Map<String,List<Score>>result=new HashMap<String,List<Score>>();
		for(String userid:ids) {
			User user = userMapper.findUserById(Long.valueOf(userid));
			param.put("userid", userid);
			List<Score> findScoreByParam = scoreMapper.findScoreByParam(param);
			List<Score> result1=new ArrayList<Score>();
			Map<String,Object>data=new HashMap<String,Object>();
			for(Score s:findScoreByParam) {
				if(data.get(s.getType()+"_VALUE")!=null){
					if(data.get(s.getType()+"_VALUE") instanceof String) {
						String value=(String)data.get(s.getType()+"_VALUE");
						data.put(s.getType()+"_VALUE", Integer.valueOf(value)+Integer.valueOf(s.getScorevalue()));
					}else {
						Integer value=(Integer)data.get(s.getType()+"_VALUE");
						data.put(s.getType()+"_VALUE", value+Integer.valueOf(s.getScorevalue()));
					}
					
				}else {
					data.put(s.getType()+"_VALUE", s.getScorevalue());
					data.put(s.getType()+"_NAME",  s.getName());
					data.put(s.getType()+"_DESCR", s.getDescr());
					data.put(s.getType()+"_CTIME", s.getCtime());
					data.put(s.getType()+"_TYPE",  s.getType());
					
				}
			}
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				if(key.endsWith("TYPE")) {
					Score s=new Score();
					if(data.get(key.replace("TYPE", "VALUE"))!=null&&data.get(key.replace("TYPE", "VALUE")) instanceof String){
						s.setScores((String)data.get(key.replace("TYPE", "VALUE")));
					}else if(data.get(key.replace("TYPE", "VALUE"))!=null&&data.get(key.replace("TYPE", "VALUE")) instanceof Integer){
						Integer i=(Integer)data.get(key.replace("TYPE", "VALUE"));
						s.setScores(i.toString());
					}
					
					s.setCtime((Date)data.get(key.replace("TYPE", "CTIME")));
					s.setDescr((String)data.get(key.replace("TYPE", "DESCR")));
					s.setName((String)data.get(key.replace("TYPE", "NAME")));
					s.setUserid(user.getName());
					s.setScorevalue(user.getLiveness());
					s.setType((Integer)data.get(key));
					result1.add(s);
					result.put(user.getName(), result1);
				}
			}
		}
		
		
		return result;
	}
	
	
	

}
