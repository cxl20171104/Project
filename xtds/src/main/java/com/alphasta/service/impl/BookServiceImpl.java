package com.alphasta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.BookMapper;
import com.alphasta.service.BookService;
@Service
public class BookServiceImpl implements BookService {
	 @Autowired
     private BookMapper bookMapper;
	@Override
	public void findDataGrid(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		pageInfo.setRows(bookMapper.findGiftPageCondition(pageInfo));
		pageInfo.setTotal(bookMapper.findGiftPageCount(pageInfo));
	}

}
