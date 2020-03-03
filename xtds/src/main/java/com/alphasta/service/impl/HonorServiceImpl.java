package com.alphasta.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.HonorMapper;
import com.alphasta.model.Honor;
import com.alphasta.service.HonorService;
@Service
public class HonorServiceImpl implements HonorService {
	@Autowired
    public HonorMapper honorMapper;
	@Override
	public Honor getByid(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return honorMapper.findByid(map);
	}

	@Override
	public List<Honor> getByPageInfo(PageInfo pageinfo) {
		// TODO Auto-generated method stub
		return honorMapper.findByPageInfo(pageinfo);
	}

	@Override
	public void insertOne(Honor honor) {
		// TODO Auto-generated method stub
		honorMapper.insertOne(honor);
	}

	@Override
	public void updateOne(Honor honor) {
		// TODO Auto-generated method stub
		honorMapper.updateOne(honor);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		honorMapper.deleteOne(id);
	}
	
	public  List<Honor> getConutNum(Map<String,Object> map){
		return honorMapper.getCountHonor(map);
	}

}
