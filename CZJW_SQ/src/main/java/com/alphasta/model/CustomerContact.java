package com.alphasta.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 客户联系人model
 * 
 * @author LiYunhao
 *
 */
public class CustomerContact implements Serializable {
	private static final long serialVersionUID = -5728479851230307823L;

	private Long id;

	private Long customerId;

	private Long contactId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

	private User contact;

	public User getContact() {
		return contact;
	}

	public void setContact(User contact) {
		this.contact = contact;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

}
