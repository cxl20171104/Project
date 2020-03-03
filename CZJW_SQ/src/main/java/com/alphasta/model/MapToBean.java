package com.alphasta.model;

import java.util.HashMap;
import java.util.Map;

import com.alphasta.en.Stage;



public class MapToBean {
   public Map<String,Object>statictics_source(String id,String cluesId,Stage stage,int isUse){
	   Map<String,Object>result=new HashMap<String,Object>();
	   result.put("id", id);
	   result.put("cluesId", cluesId);
	   result.put("stage", stage);
	   result.put("isUse", isUse);
	   return result;
   }
  
    
}
