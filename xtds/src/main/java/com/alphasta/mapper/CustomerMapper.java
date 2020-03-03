package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Customer;

public interface CustomerMapper {
	/**
	 * 根据分页条件查询客户信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	List<Customer> findCustomerPageCondition(PageInfo pageInfo);

	/**
	 * 根据分页条件查询客户信息数量
	 * 
	 * @param pageInfo
	 * @return
	 */
	int findCustomerPageCount(PageInfo pageInfo);

	/**
	 * 获取客户信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	List<Customer> findCustomer(Customer customer);

	/**
	 * 通过id查询客户信息
	 * 
	 * @param id
	 * @return
	 */
	Customer findCustomerById(Long id);

	/**
	 * 查询客户统计
	 * 
	 * @return
	 */
	List<Map<String, Object>> findCustomerStatistics();

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
	 * @param id
	 * @return
	 */
	int deleteByIds(String ids);

	/**
	 * 通过id共享客户信息
	 * 
	 * @param id
	 * @return
	 */
	int updateTypeByIds(Map<String, Object> map);
}
