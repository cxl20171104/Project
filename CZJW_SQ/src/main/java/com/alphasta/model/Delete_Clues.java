package com.alphasta.model;

import java.util.Date;

public class Delete_Clues {
       private String id;
       private String organId;
       private String causeId;
       private String reason;
       private Date createTime;
       private String executor;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getCauseId() {
		return causeId;
	}
	public void setCauseId(String causeId) {
		this.causeId = causeId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
       
}
