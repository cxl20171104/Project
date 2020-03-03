package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.alphasta.commons.utils.InspectionCountExcel;
import com.alphasta.commons.utils.ProgressCountExcel;
import com.alphasta.commons.utils.ResultCountExcel;
import com.alphasta.commons.utils.ResultCountExcel2;
import com.alphasta.commons.utils.ReviewCountExcel;
import com.alphasta.commons.utils.TerritoryCountExcel;

public interface ProStaticesMapper {
	/**
	 * 统计报表查询数量
	 */
	Map<String,Object> probleClueCount(Map<String,Object>map);
	/**
	 * 案件线索统计分析(按职级和违纪行为分类)
	 */
	List<Map<String,Object>> findCountExcel(String sql);
	
	/**
	 * <!-- 根据任意字段以及日期统计数量 -->  
	 */
	List<Map<String,Object>> probleClueByCount(Map<String, Object>map);
	/**
	 * 按年分组统计处置情况
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> probleClueByczMethodCount(Map<String,Object>map);
	/**
	 * 案件线索统计分析(处置情况-增减率)
	 */
	List<ReviewCountExcel> findReviewCountExcel(@Param("attYearMonth")String attYearMonth,@Param("attYearMonth2")String attYearMonth2);
	
	/**
	 * 案件线索统计分析(办理进展-增减率)
	 */
	List<ProgressCountExcel> findProgressCountExcel(@Param("attYearMonth")String attYearMonth,@Param("attYearMonth2")String attYearMonth2,@Param("beReflectRank")String beReflectRank);
	
	/**
	 * 案件线索统计分析（纪委审查情况）
	 */
	List<InspectionCountExcel> findInspectionCountExcel(@Param("attYearMonth")String attYearMonth,
			@Param("attYearMonth2")String attYearMonth2, @Param("xxstate")String xxstate, @Param("beReflectRank")String beReflectRank,@Param("blResult_djcf")String blResult_djcf,@Param("blResult_zjcf")String blResult_zjcf,@Param("blResult_zzcl")String blResult_zzcl,@Param("blResult_yjsf")String blResult_yjsf,@Param("blResult_qtcl")String blResult_qtcl,@Param("zyViolation")String zyViolation);
		
	/**
	 * 案件线索统计分析党纪处分（违纪行为分类情况）
	 */
	List<ResultCountExcel> findResultCountExcel(@Param("attYearMonth")String attYearMonth,
			@Param("attYearMonth2")String attYearMonth2, @Param("xxstate")String xxstate);
	/**
	 * 案件线索统计分析政纪处分（违纪行为分类情况）
	 */
	List<ResultCountExcel2> findResultCountExcel2(@Param("attYearMonth")String attYearMonth,
			@Param("attYearMonth2")String attYearMonth2, @Param("xxstate")String xxstate);
	/**
	 * 自定义列查询处置方式，督办单位，所属领域
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> probleClueBycolumnsCount(Map<String,Object>map);
	/**
	 * 生成领域统计表
	 */
	List<TerritoryCountExcel> territoryCountExcel(@Param("attYearMonth")String attYearMonth,
			@Param("attYearMonth2")String attYearMonth2);
	
	
	/**
	 * 统计类案件数量
	 */
	List<Map<String,Object>> proClueByCount(Map<String,Object> map);
}
