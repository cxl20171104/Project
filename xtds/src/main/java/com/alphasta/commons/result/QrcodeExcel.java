package com.alphasta.commons.result;

import java.util.Date;

import com.alphasta.commons.annotation.ExcelFild;

public class QrcodeExcel {

	@ExcelFild(title = "序号")
	public String id;
	@ExcelFild(title = "姓名")
	public String name;
	@ExcelFild(title = "部门")
	public String oname;
	@ExcelFild(title = "扫码时间")
	public Date ctime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public QrcodeExcel() {
	}

	public QrcodeExcel(String name, String oname, Date ctime) {
		super();
		this.name = name;
		this.oname = oname;
		this.ctime = ctime;
	}

}
