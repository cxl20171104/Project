package com.alphasta.commons.result;

import com.alphasta.commons.annotation.ExcelFild;

public class ExcelField {

	@ExcelFild(title = "序号")
	public String id;
	@ExcelFild(title = "名称")
	public String name;
	@ExcelFild(title = "总分")
	public String sum;
	@ExcelFild(title = "人均分值")
	public String scores;
	@ExcelFild(title = "积分周期")
	public String type;
	@ExcelFild(title = "日期")
	public String ctime;
	

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

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	
}
