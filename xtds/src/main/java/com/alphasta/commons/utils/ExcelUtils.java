package com.alphasta.commons.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static XSSFCellStyle getCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		return style;
	}

	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param is
	 * @return String 表头内容的数组
	 */
	public String[] readExcelTitle(InputStream is) {
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = getCellFormatValue(row.getCell((short) i));
		}
		return title;
	}

	/**
	 * 读取Excel数据内容
	 * 
	 * @param is
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public Map<Integer, String> readExcelContent(InputStream is) {
		Map<Integer, String> content = new HashMap<Integer, String>();
		String str = "";
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 0; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
				str += getCellFormatValue(row.getCell((short) j)).trim() + "_";
				j++;
			}
			content.put(i, str);
			str = "";
		}
		return content;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}
	
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
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(suffix)) {
			excelInfo = readXls(is);
		} else if ("xlsx".equals(suffix)) {
			excelInfo = readXlsx(is);
		} else {
			throw new Exception("未知文件类型");
		}
		return excelInfo;
	}
	
	public static ExcelInfo readJzkExcel(InputStream is, String fileName) throws Exception {
		ExcelInfo excelInfo = new ExcelInfo();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(suffix)) {
			excelInfo = readZjks(is);
		} else if ("xlsx".equals(suffix)) {
			excelInfo = readZjkx(is);
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
	private static ExcelInfo readXls(InputStream is) throws IOException {
		ExcelInfo excelInfo = new ExcelInfo();
		// 通过poi读取流
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		Map<String, List<Map<Integer, String>>> sheets = new HashMap<String, List<Map<Integer, String>>>();
		// 获取excel中的sheet
		int numberOfSheets = hssfWorkbook.getNumberOfSheets();
		System.out.println("numberOfSheets::"+numberOfSheets);
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
					for (int k = 0; k < lastCellNum; k++) {
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
					for (int k = 0; k < lastCellNum; k++) {
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
