package com.alphasta.controller.app;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.mapper.AnswerlogMapper;
import com.alphasta.mapper.QuestionnaireMapper;
import com.alphasta.mapper.ScoreMapper;
import com.alphasta.model.AnswerLog;
import com.alphasta.model.Questionnaire;
import com.alphasta.model.Score;
import com.alphasta.model.User;
import com.alphasta.service.AnswerlogService;
import com.alphasta.service.QuestionnaireService;
import com.alphasta.service.ScoreService;

@Controller
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	private AnswerlogService answerlogService;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private QuestionnaireService questionnaireService;

	@RequestMapping("/submit.json")
	@ResponseBody
	@AppLog
	public Result submit(HttpServletRequest request,
			HttpServletResponse response, String userscore, String scores,
			Long userId, String userName, String answer_id, int scorestype,
			String qgp, String name, String hd_name, String questionName,
			String isDa) {
		Result result = new Result();
		User user = (User) request.getAttribute("appUser");
		if ("1".equals(isDa)) {
			result.setMsg("1");
			return result;
		}
		try {
			// 答题人数加1
			int nums = 0;
			if (answer_id != null) {
				List<Questionnaire> q = questionnaireService
						.findQuestionNaireById(answer_id);
				for (Questionnaire questionnaire : q) {
					nums = questionnaire.getNums();
				}
			}
			nums += 1;
			System.out.println("答题人数:" + nums);
			Questionnaire qt = new Questionnaire();
			qt.setId(answer_id);
			qt.setNums(nums);
			questionnaireService.updateQuestionNaireById(qt);
			AnswerLog ag = new AnswerLog();
			if (scores != null && userId != null && userName != null
					&& userscore != null) {
				int userscore1 = Integer.parseInt(userscore);
				String uuid = GetIdUtil.getId();
				ag.setId(uuid);
				ag.setUserid(userId);
				ag.setName(userName);
				ag.setAnswertime(new Date());
				ag.setScore(userscore1);
				ag.setScores(scores);
				ag.setQuestionId(answer_id);
				// 保存用户信息
				answerlogService.insert(ag);
				// 保存积分信息
				/*if (user.getOrganizationId() != 1) {*/
					Score s = new Score();
					s.setId(uuid); // id
					s.setCtime(new Date()); // 得分时间
					s.setType(5);
					// 把long类型转换为string
					String userid = String.valueOf(userId);
					s.setUserid(userid);
					s.setScoresource(answer_id);
					s.setQgpend(qgp);
					s.setScorevalue(scores);
					s.setDescr("参与答题<" + questionName + ">获得积分");
					scoreService.addScore(s); // 保存积分
				/*}*/
				request.setAttribute("remark", "参加<" + hd_name + "活动>,完成答题<"
						+ name + ">,得分:" + userscore1 + "分");
				request.setAttribute("liveness", scores);
				request.setAttribute("openType", "3");
				result.setMsg("1"); // 保存成功

			} else {
				result.setMsg("0");
			}
		} catch (Exception e) {
			result.setMsg("2");
		}
		return result;
	}

	@RequestMapping("/yadt.json")
	@ResponseBody
	@AppLog
	public Result yadt(HttpServletRequest request,
			HttpServletResponse response, Long userId, String questionId) {
		Result result = new Result();
		try {
			if (userId != null && !"".equals(userId) && questionId != null) {
				AnswerLog ag = new AnswerLog();
				ag.setQuestionId(questionId);
				ag.setUserid(userId);
				List<AnswerLog> list = answerlogService.selectByquentionId(ag);
				if (list.size() <= 0) {
					result.setMsg("0");// 可以答题
				} else
					result.setObj(list);
			}
		} catch (Exception e) {
			result.setMsg("出错了");
		}
		return result;
	}

}
