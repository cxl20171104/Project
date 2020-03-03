package com.alphasta.commons.result;

public class BorthMsg {

	private String id; // 用户ID
	private String name; // 用户名
	private String ageNum; // 入党几周年
	private String intime; // 入党时间

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgeNum() {
		return ageNum;
	}

	public void setAgeNum(String ageNum) {
		this.ageNum = ageNum;
	}

}
