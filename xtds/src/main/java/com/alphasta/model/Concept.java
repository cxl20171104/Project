package com.alphasta.model;

import java.util.Date;

/**
 * App首页滚动内容
 * 
 * @author chenxiaoliang
 * 
 */
public class Concept {

	private String id;
	private String content;
	private Date ctime;
	private String useing; // 是否再用 0 没用 1 再用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getUseing() {
		return useing;
	}

	public void setUseing(String useing) {
		this.useing = useing;
	}

}
