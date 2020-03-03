package com.alphasta.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alphasta.model.ExcelPojo;

public interface ProblemCluesService2 {
	  /**
	   * 根据条件数量统计
	   */
	   Map<String,Object> probleClueCount(Map<String,Object>map);
		/**
		 * 按年分组统计处置情况
		 * @param map
		 * @return
		 */
		List<Map<String,Object>> probleClueByczMethodCount(Map<String,Object>map);
		/**
		 * 按照处分情况分类
		 */
		Map<String,Object> probleClueByblResultCount(Map<String,Object>map);
		/**
		 * 生成excel的service
		 */
		public void createExcelService(String which,Map<String,Object>param,HttpServletRequest req,HttpServletResponse res);
		
		
		/**
		 * 生成word的service
		 */
		public void createWordService(String which,Map<String,Object>param,HttpServletRequest req,HttpServletResponse res);
		/**
		 * 导出线索
		 */
		public void exportFile(List<ExcelPojo> list,HttpServletRequest req,HttpServletResponse res);
}
