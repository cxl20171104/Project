package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Accessories;

public interface AccessoriesService {
	     int countByExample(Accessories example);


	    int deleteByPrimaryKey(Long id);

	    int insert(Accessories record);

	    int insertSelective(Accessories record);
	    int insertSelective2(Accessories record);

	    Accessories selectByPrimaryKey(Long id);
	    Accessories selectByPrimaryKey2(Long id);

	    int updateByPrimaryKeySelective(Accessories record);

	    int updateByPrimaryKey(Accessories record);
	    int updateByPrimaryKey2(Accessories record);
	    
	    
	     List<Accessories>   searAccByCaseid(Long id);
	     List<Accessories>   searAccByCaseid2(String id);
	     Map<String,Object>   getReportListService(Integer page, Integer rows, String sort, String order,Accessories acc);
}
