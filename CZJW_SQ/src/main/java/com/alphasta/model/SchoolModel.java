package com.alphasta.model;

import java.io.Serializable;

/**
 * 学校
 * 
 * @author gengzhuang
 *
 */
public class SchoolModel implements Serializable {

	private static final long serialVersionUID = -1016421731927508358L;

	private Long id;

	private String schoolName;

	private String schoolCode;

	private String schoolAddress;

	private Integer schoolCost;

	private String schoolUrl;

	private Integer schoolNum;

	private Integer low2015;

	private Integer low2014;

	private Integer low2013;

	private Integer schoolSort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public String getSchoolUrl() {
		return schoolUrl;
	}

	public void setSchoolUrl(String schoolUrl) {
		this.schoolUrl = schoolUrl;
	}

	public Integer getSchoolNum() {
		return schoolNum;
	}

	public void setSchoolNum(Integer schoolNum) {
		this.schoolNum = schoolNum;
	}

	public Integer getLow2015() {
		return low2015;
	}

	public void setLow2015(Integer low2015) {
		this.low2015 = low2015;
	}

	public Integer getLow2014() {
		return low2014;
	}

	public void setLow2014(Integer low2014) {
		this.low2014 = low2014;
	}

	public Integer getLow2013() {
		return low2013;
	}

	public void setLow2013(Integer low2013) {
		this.low2013 = low2013;
	}

	public Integer getSchoolSort() {
		return schoolSort;
	}

	public void setSchoolSort(Integer schoolSort) {
		this.schoolSort = schoolSort;
	}

	@Override
	public String toString() {
		return "SchoolModel [id=" + id + ", schoolName=" + schoolName + ", schoolCode=" + schoolCode
				+ ", schoolAddress=" + schoolAddress + ", schoolCost=" + schoolCost + ", schoolUrl=" + schoolUrl
				+ ", schoolNum=" + schoolNum + ", low2015=" + low2015 + ", low2014=" + low2014 + ", low2013=" + low2013
				+ ", schoolSort=" + schoolSort + "]";
	}

	public Integer getSchoolCost() {
		return schoolCost;
	}

	public void setSchoolCost(Integer schoolCost) {
		this.schoolCost = schoolCost;
	}

}