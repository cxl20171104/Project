package com.alphasta.commons.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelInfo {
	private String name;
	private Map<String, List<Map<Integer, String>>> sheets;

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
			// 获取表头对应列号
			List<Map<Integer, String>> rows = this.sheets.get(sheetName);
			System.out.println("rows:"+rows);
			Map<Integer, String> titleRow = rows.get(titleRowNum-1);
			Set<Integer> titles = titleRow.keySet();
			Map<String, Integer> titleRow2 = new HashMap<String, Integer>();
			for (Integer num : titles) {
				titleRow2.put(titleRow.get(num), num);
			}
			// 转成对应实体类
			Class<?> c = Class.forName(className);
			// 获取实体类的所有属性，返回Field数组  
			for (int i = titleRowNum; i < rows.size(); i++) {
				Object obj = c.newInstance();
				Set<String> propertys = map.keySet();
				// 遍历属性
				for (String property : propertys) {
					Field field = obj.getClass().getDeclaredField(property);
					// 为字段赋值
					field.setAccessible(true);
					field.set(obj, rows.get(i).get(titleRow2.get(map.get(property))));
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
