package com.alphasta.mapper;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Gift;

public interface GiftMapper {
    //增加礼品
	int addGift(Gift gift);
	//删除礼品
    int deleteGift(String ids);	
	//修改礼品
	int updateGift(Gift gift);
	Gift findGiftByid(String id);
	//查询礼品
	List<Gift> findGiftPageCondition(PageInfo pageInfo);
	int findGiftPageCount(PageInfo pageInfo);
}
