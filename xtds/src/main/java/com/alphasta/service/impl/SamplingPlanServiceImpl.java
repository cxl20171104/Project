package com.alphasta.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.exception.ServiceException;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.SamplingPlanMapper;
import com.alphasta.model.SamplingPlan;
import com.alphasta.service.SamplingPlanService;

/**
 * 
 * @author LiYunhao
 * 
 */
@Service("samplingPlanService")
public class SamplingPlanServiceImpl implements SamplingPlanService {
	private static Logger LOGGER = LoggerFactory
			.getLogger(CustomerServiceImpl.class);

	@Autowired
	private SamplingPlanMapper samplingPlanMapper;

	@Override
	public void findSamplingPlanPageCondition(PageInfo pageInfo) {
		List<SamplingPlan> list = samplingPlanMapper
				.findSamplingPlanPageCondition(pageInfo);
		int count = samplingPlanMapper.findSamplingPlanPageCount(pageInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(count);
	}

	@Override
	public SamplingPlan findSamplingPlanById(Long id) {
		return samplingPlanMapper.findSamplingPlanById(id);
	}

	@Override
	public List<SamplingPlan> findSamplingPlan(Map<String, Object> map) {
		List<SamplingPlan> list = samplingPlanMapper.findSamplingPlan(map);
		return list;
	}

	@Override
	public int insert(SamplingPlan samplingPlan) {
		int result = samplingPlanMapper.insert(samplingPlan);
		if (result != 1) {
			LOGGER.warn("新增采样排班信息失败");
			throw new ServiceException("新增失败");
		}
		return result;
	}

	@Override
	public int update(SamplingPlan samplingPlan) {
		int result = samplingPlanMapper.update(samplingPlan);
		if (result != 1) {
			LOGGER.warn("更新采样排班信息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}

	@Override
	public int deleteByIds(String ids) {
		return samplingPlanMapper.deleteByIds(ids);
	}

}
