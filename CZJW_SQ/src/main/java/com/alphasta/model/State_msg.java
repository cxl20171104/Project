package com.alphasta.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class State_msg {
   private String id;
   private String caseid;
   private CaseClue caseClue;
   private String isOut;
   @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
   private Date outime;
   private String style;
   private String msgfor;
   public String getCaseid() {
	return caseid;
}
public void setCaseid(String caseid) {
	this.caseid = caseid;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}

public CaseClue getCaseClue() {
	return caseClue;
}
public void setCaseClue(CaseClue caseClue) {
	this.caseClue = caseClue;
}
public String getIsOut() {
	return isOut;
}
public void setIsOut(String isOut) {
	this.isOut = isOut;
}
public Date getOutime() {
	return outime;
}
public void setOutime(Date outime) {
	this.outime = outime;
}
public String getStyle() {
	return style;
}
public void setStyle(String style) {
	this.style = style;
}
public String getMsgfor() {
	return msgfor;
}
public void setMsgfor(String msgfor) {
	this.msgfor = msgfor;
}
  



}
