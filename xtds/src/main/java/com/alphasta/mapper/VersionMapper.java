package com.alphasta.mapper;

import java.util.Map;

import com.alphasta.model.Version;

public interface VersionMapper {

	public Version getLast(String which);

	public void addVersion(Version version);
	
	public Version selectVersion(Map<String,Object>map);

}
