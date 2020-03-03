package com.alphasta.service;

import java.util.List;

import com.alphasta.model.Progress;

public interface ProgressService {
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
	
}
