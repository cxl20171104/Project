package com.alphasta.model;

import java.io.Serializable;

/**
 * 系统参数
 * 
 * @author LiYunhao
 * 
 */
public class SysParam implements Serializable {
	private static final long serialVersionUID = -1723785871249514149L;

	private Long id;

	private String name;

	private String key;

	private String value;

	private String remark;

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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

}
