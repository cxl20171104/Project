package com.alphasta.commons.statisticsUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PercentFormula {
	/**
	 * 同比计算公式
	 * @param num 现在数量，
	 * @param total 以前数量
	 * @return 比值
	 */
	public  static  String accuracy(double now_num, double old_num, int scale){
		if(old_num ==0.0&&now_num!=0.0){
			return "100%";
		}else if(old_num==0.0&&now_num==0.0){
			return "0%";
		}else{
			DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();  
			//可以设置精确几位小数  
			df.setMaximumFractionDigits(scale);  
			//模式 例如四舍五入  
			df.setRoundingMode(RoundingMode.HALF_UP);  
			double accuracy_num = (now_num - old_num)/old_num * 100;  
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
	public static  String acczb(int num, int total, int scale){   
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
	/**
	 * 根据最新的数量和旧的同比计算新的同比
	 * @param args
	 */
	public static String  new_tb(double now_num_2018,double old_num_2018,String old_tb_2018,int scale) {
		if(old_tb_2018.equals("100%")) {
			/*只有一种情况是100%*/
			return "100%";
		}
		double ot_2018=Double.parseDouble(old_tb_2018.replace("%",""))*0.01;
		System.out.println("ot_2018:"+ot_2018);
		double old_num_2017=old_num_2018/(1+ot_2018);
		System.out.println("old_num_2017:"+old_num_2017);
		System.out.println(old_num_2017);
		String accuracy = accuracy(now_num_2018, old_num_2017, scale);
		return accuracy;
	}
	/**
	 * 同比求和
	 * @param args
	 */
	public static String tb_qh(String tb_0,String tb_1,int scale) {
		double ot_0=Double.parseDouble(tb_0.replace("%",""))*0.01;
		double ot_1=Double.parseDouble(tb_1.replace("%",""))*0.01;
		
		DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();  
		//可以设置精确几位小数  
		df.setMaximumFractionDigits(scale);  
		//模式 例如四舍五入  
		df.setRoundingMode(RoundingMode.HALF_UP); 
		double accuracy_num=(ot_0+ot_1)*100/2;
		String c = df.format(accuracy_num)+"%";
		return c;
	}
	public static void main(String[] args) {
		System.out.println(tb_qh("10%", "20%",2));
		
	}
}
