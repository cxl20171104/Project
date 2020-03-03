package com.alphasta.commons.utils;

public class SpiltString {
	public static String spilt(String str) {
		 StringBuffer sb = new StringBuffer();
		 String[] temp = str.split(",");
		 for (int i = 0; i < temp.length; i++) {
		  if (!"".equals(temp[i]) && temp[i] != null)
		   sb.append("'" + temp[i] + "',");
		 }
		 String result = sb.toString();
		 String tp = result.substring(result.length() - 1, result.length());
		 if (",".equals(tp))
		  return result.substring(0, result.length() - 1);
		 else
		  return result;
	}
}