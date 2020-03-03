package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alphasta.mapper.ProgressMapper;
import com.alphasta.model.Progress;
import com.alphasta.service.ProgressService;

public class ProgressServiceImp implements ProgressService{
    @Autowired 
    ProgressMapper progressMapper;
	@Override
	public int addProgress(Progress progress) {
		int p=progressMapper.addProgress(progress);
		return p;
	}

	@Override
	public Progress deleteProgress(String id) {
		// TODO Auto-generated method stub
		Progress deleteProgress = progressMapper.deleteProgress(id);
		return deleteProgress;
	}

	@Override
	public int updateProgress(Progress progress) {
		// TODO Auto-generated method stub
		int updateProgress = progressMapper.updateProgress(progress);
		return updateProgress;
	}

	@Override
	public Progress findProgressById(String id) {
		// TODO Auto-generated method stub
		Progress findProgressById = progressMapper.findProgressById(id);
		return findProgressById;
	}

	@Override
	public List<Progress> findProgressByCid(String cid) {
		// TODO Auto-generated method stub
		List<Progress> findProgressByCid = progressMapper.findProgressByCid(cid);
		return findProgressByCid;
	}

	@Override
	public Progress findLastProgress(String cid) {
		// TODO Auto-generated method stub
		Progress findLastProgress = progressMapper.findLastProgress(cid);
		return findLastProgress;
	}
   
}
