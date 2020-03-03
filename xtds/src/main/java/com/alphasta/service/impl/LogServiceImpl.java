package com.alphasta.service.impl;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.SysLogMapper;
import com.alphasta.model.SysLog;
import com.alphasta.service.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description：
 * @author：liyunhao
 * @date：2015/10/30 10:40
 */
@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public void insertLog(SysLog sysLog) {
		sysLogMapper.insert(sysLog);
	}

	@Override
	public void findDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(sysLogMapper.findDataGrid(pageInfo));
		pageInfo.setTotal(sysLogMapper.findDataGridCount(pageInfo));
	}
}
