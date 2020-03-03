package com.alphasta.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.exception.ServiceException;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.CustomerContactMapper;
import com.alphasta.mapper.CustomerMapper;
import com.alphasta.model.Customer;
import com.alphasta.model.CustomerContact;
import com.alphasta.service.CustomerService;

/**
 * 客户管理service
 * 
 * @author LiYunhao
 * 
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	private static Logger LOGGER = LoggerFactory
			.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CustomerContactMapper customerContactMapper;

	@Override
	public void findCustomerPageCondition(PageInfo pageInfo) {
		List<Customer> list = customerMapper
				.findCustomerPageCondition(pageInfo);
		int count = customerMapper.findCustomerPageCount(pageInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(count);
	}

	@Override
	public Customer findCustomerById(Long id) {
		return customerMapper.findCustomerById(id);
	}

	@Override
	public List<Customer> findCustomer(Customer customer) {
		return customerMapper.findCustomer(customer);
	}

	@Override
	public int insert(Customer customer) {
		int result = customerMapper.insert(customer);
		CustomerContact customerContact = new CustomerContact();
		customerContact.setCustomerId(customer.getId());
		customerContact.setContactId(customer.getTrackingId());
		customerContactMapper.insert(customerContact);
		if (result != 1) {
			LOGGER.warn("新增客户信息失败");
			throw new ServiceException("新增失败");
		}
		return result;
	}

	@Override
	public int update(Customer customer) {
		int result = customerMapper.update(customer);
		if (result != 1) {
			LOGGER.warn("更新客户信息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}

	@Override
	public int deleteByIds(String ids) {
		int result = customerMapper.deleteByIds(ids);
		return result;
	}

	@Override
	public int updateTypeByIds(String ids, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("type", type);
		int result = customerMapper.updateTypeByIds(map);
		return result;
	}

	@Override
	public int setTracking(Long customerId, Long trackingId) {
		Customer customer = customerMapper.findCustomerById(customerId);
		customer.setTrackingId(trackingId);
		customer.setType(0);
		int result = customerMapper.update(customer);
		if (result != 1) {
			LOGGER.warn("指定跟单人信息失败");
			throw new ServiceException("指定跟单人失败");
		}
		CustomerContact customerContact = new CustomerContact();
		customerContact.setCustomerId(customerId);
		customerContact.setContactId(trackingId);
		customerContactMapper.insert(customerContact);
		return result;
	}

}
