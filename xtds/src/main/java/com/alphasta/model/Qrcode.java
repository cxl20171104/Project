package com.alphasta.model;

import java.util.Date;

public class Qrcode {
	private String id;

	private String url;

	private String info;

	private String type;

	private String user;

	private Date ctime;

	private Date etime;

	private String name;
	
	private String content;
	
	private String scanNum;  //签到人数
	
	private Long organid; //举办部门
	
	private String oname;//部门名称
	
	
	
	
	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public Long getOrganid() {
		return organid;
	}

	public void setOrganid(Long organid) {
		this.organid = organid;
	}

	public String getScanNum() {
		return scanNum;
	}

	public void setScanNum(String scanNum) {
		this.scanNum = scanNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String dictid; //字典id
	

	public String getDictid() {
		return dictid;
	}

	public void setDictid(String dictid) {
		this.dictid = dictid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user == null ? null : user.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getEtime() {
		return etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}
}