package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.SlsResult;

public interface SlsResultMapper {
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
