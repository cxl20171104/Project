package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.CaseClue;

public interface CaseClueMapper {
	/**
	 * 根据分页条件查询案件线索信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	List<CaseClue> findCaseCluePageCondition(PageInfo pageInfo);

	/**
	 * 根据分页条件查询案件线索信息数量
	 * 
	 * @param pageInfo
	 * @return
	 */
	int findCaseCluePageCount(PageInfo pageInfo);

	/**
	 * 获取案件线索信息
	 * 
	 * @param caseClue
	 * @return
	 */
	List<CaseClue> findCaseClue(CaseClue caseClue);

	/**
	 * 通过id查询案件线索信息
	 * 
	 * @param id
	 * @return
	 */
	CaseClue findCaseClueById(Long id);

	/**
	 * 插入案件线索信息
	 * 
	 * @param CaseClue
	 * @return
	 */
	int insert(CaseClue caseClue);

	/**
	 * 更新案件线索信息
	 * 
	 * @param CaseClue
	 * @return
	 */
	int update(CaseClue caseClue);

	/**
	 * 通过id删除案件线索信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByIds(String ids);
	/**
	 *获取刚插入的自增id 
	 */
	
	Long last_insert_id();
	
	List<Map<String,Object>>groupBymonths(Map<String,Object>map);
	
	
	
	List<Map<String,Object>>groupCaseByjsAndTime(Map<String,Object>map);
	
	/**
	 * 根据条件数量统计
	 */
	 List<Map<String,Object>> findCaseClueCountNum(Map<String,Object>map);
}
