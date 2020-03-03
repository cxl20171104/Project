package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.mapper.AnswerlogMapper;
import com.alphasta.model.AnswerLog;
import com.alphasta.service.AnswerlogService;

@Service("AnswerlogService")
public class AnswerlogServiceImpl implements AnswerlogService {

	@Autowired
	AnswerlogMapper answerlogMapper;

	@Override
	public AnswerLog selectByPrimaryKey(String id) {
		return answerlogMapper.selectByPrimaryKey(id);
	}

	@Override
	public AnswerLog findByuserid(Long id) {
		return answerlogMapper.findByuserid(id);
	}

	@Override
	public void deleteByPrimaryKey(String id) {
		answerlogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(AnswerLog answerlog) {
		answerlogMapper.insert(answerlog);
	}

	@Override
	public void updateByPrimaryKeySelective(AnswerLog answerlog) {
		answerlogMapper.updateByPrimaryKeySelective(answerlog);
	}

	@Override
	public List<AnswerLog> selectByquentionId(AnswerLog answerlog) {
		// TODO Auto-generated method stub
		return answerlogMapper.selectByquentionId(answerlog);
	}

}
