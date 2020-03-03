package com.alphasta.model;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Questionnaire {
	private String id;

	private String activities;

	private String name;

	private String pubshlisher;
	private String ctime;

	private Integer nums;

	private Integer amount;
	private String btime;
	private String etime;

	private String scores;

	private String award;

	private Integer scorestype;

	private String total;

	private String qgp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities == null ? null : activities.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPubshlisher() {
		return pubshlisher;
	}

	public void setPubshlisher(String pubshlisher) {
		this.pubshlisher = pubshlisher == null ? null : pubshlisher.trim();
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getBtime() {
		return btime;
	}

	public void setBtime(String btime) {
		this.btime = btime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores == null ? null : scores.trim();
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award == null ? null : award.trim();
	}

	public Integer getScorestype() {
		return scorestype;
	}

	public void setScorestype(Integer scorestype) {
		this.scorestype = scorestype;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total == null ? null : total.trim();
	}

	public String getQgp() {
		return qgp;
	}

	public void setQgp(String qgp) {
		this.qgp = qgp == null ? null : qgp.trim();
	}

	@Override
	public String toString() {

		return JSON.toJSONString(this);
	}
}