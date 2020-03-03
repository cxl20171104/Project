package com.alphasta.commons.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;

public class ExcelInfo {
	private String name;
	private Map<String, List<Map<Integer, String>>> sheets;
    private String msg;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, List<Map<Integer, String>>> getSheets() {
		return sheets;
	}

	public void setSheets(Map<String, List<Map<Integer, String>>> sheets) {
		this.sheets = sheets;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * excel数据转实体类
	 * @param sheetName sheet名称
	 * @param titleRowNum 表头的行号
	 * @param className 实体类名称
	 * @param map 实体类属性与表头名称对应关系
	 * @return
	 */
	public List<Object> dataToModel(String sheetName,Integer titleRowNum,String className,Map<String, String> map){
		List<Object> list = new ArrayList<Object>();
		try {
			//获取所有行
			List<Map<Integer, String>> rows = this.sheets.get(sheetName);
			if(rows==null){
				rows = this.sheets.get("Sheet0");
			}
			//表头和数值        {0=编号}
			Map<Integer, String> titleRow = rows.get(titleRowNum-1);
			//行数
			Set<Integer> titles = titleRow.keySet();
			//用于key和value交换{编号=0}
			Map<String, Integer> titleRow2 = new HashMap<String, Integer>();
			for (Integer num : titles) {
				titleRow2.put(titleRow.get(num), num);
			}
			// 转成对应实体类
			Class<?> c = Class.forName(className);
			// 获取实体类的所有属性，返回Field数组 行 
			for (int i = titleRowNum; i < rows.size(); i++) {
				Object obj = c.newInstance();
				Set<String> propertys = map.keySet();
				// 遍历属性     编号   列
				for (String property : propertys) {
					   //列名
					  //System.out.println(property);
					 //根据列名查出列数值
					//System.out.println(titleRow2.get(map.get(property)));
				   //取出excel中的值
					//System.out.println(property+":"+rows.get(i).get(titleRow2.get(map.get(property))));
					//根据列名
					Field field = obj.getClass().getDeclaredField(property);
					// 为私有字段赋值
					field.setAccessible(true);
					//为类字段赋值              //根据列数值取出excel表中的值
					String string = map.get(property);
					Integer integer = titleRow2.get(string);
					String string2 = rows.get(i).get(integer);
					field.set(obj, string2);
					
					
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
