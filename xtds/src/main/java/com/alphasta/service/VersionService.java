package com.alphasta.service;

import com.alphasta.model.Version;

public interface VersionService {

	public Version getLastVersion(String verNum);

	public void addVersion(Version version);
	
	public Version selectVersion(String verNum);
	
  

}
