package com.alphasta.commons.result;

public class CommentNum {
   private String seeNum;
   private String greatNum;
   private String commentNum;
public String getSeeNum() {
	return seeNum;
}
public void setSeeNum(String seeNum) {
	this.seeNum = seeNum;
}
public String getGreatNum() {
	return greatNum;
}
public void setGreatNum(String greatNum) {
	this.greatNum = greatNum;
}
public String getCommentNum() {
	return commentNum;
}
public void setCommentNum(String commentNum) {
	this.commentNum = commentNum;
}
public CommentNum(String seeNum, String greatNum, String commentNum) {
	super();
	this.seeNum = seeNum;
	this.greatNum = greatNum;
	this.commentNum = commentNum;
}
public CommentNum() {
	super();
	// TODO Auto-generated constructor stub
}
   
}
