package com.alphasta.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.exception.ServiceException;
import com.alphasta.mapper.SysParamMapper;
import com.alphasta.model.SysParam;
import com.alphasta.service.SysParamService;

/**
 * 系统参数service
 * 
 * @author LiYunhao
 *
 */
@Service("sysParamService")
public class SysParamServiceImpl implements SysParamService {
	private static Logger LOGGER = LoggerFactory.getLogger(SysParamServiceImpl.class);

	@Autowired
	private SysParamMapper sysParamMapper;

	@Override
	public List<SysParam> findSysParam(SysParam sysParam) {
		return sysParamMapper.findSysParam(sysParam);
	}

	@Override
	public SysParam findSysParamById(Long id) {
		return sysParamMapper.findSysParamById(id);
	}
	
	@Override
	public String findSysParamValueByKey(String key) {
		return sysParamMapper.findSysParamValueByKey(key);
	}

	@Override
	public int update(SysParam sysParam) {
		int result = sysParamMapper.update(sysParam);
		if (result != 1) {
			LOGGER.warn("更新系统参数信息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}

}
