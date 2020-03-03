package com.alphasta.service;

import java.util.List;

import com.alphasta.model.Dict;

/**
 * 数据字典mapper
 * 
 * @author LiYunhao
 *
 */
public interface DictService {
	/**
	 * 通过dictPid获取数据字典信息
	 * 
	 * @param dict
	 * @return
	 */
	List<Dict> findDictByDictPid(Dict dict);
	
	/**
	 * 通过id获取数据字典信息
	 * 
	 * @param id
	 * @return
	 */
	Dict findDictById(Long id);

	/**
	 * 新增数据字典信息
	 * 
	 * @param dict
	 * @return
	 */
	int save(Dict dict);

	/**
	 * 通过id删除数据字典信息
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByIds(String ids);
	
	/**
	 * 通过dictPid获取数据字典信息,展示到导出选项的下拉菜单中
	 * @param dict
	 * @return
	 */
	List<Dict> findByDictPid(String dictPid);
	
	/**
	 * 通过DICT获取数据字典信息
	 * @param dict
	 * @return
	 */
	List<Dict> findByDict(Dict dict);
}
