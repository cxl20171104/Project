package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alphasta.mapper.SlsResultMapper;
import com.alphasta.model.SlsResult;
import com.alphasta.service.SlsResultService;

public class SlsResultServiceImpl implements SlsResultService{
    @Autowired 
    SlsResultMapper slsResultMapper;
	@Override
	public int addSlsResult(SlsResult slsResult) {
		int p=slsResultMapper.addSlsResult(slsResult);
		return p;
	}

	@Override
	public SlsResult deleteSlsResult(String id) {
		// TODO Auto-generated method stub
		SlsResult deleteSlsResult = slsResultMapper.deleteSlsResult(id);
		return deleteSlsResult;
	}

	@Override
	public SlsResult updateSlsResult(SlsResult slsResult) {
		// TODO Auto-generated method stub
		SlsResult updateSlsResult = slsResultMapper.updateSlsResult(slsResult);
		return updateSlsResult;
	}

	@Override
	public SlsResult findSlsResultById(String id) {
		// TODO Auto-generated method stub
		SlsResult findSlsResultById = slsResultMapper.findSlsResultById(id);
		return findSlsResultById;
	}

	@Override
	public List<SlsResult> findSlsResultByRid(String rid) {
		// TODO Auto-generated method stub
		List<SlsResult> findSlsResultByCid = slsResultMapper.findSlsResultByRid(rid);
		return findSlsResultByCid;
	}

	@Override
	public SlsResult findSlsResult(SlsResult slsResult) {
		SlsResult findSlsResult = slsResultMapper.findSlsResult(slsResult);
		return findSlsResult;
	}

   
}
