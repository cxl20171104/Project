package com.alphasta.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.mapper.StateMapper;
import com.alphasta.model.State;
import com.alphasta.service.StateService;
@Service
public class StateServiceImp implements StateService{
	@Autowired
	private StateMapper stateMaper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return stateMaper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(State record) {
		// TODO Auto-generated method stub
		return stateMaper.insert(record);
	}

	@Override
	public int insertSelective(State record) {
		// TODO Auto-generated method stub
		return stateMaper.insertSelective(record);
	}

	@Override
	public State selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return stateMaper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(State record) {
		// TODO Auto-generated method stub
		return stateMaper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(State record) {
		// TODO Auto-generated method stub
		return stateMaper.updateByPrimaryKey(record);
	}

	@Override
	public List<State> getStateList(Map<String, Object> map) {
		return stateMaper.selectStateList(map);
	}
	
}
