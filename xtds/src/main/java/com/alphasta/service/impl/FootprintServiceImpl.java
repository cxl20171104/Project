package com.alphasta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.FootprintMapper;
import com.alphasta.service.FootprintService;

@Service("footprintService")
public class FootprintServiceImpl implements FootprintService {

	@Autowired
	private FootprintMapper footprintMapper;

	@Override
	public void findDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(footprintMapper.findfootprintPageCondition(pageInfo));
		pageInfo.setTotal(footprintMapper.findfootprintPageCount(pageInfo));
	}

}
