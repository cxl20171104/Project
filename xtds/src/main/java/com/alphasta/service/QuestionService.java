package com.alphasta.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alphasta.commons.result.Tree;
import com.alphasta.model.Question;
import com.alphasta.model.QuestionExample;

public interface QuestionService {
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

	List<Tree> getAllOption(String naireid);

	public String importXls(File file, String questionnaire);

	List<Question> findByQuestionnate(Map<String, String> map);
}
