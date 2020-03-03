package com.alphasta.model;

public class LegalAct {
   String id;
   String reflectedId;
   String lawName;
   String lawTime;
   String lawResult;
   String time;
   public  LegalAct() {
		super();
	}
	public LegalAct(String id, String reflectedId, String lawName, String lawTime, String lawResult,String time) {
		super();
		this.id = id;
		this.reflectedId = reflectedId;
		this.lawName = lawName;
		this.lawTime = lawTime;
		this.lawResult = lawResult;
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReflectedId() {
		return reflectedId;
	}
	public void setReflectedId(String reflectedId) {
		this.reflectedId = reflectedId;
	}
	public String getLawName() {
		return lawName;
	}
	public void setLawName(String lawName) {
		this.lawName = lawName;
	}
	public String getLawTime() {
		return lawTime;
	}
	public void setLawTime(String lawTime) {
		this.lawTime = lawTime;
	}
	public String getLawResult() {
		return lawResult;
	}
	public void setLawResult(String lawResult) {
		this.lawResult = lawResult;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

}
