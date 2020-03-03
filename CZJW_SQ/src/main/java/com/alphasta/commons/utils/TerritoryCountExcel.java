package com.alphasta.commons.utils;

public class TerritoryCountExcel {
	private String fields;
	private String problemLand;
	private String count;
	private String statDate;
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getProblemLand() {
		return problemLand;
	}
	public void setProblemLand(String problemLand) {
		this.problemLand = problemLand;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
	@Override
	public String toString() {
		return "TerritoryCountExcel [fields=" + fields + ", problemLand="
				+ problemLand + ", count=" + count + ", statDate=" + statDate
				+ "]";
	}

	
}
