package com.alphasta.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.User;
import com.alphasta.model.Zjk;

public interface ZjkService {
	
	/*//获取专家库信息
	List<Zjk> findZjk1();*/
	
	//获取专家库信息
	List<Map<String, Object>> findZjk(PageInfo pageInfo);
	
	Zjk findZjkById(Long id);
	
	//通过ID查询专家信息
	List<Zjk> findDictByDictPid(Zjk zjk);
	
	
	void findCompanyPageCondition(PageInfo pageInfo);
	
	int insert(Zjk zjk);
	
	int insertData(Zjk zjk);
	
	int deleteByIds(String ids);

	//更新专家库信息
	int update(Zjk zjk);
	
	//public String importXls(File file,String questionnaire);
	
	/**
	 * 通过excel导入案件线索
	 * @param list
	 * @return
	 */
	public int importJzk(List<Object> list,User user);
    
	
	
	public List<Map<String,Object>> groupCaseByjsAndTime(String js,String start,String end);
}
