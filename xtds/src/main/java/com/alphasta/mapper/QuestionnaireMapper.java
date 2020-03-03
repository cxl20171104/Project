package com.alphasta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Questionnaire;
import com.alphasta.model.QuestionnaireExample;

public interface QuestionnaireMapper {
	int countByExample(QuestionnaireExample example);

	int deleteByExample(QuestionnaireExample example);

	int insert(Questionnaire record);

	int insertSelective(Questionnaire record);

	List<Questionnaire> selectByExample(QuestionnaireExample example);

	int updateByExampleSelective(@Param("record") Questionnaire record,
			@Param("example") QuestionnaireExample example);

	int updateByExample(@Param("record") Questionnaire record,
			@Param("example") QuestionnaireExample example);

	/**
	 * 根据id查询问卷
	 * 
	 * @param id
	 * @return
	 */
	List<Questionnaire> findQuestionNaireById(String id);

	/**
	 * 根据发布人查询
	 * 
	 * @param pubshlisher
	 * @return
	 */
	List<Questionnaire> findQuestionNaireByPubshlisher(String pubshlisher);

	/**
	 * 根据主题名称
	 * 
	 * @param Name
	 * @return
	 */
	Questionnaire findQuestionNaireByName(String Name);

	/**
	 * 根据活动ID查询
	 * 
	 * @param activities
	 * @return
	 */

	Questionnaire findQuestionNaireByActivities(String activities);

	/**
	 * 更新
	 * 
	 * @param id
	 */
	void updateQuestionNaireById(Questionnaire questionNaire);

	/**
	 * 分页查询
	 * 
	 * @param questionNaire
	 * @return
	 */
	List<Questionnaire> findpage(Questionnaire questionNaire);

	/**
	 * 查询条件查询 分页
	 * 
	 * @param pageInfo
	 * @return
	 */
	public List<Questionnaire> findQuestionNaire(PageInfo pageInfo);
}