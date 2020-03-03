package com.alphasta.model;

import com.alibaba.fastjson.JSON;

public class Question {
	private String id;

	private Integer method;

	private String topic;

	private String questionnaire;

	private String score;

	private Integer type;
	// 选项序号，不要翻译
	private String answer;

	@Override
	public String toString() {

		return JSON.toJSONString(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic == null ? null : topic.trim();
	}

	public String getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(String questionnaire) {
		this.questionnaire = questionnaire == null ? null : questionnaire
				.trim();
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score == null ? null : score.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer == null ? null : answer.trim();
	}
}