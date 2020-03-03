package com.alphasta.commons.statisticsUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;

import com.alphasta.model.Dict;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.ReflectedPerson;

public class SqlMaker10 {
	//导出时使用的查询sql
     public static String maker(String[]columns,String ids,String sqlStore){
    	 StringBuffer suf=new StringBuffer();
    	 int index=0;
    	 for(String column:columns){
    		 if(index==columns.length-1){
    			 suf.append(column);
    		 }else{
    			 suf.append(column+",");
    			 
    		 }
    		 index++;
    	 }
    	 //ids为空则为查询导出 导出全部的
    	 //ids不为空则为选择导出
    	 String sql="";
    	 if(ids!=null&&!ids.equals("")) {
    		
          sql="Select id,"+suf.toString()+" from excel_v  where id in ( '"+ids.replaceAll(",", "','")+"')";	 
    	 }else {
    		 //处理特殊字符
    		 sqlStore=sqlStore.replaceAll("&amp;#39;", "'").replaceAll("&amp;gt;", ">").replaceAll("&amp;lt;", "<");
    		 sql="Select "+suf.toString()+" from excel_v p where 1=1 "+sqlStore;
    	 }
    	 
    	 return sql;
     }
     //判断某个属性名是那张表的
     public static String whichTable(String filedName) {
    	    System.out.println(filedName);
    	    Field[] ProblemClues = ProblemClues.class.getDeclaredFields();
			String result="no";
			for(Field filed:ProblemClues) {
				filed.setAccessible(true);
				if(filedName.equals(filed.getName())){
					result= "p";
					break;
				}
			}
			Field[] ReflectedPerson = ReflectedPerson.class.getDeclaredFields();
			for(Field filed:ReflectedPerson) {
				filed.setAccessible(true);
				if(filedName.equals(filed.getName())){
					result= "rd";
					break;
				}
			}
			Field[] ReflectingPerson = com.alphasta.model.ReflectingPerson.class.getDeclaredFields();
			for(Field filed:ReflectingPerson) {
				filed.setAccessible(true);
				if(filedName.equals(filed.getName())){
					result= "ring";
					break;
				}
			}
			return result;
     }
     
     public static String num_to_world(String num,String options,List<Dict> list) {
    	/* Map<String,List<Dict>> ml=OptionModel.options;
    	 List<Dict> list = ml.get(options);*/
    	/* DictService dictService = new DictService();*/
    	 String result="";
    	 System.out.println(options);
    	 if(num!=null) {
    		 
    	 if(num.indexOf(",")!=-1) {
    		 String[]nums=num.split(",");
    		 StringBuffer suf=new StringBuffer();
    		 int index=0;
    		 for(String n:nums) {
                 for(Dict d:list) {
                	 if(n.equals(d.getValue())) {
                		 if(index==nums.length-1) {
                			 
                			 suf.append(d.getName());
                		 }else {
                			 
                			 suf.append(d.getName()+",");
                		 }
                		
                	 }
                	 continue;
                 }
                 index++;
    		 }
    		 result=suf.toString();
    	 }else {
    		for(Dict d:list) {
    			 if(d.getValue().equals(num)) {
    				 result=d.getName();
    			 }
    		 }
    		 
    	 }
    	
    	 }
    	 return result;
     }
     
     public static String num_to_world_dictId(String num,String options,List<Dict> list) {
     	/* Map<String,List<Dict>> ml=OptionModel.options;
     	 List<Dict> list = ml.get(options);*/
     	/* DictService dictService = new DictService();*/
     	 String result="";
     	 System.out.println(options);
     	 if(num!=null) {
     		 
     	 if(num.indexOf(",")!=-1) {
     		 String[]nums=num.split(",");
     		 StringBuffer suf=new StringBuffer();
     		 int index=0;
     		 for(String n:nums) {
                  for(Dict d:list) {
                 	 if(n.equals(d.getDictId())) {
                 		 if(index==nums.length-1) {
                 			 
                 			 suf.append(d.getName());
                 		 }else {
                 			 
                 			 suf.append(d.getName()+",");
                 		 }
                 		
                 	 }
                 	 continue;
                  }
                  index++;
     		 }
     		 result=suf.toString();
     	 }else {
     		for(Dict d:list) {
     			 if(d.getDictId().equals(num)) {
     				 result=d.getName();
     			 }
     		 }
     		 
     	 }
     	
     	 }
     	 return result;
      }
     
   //处理excl时间格式XXXX年XX月XX日格式
 	/*public  static String zhTime(String str){
 		SimpleDateFormat yyyy_MM_dd=new SimpleDateFormat("yyyy-MM-dd");  
 		SimpleDateFormat yyyyyMMdd=new SimpleDateFormat("yyyyMMdd");
 		String result="";
 		if(str.indexOf("-")!=-1) {
 			str=str.replace("-", "");
 			
 			Date parse;
			try {
				 parse = yyyyyMMdd.parse(str);
				 result = yyyy_MM_dd.format(parse);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			
 		}
 		if(str.indexOf("年")!=-1&&str.indexOf("月")!=-1&&str.indexOf("日")!=-1) {
 			str=str.replace("年", "");
 			str=str.replace("月", "");
 			str=str.replace("日", "");
 			Date parse;
			try {
				 parse = yyyyyMMdd.parse(str);
				 result = yyyy_MM_dd.format(parse);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			
 		}
 		return result;
 	}*/
     //处理excl时间格式 1.时间格式 407090 2.字符串格式
  	public  static String zhTime(String str){
  	    Pattern pattern = Pattern.compile("^-?[1-9]\\d*$");
        Matcher isNum = pattern.matcher(str);
        String result = "";
  		if(isNum.matches()) {
  	  		Date ctime;
  	  		Calendar c = new GregorianCalendar(1900,0,-1);  
  	  		Date d = c.getTime();  
  	  		ctime = DateUtils.addDays(d, Integer.valueOf(str));
  	  	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	  		result=sdf.format(ctime);
  		}else {
  			str=str.replace("年", "-")
  					.replace("月", "-")
  					.replace("日", "-")
  					.replace("/", "-")
  					.replace(".", "-")
  					.replace("\\", "-");
  			String[] strs = str.split("-");
  			String year="";
  			String month="";
  			String day="";
  			if(strs[0].length()<4){
  				year="20"+strs[0];
  			}else {
  				year=strs[0];
  			}
            if(strs[1].length()<2){
  				month="0"+strs[1];
  			}else {
  				month=strs[1];
  			}
            if(strs[2].length()<2){
  				day="0"+strs[2];
  			}else {
  				day=strs[2];
  			}
            
            result=year+"-"+month+"-"+day;
  		}
  		return result;
  	}
 	public static void main(String[] args) {
 		System.out.println(zhTime("2017/09/10"));
 		System.out.println(zhTime("2017年09月10日"));
 		System.out.println(zhTime("17年9月5日"));
 		System.out.println(zhTime("2017.9.5"));
 		System.out.println(zhTime("2017-9-5"));
	}
}
