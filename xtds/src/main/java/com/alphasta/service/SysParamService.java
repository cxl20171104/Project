package com.alphasta.service;

import java.util.List;

import com.alphasta.model.SysParam;

public interface SysParamService {
	/**
	 * 获取系统参数信息
	 * 
	 * @param sysParam
	 * @return
	 */
	List<SysParam> findSysParam(SysParam sysParam);

	/**
	 * 通过id查询系统参数信息
	 * 
	 * @param id
	 * @return
	 */
	SysParam findSysParamById(Long id);

	/**
	 * 通过key查询系统参数值
	 * 
	 * @param key
	 * @return
	 */
	String findSysParamValueByKey(String key);

	/**
	 * 更新系统参数信息
	 * 
	 * @param sysParam
	 * @return
	 */
	int update(SysParam sysParam);

}
