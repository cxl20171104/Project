package com.alphasta.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 客户model
 * 
 * @author LiYunhao
 *
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 63571797592678385L;

	private Long id;

	private String name;

	private String contacts;

	private String phone;

	private String address;

	private String province;

	private String city;

	private String village;

	private Integer type;

	private Long trackingId;

	private Long createrId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	private User createUser;

	private User trackingUser;

	private List<CustomerContact> trackingList;

	public List<CustomerContact> getTrackingList() {
		return trackingList;
	}

	public void setTrackingList(List<CustomerContact> trackingList) {
		this.trackingList = trackingList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public Long getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(Long trackingId) {
		this.trackingId = trackingId;
	}

	public User getTrackingUser() {
		return trackingUser;
	}

	public void setTrackingUser(User trackingUser) {
		this.trackingUser = trackingUser;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
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

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
