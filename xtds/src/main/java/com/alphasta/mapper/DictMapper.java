package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.Dict;

/**
 * 数据字典mapper
 * 
 * @author LiYunhao
 * 
 */
public interface DictMapper {
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
	 * 通过dictPid获取子节点数量
	 * 
	 * @param dictPid
	 * @return
	 */
	Integer findDictCountByDictPid(String dictPid);

	/**
	 * 新增数据字典信息
	 * 
	 * @param dict
	 * @return
	 */
	int insert(Dict dict);

	/**
	 * 更新数据字典信息
	 * 
	 * @param dict
	 * @return
	 */
	int update(Dict dict);

	/**
	 * 通过id删除数据字典信息
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByIds(String ids);
}
