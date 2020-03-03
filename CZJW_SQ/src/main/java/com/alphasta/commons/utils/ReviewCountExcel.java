package com.alphasta.commons.utils;

public class ReviewCountExcel {
	private String czMethod;
	private String czMethodCount;
	private String statDate;
	private String problemLand;
	public String getCzMethod() {
		return czMethod;
	}
	public void setCzMethod(String czMethod) {
		this.czMethod = czMethod;
	}
	public String getCzMethodCount() {
		return czMethodCount;
	}
	public void setCzMethodCount(String czMethodCount) {
		this.czMethodCount = czMethodCount;
	}
	
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
	
	public String getProblemLand() {
		return problemLand;
	}
	public void setProblemLand(String problemLand) {
		this.problemLand = problemLand;
	}
	@Override
	public String toString() {
		return "ReviewCountExcel [czMethod=" + czMethod + ", czMethodCount="
				+ czMethodCount + ", statDate=" + statDate + ", problemLand="
				+ problemLand + "]";
	}
	
}
