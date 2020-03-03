package com.alphasta.model;

import com.alphasta.commons.utils.FileUtils;

public class Picip {
  String ip;

public String getIp() {
	return ip;
}

public void setIp(String ip) {
	System.out.println("========================================>设置ip:"+ip);
	FileUtils.pic_ip=ip;
	this.ip = ip;
}
  
}
