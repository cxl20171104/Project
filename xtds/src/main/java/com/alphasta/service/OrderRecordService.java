package com.alphasta.service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.OrderRecord;

/**
 * 订单记录mapper
 * 
 * @author LiYunhao
 * 
 */
public interface OrderRecordService {
	/**
	 * 根据分页条件查询订单记录信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	void findOrderRecordPageCondition(PageInfo pageInfo);

	/**
	 * 通过id查询订单记录信息
	 * 
	 * @param id
	 * @return
	 */
	OrderRecord findOrderRecordById(Long id);

	/**
	 * 插入订单记录信息
	 * 
	 * @param OrderRecord
	 * @return
	 */
	int insert(OrderRecord orderRecord);

	/**
	 * 更新订单记录信息
	 * 
	 * @param OrderRecord
	 * @return
	 */
	int update(OrderRecord orderRecord);

	/**
	 * 通过id删除订单记录信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByIds(String ids);
}
