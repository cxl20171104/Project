package com.alphasta.commons.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CalculationUtils {
	/**
	 * 同比计算公式
	 * @param num 现在数量，
	 * @param total 以前数量
	 * @return 比值
	 */
	public   String accuracy(double num, double total, int scale){
		if(total ==0){
			return "100%";
		}else{
			DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();  
			//可以设置精确几位小数  
			df.setMaximumFractionDigits(scale);  
			//模式 例如四舍五入  
			df.setRoundingMode(RoundingMode.HALF_UP);  
			double accuracy_num = (num - total)/total * 100;  
			String c = df.format(accuracy_num)+"%";
			//去掉逗号
			String result = c.replaceAll(",","");
			return result;  
		}
	}
	/**
	 * 占比计算公式
	 * @param dateStr
	 * @return
	 */
	public  String acczb(int num, int total, int scale){   
		if(num ==0 || total == 0){
			String result = "0%";
			return result;  
		}else{
			NumberFormat numberFormat = NumberFormat.getInstance();  
			// 设置精确到小数点后2位  
			numberFormat.setMaximumFractionDigits(2);  
			String result = numberFormat.format((float) num / (float) total * 100)+"%";  
			return result;
		}
	}
	public static void main(String[] args) {
		
		//String a = acczb(1,26,2);
		//System.out.println(a);
	}
}
