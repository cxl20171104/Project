package com.alphasta.service;

import java.util.List;

import com.alphasta.model.Position;

/**
 * 职位mapper
 * 
 * @author LiYunhao
 *
 */
public interface PositionService {
	/**
	 * 获取职位信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	List<Position> findPosition();

	/**
	 * 通过id查询职位信息
	 * 
	 * @param id
	 * @return
	 */
	Position findPositionById(Long id);

	/**
	 * 插入职位信息
	 * 
	 * @param customer
	 * @return
	 */
	int insert(Position position);

	/**
	 * 更新职位信息
	 * 
	 * @param customer
	 * @return
	 */
	int update(Position position);

	/**
	 * 通过id删除职位信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByIds(String ids);

}
