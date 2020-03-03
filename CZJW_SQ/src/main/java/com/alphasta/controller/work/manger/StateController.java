package com.alphasta.controller.work.manger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.State;
import com.alphasta.service.StateService;
@Controller
@RequestMapping("/state")
public class StateController {
	  @Autowired
	  private StateService stateService;
	  
	  
	  @RequestMapping("/clueStateList")
	  @ResponseBody
	  public PageInfo getListByClueid(Long id){
		  PageInfo result=new PageInfo();
		  try {
			  if(id!=null){
				  Map<String,Object>  map=new HashMap<String,Object>();				  
				  map.put("ajxsid", id);
				  List<State> stateList = stateService.getStateList(map);
				  result.setRows(stateList);
				  return result; 				  
			  }
			  return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			  return result;
			
		}
		 
	  }
	  //查询进展详情
	  @RequestMapping("/problemStateList")
	  @ResponseBody
	  public PageInfo problemStateList(String id){
		  PageInfo result=new PageInfo();
		  try {
			  if(id!=null){
				  Map<String,Object>  map=new HashMap<String,Object>();				  
				  map.put("ajnum", id);
				  List<State> stateList = stateService.getStateList(map);
				  result.setRows(stateList);
				  return result; 				  
			  }
			  return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			  return result;
			
		}
		 
	  }
}
