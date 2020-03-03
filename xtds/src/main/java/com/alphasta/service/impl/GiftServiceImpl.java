package com.alphasta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.GiftMapper;
import com.alphasta.service.GiftService;
@Service
public class GiftServiceImpl implements GiftService{
     @Autowired
     private GiftMapper giftMapper;
	 @Override
		public void findDataGrid(PageInfo pageInfo) {
			pageInfo.setRows(giftMapper.findGiftPageCondition(pageInfo));
			pageInfo.setTotal(giftMapper.findGiftPageCount(pageInfo));
		}   
  
}
