package com.alphasta.model;

import java.io.Serializable;

/**
 * 编号详情
 * 
 * @author LiYunhao
 *
 */
public class NumberDetail implements Serializable {
	private static final long serialVersionUID = -6034976701275965904L;

	private Long id;

	private Long ruleId;

	private String fixedNumber;

	private String seqNumber;

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

	public String getFixedNumber() {
		return fixedNumber;
	}

	public void setFixedNumber(String fixedNumber) {
		this.fixedNumber = fixedNumber;
	}

	public String getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(String seqNumber) {
		this.seqNumber = seqNumber;
	}

}
