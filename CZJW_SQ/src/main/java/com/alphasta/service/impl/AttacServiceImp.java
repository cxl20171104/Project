package com.alphasta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.mapper.AttacMapper;
import com.alphasta.model.Attac;
import com.alphasta.service.AttacService;
@Service
public class AttacServiceImp implements AttacService{
	@Autowired
	private AttacMapper attacMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return attacMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Attac record) {
		// TODO Auto-generated method stub
		return attacMapper.insert(record);
	}

	@Override
	public int insertSelective(Attac record) {
		// TODO Auto-generated method stub
		return attacMapper.insertSelective(record);
	}

	@Override
	public Attac selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return attacMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Attac record) {
		// TODO Auto-generated method stub
		return attacMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Attac record) {
		// TODO Auto-generated method stub
		return attacMapper.updateByPrimaryKey(record);
	}

}
