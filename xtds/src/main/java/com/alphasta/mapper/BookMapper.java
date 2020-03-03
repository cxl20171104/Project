package com.alphasta.mapper;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Book;
import com.alphasta.model.Gift;

public interface BookMapper {
	    //增加图书
		int addBook(Book book);
		//删除图书
	    int deleteBook(String ids);	
		//修改图书
		int updateBook(Book book);
		Book findBookByid(String id);
		//查询图书
		List<Book> findGiftPageCondition(PageInfo pageInfo);
		int findGiftPageCount(PageInfo pageInfo);
}
