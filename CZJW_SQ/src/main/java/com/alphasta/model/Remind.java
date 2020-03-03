package com.alphasta.model;

public class Remind {
	private String id;
	private String modifierId;
	private String problemId;
	private String remindContent;
	private String remindTime;
	private String sendState;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	
	public String getProblemId() {
		return problemId;
	}
	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}
	public String getRemindContent() {
		return remindContent;
	}
	public void setRemindContent(String remindContent) {
		this.remindContent = remindContent;
	}
	public String getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(String remindTime) {
		this.remindTime = remindTime;
	}
	public String getSendState() {
		return sendState;
	}
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	@Override
	public String toString() {
		return "Remind [id=" + id + ", modifierId=" + modifierId
				+ ", problemId=" + problemId + ", remindContent="
				+ remindContent + ", remindTime=" + remindTime + ", sendState="
				+ sendState + "]";
	}
	
}
