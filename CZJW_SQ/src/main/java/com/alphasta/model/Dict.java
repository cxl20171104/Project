package com.alphasta.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典model
 * @author LiYunhao
 *
 */
public class Dict implements Serializable{
	private static final long serialVersionUID = 2451838557523988845L;

	private Long id;
	
	private String dictId;
	
	private String dictPid;
	
	private String name;
	
	private String value;
	
	private String remark;
	
	private Long createrId;
	
	private Date createTime;
	
	private String state;
	
	private String text;
	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictPid() {
		return dictPid;
	}

	public void setDictPid(String dictPid) {
		this.dictPid = dictPid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Dict [id=" + id + ", dictId=" + dictId + ", dictPid=" + dictPid
				+ ", name=" + name + ", value=" + value + ", remark=" + remark
				+ ", createrId=" + createrId + ", createTime=" + createTime
				+ ", state=" + state + ", text=" + text + "]";
	}
	
}
