package com.alphasta.model;

import java.io.Serializable;

/**
 * 
 * @author gengzhuang
 * 
 */
public class MenuQueryMdoel implements Serializable {

	private static final long serialVersionUID = 8938751807616840564L;

	private Long id;
	private Long pid;
	private Long userId;
	private String roleIds;

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
