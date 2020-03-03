package com.alphasta.model;

import com.alibaba.fastjson.JSON;

public class Catalog {
	private String id;

	private String name;

	private String root;

	private String descn;

	private String parentcata;

	private String activities;

	private String pic;

	private Integer picWidth;

	private Integer picHeight;

	private String template;

	private String arttemplate;

	private String showonindex;

	private String isenable;

	private String creator;
	
	private Long organ; //栏目所属部门;

	public Long getOrgan() {
		return organ;
	}

	public void setOrgan(Long organ) {
		this.organ = organ;
	}

	private String showNextColumn; // 是否有子栏目 0 代表有
	private Integer iscommon; // 是否是公共栏目
	private String text;
	private String state;
	private String artNum; // 栏目下文章数量

	public String getArtNum() {
		return artNum;
	}

	public void setArtNum(String artNum) {
		this.artNum = artNum;
	}

	public Integer getIscommon() {
		return iscommon;
	}

	public void setIscommon(Integer iscommon) {
		this.iscommon = iscommon;
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

	public void setShowNextColumn(String showNextColumn) {
		this.showNextColumn = showNextColumn;
	}

	public String getShowNextColumn() {
		return showNextColumn;
	}

	public void setShowNextColunm(String showNextColumn) {
		this.showNextColumn = showNextColumn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root == null ? null : root.trim();
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn == null ? null : descn.trim();
	}

	public String getParentcata() {
		return parentcata;
	}

	public void setParentcata(String parentcata) {
		this.parentcata = parentcata == null ? null : parentcata.trim();
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities == null ? null : activities.trim();
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public Integer getPicWidth() {
		return picWidth;
	}

	public void setPicWidth(Integer picWidth) {
		this.picWidth = picWidth;
	}

	public Integer getPicHeight() {
		return picHeight;
	}

	public void setPicHeight(Integer picHeight) {
		this.picHeight = picHeight;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template == null ? null : template.trim();
	}

	public String getArttemplate() {
		return arttemplate;
	}

	public void setArttemplate(String arttemplate) {
		this.arttemplate = arttemplate == null ? null : arttemplate.trim();
	}

	public String getShowonindex() {
		return showonindex;
	}

	public void setShowonindex(String showonindex) {
		this.showonindex = showonindex == null ? null : showonindex.trim();
	}

	public String getIsenable() {
		return isenable;
	}

	public void setIsenable(String isenable) {
		this.isenable = isenable == null ? null : isenable.trim();
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator == null ? null : creator.trim();
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}