package com.alphasta.mapper;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Footprint;

public interface FootprintMapper {



	/**
	 * 用户足迹列表
	 * 
	 * @param pageInfo
	 * @return
	 */
	List<Footprint> findfootprintPageCondition(PageInfo pageInfo);

	/**
	 * 统计用户足迹
	 * 
	 * @param pageInfo
	 * @return
	 */
	int findfootprintPageCount(PageInfo pageInfo);
}