package com.alphasta.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.exception.ServiceException;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.OrderPlanMapper;
import com.alphasta.model.OrderPlan;
import com.alphasta.service.OrderPlanService;

/**
 * 跟单计划service
 * 
 * @author LiYunhao
 * 
 */
@Service("orderPlanService")
public class OrderPlanServiceImpl implements OrderPlanService {
	private static Logger LOGGER = LoggerFactory
			.getLogger(OrderPlanServiceImpl.class);
	@Autowired
	private OrderPlanMapper orderPlanMapper;

	@Override
	public void findOrderPlanPageCondition(PageInfo pageInfo) {
		List<OrderPlan> list = orderPlanMapper
				.findOrderPlanPageCondition(pageInfo);
		int count = orderPlanMapper.findOrderPlanPageCount(pageInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(count);
	}

	@Override
	public OrderPlan findOrderPlanById(Long id) {
		return orderPlanMapper.findOrderPlanById(id);
	}

	@Override
	public int insert(OrderPlan orderPlan) {
		int result = orderPlanMapper.insert(orderPlan);
		if (result != 1) {
			LOGGER.warn("新增跟单计划信息失败");
			throw new ServiceException("新增失败");
		}
		return result;
	}

	@Override
	public int update(OrderPlan orderPlan) {
		int result = orderPlanMapper.update(orderPlan);
		if (result != 1) {
			LOGGER.warn("更新跟单计划信息失败");
			throw new ServiceException("更新跟单计划失败");
		}
		return result;
	}

	@Override
	public int deleteByIds(String ids) {
		return orderPlanMapper.deleteByIds(ids);
	}

}
