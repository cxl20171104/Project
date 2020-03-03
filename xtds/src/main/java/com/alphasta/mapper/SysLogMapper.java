package com.alphasta.mapper;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.SysLog;

import java.util.List;

public interface SysLogMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SysLog record);

	int insertSelective(SysLog record);

	SysLog selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SysLog record);

	int updateByPrimaryKey(SysLog record);

	List findDataGrid(PageInfo pageInfo);

	int findDataGridCount(PageInfo pageInfo);
}