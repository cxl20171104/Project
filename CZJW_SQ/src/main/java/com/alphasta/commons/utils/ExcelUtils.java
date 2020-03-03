package com.alphasta.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel工具
 * 
 * @author LiYunhao
 *
 */
public class ExcelUtils {
	/**
	 * 读取excel
	 * 
	 * @param is
	 *            输入流
	 * @param fileName
	 *            文件名称（包含后缀名）
	 * @return
	 * @throws Exception
	 */
	public static ExcelInfo readExcel(InputStream is, String fileName) throws Exception {
		ExcelInfo excelInfo = new ExcelInfo();
		//获取文件名
 		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		//xls格式
       		if ("xls".equals(suffix)) {
			excelInfo = readXls(is);
	    //xlsx格式
		} else if ("xlsx".equals(suffix)) {
			excelInfo = readXlsx(is);
		} else {
			throw new Exception("未知文件类型");
		}
		return excelInfo;
	}

	/**
	 * 读取xls信息
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	//HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls 
	private static ExcelInfo readXls(InputStream is) throws IOException {
		int null_num=0;
		ExcelInfo excelInfo = new ExcelInfo();
		// 通过poi读取流
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		//sheets
		Map<String, List<Map<Integer, String>>> sheets = new HashMap<String, List<Map<Integer, String>>>();
		// 获取excel中的sheet数量
		int numberOfSheets = hssfWorkbook.getNumberOfSheets();
		//遍历sheet
		for (int i = 0; i < numberOfSheets; i++) {
			//获取sheet类
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
			if (hssfSheet == null) {
				continue;
			}
			//创建行 
			List<Map<Integer, String>> rows = new ArrayList<Map<Integer, String>>();
			//最后一行行标
			int lastRowNum = hssfSheet.getLastRowNum();
			//遍历所有行
			for (int j = 0; j <= lastRowNum; j++) {
				//或取行
				HSSFRow hssfRow = hssfSheet.getRow(j);
				if (hssfRow != null) {
					if(hssfRow.getCell(0)==null) {
						null_num+=1;
						continue;
					}
					Map<Integer, String> row = new HashMap<Integer, String>();
					// 获取列数
					short lastCellNum = hssfRow.getLastCellNum();
				
					for (int k = 0; k < lastCellNum; k++) {
						if(hssfRow.getCell(k)==null) continue;
						hssfRow.getCell(k).setCellType(CellType.STRING);
						row.put(k, hssfRow.getCell(k).getStringCellValue());
						
					}
					rows.add(row);
				}
			}
			sheets.put(hssfSheet.getSheetName(), rows);
		}
		//存储值  类型Map<String, List<Map<Integer, String>>>
		excelInfo.setSheets(sheets);
		if(null_num!=0){
			excelInfo.setMsg("没有填序号的案件"+null_num+"条");
		}
		return excelInfo;
	}
	
	
	

	
	
	
	/**
	 * 读取xlsx信息
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private static ExcelInfo readXlsx(InputStream is) throws IOException {
		ExcelInfo excelInfo = new ExcelInfo();
		// 通过poi读取流
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		//读取sheet标签
		Map<String, List<Map<Integer, String>>> sheets = new HashMap<String, List<Map<Integer, String>>>();
		// 获取excel中的sheet
		int numberOfSheets = xssfWorkbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
			if (xssfSheet == null) {
				continue;
			}
			List<Map<Integer, String>> rows = new ArrayList<Map<Integer, String>>();
			// 获取sheet中的行
			int lastRowNum = xssfSheet.getLastRowNum();
			System.out.println(lastRowNum);
			for (int j = 0; j <= lastRowNum; j++) {
				//获取行数据
				XSSFRow xssfRow = xssfSheet.getRow(j);
				if(xssfRow != null){
					Map<Integer, String> row = new HashMap<Integer, String>();
					// 获取行中的单元格
					short lastCellNum = xssfRow.getLastCellNum();
					for (int k = 0; k < lastCellNum; k++) {
						//如果没有序号则不加载本行
					
						// 把单元格类型转成string类型
							xssfRow.getCell(k).setCellType(CellType.STRING);
							row.put(k, xssfRow.getCell(k).getStringCellValue());
							
					}
					//处理表格空字符串
						boolean value = false;
						for (Integer in : row.keySet()) {
						 	String str = row.get(in);
						 	if(!str.equals("")){
						 		value=true;
						 	}
					   }
						if(value){
							rows.add(row);
							value = false;
						}
						if(rows.size()==3){
							rows.get(1).put(14, rows.get(2).get(14));
							rows.get(1).put(15, rows.get(2).get(15));
							rows.get(1).put(16, rows.get(2).get(16));
							rows.get(1).put(17, rows.get(2).get(17));
							rows.get(1).put(18, rows.get(2).get(18));
						}
				}else{
					continue;
				}
			}
		
				sheets.put(xssfSheet.getSheetName(), rows);
				System.out.println("rsjL："+rows);
		}
		excelInfo.setSheets(sheets);
		return excelInfo;
	}




/**
 * 专家信息读取xls信息
 * 
 * @param is
 * @return
 * @throws IOException
 */
private static ExcelInfo readZjks(InputStream is) throws IOException {
	ExcelInfo excelInfo = new ExcelInfo();
	// 通过poi读取流
	HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	Map<String, List<Map<Integer, String>>> sheets = new HashMap<String, List<Map<Integer, String>>>();
	System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLL=sheets=LL:"+sheets);
	// 获取excel中的sheet
	int numberOfSheets = hssfWorkbook.getNumberOfSheets();
	for (int i = 0; i < numberOfSheets; i++) {
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
		if (hssfSheet == null) {
			continue;
		}
		List<Map<Integer, String>> rows = new ArrayList<Map<Integer, String>>();
		// 获取sheet中的行
		int lastRowNum = hssfSheet.getLastRowNum();
		for (int j = 0; j <= lastRowNum; j++) {
			HSSFRow hssfRow = hssfSheet.getRow(j);
			if (hssfRow != null) {
				Map<Integer, String> row = new HashMap<Integer, String>();
				// 获取行中的单元格
				short lastCellNum = hssfRow.getLastCellNum();
				for (int k = 1; k < lastCellNum; k++) {
					// 把单元格类型转成string类型
					hssfRow.getCell(k).setCellType(CellType.STRING);
					row.put(k, hssfRow.getCell(k).getStringCellValue());
				}
				rows.add(row);
			}
		}
		sheets.put(hssfSheet.getSheetName(), rows);
	}
	excelInfo.setSheets(sheets);
	return excelInfo;
}


public static void main(String[] args) {
	
	Map<String,String>map = new HashMap<String, String>();
		map.put("1", "任胜杰1");
		map.put("1", "任红雨");
		System.out.println(map.get("1"));
}

/**
 * 读取xlsx信息
 * 
 * @param is
 * @return
 * @throws IOException
 */
private static ExcelInfo readZjkx(InputStream is) throws IOException {
	ExcelInfo excelInfo = new ExcelInfo();
	// 通过poi读取流
	XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
	Map<String, List<Map<Integer, String>>> sheets = new HashMap<String, List<Map<Integer, String>>>();
	// 获取excel中的sheet
	int numberOfSheets = xssfWorkbook.getNumberOfSheets();
	for (int i = 0; i < numberOfSheets; i++) {
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
		if (xssfSheet == null) {
			continue;
		}
		List<Map<Integer, String>> rows = new ArrayList<Map<Integer, String>>();
		// 获取sheet中的行
		int lastRowNum = xssfSheet.getLastRowNum();
		for (int j = 0; j <= lastRowNum; j++) {
			XSSFRow xssfRow = xssfSheet.getRow(j);
			if (xssfRow != null) {
				Map<Integer, String> row = new HashMap<Integer, String>();
				// 获取行中的单元格
				short lastCellNum = xssfRow.getLastCellNum();
				for (int k = 1; k < lastCellNum; k++) {
					// 把单元格类型转成string类型
						xssfRow.getCell(k).setCellType(CellType.STRING);
						row.put(k, xssfRow.getCell(k).getStringCellValue());
				}
				rows.add(row);
			}
		}
		sheets.put(xssfSheet.getSheetName(), rows);
	}
	excelInfo.setSheets(sheets);
	return excelInfo;
}
 
}

