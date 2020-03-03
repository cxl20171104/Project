package com.alphasta.model;

import java.util.Date;

/**
 * 消息发布表
 * 
 * @author sjb
 * 
 */
public class MsgPut {

	private String id; // 主键
	private User puter; // 发布人

	public User getPuter() {
		return puter;
	}

	public void setPuter(User puter) {
		this.puter = puter;
	}

	private String content; // 消息内容
	private int type; // 1 个人 2 部门 3 全体
	private String geterId; // 接受消息人ID 或者 部门ID
	private Date ctime; // 发布时间
	private Date endTime; // 消息有效期时间
	private int canReply; // 是否可恢复
	private String toHref; // 跳转页面链接
	private String toUserId;// 消息发向具体的人
	private String hasRead; // 是否已读
	private String cid; // 用户的Cid
	private String title;  //消息标题
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getHasRead() {
		return hasRead;
	}

	public void setHasRead(String hasRead) {
		this.hasRead = hasRead;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getGeterId() {
		return geterId;
	}

	public void setGeterId(String geterId) {
		this.geterId = geterId;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getCanReply() {
		return canReply;
	}

	public void setCanReply(int canReply) {
		this.canReply = canReply;
	}

	public String getToHref() {
		return toHref;
	}

	public void setToHref(String toHref) {
		this.toHref = toHref;
	}

}
