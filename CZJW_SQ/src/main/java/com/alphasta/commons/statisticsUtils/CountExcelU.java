package com.alphasta.commons.statisticsUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;


import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.WordUtils;

public class CountExcelU {
	static{
		
	}
	/**
	 * 案件线索统计导出(按职级和违纪行为分类)
	 */
	/**
	 * 
	 * @param attYearMonth
	 * @param attYearMonth2
	 * @param beforeAttYearMonth
	 * @param beforeAttYearMonth2
	 * @param req
	 * @param res
	 */
	public static void zyCountExcel(Map<String,Object>dataMap,String templateDetail,HttpServletRequest req,HttpServletResponse res) {
		System.out.println("ddddddd");
		String excelName=templateDetail.split("_")[0];
		String templateName=templateDetail.split("_")[1];
		createExcel(dataMap,excelName,templateName,req,res);	
	}
    private static  void  createExcel(Map<String, Object> dataMap,String excelName,String templateName,HttpServletRequest req,HttpServletResponse res){
    	String floderPath = req.getSession().getServletContext().getRealPath("outExcel");
		File floder = new File(floderPath);
		if (!floder.exists()) {
			floder.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			File exportMillCertificateWord = WordUtils
					.exportMillCertificateWord(dataMap, floderPath
							+ File.separator + sdf.format(new Date())
							+ excelName,templateName);
			System.out.println(exportMillCertificateWord.getAbsolutePath());
			// 下载
			FileUtils.downFile(res,
					exportMillCertificateWord.getAbsolutePath(),
					sdf.format(new Date()) + excelName);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    }


}
