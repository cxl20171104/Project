package com.alphasta.service;

import java.util.List;

import com.alphasta.model.State_msg;

public interface State_msg_Service {
    List<State_msg> findState_msg();
     
    int insert(State_msg state_msg);
    
    int deleteState_msg(String ids);
    
    int upate (State_msg state_msg);
}
