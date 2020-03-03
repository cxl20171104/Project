package com.alphasta.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.mapper.VersionMapper;
import com.alphasta.model.Version;
import com.alphasta.service.VersionService;

@Service
public class VersionServiceImpl implements VersionService {

	@Autowired
	private VersionMapper versionMapper;

	@Override
	public Version getLastVersion(String vernum) {
		String which=null;
		if(vernum.indexOf(".1")!=-1){
			which="ios";
		}
		return versionMapper.getLast(which);
	}

	@Override
	public void addVersion(Version version) {
		versionMapper.addVersion(version);
	}

	@Override
	public Version selectVersion(String vernum) {
		// TODO Auto-generated method stub
		String which=null;
		if(vernum.indexOf(".1")!=-1){
			//取处版本号
			vernum=vernum.substring(0,vernum.lastIndexOf("."));
			which="ios";
		}
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("vernum", vernum);
		map.put("which", which);
		return versionMapper.selectVersion(map);
	}

}
