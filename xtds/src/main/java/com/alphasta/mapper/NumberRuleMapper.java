package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.NumberRule;

public interface NumberRuleMapper {
	/**
	 * 查询编号规则
	 * 
	 * @return
	 */
	List<NumberRule> findNumberRule();

	/**
	 * 通过id查询编号规则
	 * 
	 * @param id
	 * @return
	 */
	NumberRule findNumberRuleById(Long id);

	/**
	 * 新增编号规则
	 * 
	 * @param numberRule
	 * @return
	 */
	int insert(NumberRule numberRule);

	/**
	 * 更新编号规则
	 * 
	 * @param numberRule
	 * @return
	 */
	int update(NumberRule numberRule);

	/**
	 * 删除编号规则
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByIds(String ids);
}
