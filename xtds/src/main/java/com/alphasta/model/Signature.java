package com.alphasta.model;

import java.io.Serializable;

public class Signature implements Serializable {
	private static final long serialVersionUID = 6192178677022740273L;

	private Long id;

	private Long userId;

	private String filePath;

	private Long orgId;

	private User user;

	private Organization organ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Organization getOrgan() {
		return organ;
	}

	public void setOrgan(Organization organ) {
		this.organ = organ;
	}

}
