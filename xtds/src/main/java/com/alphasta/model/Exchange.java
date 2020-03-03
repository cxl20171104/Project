package com.alphasta.model;

import java.util.Date;

/**
 * 兑换礼品实体类
 * @author cxl
 *
 */
public class Exchange {
   private String id;
   private String userId;
   private String giftId;
   private String userName;
   private String orgaName;
   private String giftName;
   private String giftPic;
   private Date creaTime;
   private String startTime;
   private String endTime;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getGiftId() {
	return giftId;
}
public void setGiftId(String giftId) {
	this.giftId = giftId;
}
public String getGiftName() {
	return giftName;
}
public void setGiftName(String giftName) {
	this.giftName = giftName;
}
public String getGiftPic() {
	return giftPic;
}
public void setGiftPic(String giftPic) {
	this.giftPic = giftPic;
}
public Date getCreaTime() {
	return creaTime;
}
public void setCreaTime(Date creaTime) {
	this.creaTime = creaTime;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getOrgaName() {
	return orgaName;
}
public void setOrgaName(String orgaName) {
	this.orgaName = orgaName;
}

   
}
