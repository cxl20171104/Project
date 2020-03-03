package com.alphasta.model;

import com.alibaba.fastjson.JSON;

public class Options {
	private String id;

	private String question;

	private String num;

	private String content;

	private Integer isanswer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question == null ? null : question.trim();
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num == null ? null : num.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getIsanswer() {
		return isanswer;
	}

	public void setIsanswer(Integer isanswer) {
		this.isanswer = isanswer;
	}

	@Override
	public String toString() {

		return JSON.toJSONString(this);
	}

}