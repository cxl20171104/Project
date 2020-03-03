package com.alphasta.mapper;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Exchange;

public interface ExchangeMapper {
    //增加礼品
   	int addExchange(Exchange exchange);
   	//删除礼品
    int deleteExchange(String ids);	
   	//修改礼品
   	int updateExchange(Exchange exchange);
   	Exchange findExchangeByid(String id);
   	//查询礼品（查询和分页）
   	List<Exchange> findExchangePageCondition(PageInfo pageInfo);
   	int findExchangePageCount(PageInfo pageInfo);
}
