package com.alphasta.model;

import java.util.Date;

public class Version {

	private String id;
	private String url;
	private Date ctime;
	private String verNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getVerNum() {
		return verNum;
	}

	public void setVerNum(String verNum) {
		this.verNum = verNum;
	}

}
