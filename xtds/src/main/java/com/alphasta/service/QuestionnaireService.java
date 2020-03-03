package com.alphasta.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Questionnaire;
import com.alphasta.model.QuestionnaireExample;

public interface QuestionnaireService {
	int countByExample(QuestionnaireExample example);

	int deleteByExample(QuestionnaireExample example);

	int insert(Questionnaire record);

	int insertSelective(Questionnaire record);

	List<Questionnaire> selectByExample(QuestionnaireExample example);

	int updateByExampleSelective(@Param("record") Questionnaire record,
			@Param("example") QuestionnaireExample example);

	int updateByExample(@Param("record") Questionnaire record,
			@Param("example") QuestionnaireExample example);

	void updateQuestionNaireById(Questionnaire questionNaire);

	List<Questionnaire> findQuestionNaireById(String id);

	public List<Questionnaire> findQuestionNaire(PageInfo pageInfo);
}
