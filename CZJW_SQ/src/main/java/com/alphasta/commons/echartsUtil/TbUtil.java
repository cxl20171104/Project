package com.alphasta.commons.echartsUtil;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;





public class TbUtil {
	//创建一个柱形图对象
			public static Zxt createZxt(String text,List seriesData,List legendData){
				 //柱形图
				Zxt zxt=new Zxt();
				//标题
				zxt.setText(text);				
				//数据
				zxt.setSeriesData(seriesData);
				//图例
				zxt.setLegendData(legendData);
				return zxt;
			}
			
			//创建一个饼形图对象
			public static Bxt createBxt(String text,List data,List legendData){
				Bxt bxt=new Bxt();
				bxt.setText(text);
				bxt.setSeriesName(text);
				bxt.setLegendData(legendData);				
				List<Object>list=new ArrayList<Object>();
				for(int i=0;i<legendData.size();i++){
					Map<String,Object>map=new LinkedHashMap<String,Object>();
					map.put("value", data.get(i));
					map.put("name", legendData.get(i));
					list.add(map);
				}
				bxt.setSeriesData(list);
				return  bxt;
			}       
}
