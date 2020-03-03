package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.NumberDetail;

public interface NumberDetailMapper {
	/**
	 * 通过ruleId查询编号详情
	 * 
	 * @param numberDetail
	 * @return
	 */
	List<NumberDetail> findNumberDetailByRuleId(NumberDetail numberDetail);

	/**
	 * 通过id查询编号详情
	 * 
	 * @param id
	 * @return
	 */
	NumberDetail findNumberDetailById(Long id);

	/**
	 * 新增编号详情
	 * 
	 * @param numberDetail
	 * @return
	 */
	int insert(NumberDetail numberDetail);

	/**
	 * 更新编号详情
	 * 
	 * @param numberDetail
	 * @return
	 */
	int update(NumberDetail numberDetail);

	/**
	 * 删除编号详情
	 * 
	 * @param ruleId
	 * @return
	 */
	int deleteByRuleId(Long ruleId);
}
