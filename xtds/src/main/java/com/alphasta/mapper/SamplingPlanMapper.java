package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.SamplingPlan;

/**
 * 采样排班mapper
 * 
 * @author LiYunhao
 * 
 */
public interface SamplingPlanMapper {
	/**
	 * 根据分页条件查询采样排班信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	List<SamplingPlan> findSamplingPlanPageCondition(PageInfo pageInfo);

	/**
	 * 根据分页条件查询采样排班信息数量
	 * 
	 * @param pageInfo
	 * @return
	 */
	int findSamplingPlanPageCount(PageInfo pageInfo);

	/**
	 * 通过id查询采样排班信息
	 * 
	 * @param id
	 * @return
	 */
	SamplingPlan findSamplingPlanById(Long id);

	/**
	 * 查询采样排班信息
	 * 
	 * @param map
	 * @return
	 */
	List<SamplingPlan> findSamplingPlan(Map<String, Object> map);

	/**
	 * 插入采样排班信息
	 * 
	 * @param SamplingPlan
	 * @return
	 */
	int insert(SamplingPlan samplingPlan);

	/**
	 * 更新采样排班信息
	 * 
	 * @param SamplingPlan
	 * @return
	 */
	int update(SamplingPlan samplingPlan);

	/**
	 * 通过id删除采样排班信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByIds(String ids);
}
