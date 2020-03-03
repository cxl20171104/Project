package com.alphasta.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PicPathMaker;
import com.alphasta.mapper.AppLogMapper;
import com.alphasta.model.AppLog;
import com.alphasta.model.Score;
import com.alphasta.service.AppLogService;
import com.alphasta.service.ArticleService;
import com.alphasta.service.ScoreService;

@Service("appLogService")
public class AppLogServiceImpl implements AppLogService {
	@Autowired
	private AppLogMapper appLogMapper;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private ArticleService articleService;

	public void addLog(AppLog log) {

		appLogMapper.addLog(log);

	}

	@Override
	public List<AppLog> selectByCondition(AppLog log) {
		// TODO Auto-generated method stub
		return appLogMapper.selectByCondition(log);
	}

	// 获取日志前三天的阅读的文章
	// 答题
	public List<Map<String, Object>> getEveryThing(Long operater, String start,
			String end) {

		List<Map<String, String>> list = searchCzlist(operater,Integer.valueOf(start), Integer.valueOf(end));
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Map<String, String> m : list) {
			//查score这张表
			System.out.println(m.get("day"));
			List<Score> scorelist = scoreService.findScoreList(m.get("day"), operater);
			
			Map<String,Object>have=new HashMap<String,Object>();
			//文章总分
			float articleScore = 0;
			//答题总分
			float questionScore = 0;
			//答题数量
			int questionNum = 0;
			//看文章数量
			int articleNum = 0;
			//评论文章数量
			int pingNum=0;
			//评论得分
			float pingScore=0;
			for(Score s:scorelist){
				System.out.println(s.getType()+"=======================================");
				//发布文章
				if(s.getType()==4){
					articleNum++;
					articleScore+=Float.valueOf(s.getScorevalue());
				}
				//参与答题
				if(s.getType()==5){
					questionNum++;
					questionScore+=Float.valueOf(s.getScorevalue());
				}
				//评论文章
				if(s.getType()==2){
					pingNum++;
					pingScore+=Float.valueOf(s.getScorevalue());
				}	
				
			}
			
			
			
			
			System.out.println(questionNum+"=====================================================");
			have.put("questionNum", questionNum);
			have.put("articleNum", articleNum);
			
			
			have.put("totalArticleScore", articleScore);
			have.put("questionScore", questionScore);
			
			have.put("pingScore", pingScore);
			have.put("pingNum", pingNum);
			
			have.put("time", m.get("day"));
			result.add(have);

		}

		return result;
	}

	@Override
	public List<Map<String, String>> searchCzlist(Long operater, int start,
			int end) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("operater", operater);
		param.put("start", start);
		param.put("end", end);
		return appLogMapper.searchCzlist(param);
	}

	@Override
	public List<AppLog> findCzByTimeAndOperater(String otime, Long operater) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("otime", otime);
		param.put("operater", operater);
		return appLogMapper.findCzByTimeAndOperater(param);
	}

}
