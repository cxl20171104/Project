package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.mapper.OptionsMapper;
import com.alphasta.model.Options;
import com.alphasta.model.OptionsExample;
import com.alphasta.service.OptionService;

@Service
public class OptionServiceImpl implements OptionService {
	@Autowired
	private OptionsMapper optionsMapper;

	@Override
	public int countByExample(OptionsExample example) {
		// TODO Auto-generated method stub
		return optionsMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(OptionsExample example) {
		// TODO Auto-generated method stub
		return optionsMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return optionsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Options record) {
		// TODO Auto-generated method stub
		return optionsMapper.insert(record);
	}

	@Override
	public int insertSelective(Options record) {
		// TODO Auto-generated method stub
		return optionsMapper.insertSelective(record);
	}

	@Override
	public List<Options> selectByExample(OptionsExample example) {
		// TODO Auto-generated method stub
		return optionsMapper.selectByExample(example);
	}

	@Override
	public Options selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return optionsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Options record, OptionsExample example) {
		// TODO Auto-generated method stub
		return optionsMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Options record, OptionsExample example) {
		// TODO Auto-generated method stub
		return optionsMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Options record) {
		// TODO Auto-generated method stub
		return optionsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Options record) {
		// TODO Auto-generated method stub
		return optionsMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Options> findByQuestion(String question) {
		// TODO Auto-generated method stub
		return optionsMapper.findByQuestion(question);
	}

}
