package com.alphasta.model;

import java.util.Date;

import com.alibaba.fastjson.JSON;

public class Activities {
	private String id;

	private String name;

	private String topic;

	private Integer type;

	private String dept;

	private String etime;

	private Integer useable;

	private String picture;

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic == null ? null : topic.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept == null ? null : dept.trim();
	}

	public Integer getUseable() {
		return useable;
	}

	public void setUseable(Integer useable) {
		this.useable = useable;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}