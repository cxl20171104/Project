package com.alphasta.model;

import java.util.Date;

public class SysSensitive {
	private String id;

	private String name;

	private Date ctime;

	private String replaceword;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getReplaceword() {
		return replaceword;
	}

	public void setReplaceword(String replaceword) {
		this.replaceword = replaceword == null ? null : replaceword.trim();
	}
}