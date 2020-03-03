package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alphasta.model.Question;
import com.alphasta.model.QuestionExample;

public interface QuestionMapper {
	int countByExample(QuestionExample example);

	int deleteByExample(QuestionExample example);

	int deleteByPrimaryKey(String id);

	int insert(Question record);

	int insertSelective(Question record);

	List<Question> selectByExample(QuestionExample example);

	Question selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") Question record,
			@Param("example") QuestionExample example);

	int updateByExample(@Param("record") Question record,
			@Param("example") QuestionExample example);

	int updateByPrimaryKeySelective(Question record);

	int updateByPrimaryKey(Question record);

	List<Question> findByQuestionnate(Map<String, String> map);
}