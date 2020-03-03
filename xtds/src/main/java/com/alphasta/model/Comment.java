package com.alphasta.model;

import java.util.Date;

public class Comment {
	private String id;

	private String articleid;

	private Date ctime;

	private Integer ctype;

	private String content;
	// 评论人
	private String cuser;

	private String hascomment;
	// 评论人名字
	private String cusername;
	// 被评论人
	private String onuser;
	// 被评论人名字
	private String onusername;
	// 回复评论所在文章的ID
	private String replyartid;
	// 评论人的头像
	private String cuserpic;

	private String showable; // 是否显示

	private Integer organ; // 非表字段

	public Integer getOrgan() {
		return organ;
	}

	public void setOrgan(Integer organ) {
		this.organ = organ;
	}

	public String getShowable() {
		return showable;
	}

	public void setShowable(String showable) {
		this.showable = showable;
	}

	public String getCuserpic() {
		return cuserpic;
	}

	public void setCuserpic(String cuserpic) {
		this.cuserpic = cuserpic;
	}

	public String getReplyartid() {
		return replyartid;
	}

	public void setReplyartid(String replyartid) {
		this.replyartid = replyartid;
	}

	public String getCusername() {
		return cusername;
	}

	public void setCusername(String cusername) {
		this.cusername = cusername;
	}

	public String getOnuser() {
		return onuser;
	}

	public void setOnuser(String onuser) {
		this.onuser = onuser;
	}

	public String getOnusername() {
		return onusername;
	}

	public void setOnusername(String onusername) {
		this.onusername = onusername;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getArticleid() {
		return articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid == null ? null : articleid.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getCtype() {
		return ctype;
	}

	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getCuser() {
		return cuser;
	}

	public void setCuser(String cuser) {
		this.cuser = cuser == null ? null : cuser.trim();
	}

	public String getHascomment() {
		return hascomment;
	}

	public void setHascomment(String hascomment) {
		this.hascomment = hascomment == null ? null : hascomment.trim();
	}

	public Comment() {
		super();
	}

	public Comment(String articleid, Date ctime, Integer ctype, String cuser) {
		this.articleid = articleid;
		this.ctime = ctime;
		this.ctype = ctype;
		this.cuser = cuser;
	}

}