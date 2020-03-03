package com.alphasta.controller.work.manger;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.dataSource.DataSourceContextHolder;
import com.alphasta.mapper.SysParamMapper;
import com.alphasta.model.PageControl;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.ReflectingPerson;
import com.alphasta.model.Report;
import com.alphasta.service.HoleWeb;
import com.alphasta.service.PageService;

/**
 * 用于界面每个步骤打开界面时加载数据
 * @author chenxiaoliang
 *
 */
@RestController
@RequestMapping("/page")
public class PageController extends BaseController{
     @Autowired
     PageService pageService;
     @Autowired
     HoleWeb     holeWeb;
     @Autowired
 	 SysParamMapper sysParamMapper;
	/**
	 * 
	 * 
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param problemClues
	 * @param startDate
	 * @param endDate
	 * @param pageName
	 * @return 主要业务集中在Mapper中
	 */
	
	@RequestMapping("/getListData")
	public Object getListData(Integer page, Integer rows, String sort, String order,
			//因为嵌套关系  又因为后台不是用form提交所以用三个实体类
			ProblemClues problemClues, 
			ReflectedPerson reflectedPerson,
			ReflectingPerson reflectingPerson,
			Report report,
			String startDate,
			String endDate,
			String pageName,
			String zddb,
			PageControl pageControl
			) {
		//一级查询条件
		//先切换数据库切换回来
		DataSourceContextHolder.setDbType("master_dataSource");
		//如果最后进度的detail是 2予以了结 3谈话提醒4暂存待查5移送有关党组织处理  案件结束
	    //如果最后进度的detail是6诫勉谈话转到审理室
		Map<String, Object> condition = pageService.MakeMap(problemClues,reflectedPerson,reflectingPerson,report,startDate,endDate,page,rows,sort,order,zddb);
		if(StringUtils.isEmpty(pageControl.getChongfu())) {
			String sql="and ((p.duplicateId is null or p.duplicateId ='' or p.duplicateId='cancelRepeat') and p.isDel is null)";
			condition.put("sql", sql+=condition.get("sql")); 
		}
		//当前用户
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		PageInfo pageInof=null;
		//在办件页面-----the day01
		if(pageName.equals("having"))   {
			  Integer currentOrganId = getCurrentOrganId();
			  String sql="";
			  //监督室的在办件
			  if(user.roleList.contains(Param.jds_roleId)) {
				      //来自案管室
				 sql= " and ((state like 'AGSTJ%' and isGET=1)"+
				       //来自党风室
				      " or (state like 'AGSTJ%'   and isGET=1)"+
				       //监督室自己提交的但是没有结案
				      " or (state like'JDSTJ%'             and isGET=1  and finalstate!=-1  )"+
				       //在监督室修改过留存的
				      " or (state like 'JDSLC%') "+
				      " or (state like 'JDSJBLC%') "+
				       //监督室自行发现基本信息提交的
				      " or (state ='CLUESTJ"+currentOrganId+"')"+
				       //监督室自行发现的基本信息留存的
				      " ) and organId='"+currentOrganId+"'";
				 //派驻纪检组在办件
				  if(StringUtils.isNotEmpty(pageControl.getControl())&&pageControl.getControl().equals("PZ")) {
					  sql=" and state like 'JDSPZLC%' and organId='"+currentOrganId+"'";
				  }
				 
			  }
			  //审查室的在办件或者干部监督室和党风室（全程办理的怪物）
              if(user.roleList.contains(Param.scs_roleId)||user.roleList.contains(Param.qcbl_roleId)) {
            	  //来自案管室
            	  sql=" and ((state='AGSTJ"+Param.agsId+"' and organId='"+currentOrganId+"' and isGET=1)"+
            			" or (state like'SCTJ%' and organId='"+currentOrganId+"' and isGET=1 and state not like '%JA')"+
            			" or (state='SCLC"+currentOrganId+"'   )"+
            			" or (state ='CLUESTJ"+currentOrganId+"')"+
            			" or (state='LALC"+currentOrganId+"'   ))";
            	  //审查室立案新案件
            	  if(StringUtils.isNotEmpty(pageControl.getControl())&&pageControl.getControl().equals("LAX")) {
    				  sql=" and finalstate='LA' and state like 'SCTJ%' and isGET=1 and  organId='"+currentOrganId+"'";
    			  }
            	  //审查室初核案件显示
            	  if(pageControl.getControl().equals("CHX")) {
            		  //这是刚进入审查室的案件
            		  sql=" AND finalstate='9' and isGET=1 and  organId='"+currentOrganId+"'";
            	  }
            	  //审查室初核留存件
            	  if(pageControl.getControl().equals("CHLC")) {
            		  
            		  sql=" AND  state like 'SCLC%' and isGET=1 and  organId='"+currentOrganId+"'";
            	  }
            	  //审查室立案留存件
            	  if(pageControl.getControl().equals("LALC")) {
            		  
            		  sql=" AND state like 'LALC%' and isGET=1 and  organId='"+currentOrganId+"'";
            	  }
			  }
			  //审理室的在办件
              if(user.roleList.contains(Param.sls_roleId)) {
				  sql= "and ((state like'LATJ%' and organId='"+currentOrganId+"' and isGET=1)"+
					   " or (state='SLSLC"+currentOrganId+"'   )  or (state ='CLUESTJ"+currentOrganId+"'))";
			  }
              if(user.roleList.contains(Param.qcbl_roleId)) {
            	  if(pageControl.getControl().equals("QC_ZC")) {
            		  sql= " and (finalState='3.6' or (finalState='-1' and state='JDSLC26')) "
            		  		+ " and organId="+currentOrganId+" ";
            	  }
              }
			  condition.put("sql", sql+=condition.get("sql")); 
			  pageInof= pageService.havingPageService(condition);
		}
		//待接收案件界面-----the day02
		if(pageName.equals("waiting"))   {
			//二级查询条件
			Integer currentOrganId = getCurrentOrganId();
			//案管室  提交到  监督室的待接收     案管室提交到审查室的待接收   监督室自行发现的待接收 
			
			        String sql="";
			        //案管室待接收
			        if(Param.agsId.indexOf(currentOrganId+"")>=0) {
			        	//监督室转来
				        if(pageControl!=null&&StringUtils.isNotEmpty(pageControl.getControl())&&pageControl.getControl().equals("JDSZL")) {
				        	sql= " and state like '%JDSZL'";
				        }
				        //市案管室交办
				        if(pageControl!=null&&StringUtils.isNotEmpty(pageControl.getControl())&&pageControl.getControl().equals("AGSJB")) {
				        	sql=" and  isXf is not null and isXf!='' and fromId='"+Param.agsId+"' ";
				        }
				        //市监督室交办
				        if(pageControl!=null&&StringUtils.isNotEmpty(pageControl.getControl())&&pageControl.getControl().equals("JDSJB")) {
				            sql=" and  isXf is not null and isXf!='' and LOCATE(fromId,'"+Param.jdsIds+"')!=0 ";
				        }
			        }
			        //监督室待接收
			        if(Param.jdsIds.indexOf(","+currentOrganId+",")>=0) {
			        	
			        	    sql=" and (state='AGSTJ"+Param.dfsId+"' or state='AGSTJ"+Param.agsId+"')";
			        	
			        }
			        //审查室待接收
			        if(Param.scsIds.indexOf(","+currentOrganId+",")>=0) {
			        	
			        	    sql=" and  state = 'AGSTJ"+Param.agsId+"'";
			        	
			        }
			        //审理室待接收
			        if(Param.slsId.indexOf(currentOrganId+"")>=0) {
			        	
			        	    sql=" and ( state like 'SCTJ%'  or fromId=-1 or state like 'LATJ%')";
			        }
					sql+= " and organId='"+currentOrganId+"' and p.isGet='0'";
			        condition.put("sql", sql+=condition.get("sql"));            
			        pageInof=pageService.agsWorkingService(condition);
		}
		//办结案件
         if(pageName.equals("overing")) {
        	 if(pageControl!=null&&StringUtils.isNotEmpty(pageControl.getControl())) {
        	 //派驻纪检组了结件
        	 if(pageControl.getControl().equals("PZ")) {
        		 String sql=" and p.state like 'JDSPZTJ%'";
        		 condition.put("sql", sql+=condition.get("sql"));
        	 }
        	 //审查室了结件
        	 if(pageControl.getControl().equals("SC")) {
        		 String sql=" and p.state not like 'JDS%'";
        		 condition.put("sql", sql+=condition.get("sql"));
        	 }
        	 }
        	 pageInof= pageService.overingService(condition);
		 }
		
		//到期案件页面
        if(pageName.equals("czjy")) {
        	pageInof=pageService.overTimePage_czjyService(condition);
        }
        if(pageName.equals("lasc")) {
        	
        	pageInof=pageService.overTimePage_lascService(condition);
        }
        if(pageName.equals("ajsl")) {
        	
        	pageInof=pageService.overTimePage_ajslService(condition);
        }
        //(新添)
        if(pageName.equals("ldck")) {
        	
        	pageInof=pageService.overTimePage_ldckService(condition);
        }
        if(pageName.equals("zcdc")) {
        	
        	pageInof=pageService.overTimePage_zcdcService(condition);
        }
        if(pageName.equals("thhx")) {
        	
        	pageInof=pageService.overTimePage_thhxService(condition);
        }
        //处分决定执行期限到期
        if(pageName.equals("takeEffect")) {
        	
        	pageInof=pageService.overTimePage_takeEffectService(condition);
        }
		//监督会议决定初核
        if(pageName.equals("initial")) {
        	//案管室的
        	//初步核实1.案管室收到的初核件  2.案管室收到初核件后  留存的
        	if(user.roleList.contains(Param.ags_roleId)) {
        		String sql=" AND finalState IN ('4')";
            	condition.put("sql", sql+=condition.get("sql")); 
        	}
        	
        	pageInof=pageService.initialService(condition);
        }
        //已办件
		if(pageName.equals("working")) {
			ProblemClues pro=new ProblemClues();
			Integer currentOrganId = getCurrentOrganId();
		    pro.setOrganId(currentOrganId+""); 
		    condition.put("problemClues", pro);
		     //监督室初核件
			if(StringUtils.isNotEmpty(pageControl.getControl())&&pageControl.getControl().equals("CH")) {
				String sql=" AND finalState IN ('4', '9') ";
				condition.put("sql", sql+=condition.get("sql")); 
			}
			//监督室转案管室案件
			if(StringUtils.isNotEmpty(pageControl.getControl())&&pageControl.getControl().equals("ZAG")) {
				pro.setOrganId(Param.agsId); 
				String sql=" AND p.state like '%JDSZL' ";
				condition.put("sql", sql+=condition.get("sql"));
				
			}
			//下发区县案件（全部）
			if(StringUtils.isNotEmpty(pageControl.getControl())&&pageControl.getControl().equals("XF")) {
				//下发区县的案件分为两种1.本科室直接下发2.本科室转交其他科室下发
				//本科室直接下发的 就是  fromId等于本科室的部门id
				//间接下发就是    maked表中有但是fromId不等于
				String sql=" AND p.isXf IS NOT NULL AND p.isXF !='' AND p.isXF !='XFCH' ";
				condition.put("sql", sql+=condition.get("sql"));
			}
			//下发区县案件（直接下发）
			if(StringUtils.isNotEmpty(pageControl.getControl())&&pageControl.getControl().equals("ZJXF")) {
				//下发区县的案件分为两种1.本科室直接下发2.本科室转交其他科室下发
				//本科室直接下发的 就是  fromId等于本科室的部门id
				//间接下发就是    maked表中有但是fromId不等于
				String sql=" AND p.isXf IS NOT NULL AND p.isXF !='' AND p.isXF !='XFCH' AND p.fromId='"+currentOrganId+"'";
				condition.put("sql", sql+=condition.get("sql"));
			}
			//下发区县案件（间接接下发）
			if(StringUtils.isNotEmpty(pageControl.getControl())&&pageControl.getControl().equals("JJXF")) {
				//下发区县的案件分为两种1.本科室直接下发2.本科室转交其他科室下发
				//本科室直接下发的 就是  fromId等于本科室的部门id
				//间接下发就是    maked表中有但是fromId不等于
				String sql=" AND p.isXf IS NOT NULL AND p.isXF !='' AND p.isXF !='XFCH' AND p.fromId!='"+currentOrganId+"'";
				condition.put("sql", sql+=condition.get("sql"));
			}
			condition.put("problemClues", pro);
			pageInof=pageService.workingService(condition);
		}
		
		
	    //待办件
		if(pageName.equals("newClues")) {
            //CLUESLC11 案件没有提交 也没有分办
            //FBLC11   案件留存分办信息
            //CLUESTJ11 案件提交但未分办
			//撤回案件只能存在于案管室
		   Integer currentOrganId = getCurrentOrganId();
		   String a="";
		   if(user.roleList.contains(Param.xfs_roleId)) {
         	  a="state='CLUESTJ"+Param.xfsId+"'   or ";
           }
		   if(user.roleList.contains(Param.qcbl_roleId)) {
			  a="(state='AGSTJ"+Param.agsId+"'  and organId='"+currentOrganId+"') or "
			 + " (state='AGSTJ"+Param.dfsId+"'  and organId='"+currentOrganId+"') or "
			 + " (state='JDSJBLC"+currentOrganId+"') or ";
		   }
		   if(user.roleList.contains(Param.ags_roleId)) {
			   a="  finalState='31' or ";
		   }
           String sql= "  and (state='CLUESLC"+currentOrganId+"' or  state='AGSLC"  +currentOrganId+"'   or   "+
        		                 a+
           		                 //监督室返回案管室
           " (LOCATE(fromId,'"+Param.jdsIds+"')!=0  and organId='"+currentOrganId+"' and state like 'JDSTJ%') or state='CLUESTJ"+currentOrganId+"') and isGet='1' ";
           condition.put("sql", sql+=condition.get("sql")); 
           pageInof=pageService.neweCluesService(condition);		
		}
		
		//检索库
		if(pageName.equals("library")) {			
			pageInof=pageService.libraryService(condition);		
		}
		//县区线索
		if(pageName.equals("xq")) {
			pageInof=pageService.xqService(condition);	
		}
		//暂存待查件
		if(pageName.equals("temporary")) {
			Integer currentOrganId = getCurrentOrganId();
       	    String sql= " and organId='"+currentOrganId+"'";
       	    condition.put("sql", sql);
			pageInof=pageService.temporaryService(condition);	
		}
		
		//线索库
		if(pageName.equals("AGSZAIBAN")||pageName.equals("ADDTONGA")) {
			pageInof=pageService.agsWorkingService(condition);
		}
		//监督市报备案件
		if(pageName.equals("add_from_jds")) {
			String sql=" and LOCATE(r.submitOrganId,'"+Param.jdsIds+"')!=0 ";
			condition.put("sql", sql+=condition.get("sql")); 
			pageInof=pageService.report(condition);
		}
		//党风室报备案件
		if(pageName.equals("add_from_dfs")) {
			String sql=" and LOCATE(r.submitOrganId,"+Param.dfsId+")!=0 ";
			condition.put("sql", sql+=condition.get("sql")); 
			pageInof=pageService.report(condition);
		}
		//干部监督室报备
		if(pageName.equals("add_from_gbjds")) {
			String sql=" and LOCATE(r.submitOrganId,"+Param.gbjdsId+")!=0 ";
			condition.put("sql", sql+=condition.get("sql")); 
			pageInof=pageService.report(condition);
		}
		//退回件
        if(pageName.equals("returnList")) {
        	pageInof=pageService.returnService(condition);
        }
        //退还件
        if(pageName.equals("rtingList")) {
        	pageInof=pageService.rtingList(condition);
        }
        //统计的详细线索
        if(pageName.equals("tj_detail")) {
        	pageInof=pageService.tj_detail(condition);
        }
        //同案人员
        if(pageName.equals("tonganList")) {
        	pageInof=pageService.tonganList(condition);
        }
        //信访室案件管理
        if(pageName.equals("xfClues")) {
        	Integer currentOrganId = getCurrentOrganId();
        	String sql=" and state='CLUESLC"+currentOrganId+"'";
        	condition.put("sql", sql+=condition.get("sql")); 
        	pageInof=pageService.xfClueServices(condition);
        }
        //本案件已有重复件
        if(pageName.equals("repeatList")) {
        	condition.put("sql", " and duplicateId='"+problemClues.getId()+"'"); 
        	pageInof=pageService.repeatService(condition);	
        }
        //本案件未添加的重复件
        if(pageName.equals("repeatListAll")){
        	condition.put("sql", " and rd.reflectedName like'%"+reflectedPerson.getReflectedName()+"%' and p.id!='"+problemClues.getId()+"'"); 
        	pageInof=pageService.repeatAllService(condition);	
        }
        //删除件
        if(pageName.equals("del_clues")){
        	pageInof=pageService.del_clues(condition);
        }
        //全网查询:意思是查询的数据库不仅包括 市里的也包括县里的  这就要求 在查询中变换19个数据库
        if(pageName.equals("holeWeb")) {
        	//当前页数如果为第一页 将sysParam表的所有value字段都设置为1
        	if(page==1) {
        		sysParamMapper.updateAllNoReason();
        	}
        	pageInof = holeWeb.holeSearch(condition);
        }
        //超期案件
        if(pageName.equals("outOfTime")) {
        	//当前页数如果为第一页 将sysParam表的所有value字段都设置为1
        	String sql=" and p.expireTime is not null and p.expireTime!='' and DATEDIFF(RIGHT(p.expireTime, 10),now())<0 ";
        	condition.put("sql", sql+=condition.get("sql")); 
        	pageInof=pageService.neweCluesService(condition);		
        }
        
		pageInof.setSqlStore((String)condition.get("sql"));
		return pageInof;
	}
	
}
