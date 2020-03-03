package com.alphasta.model;

public class Maked {
   private String id;
   private String makedOrganId;
   private String cluesId;
   
public Maked() {
	super();
}

public Maked(String id, String makedOrganId, String cluesId) {
	super();
	this.id = id;
	this.makedOrganId = makedOrganId;
	this.cluesId = cluesId;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getMakedOrganId() {
	return makedOrganId;
}

public void setMakedOrganId(String makedOrganId) {
	this.makedOrganId = makedOrganId;
}

public String getCluesId() {
	return cluesId;
}

public void setCluesId(String cluesId) {
	this.cluesId = cluesId;
}


   
}
