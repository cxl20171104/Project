package com.alphasta.service;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Customer;

public interface CustomerService {
	/**
	 * 根据分页条件查询客户信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	void findCustomerPageCondition(PageInfo pageInfo);

	/**
	 * 通过id查询客户信息
	 * 
	 * @param id
	 * @return
	 */
	Customer findCustomerById(Long id);

	/**
	 * 查询客户信息
	 * 
	 * @param contactIds
	 * @return
	 */
	public List<Customer> findCustomer(Customer customer);

	/**
	 * 插入客户信息
	 * 
	 * @param customer
	 * @return
	 */
	int insert(Customer customer);

	/**
	 * 更新客户信息
	 * 
	 * @param customer
	 * @return
	 */
	int update(Customer customer);

	/**
	 * 通过id删除客户信息
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByIds(String ids);

	/**
	 * 通过id共享客户信息
	 * 
	 * @param ids
	 * @return
	 */
	public int updateTypeByIds(String ids, Integer type);

	/**
	 * 指定跟单人信息
	 * 
	 * @return
	 */
	public int setTracking(Long customerId, Long trackingId);

}
