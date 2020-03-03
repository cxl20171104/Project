package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.AnswerLog;

public interface AnswerlogMapper {

	/**
	 * 根据id查询
	 * 
	 * @param id
	 */
	AnswerLog selectByPrimaryKey(String id);

	/**
	 * 根据用户id查询
	 */
	AnswerLog findByuserid(Long id);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	void deleteByPrimaryKey(String id);

	/**
	 * 根据问卷id和用户id查询
	 * 
	 * @param answerlog
	 */
	List<AnswerLog> selectByquentionId(AnswerLog answerlog);

	/**
	 * 添加
	 * 
	 * @param answerlog
	 */
	void insert(AnswerLog answerlog);

	/**
	 * 更新保存
	 * 
	 * @param answerlog
	 */
	void updateByPrimaryKeySelective(AnswerLog answerlog);
}
