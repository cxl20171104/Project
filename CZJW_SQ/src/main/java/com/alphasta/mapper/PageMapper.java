package com.alphasta.mapper;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.ProblemClues;

public interface PageMapper {
	//分办案件数据分页查询
	List<ProblemClues> heavingPageData(PageInfo pageInfo);
	//新案件数据分页查询
	List<ProblemClues> newCluesPageData(PageInfo pageInfo);
	//在办件数据分页查询
	List<ProblemClues> workingPageData (PageInfo pageInfo);
	//初步核实数据分页查询
	List<ProblemClues> initialServiceM(PageInfo pageInfo);
	//到期案件分页查询
	//处置建议到期
	List<ProblemClues> overTimePageData_czjy(PageInfo pageInfo);
	//立案审查到期
	List<ProblemClues> overTimePageData_lasc(PageInfo pageInfo);
	//案件审理到期
	List<ProblemClues> overTimePageData_ajsl(PageInfo pageInfo);
	//留党察看到期(新添)
	List<ProblemClues> overTimePageData_ldck(PageInfo pageInfo);
	//暂存待查到期
	List<ProblemClues> overTimePageData_zcdc(PageInfo pageInfo);
	//谈话函询到期
	List<ProblemClues> overTimePageData_thhx(PageInfo pageInfo);
	//处分决定执行期限到期
	List<ProblemClues> overTimePageData_takeEffect(PageInfo pageInfo);
	//了结案件分页查询
	List<ProblemClues> overingPageData(PageInfo pageInfo);
	//检索库查询
	List<ProblemClues> libraryPageData(PageInfo pageInfo);
	//县区线索
	List<ProblemClues> xqData(PageInfo pageInfo);
	//暂存待查件
	List<ProblemClues> temporaryData(PageInfo pageInfo);	
	//案管室在办件数据分页查询
	List<ProblemClues> agsWorkingPageData (PageInfo pageInfo);
	
	//报备案件
	List<ProblemClues> report (PageInfo pageInfo);
	//退还案件
    List<ProblemClues> returnServiceM (PageInfo pageInfo);
     //退还案件
    List<ProblemClues> rtingList (PageInfo pageInfo);
    //线索统计详情
    List<ProblemClues> tj_detail(PageInfo pageInfo);
    //信访室新案件
    List<ProblemClues>xfClues(PageInfo pageInfo);
    //重复件
    List<ProblemClues>repeatList(PageInfo pageInfo);
    //已有重复件
    List<ProblemClues>repeatAllList(PageInfo pageInfo);
    //同案人员
    List<ProblemClues> tonganList (PageInfo pageInfo);
    //删除件
    List<ProblemClues> del_clues (PageInfo pageInfo);
    //全网查询
    List<ProblemClues> holeWeb(PageInfo pageInfo);
}
