package com.alphasta.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 编号规则详情
 * 
 * @author LiYunhao
 *
 */
public class RuleDetail implements Serializable {
	private static final long serialVersionUID = -2009018942335804452L;

	private Long id;

	private Long ruleId;

	private Integer type;

	private Integer subType;

	private String value;

	private Long seq;

	private Long createrId;

	private Date createTime;

	private User createUser;

	private NumberRule numberRule;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSubType() {
		return subType;
	}

	public void setSubType(Integer subType) {
		this.subType = subType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
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

	public NumberRule getNumberRule() {
		return numberRule;
	}

	public void setNumberRule(NumberRule numberRule) {
		this.numberRule = numberRule;
	}

}
