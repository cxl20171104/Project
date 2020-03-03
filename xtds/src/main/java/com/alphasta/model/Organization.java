package com.alphasta.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @description：部门
 * @author：liyunhao
 * @date：2015/10/1 14:51
 */
public class Organization implements Serializable {

	private static final long serialVersionUID = 1282186495210887307L;

	private Long id;

	private String name;

	private String address;

	private String code;

	@JsonProperty("iconCls")
	private String icon;

	private Long pid;

	private Integer seq;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdate;

	private String state;

	private String text;
	
	private String level;
	
	private String summarize;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
    public String  grade; //部门级别 1 局党委 2. 总支  3 支部  4 小组
    
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	private String scores;
	private Integer type;
	private String ctime;
	private Integer classType = 1;
	private String sum;

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public Integer getClassType() {
		return classType;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
		this.name = name == null ? null : name.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
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

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getSummarize() {
		return summarize;
	}

	public void setSummarize(String summarize) {
		this.summarize = summarize;
	}

	@Override
	public String toString() {
		return "Organization{" + "id=" + id + ", name='" + name + '\''
				+ ", address='" + address + '\'' + ", code='" + code + '\''
				+ ", icon='" + icon + '\'' + ", pid=" + pid + ", seq=" + seq
				+ ", createdate=" + createdate + '}';
	}
}