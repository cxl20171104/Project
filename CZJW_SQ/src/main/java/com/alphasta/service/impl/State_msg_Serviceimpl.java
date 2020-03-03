package com.alphasta.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.alphasta.commons.exception.ServiceException;
import com.alphasta.mapper.State_msg_Mapper;
import com.alphasta.model.State_msg;
import com.alphasta.service.State_msg_Service;

@Service("state_msg_Service")
public class State_msg_Serviceimpl  implements State_msg_Service{
	private static Logger LOGGER = LoggerFactory.getLogger(State_msg_Serviceimpl.class);
	//private static Logger LOGGER=(Logger) LoggerFactory.getLogger(State_msg_Serviceimpl.class);
	@Autowired
	private State_msg_Mapper state_msg_Mapper;
	@Override
	public List<State_msg> findState_msg() {
		
		return state_msg_Mapper.findState_msg();
	}
	@Override
	public int insert(State_msg state_msg) {
		int result =state_msg_Mapper.insert(state_msg);
		if(result!=1){
			LOGGER.warn("新增消息失败");
			throw new ServiceException("新增失败");
		}
		return result;
	}
	@Override
	public int deleteState_msg(String ids) {
		
		return state_msg_Mapper.deleteState_msg(ids);
	}
	
	
	
	@Override
	public int upate(State_msg state_msg) {
		// TODO Auto-generated method stub
		int result=state_msg_Mapper.update(state_msg);
		if(result!=1){
			LOGGER.warn("更新消息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}
    	
   
}
