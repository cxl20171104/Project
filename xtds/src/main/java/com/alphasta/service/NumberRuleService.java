package com.alphasta.service;

import java.util.List;

import com.alphasta.model.NumberDetail;
import com.alphasta.model.NumberRule;
import com.alphasta.model.RuleDetail;
import com.alphasta.model.User;

public interface NumberRuleService {
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
	int insertDetail(RuleDetail ruleDetail);

	/**
	 * 更新编号规则详情
	 * 
	 * @param numberRule
	 * @return
	 */
	int updateDetail(RuleDetail ruleDetail);

	/**
	 * 删除编号规则详情
	 * 
	 * @param ids
	 * @return
	 */
	int deleteDetailByIds(String ids);

	/**
	 * 获得编号
	 * 
	 * @param ruleId
	 * @param user
	 * @return
	 */
	NumberDetail getNumber(Long ruleId, User user);

	/**
	 * 保存编号详情
	 * 
	 * @param ruleId
	 * @param user
	 * @return
	 */
	int saveNumberDetail(Long ruleId, User user);
}
