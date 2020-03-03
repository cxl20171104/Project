package com.alphasta.model;

import java.util.Date;

public class AppLog {
	private String id;
	private String operater;
	private String operaterName;
	private Date otime;
	private Integer type;
	private String detail;
	private String liveness;

	public String getOperaterName() {
		return operaterName;
	}

	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public Date getOtime() {
		return otime;
	}

	public void setOtime(Date otime) {
		this.otime = otime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getLiveness() {
		return liveness;
	}

	public void setLiveness(String liveness) {
		this.liveness = liveness;
	}

}
