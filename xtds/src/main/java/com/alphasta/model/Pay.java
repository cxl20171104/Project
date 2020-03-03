package com.alphasta.model;

import com.alphasta.commons.annotation.ExcelFild;

public class Pay {
   //id
   String id;
   //用户id
   String userid;
   //部门id
   String organization_id;
   //缴纳时间
   String payTime;
   //缴纳方式
   String payType;
   //缴纳金额
   String money;
   //用户
   User user;
   //用户部门最低级
   Organization organization;
   
   String year;
   String month;
   String quarter;
   String state;
   String paybase; //缴费基数
   String payscale; //党费比例
   
   String oname; //部门名称
   String uname; //用户名称
   
   

public String getOname() {
	return oname;
}
public void setOname(String oname) {
	this.oname = oname;
}
public String getUname() {
	return uname;
}
public void setUname(String uname) {
	this.uname = uname;
}
public String getPaybase() {
	return paybase;
}
public void setPaybase(String paybase) {
	this.paybase = paybase;
}
public String getPayscale() {
	return payscale;
}
public void setPayscale(String payscale) {
	this.payscale = payscale;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getPayTime() {
	return payTime;
}
public void setPayTime(String payTime) {
	this.payTime = payTime;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public String getPayType() {
	return payType;
}
public void setPayType(String payType) {
	this.payType = payType;
}
public Organization getOrganization() {
	return organization;
}
public void setOrganization(Organization organization) {
	this.organization = organization;
}

public String getOrganization_id() {
	return organization_id;
}
public void setOrganization_id(String organization_id) {
	this.organization_id = organization_id;
}

public String getMoney() {
	return money;
}
public void setMoney(String money) {
	this.money = money;
}

public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public String getMonth() {
	return month;
}
public void setMonth(String month) {
	this.month = month;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}

public String getQuarter() {
	return quarter;
}
public void setQuarter(String quarter) {
	this.quarter = quarter;
}
@Override
public String toString() {
	return "Pay [id=" + id + ", userid=" + userid + ", payTime=" + payTime
			+ ", payType=" + payType + ", user=" + user + ", organization="
			+ organization + "]";
}

   
}
