package com.alphasta.commons.statisticsUtils;

import java.lang.reflect.Field;
import java.util.Date;

import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.model.ProblemClues;

public class SqlMaker11<T>{
	public  String  insert(T t) {
		//获取实体类的所有属性
		Date date=new Date();
		long start=date.getTime();
		
    	Field[] declaredFields = t.getClass().getDeclaredFields();
    	StringBuffer colums=new StringBuffer();
    	StringBuffer values=new StringBuffer();
    	for(Field f:declaredFields) {
    		f.setAccessible(true);
    		try {
				if(f.get(t)!=null&&!f.get(t).equals("")) {
					if(f.getName().equals("serialVersionUID")) {
						continue;
					}
					colums.append(f.getName()+", ");
					values.append(f.get(t)+", ");
				   
  	        }
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	colums.append("id");
    	values.append(GetIdUtil.getId());
    	
    	String sql="insert into "+t.getClass().getSimpleName()+" ("+colums.toString()+" ) VALUES ("+values.toString()+")";
    	Date date2=new Date();
    	long end=date2.getTime();
    	System.out.println("执行时间"+(end-start));
    	return sql;
    }
	public static void main(String[] args) {
		ProblemClues p=new ProblemClues();
		SqlMaker11<ProblemClues> sql=new SqlMaker11<ProblemClues>();
		System.out.println(sql.insert(p));
	}
}
