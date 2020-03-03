package com.alphasta.model;

import java.util.Date;

public class QrcodeUser {

	private String id;
	private String qrcodeid;
	private String user;
	private String name;
	private Date ctime;
	private String oname;

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQrcodeid() {
		return qrcodeid;
	}

	public void setQrcodeid(String qrcodeid) {
		this.qrcodeid = qrcodeid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
