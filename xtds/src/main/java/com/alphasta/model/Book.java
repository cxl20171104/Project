package com.alphasta.model;
/**
 * 图书实体类
 * @author cxl
 *
 */
public class Book {
   
	private String id;
	private String name;
	private String headPic;
	private String filePic;
	//上架时间
	private String startTime;
	//下架时间
	private String endTime; 
	//图书简述
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
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getFilePic() {
		return filePic;
	}
	public void setFilePic(String filePic) {
		this.filePic = filePic;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
