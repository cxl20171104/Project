package com.alphasta.mapper;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.ExcelPojo;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.ReflectedPerson;
public interface ProblemCluesMapper {
	
	/**
	 *  查询所有的案件线索：findProblemClues
	 */
	List<ProblemClues> findProblemClues();
	/**
	 * 根据字段查询案件线索
	 * 
	 */
	//List<ProblemClues> findProblemCluesByColumn(ProblemClues problemClues);
	/**
	 * 根据导出条件查询
	 */
	//List<ProblemClues> findProblemCluesOnimpExcl(PageInfo pageInfo);
	/**
	 * 根据ID查询线索
	 */
	ProblemClues findProblemCluesById(String id);
	
	/**
	 * 根据belongToId查询线索
	 */
	List<ProblemClues> findProblemCluesByBelongToId(String id);
	
	/**
	 * 根据分页条件查询案件线索信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	//List<ProblemClues> findProblemCluesPageCondition(PageInfo pageInfo);
	
	/**
	 * 根据分页条件查询案件线索信息数量
	 */
	//int findProblemCluesPageCount(PageInfo pageInfo);
	/**
	 * 案件线索添加
	 * @return 
	 */
	void insertProblemClues(ProblemClues problemClues);
	/**
	 * 案件线索更新
	 */
	int update(ProblemClues problemClues);
	/**
	 * 删除案件线索
	 */
	void deleteByids(String[] str);
	/**
	 * 批量删除重复件
	 */
	void deleteRepeatByids(String[] str);
	/**
	 * 批量删除同案人员
	 */
	void deleteBelongToIdByids(String[] str);
	
	
	/**
	 * 重复件下拉选
	 * @param problemClues
	 * @return
	 */
	List<ProblemClues> findTorepeat(ProblemClues problemClues);
	
	/**
	 * 重复件移除
	 */
	int updateRepeat(ProblemClues problemClues);
	
	/**
	 * 根据ID查询线索
	 */
	List<ProblemClues> findProblemCluesByDuplicateId(String id);
	
	/**
	 * 把字段编号转换成文字（导出excel表）
	 */
	//ProblemClues changeNumToWord(ProblemClues problemClues);
	
	/**
	 * 查询重复件
	 */
	List<ProblemClues>findProblemCluesByduplicate(ProblemClues problemClues);
	
	
	List<ExcelPojo> excelData(String sql);
	
	/**
	 * 根据CASEID查询线索
	 */
	ProblemClues findProblemCluesByCluesNum(String cluesNum);
	List<ProblemClues> findProblemCluesRepeat(ProblemClues problemClues2);
	
	ProblemClues findMaxCluesNum();
	public ProblemClues find_clues_by_ref(ReflectedPerson reflectedPerson);
}

