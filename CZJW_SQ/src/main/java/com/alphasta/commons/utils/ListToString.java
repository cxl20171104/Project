package com.alphasta.commons.utils;

import java.util.List;

import com.alphasta.model.Dict;

public class ListToString {
   public static String  make(List<Dict>list){
	   int i=0;
	   String result="";
	  
	   for(Dict d:list){
		   if(i==list.size()-1){
			   
			   result+=d.getName();
		   }else{
			   
			   result+=d.getName()+",";
		   }
		   i++;
	   }
	   return result;
   }
 
}
