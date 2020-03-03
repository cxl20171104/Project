package com.alphasta.model;

import java.util.Date;

public class Back {
   private String   id;
   private String   cluesId;
   private String   reason;
   private String    type;
   private String   executor;
   private int      organId;
   private String   remark;
   private Date     createTime;
   //状态
   private String state;
   //回复
   private String back_answer;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCluesId() {
	return cluesId;
}
public void setCluesId(String cluesId) {
	this.cluesId = cluesId;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getExecutor() {
	return executor;
}
public void setExecutor(String executor) {
	this.executor = executor;
}
public int getOrganId() {
	return organId;
}
public void setOrganId(int organId) {
	this.organId = organId;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getBack_answer() {
	return back_answer;
}
public void setBack_answer(String back_answer) {
	this.back_answer = back_answer;
}  

}
