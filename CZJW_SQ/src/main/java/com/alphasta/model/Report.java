package com.alphasta.model;
public class Report {
      private String id;
      private String reportOrganId;
      private String cluesId;
      private  String submitOrganId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReportOrganId() {
		return reportOrganId;
	}
	public void setReportOrganId(String reportOrganId) {
		this.reportOrganId = reportOrganId;
	}
	public String getCluesId() {
		return cluesId;
	}
	public void setCluesId(String cluesId) {
		this.cluesId = cluesId;
	}
	public Report(String id, String reportOrganId, String cluesId,String submitOrganId) {
		super();
		this.id = id;
		this.reportOrganId = reportOrganId;
		this.cluesId = cluesId;
		this.submitOrganId=submitOrganId;
	}
	public Report() {
		super();
	}
	public String getSubmitOrganId() {
		return submitOrganId;
	}
	public void setSubmitOrganId(String submitOrganId) {
		this.submitOrganId = submitOrganId;
	}
	
     
}
