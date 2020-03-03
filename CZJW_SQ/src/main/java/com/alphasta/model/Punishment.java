package com.alphasta.model;

public class Punishment {
   String id;
   String reflectedId;
   String cfName;
   String cfTime;
   String cfResult;
   String time;
   public  Punishment() {
		super();
	}
	public Punishment(String id, String reflectedId, String cfName, String cfTime, String cfResult,String time) {
		super();
		this.id = id;
		this.reflectedId = reflectedId;
		this.cfName = cfName;
		this.cfTime = cfTime;
		this.cfResult = cfResult;
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
	public String getCfName() {
		return cfName;
	}
	public void setCfName(String cfName) {
		this.cfName = cfName;
	}
	public String getCfTime() {
		return cfTime;
	}
	public void setCfTime(String cfTime) {
		this.cfTime = cfTime;
	}
	public String getCfResult() {
		return cfResult;
	}
	public void setCfResult(String cfResult) {
		this.cfResult = cfResult;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
