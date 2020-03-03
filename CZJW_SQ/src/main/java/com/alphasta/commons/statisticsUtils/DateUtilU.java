package com.alphasta.commons.statisticsUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

public class DateUtilU {
	@SuppressWarnings("finally")
	public static String FormatDate(String dateStr){
		HashMap<String, String> dateRegFormat = new HashMap<String, String>();
		dateRegFormat.put(
				"^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$",
				"yyyy-MM-dd-HH-mm-ss");//2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
		dateRegFormat.put("^\\d{4}\\D$", "yyyy");//2017年
		dateRegFormat.put("^\\d{4}\\D+\\d{1,2}$", "yyyy-M");//2017年12
		dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D$", "yyyy-M");  //2017年12月
		dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}$", "yyyy-M-d"); //2017年12月12
		dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D$", "yyyy-M-d"); //2017年12月12日
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$",
				"yyyy-MM-dd-HH-mm");//2014-03-12 12:05
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$",
				"yyyy-MM-dd-HH");//2014-03-12 12
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd");//2014-03-12
		dateRegFormat.put("^\\d{4}\\D+\\d{2}$", "yyyy-MM");//2014-03
		dateRegFormat.put("^\\d{4}$", "yyyy");//2014
		dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");//20140312120534
		dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");//201403121205
		dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");//2014031212
		dateRegFormat.put("^\\d{8}$", "yyyyMMdd");//20140312
		dateRegFormat.put("^\\d{6}$", "yyyyMM");//201403
		dateRegFormat.put("^\\d{4}\\.+\\d{1}\\.+\\d{1}$","yyyy-M-d");
		dateRegFormat.put("^\\d{4}\\.+\\d{1}\\.+\\d{2}$","yyyy-M-dd");
		dateRegFormat.put("^\\d{4}\\.+\\d{2}\\.+\\d{1}$","yyyy-MM-d");
		dateRegFormat.put("^\\d{4}\\.+\\d{2}\\.+\\d{2}$","yyyy-MM-dd");

		dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$",
				"yyyy-MM-dd-HH-mm-ss");//13:05:34 拼接当前日期
		dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");//13:05 拼接当前日期
		dateRegFormat.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");//14.10.18(年.月.日)
		dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");//30.12(日.月) 拼接当前年份
		dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");//12.21.2013(日.月.年)

		String curDate =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		DateFormat formatter1 =new SimpleDateFormat("yyyy-MM-dd");
		DateFormat formatter2;
		String dateReplace;
		String strSuccess="";
		try {
			for (String key : dateRegFormat.keySet()) {
				if (Pattern.compile(key).matcher(dateStr).matches()) {
					formatter2 = new SimpleDateFormat(dateRegFormat.get(key));
					if (key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$")
							|| key.equals("^\\d{2}\\s*:\\s*\\d{2}$")) {//13:05:34 或 13:05 拼接当前日期
						dateStr = curDate + "-" + dateStr;
					} else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {//21.1 (日.月) 拼接当前年份
						dateStr = curDate.substring(0, 4) + "-" + dateStr;
					}
					dateReplace = dateStr.replaceAll("\\D+", "-");
					// System.out.println(dateRegExpArr[i]);
					strSuccess = formatter1.format(formatter2.parse(dateReplace));
					break;
				}
			}
		} catch (Exception e) {
			System.err.println("-----------------日期格式无效:"+dateStr);
			throw new Exception( "日期格式无效");
		} finally {
			return strSuccess;
		}
	}
	
	
	
	/**
	 * 时间减一年
	 * @param time
	 * @return
	 */
	public static  String date_j(String  time){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		String result="";
		try {  
			Date d1 = df.parse(time);  
			Calendar  g = Calendar.getInstance();  
			g.setTime(d1);  
			g.add(Calendar.YEAR,-1);             
			Date d2 = g.getTime();  
			result=df.format(d2);  
		} catch (ParseException e) {              
			e.printStackTrace();  
		}  
		return result;
	}
}
