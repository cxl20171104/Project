package com.alphasta.model;

public class ReflectingPerson {
      String id;
      String causeId;
      String reflectingName;
      String tell;
      String reflectProblem;
      String reflectingDept;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReflectingName() {
		return reflectingName;
	}
	public void setReflectingName(String reflectingName) {
		this.reflectingName = reflectingName;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getReflectProblem() {
		return reflectProblem;
	}
	public void setReflectProblem(String reflectProblem) {
		this.reflectProblem = reflectProblem;
	}
	public String getCauseId() {
		return causeId;
	}
	public void setCauseId(String causeId) {
		this.causeId = causeId;
	}
	public String getReflectingDept() {
		return reflectingDept;
	}
	public void setReflectingDept(String reflectingDept) {
		this.reflectingDept = reflectingDept;
	}
      
}
