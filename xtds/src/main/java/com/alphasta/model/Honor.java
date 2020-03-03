package com.alphasta.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Honor {
	private String id;        //主键
	private long  userid;    //获奖人 或获奖单位
	private String type;
	private long  organ_id;  //获奖人部门ID
	private Date   ctime;   //创建时间
	private long   dictid;   //荣誉级别字典ID
	private String honorname;//荣誉名称
	private long  lruser;    //录入人
	private String imgurl;  //图片路径
	private String yearly;//荣誉年度
	private String xtdsName;//邢台地税名
	
	public String getXtdsName() {
		return xtdsName;
	}
	public void setXtdsName(String xtdsName) {
		System.out.println(xtdsName+"============================================>");
		if(userName==null){
			userName=xtdsName;
		}
		
		this.xtdsName = xtdsName;
	}



	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	
	private Date  gettime; //嘉奖时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public String getYearly() {
		return yearly;
	}
	public void setYearly(String yearly) {
		this.yearly = yearly;
	}
	public Date getGettime() {
		return gettime;
	}
	public void setGettime(Date gettime) {
		this.gettime = gettime;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public long getDictid() {
		return dictid;
	}
	public void setDictid(long dictid) {
		this.dictid = dictid;
	}
	public long getLruser() {
		return lruser;
	}
	public void setLruser(long lruser) {
		this.lruser = lruser;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public long getOrgan_id() {
		return organ_id;
	}
	public void setOrgan_id(long organ_id) {
		this.organ_id = organ_id;
	}
	
	
	
	public String getHonorname() {
		return honorname;
	}
	public void setHonorname(String honorname) {
		this.honorname = honorname;
	}



	private  String  userName;   //获奖人姓名
	private String  organName;   //部门ID
	private String  lrrName;    //录入人名字
	private String  dictName;  //荣誉级别名称
	private String  countNum;  //荣誉数量
	
	public String getCountNum() {
		return countNum;
	}
	public void setCountNum(String countNum) {
		this.countNum = countNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getLrrName() {
		return lrrName;
	}
	public void setLrrName(String lrrName) {
		this.lrrName = lrrName;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	
	
	
	

}
