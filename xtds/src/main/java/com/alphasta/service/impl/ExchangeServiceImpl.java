package com.alphasta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.ExchangeMapper;
import com.alphasta.service.ExchangeService;
@Service
public class ExchangeServiceImpl implements ExchangeService {

	 @Autowired
     private ExchangeMapper exchangeMapper;
	 @Override
		public void findDataGrid(PageInfo pageInfo) {
			pageInfo.setRows(exchangeMapper.findExchangePageCondition(pageInfo));
			pageInfo.setTotal(exchangeMapper.findExchangePageCount(pageInfo));
		} 

}
