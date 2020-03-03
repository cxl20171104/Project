package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Accessories;

public interface AccessoriesMapper {
	 int countByExample(Accessories example);

	    int deleteByPrimaryKey(Long id);

	    int insert(Accessories record);

	    Integer insertSelective(Accessories record);
	    int insertSelective2(Accessories record);

	    Accessories selectByPrimaryKey(Long id);
	    Accessories selectByPrimaryKey2(Long id);

	    int updateByPrimaryKeySelective(Accessories record);

	    int updateByPrimaryKey(Accessories record);
	    int updateByPrimaryKey2(Accessories record);
	    
	    List<Accessories> selectByCaseid(Long id);
	    List<Accessories> selectByCaseid2(String id);
	    
	    
	    List<Accessories> getAccByMap(Map<String,Object>map);
	    List<Accessories> getType();
}