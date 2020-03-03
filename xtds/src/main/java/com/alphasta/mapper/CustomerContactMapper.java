package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.CustomerContact;

/**
 * 客户联系人mapper
 * 
 * @author LiYunhao
 * 
 */
public interface CustomerContactMapper {
	List<CustomerContact> findContactByCustomerId(Long customerId);

	/**
	 * 批量新增客户联系人
	 * 
	 * @param list
	 * @return
	 */
	int insert(CustomerContact customerContact);

	/**
	 * 通过客户id删除客户联系人
	 * 
	 * @param customerId
	 * @return
	 */
	int deleteByCustomerId(Long customerId);
}
