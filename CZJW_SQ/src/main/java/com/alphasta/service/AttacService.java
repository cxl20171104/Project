package com.alphasta.service;

import com.alphasta.model.Attac;

public interface AttacService {
	  int deleteByPrimaryKey(Long id);

	    int insert(Attac record);

	    int insertSelective(Attac record);

	    Attac selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(Attac record);

	    int updateByPrimaryKey(Attac record);
}
