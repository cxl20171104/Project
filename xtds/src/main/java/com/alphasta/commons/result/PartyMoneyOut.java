package com.alphasta.commons.result;

import com.alphasta.commons.annotation.ExcelFild;

public class PartyMoneyOut {
	
	@ExcelFild(title="序号")
	private  int  id;
	@ExcelFild(title="用户ID")
	private  String  userid;
	@ExcelFild(title="姓名")
	private  String name;
	@ExcelFild(title="部门名称")
	private  String  oname;
	@ExcelFild(title="缴费基数")
	private  String  paybase;
	@ExcelFild(title="缴费比例")
	private  String  payscale;
	@ExcelFild(title="月度")
	private  String month;
	@ExcelFild(title="月应缴金额")
	private  String  monny;
	@ExcelFild(title="缴费时间")
	private  String payTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
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
	public String getMonny() {
		return monny;
	}
	public void setMonny(String monny) {
		this.monny = monny;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	

}
