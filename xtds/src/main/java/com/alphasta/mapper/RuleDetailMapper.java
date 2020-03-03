package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.RuleDetail;

public interface RuleDetailMapper {
	/**
	 * 查询编号规则详情
	 * 
	 * @return
	 */
	List<RuleDetail> findRuleDetailByRuleId(Long ruleId);

	/**
	 * 通过id查询编号规则详情
	 * 
	 * @param id
	 * @return
	 */
	RuleDetail findRuleDetailById(Long id);

	/**
	 * 新增编号规则详情
	 * 
	 * @param numberRule
	 * @return
	 */
	int insert(RuleDetail ruleDetail);

	/**
	 * 更新编号规则详情
	 * 
	 * @param numberRule
	 * @return
	 */
	int update(RuleDetail ruleDetail);

	/**
	 * 删除编号规则详情
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByRuleIds(String ruleIds);

	/**
	 * 删除编号规则详情
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByIds(String ids);
}
