package com.alphasta.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alphasta.model.Options;
import com.alphasta.model.OptionsExample;

public interface OptionService {
	int countByExample(OptionsExample example);

	int deleteByExample(OptionsExample example);

	int deleteByPrimaryKey(String id);

	int insert(Options record);

	int insertSelective(Options record);

	List<Options> selectByExample(OptionsExample example);

	Options selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") Options record,
			@Param("example") OptionsExample example);

	int updateByExample(@Param("record") Options record,
			@Param("example") OptionsExample example);

	int updateByPrimaryKeySelective(Options record);

	int updateByPrimaryKey(Options record);

	List<Options> findByQuestion(String question);
}
