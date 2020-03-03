package com.alphasta.controller.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.stat.TableStat.Condition;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.app.AppController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.Config;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.AnswerlogMapper;
import com.alphasta.mapper.OptionsMapper;
import com.alphasta.mapper.QuestionMapper;
import com.alphasta.mapper.QuestionnaireMapper;
import com.alphasta.model.AnswerLog;
import com.alphasta.model.Options;
import com.alphasta.model.Question;
import com.alphasta.model.Questionnaire;
import com.alphasta.service.AnswerlogService;
import com.alphasta.service.OptionService;
import com.alphasta.service.QuestionService;
import com.alphasta.service.QuestionnaireService;
import com.mysql.fabric.xmlrpc.base.Array;

@Controller
@RequestMapping("/question")
public class QuestionConntroller {

	@Autowired
	private QuestionnaireService questionnaireService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private OptionService optionService;
	@Autowired
	private AnswerlogService answerlogService;

	@RequestMapping("/listShow.json")
	@ResponseBody
	public Result listShow(Long userId, String token, String answerId,
			String pageSize, String pageNum, HttpServletRequest request,
			HttpServletResponse response) {
		//每次加载100条
		pageSize="100";
		PageInfo pageInfo = new PageInfo();
		Result result = new Result();
		if (pageSize != null && pageNum != null && answerId != null) {
			int pagesize = Integer.parseInt(pageSize);
			int pagenum = Integer.parseInt(pageNum);
			pageInfo.setFrom((pagenum - 1) * pagesize);
			pageInfo.setSize(pagesize);
			Map<String, Object> map = new HashMap<String, Object>();
			// map.put("adtivities", answerId);
			map.put("userId", userId);
			pageInfo.setCondition(map);
			List<Questionnaire> list = questionnaireService
					.findQuestionNaire(pageInfo);
			if (list.size() == 0) {
				result.setMsg("0");// 没有查询到数据
			} else {
				AnswerLog al = new AnswerLog();
				for (int i = 0; i < list.size(); i++) {
					al.setQuestionId(list.get(i).getId());
					al.setUserid(userId);
					List<AnswerLog> list2 = answerlogService
							.selectByquentionId(al);
					if (list2.size() != 0) {
						// 临时占用etime字段为:是否过问题 1答过,0未答
						for (int j = 0; j < list2.size(); j++) {
							if (list.get(i).getId()
									.equals(list2.get(j).getQuestionId())
									&& userId.equals(list2.get(j).getUserid())) {
								list.get(i).setEtime("1");
							} else {
								list.get(i).setEtime("0");
							}
						}
					} else {
						list.get(i).setEtime("0");
					}
				}
				// 根据已答.未答排序
				Collections.sort(list, new Comparator<Questionnaire>() {
					public int compare(Questionnaire o1, Questionnaire o2) {
						return o1.getEtime().compareTo(o2.getEtime());
					}
				});
				// 转换
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getEtime() == "1") {
						list.get(i).setEtime(Config.QUESTION_STATE_YD);
					} else if (list.get(i).getEtime() == "0") {
						list.get(i).setEtime(Config.QUESTION_STATE_WD);
					}
				}
				result.setObj(list);
			}
		}
		return result;
	}

	@RequestMapping("/answerlist.json")
	@ResponseBody
	public Result answerlist(String cx_id, String xxh) {
		Result result = new Result();
		if (cx_id != null && !"".equals(cx_id)) {
			// 得到题目表中的questionnaire字段cx_id
			String xxh1 = String.valueOf(xxh);
			int xxh2 = Integer.parseInt(xxh1);
			if (xxh2 <= 0) {
				result.setMsg("0");
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("questionnaire", cx_id);
				map.put("answer", xxh1);
				List<Question> list = questionService.findByQuestionnate(map);
				if (list.size() == 0 && !"".equals(list)) {
					result.setMsg("1");
				} else {
					Question qt = new Question();
					qt.setId(list.get(0).getId());
					qt.setQuestionnaire(list.get(0).getQuestionnaire());
					qt.setTopic(list.get(0).getTopic());
					qt.setType(list.get(0).getType());
					qt.setAnswer(list.get(0).getAnswer());
					qt.setScore(list.get(0).getScore());
					if (list.size() >= 1) {
						String question = list.get(0).getId();
						List<Options> list1 = optionService
								.findByQuestion(question);
						// 排序
						Collections.sort(list1, new Comparator<Options>() {
							public int compare(Options o1, Options o2) {
								String a = o1.getNum();
								String b = o2.getNum();
								return a.compareTo(b);
							}
						});
						Map<Question, List<Options>> map1 = new HashMap<Question, List<Options>>();
						map1.put(qt, list1);

						result.setObj(map1);// 返回查询的题目
					} else {
						result.setMsg("1");
					}
				}
			}
		} else {
			result.setMsg("-1");// 请求失败
		}
		return result;
	}

}
