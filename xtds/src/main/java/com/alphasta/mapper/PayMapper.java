package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Pay;

public interface PayMapper {
	
	
	
	
	public Pay selectByID(String id);
	
	
	
	public void save(Pay pay);
	
	public int deleteByIds(String id);
	
	public void update(Pay pay);
	/**
	 * 分页和查询
	 * @param pageInfo
	 * @return
	 */
	List<Pay> findPayPageCondition(PageInfo pageInfo);
	
	int findPayPageCount(PageInfo pageInfo);
	
	/**
	 * 根据用户id查询支付列表
	 */
	List<Pay>findPayByUserId(PageInfo pageInfo);
	/**
	 * 月度未缴纳党费人员
	 */
	public List<Pay> getByMonth(Map<String,Object> map);
	/**
	 * 季度缴纳党费情况
	 * @param map 
	 * @return
	 */
	public List<Pay> getByQuarter(Map<String,Object> map);
	/**
	 * 用户月份查询
	 * @param map
	 * @return
	 */
	public Pay findByUseridAndMonth(Map<String,Object> map);
	public void updatePay(Pay pay);
}
