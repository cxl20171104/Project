package com.alphasta.model;

import java.util.Date;

import com.alibaba.fastjson.JSON;

public class AnswerLog {

	private String id;
	private Long userid; // 用户id
	private String name; // 用户名字
	private String scores; // 积分
	private int score; // 得分
	private Date answertime; // 答题结束时间
	private String questionId;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getAnswertime() {
		return answertime;
	}

	public void setAnswertime(Date answertime) {
		this.answertime = answertime;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
