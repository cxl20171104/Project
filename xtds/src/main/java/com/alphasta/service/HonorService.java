package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Honor;

public interface HonorService {
	
	public Honor getByid(Map<String,Object> map);
	public List<Honor>  getByPageInfo(PageInfo pageinfo);
	public void insertOne(Honor honor);
	public void updateOne(Honor honor);
	public void delete(String id);
	public  List<Honor> getConutNum(Map<String,Object> map);

}
