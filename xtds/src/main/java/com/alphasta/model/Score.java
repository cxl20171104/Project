package com.alphasta.model;

import java.util.Date;

public class Score {
	private String id;

	private Integer type; // 积分类型

	private String userid;

	private String scoresource; // 获取积分途径的ID

	private String descr; // 获取积分的途径名称

	private String qgpend;

	private Date ctime;

	private String scorevalue;

	private String url; // 奖励积分图片路径

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	private String ctimeStr; // 获取时间 字符串类型

	public String getCtimeStr() {
		return ctimeStr;
	}

	public void setCtimeStr(String ctimeStr) {
		this.ctimeStr = ctimeStr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid == null ? null : userid.trim();
	}

	public String getScoresource() {
		return scoresource;
	}

	public void setScoresource(String scoresource) {
		this.scoresource = scoresource == null ? null : scoresource.trim();
	}

	public String getQgpend() {
		return qgpend;
	}

	public void setQgpend(String qgpend) {
		this.qgpend = qgpend == null ? null : qgpend.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getScorevalue() {
		return scorevalue;
	}

	public void setScorevalue(String scorevalue) {
		this.scorevalue = scorevalue == null ? null : scorevalue.trim();
	}

	private String name; // 积分人
	private String scores; // 分值

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

}