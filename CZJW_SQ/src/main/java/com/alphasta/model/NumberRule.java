package com.alphasta.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 编号规则
 * 
 * @author LiYunhao
 *
 */
public class NumberRule implements Serializable {
	private static final long serialVersionUID = 2086778381421148039L;

	private Long id;

	private String name;

	private Integer state;

	private Long createrId;

	private Date createTime;

	private User createUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

}
