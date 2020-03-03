package com.alphasta.commons.statisticsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.alphasta.commons.utils.CalculationUtils;
import com.alphasta.en.StaticticsType;
import com.alphasta.model.Dict;

public class UtilFroWord {
	public static Map<String,Object> basic_data(int basic_total,String context_key,List<Map<String, Object>> selectBasic,HttpServletRequest req) {
		Map<String,Object>search_param=new HashMap<String,Object>();
		ServletContext servletContext = req.getServletContext();
		List<Dict>clues_list=(List<Dict>)servletContext.getAttribute(context_key);
		Map<String,Object>clues=new HashMap<String,Object>();
		for(Dict d:clues_list) {
			String name=d.getName();
			String value=d.getValue();
			StringBuffer buf=new StringBuffer();
			int this_total=0;
			for(Map<String,Object> m:selectBasic) {
				if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1&&!m.get("option").toString().equals("-1")) {
					if(m.get("option").toString().equals(value)) {
						this_total+=(Integer)m.get("num");
					}
				}
			}
			buf.append("，数量为："+this_total);
			buf.append("，占比："+PercentFormula.acczb(this_total, basic_total, 2));
		    clues.put(name, buf.toString());
		}
		clues.put("，合计：", basic_total);
		return clues;
	}
}
