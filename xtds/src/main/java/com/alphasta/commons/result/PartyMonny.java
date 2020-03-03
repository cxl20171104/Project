package com.alphasta.commons.result;

import com.alphasta.commons.annotation.ExcelFild;
/**
 *   用于待缴台账导出
 * @author sjb
 *
 */
public class PartyMonny {
	
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
	@ExcelFild(title="党费比例")
	private  String  payscale;
	@ExcelFild(title="年度")
	private String year;
	@ExcelFild(title="月度")
	private String month;
	@ExcelFild(title="月应缴金额")
	private  String  monny;
	
	
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
	public String getMonny() {
		return monny;
	}
	public void setMonny(String monny) {
		this.monny = monny;
	}
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
	

}
