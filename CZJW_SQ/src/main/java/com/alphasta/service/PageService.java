package com.alphasta.service;

import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.ReflectingPerson;
import com.alphasta.model.Report;



public interface PageService {
	//页面数据查询
    //显示分办页面
	PageInfo havingPageService(Map<String,Object>condition);
	//新案件
	PageInfo  neweCluesService(Map<String,Object>condition);
	//在办件
	PageInfo workingService(Map<String,Object>condition);
	//初步核实
	PageInfo initialService(Map<String,Object>condition);
	//了结案件
	PageInfo overingService(Map<String,Object>condition);
    //检索库
	PageInfo libraryService(Map<String, Object> condition);
	//
	//到期案件分页查询
		//处置建议到期
	PageInfo overTimePage_czjyService(Map<String,Object>condition);
		//立案审查到期
	PageInfo overTimePage_lascService(Map<String,Object>condition);
		//案件审理到期
	PageInfo overTimePage_ajslService(Map<String,Object>condition);
		//留党察看到期(新添)
	PageInfo overTimePage_ldckService(Map<String,Object>condition);
		//暂存待查到期
	PageInfo overTimePage_zcdcService(Map<String,Object>condition);
		//谈话函询到期
	PageInfo overTimePage_thhxService(Map<String,Object>condition);
	//处分决定执行期限到期(新添)
		PageInfo overTimePage_takeEffectService(Map<String,Object>condition);
	//监督室报备案件
	PageInfo report(Map<String,Object>condition);
	//县区线索
	PageInfo xqService(Map<String,Object>condition);
	//暂存待查件
	PageInfo temporaryService(Map<String, Object> condition);
	public  Map<String, Object> MakeMap(
			ProblemClues problemClues,
			ReflectedPerson reflectedPerson,
			ReflectingPerson reflectingPerson,
			Report report,
			String startDate,
			String endDate,
			int page,int rows,
			String sort,String order,
			String zddb);
	        PageInfo agsWorkingService(Map<String, Object> condition);
	        
	        
	//退还件        
	PageInfo returnService(Map<String, Object> condition);
	//退回件
	PageInfo rtingList(Map<String, Object> condition);
	//线索统计的详情
	PageInfo tj_detail(Map<String, Object> condition);
	//信访室新案件
	PageInfo xfClueServices(Map<String,Object>condition);
	//重复件
	PageInfo repeatService(Map<String,Object>condition);
	//所有重复件
	PageInfo repeatAllService(Map<String,Object>condition);
	//同案人员列表
	PageInfo tonganList (Map<String,Object>condition);
	//删除件
	PageInfo del_clues(Map<String,Object>condition);
	
}
