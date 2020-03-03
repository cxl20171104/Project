package com.alphasta.service;

import java.util.List;

import com.alphasta.model.SlsResult;

public interface SlsResultService {
	//
	int addSlsResult(SlsResult slsResult);
	//
	SlsResult deleteSlsResult (String id);
	//
	SlsResult updateSlsResult(SlsResult slsResult);
	//
	SlsResult findSlsResultById(String id);
	//
	List<SlsResult> findSlsResultByRid(String rid);
	//
	SlsResult findSlsResult(SlsResult slsResult);
	
}
