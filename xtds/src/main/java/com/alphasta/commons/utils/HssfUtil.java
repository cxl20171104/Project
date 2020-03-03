package com.alphasta.commons.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.alphasta.commons.annotation.ExcelFild;
import com.alphasta.commons.result.ExcelField;

/**
 * 
 * @Description: excel导出封装类
 * @author kang
 * @date 2016年8月24日
 */
public class HssfUtil {
	// 通用
	public static <Q> void writeExcel(HttpServletResponse response,
			String fileName, List<Q> list, Class<Q> cls) throws IOException,
			IllegalArgumentException, IllegalAccessException {
		HSSFWorkbook wb = new HSSFWorkbook();

		Field[] fields = cls.getDeclaredFields();
		ArrayList<String> headList = new ArrayList<String>();

		for (Field f : fields) {
			ExcelFild field = f.getAnnotation(ExcelFild.class);
			if (field != null) {
				headList.add(field.title());
			}
		}

		CellStyle style = getCellStyle(wb);
		Sheet sheet = wb.createSheet();
		/**
		 * 设置Excel表的第一行即表头
		 */
		Row row = sheet.createRow(0);
		for (int i = 0; i < headList.size(); i++) {
			Cell headCell = row.createCell(i);
			headCell.setCellType(Cell.CELL_TYPE_STRING);
			headCell.setCellStyle(style);// 设置表头样式
			headCell.setCellValue(String.valueOf(headList.get(i)));
			// sheet.autoSizeColumn((short) i);// 设置单元格自适应
			sheet.setColumnWidth(0, 15 * 256);
		}

		for (int i = 0; i < list.size(); i++) {
			Row rowdata = sheet.createRow(i + 1);// 创建数据行
			Q q = list.get(i);
			Field[] ff = q.getClass().getDeclaredFields();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int j = 0;
			for (Field f : ff) {
				ExcelFild field = f.getAnnotation(ExcelFild.class);
				if (field == null) {
					continue;
				}
				f.setAccessible(true);
				Object obj = f.get(q);
				Cell cell = rowdata.createCell(j);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if(obj==null){
					cell.setCellValue("");
					
					continue;
				}
				// 当数字时
				if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
				// 当为字符串时
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				// 当为布尔时
				if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				// 当为时间时
				if (obj instanceof Date)
					cell.setCellValue(sdf.format((Date) obj));
				// 当为时间时
				if (obj instanceof Calendar)
					cell.setCellValue((Calendar) obj);
				// 当为小数时
				if (obj instanceof Double)
					cell.setCellValue((Double) obj);
				// 将序号替换为123456
				if (j == 0)
					cell.setCellValue(i + 1);
				j++;
			}
		}
		/*
		 * fileName=new String(fileName.getBytes("gbk"),"iso-8859-1");
		 * response.setHeader("Content-Disposition",
		 * "attachment; filename=\""+fileName+"\"");
		 * response.setCharacterEncoding("UTF-8");
		 * response.setHeader("Content-Type","application/octet-stream");
		 */
		response.setContentType("application/vnd.ms-excel");
		//fileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
		fileName=fileName.replace("&quot;", "");
		fileName = URLEncoder.encode(fileName,"UTF-8");
		response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
		} finally {
			ouputStream.close();
		}
	}
	// 通用
	public static <Q> void writeExcelWithTitle(HttpServletResponse response,
			String fileName, String showline,List<Q> list, Class<Q> cls) throws IOException,
			IllegalArgumentException, IllegalAccessException {
		HSSFWorkbook wb = new HSSFWorkbook();
		
		Field[] fields = cls.getDeclaredFields();
		ArrayList<String> headList = new ArrayList<String>();
		
		for (Field f : fields) {
			ExcelFild field = f.getAnnotation(ExcelFild.class);
			if (field != null) {
				headList.add(field.title());
			}
		}
		
		CellStyle style = getCellStyle(wb);
		Sheet sheet = wb.createSheet();
		/**
		 * 设置Excel表的第一行即表头
		 */
		
		Row row0 = sheet.createRow(0);
		
		for (int i = 0; i < headList.size(); i++) {
			Cell headCell = row0.createCell(i);
			headCell.setCellType(Cell.CELL_TYPE_STRING);
			headCell.setCellStyle(style);// 设置表头样式
			headCell.setCellValue(String.valueOf(headList.get(i)));
		   // sheet.autoSizeColumn((short) i);// 设置单元格自适应
			sheet.setColumnWidth(0, 15 * 256);
		}
		
		for (int i = 0; i < list.size(); i++) {
			Row rowdata = sheet.createRow(i + 1);// 创建数据行
			Q q = list.get(i);
			Field[] ff = q.getClass().getDeclaredFields();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int j = 0;
			for (Field f : ff) {
				ExcelFild field = f.getAnnotation(ExcelFild.class);
				if (field == null) {
					continue;
				}
				f.setAccessible(true);
				Object obj = f.get(q);
				Cell cell = rowdata.createCell(j);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if(obj==null){
					cell.setCellValue("");
				}
				// 当数字时
				if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
				// 当为字符串时
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				// 当为布尔时
				if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				// 当为时间时
				if (obj instanceof Date)
					cell.setCellValue(sdf.format((Date) obj));
				// 当为时间时
				if (obj instanceof Calendar)
					cell.setCellValue((Calendar) obj);
				// 当为小数时
				if (obj instanceof Double)
					cell.setCellValue((Double) obj);
				// 将序号替换为123456
				if (j == 0)
					cell.setCellValue(i + 1);
				j++;
			}
		}
		/*
		 * fileName=new String(fileName.getBytes("gbk"),"iso-8859-1");
		 * response.setHeader("Content-Disposition",
		 * "attachment; filename=\""+fileName+"\"");
		 * response.setCharacterEncoding("UTF-8");
		 * response.setHeader("Content-Type","application/octet-stream");
		 */
		response.setContentType("application/vnd.ms-excel");
		fileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
		response.setHeader("Content-disposition", "attachment; filename="
				+ fileName);
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
		} finally {
			ouputStream.close();
		}
	}

	/**
	 * 
	 * @Description:设置表头样式
	 * @author kang
	 * @date 2016年8月24日
	 */
	public static CellStyle getCellStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(HSSFCellStyle.SOLID_FOREGROUND);// 让单元格居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		style.setWrapText(true);// 设置自动换行
		style.setFont(font);
		return style;
	}

}