package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.CaseClue;
import com.alphasta.model.User;

public interface CaseClueService {
	/**
	 * 根据分页条件查询案件线索信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	void findCaseCluePageCondition(PageInfo pageInfo);
	/**
	 * 获取案件线索信息
	 * 
	 * @param pageInfo
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
	 * 重复件查询
	 */
	List<CaseClue> findTorepeat(CaseClue caseClue);
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
	 * 通过excel导入案件线索
	 * @param list
	 * @return
	 */
	public int importXls(List<Object> list,User user);
	
	//根据反映人和被反映人  查询库中是否存在相同的数据 且 重复件 
	//cfState重复情况（如果重复了，保存重复线索的id）
	//sfcf  是否重复（系统判定不重复0，系统判定重复1，人工判定不重复2，人工判定重复3）
	public void   isDouble (CaseClue caseClue);
	/**
	 *获取刚插入的自增id 
	 */
	
	Long last_insert_id();
	
	/**
	 * 按月份统计线索数量
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> groupBymonths(Map<String,Object>map);
	public List<Map<String,Object>> groupCaseByjsAndTime(String js,String start,String end);
	

	/**
	 * 根据条件数量统计
	 */
	 List<Map<String,Object>> findCaseClueCountNum(Map<String,Object>map);
	
}
