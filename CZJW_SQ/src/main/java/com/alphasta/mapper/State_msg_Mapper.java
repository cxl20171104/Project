package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.State_msg;

public interface State_msg_Mapper {
    List<State_msg>findState_msg();
    
    int deleteState_msg(String ids);
    
    int insert(State_msg state_msg); 
    
    int update(State_msg state_msg);
    
}
