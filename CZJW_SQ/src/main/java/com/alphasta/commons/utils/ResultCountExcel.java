package com.alphasta.commons.utils;

public class ResultCountExcel {
	private String djcfCount;
	private String blResult_djcf;
	private String zyViolation;
	private String statDate;
	public String getDjcfCount() {
		return djcfCount;
	}
	public void setDjcfCount(String djcfCount) {
		this.djcfCount = djcfCount;
	}
	public String getBlResult_djcf() {
		return blResult_djcf;
	}
	public void setBlResult_djcf(String blResult_djcf) {
		this.blResult_djcf = blResult_djcf;
	}
	public String getZyViolation() {
		return zyViolation;
	}
	public void setZyViolation(String zyViolation) {
		this.zyViolation = zyViolation;
	}
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
	@Override
	public String toString() {
		return "ResultCountExcel [djcfCount=" + djcfCount + ", blResult_djcf="
				+ blResult_djcf + ", zyViolation=" + zyViolation
				+ ", statDate=" + statDate + "]";
	}
	
}
