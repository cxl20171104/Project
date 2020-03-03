package com.alphasta.service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.OrderPlan;

/**
 * 跟单计划mapper
 * 
 * @author LiYunhao
 * 
 */
public interface OrderPlanService {
	/**
	 * 根据分页条件查询跟单计划信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	void findOrderPlanPageCondition(PageInfo pageInfo);

	/**
	 * 通过id查询跟单计划信息
	 * 
	 * @param id
	 * @return
	 */
	OrderPlan findOrderPlanById(Long id);

	/**
	 * 插入跟单计划信息
	 * 
	 * @param SamplingPlan
	 * @return
	 */
	int insert(OrderPlan orderPlan);

	/**
	 * 更新跟单计划信息
	 * 
	 * @param SamplingPlan
	 * @return
	 */
	int update(OrderPlan orderPlan);

	/**
	 * 通过id删除跟单计划信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByIds(String ids);
}
