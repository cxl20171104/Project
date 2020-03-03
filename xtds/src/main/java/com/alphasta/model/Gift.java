package com.alphasta.model;
/**
 * 积分兑换礼品类
 * @author cxl
 *
 */
public class Gift {
  private String id;
  private String name;
  private String pic;
  private String startTime;
  private String endTime;
  private Integer score;
  private String detail;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPic() {
	return pic;
}
public void setPic(String pic) {
	this.pic = pic;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public Integer getScore() {
	return score;
}
public void setScore(Integer score) {
	this.score = score;
}
public String getDetail() {
	return detail;
}
public void setDetail(String detail) {
	this.detail = detail;
}
  
}
