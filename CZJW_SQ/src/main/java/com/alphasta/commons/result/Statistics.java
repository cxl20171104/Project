package com.alphasta.commons.result;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alphasta.model.Organization;
import com.alphasta.model.User;

public class Statistics implements Serializable {
	private static final long serialVersionUID = -3006444680355243083L;

	private Long userId;

	private Long orgId;

	private Long total;

	private BigDecimal amount;

	private String startTime;

	private String endTime;
	
	private User user;
	
	private Organization organ;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime + " 00:00:00";
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime + " 23:59:59";
	}

}
