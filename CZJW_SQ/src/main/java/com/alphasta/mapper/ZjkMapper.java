package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Zjk;

public interface ZjkMapper {

	//测试
	List<Zjk> findZjk1();
	// 获取专家库信息
	List<Map<String, Object>> findZjk(PageInfo pageInfo);
	
	Zjk findZjkById(Long id);
	
	List<Zjk> findDictByDictPid(Zjk zjk);
	
	List<Zjk> findCompanyPageCondition(PageInfo pageInfo);
	
	int findCompanyPageCount(PageInfo pageInfo);
	
	int insert(Zjk zjk);
	
	//更新专家库信息
	int update(Zjk zjk);
	
	int deleteByIds(String ids);

	int insertData(Zjk zjk);
	
	List<Map<String,Object>>groupCaseByjsAndTime(Map<String,Object>map);
}
