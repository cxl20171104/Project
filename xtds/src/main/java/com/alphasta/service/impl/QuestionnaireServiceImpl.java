package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.QuestionMapper;
import com.alphasta.mapper.QuestionnaireMapper;
import com.alphasta.model.QuestionExample;
import com.alphasta.model.QuestionExample.Criteria;
import com.alphasta.model.Questionnaire;
import com.alphasta.model.QuestionnaireExample;
import com.alphasta.service.QuestionService;
import com.alphasta.service.QuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
	@Autowired
	private QuestionnaireMapper questionnaireMapper;
	@Autowired
	private QuestionService questionService;

	@Override
	public int countByExample(QuestionnaireExample example) {
		// TODO Auto-generated method stub
		return questionnaireMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(QuestionnaireExample example) {
		List<Questionnaire> naire = this.selectByExample(example);
		for (Questionnaire q : naire) {
			QuestionExample eq = new QuestionExample();
			Criteria c = eq.createCriteria();
			c.andQuestionnaireEqualTo(q.getId());
			questionService.deleteByExample(eq);
		}
		return questionnaireMapper.deleteByExample(example);
	}

	@Override
	public int insert(Questionnaire record) {
		// TODO Auto-generated method stub
		return questionnaireMapper.insert(record);
	}

	@Override
	public int insertSelective(Questionnaire record) {
		// TODO Auto-generated method stub
		return questionnaireMapper.insertSelective(record);
	}

	@Override
	public List<Questionnaire> selectByExample(QuestionnaireExample example) {
		// TODO Auto-generated method stub
		return questionnaireMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(Questionnaire record,
			QuestionnaireExample example) {
		// TODO Auto-generated method stub
		return questionnaireMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Questionnaire record,
			QuestionnaireExample example) {
		// TODO Auto-generated method stub
		return questionnaireMapper.updateByExample(record, example);
	}

	@Override
	public void updateQuestionNaireById(Questionnaire questionNaire) {
		// TODO Auto-generated method stub
		questionnaireMapper.updateQuestionNaireById(questionNaire);
	}

	@Override
	public List<Questionnaire> findQuestionNaireById(String id) {
		// TODO Auto-generated method stub
		return questionnaireMapper.findQuestionNaireById(id);
	}

	@Override
	public List<Questionnaire> findQuestionNaire(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return questionnaireMapper.findQuestionNaire(pageInfo);
	}

}
