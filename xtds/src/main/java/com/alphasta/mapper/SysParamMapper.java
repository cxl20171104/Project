package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.SysParam;

public interface SysParamMapper {
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
	 * 获取值
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
