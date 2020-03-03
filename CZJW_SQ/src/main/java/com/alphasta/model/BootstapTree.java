package com.alphasta.model;

import java.util.ArrayList;
import java.util.List;

public class BootstapTree {
     private String id;
     private String text;
     private String pid;
     private List<BootstapTree>nodes=new ArrayList<BootstapTree>();
	public String getId() {
		return id;
	}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<BootstapTree> getNodes() {
		return nodes;
	}
	public void setNodes(List<BootstapTree> nodes) {
		this.nodes = nodes;
	}
     
     
}  
