package com.alphasta.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author gengzhuang
 * 
 */
public class BasMenuModel implements Serializable {

	private static final long serialVersionUID = 2969944149802680755L;
	/** id */
	private Long id;
	/** 菜单名称 */
	private String name;
	/** 菜单url */
	private String url;
	/** 菜单描述 */
	private String description;
	/** 菜单图标 */
	private String icon;
	/** 父id */
	private Long pid;
	/** 菜单排序 */
	private Integer seq;
	/** 菜单状态 */
	private String state;
	/** 资源类别， 0：菜单 1：按钮 */
	private Integer resourcetype;
	/** 创建日期 */
	private Date createdate;
	/** 显示字段 */
	private String text;
	/** 菜单显示状态0 */
	private Integer status;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public void setResourcetype(Integer resourcetype) {
		this.resourcetype = resourcetype;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getResourcetype() {
		return resourcetype;
	}
}
