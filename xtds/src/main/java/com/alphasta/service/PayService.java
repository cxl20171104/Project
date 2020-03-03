package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Activities;
import com.alphasta.model.Pay;

public interface PayService {

public Pay selectByID(String id);


/**
 * 分页和查询
 * @param pageInfo
 * @return
 */
public void save(Pay pay);
/**
 * 分页和查询
 * @param pageInfo
 * @return
 */
public void del(String ids);
/**
 * 分页和查询
 * @param pageInfo
 * @return
 */
public void update(Pay pay);
/**
 * 分页和查询
 * @param pageInfo
 * @return
 */
void findPayPageCondition(PageInfo pageInfo);

/**
 * 通过excel导入案件线索
 * @param list
 * @return
 */
public int importXls(List<Object> list);

/**
 * 根据用户id查询支付列表
 */
public List<Pay>findPayByUserId(PageInfo pageInfo);
/**
 * 
 * @param map
 * @return
 */
public List<Pay> findPayByMonth(Map<String,Object> map);
/**
 * 
 * @param map
 * @return
 */
public List<Pay>  findPayByQuarter(Map<String,Object> map);

}
