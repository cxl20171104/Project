package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.model.State;

public interface StateService {
	 int deleteByPrimaryKey(Long id);

	    int insert(State record);

	    int insertSelective(State record);

	    State selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(State record);

	    int updateByPrimaryKey(State record);
	    
	    List<State> getStateList(Map<String,Object> map);
}
