package com.alphasta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.annotation.DataSourceChange;
import com.alphasta.mapper.SlaveMapper;
import com.alphasta.service.SlaveService;

@Service
public class SlaveServiceImpl implements SlaveService {

	@Autowired
	private SlaveMapper slaveMapper;

	@Override
	@DataSourceChange(slave = true)
	public Integer count() {
		return slaveMapper.count();
	}

}
