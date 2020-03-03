package com.alphasta.mapper;

import java.util.List;

import com.alphasta.commons.result.Statistics;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.OrderRecord;

/**
 * 订单记录mapper
 * 
 * @author LiYunhao
 * 
 */
public interface OrderRecordMapper {
	/**
	 * 根据分页条件查询订单记录信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	List<OrderRecord> findOrderRecordPageCondition(PageInfo pageInfo);

	/**
	 * 根据分页条件查询订单记录信息数量
	 * 
	 * @param pageInfo
	 * @return
	 */
	int findOrderRecordPageCount(PageInfo pageInfo);

	/**
	 * 通过id查询订单记录信息
	 * 
	 * @param id
	 * @return
	 */
	OrderRecord findOrderRecordById(Long id);

	/**
	 * 查询订单统计
	 * 
	 * @param statistics
	 * @return
	 */
	List<Statistics> findOrderRecordStatistics(Statistics statistics);

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
