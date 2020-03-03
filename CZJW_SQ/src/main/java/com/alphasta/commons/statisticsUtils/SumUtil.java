package com.alphasta.commons.statisticsUtils;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SumUtil {
    public static Map<String,Object> clumonSum(List<Map<String,Object>> list){
   	 Map<String,Object>result=new LinkedHashMap<String,Object>();
	    int l=0;
		for(Map<String,Object> m:list){
			Set<String> keySet = m.keySet();
			for(String key: keySet){
				
					System.out.println(key);
					
					if(key.equals("name")){
						continue;
					}
					if(key.equals("name2")){
						continue;
					}
                    if(l==0){
						
						result.put(key, 0);
						
					}
					Integer n =(Integer)result.get(key);
					if(m.get(key) instanceof String){
						String x=(String)m.get(key);
						result.put(key, Integer.valueOf(n)+Integer.valueOf(x));
					}
					if(m.get(key) instanceof BigDecimal){
						BigDecimal x=(BigDecimal )m.get(key);
						result.put(key, Integer.valueOf(n)+x.intValue());
					}
					if(m.get(key) instanceof Number){
						Number x=(Number)m.get(key);
						result.put(key, Integer.valueOf(n)+x.intValue());
					}
					
				
				
			}
			l++;
		}
    	return result;
    	
    	
    }
}
