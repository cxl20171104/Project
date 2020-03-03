package com.alphasta.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.exception.ServiceException;
import com.alphasta.commons.utils.Config;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.CustomerMapper;
import com.alphasta.mapper.OrderRecordMapper;
import com.alphasta.mapper.SysParamMapper;
import com.alphasta.mapper.UserMapper;
import com.alphasta.model.Customer;
import com.alphasta.model.OrderRecord;
import com.alphasta.service.NumberRuleService;
import com.alphasta.service.OrderRecordService;

@Service("orderRecordService")
public class OrderRecordServiceImpl implements OrderRecordService {
	private static Logger LOGGER = LoggerFactory
			.getLogger(OrderRecordServiceImpl.class);
	@Autowired
	private OrderRecordMapper orderRecordMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private NumberRuleService numberRuleService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SysParamMapper sysParamMapper;

	@Override
	public void findOrderRecordPageCondition(PageInfo pageInfo) {
		List<OrderRecord> list = orderRecordMapper
				.findOrderRecordPageCondition(pageInfo);
		int count = orderRecordMapper.findOrderRecordPageCount(pageInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(count);
	}

	@Override
	public OrderRecord findOrderRecordById(Long id) {
		return orderRecordMapper.findOrderRecordById(id);
	}

	@Override
	public int insert(OrderRecord orderRecord) {
		int result = orderRecordMapper.insert(orderRecord);
		if (result != 1) {
			LOGGER.warn("新增订单记录失败");
			throw new ServiceException("新增失败");
		}
		numberRuleService.saveNumberDetail(Long.parseLong(sysParamMapper
				.findSysParamValueByKey(Config.NUMBERRULEID)), userMapper
				.findUserById(orderRecord.getCreaterId()));
		if (orderRecord.getState().equals("1")) {
			Customer customer = customerMapper.findCustomerById(orderRecord
					.getCustomerId());
			customer.setType(2);
			customerMapper.update(customer);
		}
		return result;
	}

	@Override
	public int update(OrderRecord orderRecord) {
		int result = orderRecordMapper.update(orderRecord);
		if (result != 1) {
			LOGGER.warn("更新订单记录失败");
			throw new ServiceException("更新失败");
		}
		if (orderRecord.getState().equals("1")) {
			Customer customer = customerMapper.findCustomerById(orderRecord
					.getCustomerId());
			customer.setType(2);
			customerMapper.update(customer);
		}
		return result;
	}

	@Override
	public int deleteByIds(String ids) {
		return orderRecordMapper.deleteByIds(ids);
	}

}
