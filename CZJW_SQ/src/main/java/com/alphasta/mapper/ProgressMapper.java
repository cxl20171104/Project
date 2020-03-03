package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Progress;

public interface ProgressMapper {
	//
	int addProgress(Progress progress);
	//
	Progress deleteProgress (String id);
	//
	int updateProgress(Progress progress);
	//
	Progress findProgressById(String id);
	//
	List<Progress> findProgressByCid(String cid);
	//
	Progress findLastProgress(String cid);
	//
	List<Progress> findProgress(Progress progress);
	//查询最新一条的某条件信息
	Progress XinfindProgress(Progress progress);
	
	List<Progress>getProgressByMap(Map<String,Object>map);
	//查询所有指定范围的进度
	List<Progress>getAllSelectProgressByMap(Map<String,Object>map);
	
}
