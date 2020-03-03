package com.alphasta.commons.utils;

public class ResultCountExcel2 {
	private String zjcfCount;
	private String blResult_zjcf;
	private String zyViolation;
	private String statDate;
	public String getZjcfCount() {
		return zjcfCount;
	}
	public void setZjcfCount(String zjcfCount) {
		this.zjcfCount = zjcfCount;
	}
	public String getBlResult_zjcf() {
		return blResult_zjcf;
	}
	public void setBlResult_zjcf(String blResult_zjcf) {
		this.blResult_zjcf = blResult_zjcf;
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
		return "ResultCountExcel2 [zjcfCount=" + zjcfCount + ", blResult_zjcf="
				+ blResult_zjcf + ", zyViolation=" + zyViolation
				+ ", statDate=" + statDate + "]";
	}
	
	
}
