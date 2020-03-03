package com.alphasta.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.PageMapper;
import com.alphasta.mapper.ResourceMapper;
import com.alphasta.mapper.RoleMapper;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Resource;
import com.alphasta.service.BaseService;
import com.alphasta.service.IndexMenuService;
@Service
public class IndexMenuServiceImpl extends BaseService implements IndexMenuService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PageMapper pageMapper;
	@Override
	public String conuntNum() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		List<Long> roleList = user.roleList;
		StringBuffer suf=new StringBuffer();
		//part1:遍历这个用户所拥有的角色
		for(Long r:roleList) {
			List<Resource> resourceList = roleMapper.findResourceIdListByRoleIdAndType(r);
			//part2:遍历权限（菜单的）
			for(Resource resource:resourceList){
			//part3:判断使用哪种查询
			String except="_业务管理_文书下载_立案_初核_呈批签上传_删除件_党风室_案管室_自行发现_监督室转来_市案管室交办_市监督室交办_区县线索库_";
			if(except.indexOf("_"+resource.getName()+"_")>=0) {
				continue;
			}
		    Integer num = createCondition(resource.getName());
		    suf.append(resource.getName()+"_"+num+",");
			}
		}
		return suf.toString();
	}
    public Integer createCondition(String resourceName){
    	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	String currentOrganId = getCurrentOrganId();
    	Map<String, Object> condition =new HashMap<String,Object>();
    	String sqlBIG="and ((p.duplicateId is null or p.duplicateId ='' or p.duplicateId='cancelRepeat') and p.isDel is null)";
    	condition.put("count", "count");
    	PageInfo count = new PageInfo();
    	ProblemClues pro=new ProblemClues();
    	if(resourceName.equals(Param.zbj))   {
    		 
			  String sql="";
			  //监督室的在办件
			  if(Param.jdsIds.indexOf(","+currentOrganId+",")>=0) {
				      //来自案管室
				 sql= " and ((state like 'AGSTJ%' and isGET=1)"+
				       //来自党风室
				      " or (state like 'AGSTJ%'   and isGET=1)"+
				       //监督室自己提交的但是没有结案
				      " or (state like'JDSTJ%'             and isGET=1  and finalstate!=-1  )"+
				       //在监督室修改过留存的
				      " or (state like 'JDSLC%')"+
				       //监督室自行发现基本信息提交的
				      " or (state ='CLUESTJ"+currentOrganId+"')"+
				       //监督室自行发现的基本信息留存的
				      " ) and organId='"+currentOrganId+"'";
			  }
			  //审查室的在办件
              if(Param.scsIds.indexOf(","+currentOrganId+",")>=0) {
            	  //来自案管室
            	  sql=" and ((state='AGSTJ"+Param.agsId+"' and organId='"+currentOrganId+"' and isGET=1)"+
            			" or (state like'SCTJ%' and organId='"+currentOrganId+"' and isGET=1 and state not like '%JA')"+
            			" or (state='SCLC"+currentOrganId+"'   )"+
            			" or (state='LALC"+currentOrganId+"'   ))";
			  }
			  //审理室的在办件
              if(Param.slsId.indexOf(currentOrganId+"")>=0) {
				  sql= "and ((state like'LATJ%' and organId='"+currentOrganId+"' and isGET=1)"+
					   " or (state='SLSLC"+currentOrganId+"'   ))";
			  }
			  condition.put("sql", sqlBIG+sql); 
			  count.setCondition(condition);
			  List<ProblemClues> heavingPageData = pageMapper.heavingPageData(count);
			  if(heavingPageData!=null&&!heavingPageData.isEmpty()) {
				  return Integer.valueOf(heavingPageData.get(0).getWhereFrom());
			  }
		}
        if(resourceName.equals("党风室")) {
      	  String sql=" and state='AGSTJ"+Param.dfsId+"' and fromId='27'  and isGET=1 and organId='"+currentOrganId+"'";
      	  condition.put("sql", sqlBIG+sql); 
		  count.setCondition(condition);
		  List<ProblemClues> heavingPageData = pageMapper.heavingPageData(count);
		  if(heavingPageData!=null&&!heavingPageData.isEmpty()) {
			  return Integer.valueOf(heavingPageData.get(0).getWhereFrom());
		  }
        }
        if(resourceName.equals("案管室")) {
        	  String sql=" and state='AGSTJ"+Param.agsId+"' and fromId='11'  and isGET=1 and organId='"+currentOrganId+"'";
          	  condition.put("sql", sqlBIG+sql); 
    		  count.setCondition(condition);
    		  List<ProblemClues> heavingPageData = pageMapper.heavingPageData(count);
    		  if(heavingPageData!=null&&!heavingPageData.isEmpty()) {
    			  return Integer.valueOf(heavingPageData.get(0).getWhereFrom());
    		  }
        }
        if(resourceName.equals("自行发现")) {
      	  String sql="  and  state like 'AGSTJ%' and fromId='-1'  and isGET=1 and organId='"+currentOrganId+"'";
        	  condition.put("sql", sqlBIG+sql); 
  		  count.setCondition(condition);
  		  List<ProblemClues> heavingPageData = pageMapper.heavingPageData(count);
  		  if(heavingPageData!=null&&!heavingPageData.isEmpty()) {
  			  return Integer.valueOf(heavingPageData.get(0).getWhereFrom());
  		  }
        }
        
        if(resourceName.equals("初核(留存件)")) {
        	
          String	sql=" AND  state like 'SCLC%' and isGET=1 and  organId='"+currentOrganId+"'";
          condition.put("sql", sqlBIG+sql); 
		  count.setCondition(condition);
		  List<ProblemClues> heavingPageData = pageMapper.heavingPageData(count);
		  if(heavingPageData!=null&&!heavingPageData.isEmpty()) {
			  return Integer.valueOf(heavingPageData.get(0).getWhereFrom());
		  }	
        	
        }
        if(resourceName.equals("立案(留存件)")) {
        	
            String	sql=" AND state like 'LALC%' and isGET=1 and  organId='"+currentOrganId+"'";
            condition.put("sql", sqlBIG+sql); 
  		  count.setCondition(condition);
  		  List<ProblemClues> heavingPageData = pageMapper.heavingPageData(count);
  		  if(heavingPageData!=null&&!heavingPageData.isEmpty()) {
  			  return Integer.valueOf(heavingPageData.get(0).getWhereFrom());
  		  }	
          	
          }
        if(resourceName.equals("初核(新案件)")) {
        	
            String	 sql=" AND finalstate='9' and isGET=1 and  organId='"+currentOrganId+"'";
            condition.put("sql", sqlBIG+sql); 
  		  count.setCondition(condition);
  		  List<ProblemClues> heavingPageData = pageMapper.heavingPageData(count);
  		  if(heavingPageData!=null&&!heavingPageData.isEmpty()) {
  			  return Integer.valueOf(heavingPageData.get(0).getWhereFrom());
  		  }	
          	
          }
        if(resourceName.equals("立案(新案件)")) {
        	String sql=" and finalstate='LA' and state like 'SCTJ%' and isGET=1 and  organId='"+currentOrganId+"'";
            condition.put("sql", sqlBIG+sql); 
  		  count.setCondition(condition);
  		  List<ProblemClues> heavingPageData = pageMapper.heavingPageData(count);
  		  if(heavingPageData!=null&&!heavingPageData.isEmpty()) {
  			  return Integer.valueOf(heavingPageData.get(0).getWhereFrom());
  		  }	
          	
          }
        //待接收和在办件下级 的菜单名区别在于  待接收 名称后有空格
        
    	if(resourceName.equals("派驻纪检组在办件")) {
    		  String sql=" and state like 'JDSPZLC%' and organId='"+currentOrganId+"'";
    		  condition.put("sql", sqlBIG+sql); 
			  count.setCondition(condition);
			  List<ProblemClues> heavingPageData = pageMapper.heavingPageData(count);
			  if(heavingPageData!=null&&!heavingPageData.isEmpty()) {
				  return Integer.valueOf(heavingPageData.get(0).getWhereFrom());
			  }
    	}
    	if(resourceName.equals("转发案管室件")) {
    		pro.setOrganId(Param.agsId); 
			String sql=" AND p.state like '%JDSZL' ";
			condition.put("sql", sqlBIG+sql);
			condition.put("problemClues", pro);
			count.setCondition(condition);
			    List<ProblemClues>  p=pageMapper.workingPageData(count);
			    if(p!=null&&!p.isEmpty()) {
					  return Integer.valueOf(p.get(0).getWhereFrom());
				 }
    	}
		//待接收案件界面
		if(resourceName.equals(Param.djs))   {
			//二级查询条件
			//案管室  提交到  监督室的待接收     案管室提交到审查室的待接收   监督室自行发现的待接收 
			String sql=" and (state='AGSTJ"+Param.dfsId
					+"' or state='AGSTJ"+Param.agsId
					+"' or state like 'SCTJ%'  or fromId=-1 or state like 'LATJ%' "
					+"or state like '%JDSZL')";
					sql+= " and organId='"+currentOrganId+"'";
					sql+= " and isGet='0'";
			        condition.put("sql", sqlBIG+sql);  
			        count.setCondition(condition);
			        List<ProblemClues>  p=pageMapper.agsWorkingPageData(count);
			        if(p!=null&&!p.isEmpty()) {
						  return Integer.valueOf(p.get(0).getWhereFrom());
					}
		}
		//监督室待接收下级菜单
		 if(resourceName.equals("案管室 ")) {
       	  String sql=" and state='AGSTJ"+Param.agsId+"' and p.isGet='0'  and organId='"+currentOrganId+"'";
         	  condition.put("sql", sqlBIG+sql); 
   		  count.setCondition(condition);
   		 List<ProblemClues>  p=pageMapper.agsWorkingPageData(count);
	        if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			}
         }
		 //监督室待接收下级菜单
		 if(resourceName.equals("党风室 ")) {
	       	  String sql=" and state='AGSTJ"+Param.dfsId+"' and p.isGet='0'  and organId='"+currentOrganId+"'";
	         	  condition.put("sql", sqlBIG+sql); 
	   		  count.setCondition(condition);
	   		 List<ProblemClues>  p=pageMapper.agsWorkingPageData(count);
		        if(p!=null&&!p.isEmpty()) {
					  return Integer.valueOf(p.get(0).getWhereFrom());
				}
	         }
		
		//案管室待接收下级菜单
		 if(resourceName.equals("监督室转来")) {
	       	 String sql= " and state like '%JDSZL'";
	         condition.put("sql", sqlBIG+sql); 
	   		 count.setCondition(condition);
	   		 List<ProblemClues>  p=pageMapper.agsWorkingPageData(count);
		        if(p!=null&&!p.isEmpty()) {
					  return Integer.valueOf(p.get(0).getWhereFrom());
				}
	         }
		 
		
		//办结案件--案管室
       if(resourceName.equals(Param.yja)) {
    	 //案管室结案  是所有结案
      	 condition.put("organId", ""); 
    	 if(currentOrganId.equals("27")){
    		 condition.put("organId", "27"); 
    	 }
    	 count.setCondition(condition);
	     List<ProblemClues>  p= pageMapper.overingPageData(count);
	     if(p!=null&&!p.isEmpty()) {
			  return Integer.valueOf(p.get(0).getWhereFrom());
		 }
		 }
       
       if(resourceName.equals("派驻纪检组结案件")) {
    	   
    	   String sql=" and p.state like 'JDSPZTJ%'";
    	   condition.put("sql", sqlBIG+sql);  
    	   count.setCondition(condition);
    	   List<ProblemClues>  p= pageMapper.overingPageData(count);
  	       if(p!=null&&!p.isEmpty()) {
  			  return Integer.valueOf(p.get(0).getWhereFrom());
  		   }
       }
       //各科室办结件---审查室  审理室  监督室 
       if(resourceName.equals("审查了结件")){
    	   String sql=" and p.state not like 'JDS%'";
  		   condition.put("sql", sql);
    	   condition.put("organId", currentOrganId); 
    	     count.setCondition(condition);
    	     List<ProblemClues>  p= pageMapper.overingPageData(count);
    	     if(p!=null&&!p.isEmpty()) {
    			  return Integer.valueOf(p.get(0).getWhereFrom());
    		 } 
       }
       if(resourceName.equals(Param.bjj)||resourceName.equals("直查了结件")) {
         condition.put("organId", currentOrganId); 
  	     count.setCondition(condition);
  	     List<ProblemClues>  p= pageMapper.overingPageData(count);
  	     if(p!=null&&!p.isEmpty()) {
  			  return Integer.valueOf(p.get(0).getWhereFrom());
  		 } 
    	   
       }
		//初步审核
      if(resourceName.equals(Param.jdhy)) {
      	//初步核实1.案管室收到的初核件  2.案管室收到初核件后  留存的
    	 String sql=" AND finalState IN ('4')";
      	 condition.put("sql", sqlBIG+sql); 
      	 count.setCondition(condition);
      	List<ProblemClues>  p=pageMapper.initialServiceM(count);
      	 if(p!=null&&!p.isEmpty()) {
			  return Integer.valueOf(p.get(0).getWhereFrom());
		 }
      }
      //已办件
		if(resourceName.equals(Param.ybj)) {
		    pro.setOrganId(currentOrganId); 
		    condition.put("problemClues", pro);
		    count.setCondition(condition);
		    List<ProblemClues>  p=pageMapper.workingPageData(count);
		    if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
		}
		//下发到县区件
		if(resourceName.equals("下发县区件")) {
			pro=new ProblemClues();
		    pro.setOrganId(currentOrganId+""); 
		    condition.put("problemClues", pro);
			String sql=" AND p.isXf IS NOT NULL AND p.isXF !='' AND p.isXF !='XFCH' ";
			condition.put("sql", sqlBIG+sql);
			condition.put("problemClues", pro);
			 count.setCondition(condition);
			    List<ProblemClues>  p=pageMapper.workingPageData(count);
			    if(p!=null&&!p.isEmpty()) {
					  return Integer.valueOf(p.get(0).getWhereFrom());
				 }
		}
		
		
		
	    //待办件
		if(resourceName.equals(Param.dbj)) {
          //CLUESLC11 案件没有提交 也没有分办
          //FBLC11   案件留存分办信息
          //CLUESTJ11 案件提交但未分办
		   String a="";
		   if(user.roleList.contains(Param.xfs_roleId)) {
       	   a="state='CLUESTJ"+Param.xfsId+"'   or ";
           }
		   if(user.roleList.contains(Param.qcbl_roleId)) {
		   a="(state='AGSTJ"+Param.agsId+"'  and organId='"+currentOrganId+"') or (state='AGSTJ"+Param.dfsId+"'  and organId='"+currentOrganId+"') or ";
		   }
		   if(user.roleList.contains(Param.ags_roleId)) {
		   a="finalState='31' or ";   
		   }
           String sql= "     and (state='CLUESLC"+currentOrganId+"' or  "
         		             + "state='AGSLC"  +currentOrganId+"'   or "+
      		                 a+" (LOCATE(fromId,'"+Param.jdsIds+"')!=0  and organId='"+currentOrganId+"' and state like 'JDSTJ%') or "+
                             "state='CLUESTJ"+currentOrganId+"') and isGet='1' ";
         condition.put("sql", sqlBIG+sql); 
         count.setCondition(condition);
		 List<ProblemClues>  x=pageMapper.newCluesPageData(count);	
		 if(x!=null&&!x.isEmpty()) {
			  return Integer.valueOf(x.get(0).getWhereFrom());
		 }
		}
		//检索库
		if(resourceName.equals(Param.xsk)) {
			count.setCondition(condition);
			List<ProblemClues>  p=pageMapper.libraryPageData(count);
			if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
		}
		//县区线索
		if(resourceName.equals(Param.qx)) {
			 return -1;
		}
		//暂存待查件
		if(resourceName.equals(Param.zcdc)) {
       	    String sql= " and organId='"+currentOrganId+"'";
       	    condition.put("sql", sqlBIG+sql);
			count.setCondition(condition);
			List<ProblemClues>  p=pageMapper.temporaryData(count);	
			if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
		}
		
		//线索库
		if(resourceName.equals(Param.xsk)) {
			count.setCondition(condition);
			List<ProblemClues>  p=pageMapper.agsWorkingPageData(count);
			if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
		}
		//监督市报备案件
		if(resourceName.equals(Param.jdstj)) {
			String sql=" and LOCATE(r.submitOrganId,'"+Param.jdsIds+"')!=0 ";
			condition.put("sql", sqlBIG+sql); 
			condition.put("organId", getCurrentOrganId());
			count.setCondition(condition);
			List<ProblemClues>  p=pageMapper.report(count);
			if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
		}
		//党风室报备案件
		if(resourceName.equals(Param.dfstj)) {
			String sql=" and LOCATE(r.submitOrganId,"+Param.dfsId+")!=0 ";
			condition.put("sql", sqlBIG+sql); 
			condition.put("organId", getCurrentOrganId());
			count.setCondition(condition);
			List<ProblemClues>  p=pageMapper.report(count);
			if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
		}
		//干部监督室报备
		if(resourceName.equals(Param.gbjdstj)) {
			String sql=" and LOCATE(r.submitOrganId,"+Param.gbjdsId+")!=0 ";
			condition.put("sql", sqlBIG+sql);
			condition.put("organId", getCurrentOrganId());
			count.setCondition(condition);
			List<ProblemClues>  p=pageMapper.report(count);
			if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
		}
		
      //退还件
      if(resourceName.equals("退回到本部门件")) {
    	  condition.put("organId", getCurrentOrganId());
    	  count.setCondition(condition);
    	  List<ProblemClues>  p=pageMapper.returnServiceM(count);
			if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
      }
      if(resourceName.equals("退回到上一部门件")) {
    	  condition.put("organId", getCurrentOrganId());
    	  count.setCondition(condition);
    	  List<ProblemClues>  p=pageMapper.rtingList(count);
			if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
      }
      //信访室案件管理
      if(resourceName.equals("xfClues")) {
      	String sql=" and state='CLUESLC"+currentOrganId+"'";
      	condition.put("sql", sqlBIG+sql); 
      	count.setCondition(condition);
  	    List<ProblemClues>  p=pageMapper.report(count);
			if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
      }
      //初核件
      if(resourceName.equals("初核件")) {
    	  String sql=" AND finalState IN ('4', '9') ";
      	  condition.put("sql", sqlBIG+sql); 
	      pro.setOrganId(currentOrganId); 
	      condition.put("problemClues", pro);
      	  count.setCondition(condition);
  	      List<ProblemClues>  p=pageMapper.workingPageData(count);
			if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
    	  
      }
      
      if(resourceName.equals("到期预警")) {
    	  condition.put("sql", " and  organId='"+currentOrganId+"' "); 
      	  count.setCondition(condition);
    	  //审查室立案审查到期案件
      	  if(Param.scsIds.indexOf(","+currentOrganId+",")>=0) {
      		 List<ProblemClues>  p=pageMapper.overTimePageData_lasc(count);
      		 if(p!=null&&!p.isEmpty()) {
				  return Integer.valueOf(p.get(0).getWhereFrom());
			 }
      	  }
    	  //监督室到期预警 包括处置建议到期+谈话函询到期+暂存待查到期
      	  if(Param.jdsIds.indexOf(","+currentOrganId+",")>=0) {
      		List<ProblemClues>  p1=pageMapper.overTimePageData_czjy(count);
      		List<ProblemClues>  p2=pageMapper.overTimePageData_thhx(count);
      		List<ProblemClues>  p3=pageMapper.overTimePageData_zcdc(count);
      		Integer num=0;
      		if(p1!=null){
      			num+=Integer.valueOf(p1.get(0).getWhereFrom());
      		}
      		if(p2!=null){
      			num+=Integer.valueOf(p2.get(0).getWhereFrom());
      		}
      		if(p3!=null){
      			num+=Integer.valueOf(p3.get(0).getWhereFrom());
      		}
      		return num;
      	  }
    	  //审理室到期预警 包括 案件审理到期+留党察看到期+处分决定执行期限到期
      	  if(Param.slsId.equals(currentOrganId)) {
      		List<ProblemClues>  p6=pageMapper.overTimePageData_ajsl(count);
      		List<ProblemClues>  p4=pageMapper.overTimePageData_ldck(count);
      		List<ProblemClues>  p5=pageMapper.overTimePageData_zcdc(count);
      		Integer num=0;
      		if(p4!=null){
      			num+=Integer.valueOf(p4.get(0).getWhereFrom());
      		}
      		if(p5!=null){
      			num+=Integer.valueOf(p5.get(0).getWhereFrom());
      		}
      		if(p6!=null){
      			num+=Integer.valueOf(p6.get(0).getWhereFrom());
      		}
      		return num;
      	  }
    	  
      }
      if(resourceName.equals("删除件")) {
    	  condition.put("organId", getCurrentOrganId());
      	  count.setCondition(condition);
    	  List<ProblemClues> p = pageMapper.del_clues(count);
    	  if(p!=null&&!p.isEmpty()) {
			  return Integer.valueOf(p.get(0).getWhereFrom());
		 }
      }
      //直查办理
      if(resourceName.equals("直查办理")) {
    	  String  sql= " and (finalState='3.6' or (finalState='-1' and state='JDSLC26'))  and organId="+currentOrganId+" ";
    	  condition.put("sql", sql); 
     	  count.setCondition(condition);
     	  List<ProblemClues> heavingPageData = pageMapper.heavingPageData(count);
		  if(heavingPageData!=null&&!heavingPageData.isEmpty()) {
			  return Integer.valueOf(heavingPageData.get(0).getWhereFrom());
		  }
      }
      //超期案件
      if(resourceName.equals("超期案件")) {
    	  String sql=" and p.expireTime is not null and p.expireTime!='' and DATEDIFF(RIGHT(p.expireTime, 10),now())<0 ";
    	  condition.put("sql", sql); 
     	  count.setCondition(condition);
     	  List<ProblemClues> heavingPageData = pageMapper.newCluesPageData(count);
		  if(heavingPageData!=null&&!heavingPageData.isEmpty()) {
			  return Integer.valueOf(heavingPageData.get(0).getWhereFrom());
		  }
      }
      return 0;
    }
    
	@Override
	public Integer createNum(String resourceName) {
		Integer createCondition = createCondition(resourceName);
		return createCondition;
	}
    
}
