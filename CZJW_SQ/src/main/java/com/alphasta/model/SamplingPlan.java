package com.alphasta.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 采样排班model
 * 
 * @author LiYunhao
 *
 */
public class SamplingPlan implements Serializable {
	private static final long serialVersionUID = -3763563367681715017L;

	private Long id;

	private Long customerId;

	private Long orderRecordId;

	private String samplingAddress;

	private String contacts;

	private String phone;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date appointedTime;

	private Integer halfDay;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date financeTime;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date samplingTime;

	private Integer samplingHalfDay;

	private String customerRequest;

	private Long createrId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	private Customer customer;

	private User createUser;

	private OrderRecord orderRecord;

	public Date getFinanceTime() {
		return financeTime;
	}

	public void setFinanceTime(Date financeTime) {
		this.financeTime = financeTime;
	}

	public Date getSamplingTime() {
		return samplingTime;
	}

	public void setSamplingTime(Date samplingTime) {
		this.samplingTime = samplingTime;
	}

	public Integer getSamplingHalfDay() {
		return samplingHalfDay;
	}

	public void setSamplingHalfDay(Integer samplingHalfDay) {
		this.samplingHalfDay = samplingHalfDay;
	}

	public OrderRecord getOrderRecord() {
		return orderRecord;
	}

	public void setOrderRecord(OrderRecord orderRecord) {
		this.orderRecord = orderRecord;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public Long getOrderRecordId() {
		return orderRecordId;
	}

	public void setOrderRecordId(Long orderRecordId) {
		this.orderRecordId = orderRecordId;
	}

	public String getSamplingAddress() {
		return samplingAddress;
	}

	public void setSamplingAddress(String samplingAddress) {
		this.samplingAddress = samplingAddress;
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

	public Date getAppointedTime() {
		return appointedTime;
	}

	public void setAppointedTime(Date appointedTime) {
		this.appointedTime = appointedTime;
	}

	public Integer getHalfDay() {
		return halfDay;
	}

	public void setHalfDay(Integer halfDay) {
		this.halfDay = halfDay;
	}

	public String getCustomerRequest() {
		return customerRequest;
	}

	public void setCustomerRequest(String customerRequest) {
		this.customerRequest = customerRequest;
	}

}
