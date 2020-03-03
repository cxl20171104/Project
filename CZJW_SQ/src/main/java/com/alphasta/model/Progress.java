package com.alphasta.model;
//进度bean
public class Progress {
     private String id;
     private String causeId;
     private String pointName;
     private String pointValue;
     private String detail;
     private String time;
     private String lastPoint;
     private String organId;
     private String timeForday;
     private String remark;
     private User   user;
     //留存状态
     private String state;
     //进度名称二级
     private String type;
	public  Progress() {
		super();
	}
    
    

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Progress(String id, String causeId, String pointName, String pointValue, String detail, String time,
			String lastPoint, String organId, String timeForday, String remark, User user) {
		super();
		this.id = id;
		this.causeId = causeId;
		this.pointName = pointName;
		this.pointValue = pointValue;
		this.detail = detail;
		this.time = time;
		this.lastPoint = lastPoint;
		this.organId = organId;
		this.timeForday = timeForday;
		this.remark = remark;
		this.user = user;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCauseId() {
		return causeId;
	}
	public void setCauseId(String causeId) {
		this.causeId = causeId;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public String getPointValue() {
		return pointValue;
	}
	public void setPointValue(String pointValue) {
		this.pointValue = pointValue;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getLastPoint() {
		return lastPoint;
	}
	public void setLastPoint(String lastPoint) {
		this.lastPoint = lastPoint;
	}
	
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}	
	public String getTimeForday() {
		return timeForday;
	}
	public void setTimeForday(String timeForday) {
		this.timeForday = timeForday;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}


    
	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return "Progress [id=" + id + ", causeId=" + causeId + ", pointName=" + pointName + ", pointValue=" + pointValue
				+ ", detail=" + detail + ", time=" + time + ", lastPoint=" + lastPoint + ", organId=" + organId
				+ ", timeForday=" + timeForday + ", remark=" + remark + "]";
	}
}
