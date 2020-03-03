package com.alphasta.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.result.Result;
import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.commons.utils.DateUtil;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.dataSource.DataSourceContextHolder;
import com.alphasta.en.Repeat_order;
import com.alphasta.en.State;
import com.alphasta.mapper.CluesNumMapper;
import com.alphasta.mapper.OrganizationMapper;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.mapper.ReflectedPersonMapper;
import com.alphasta.mapper.ReflectedUnitMapper;
import com.alphasta.mapper.ReflectingPersonMapper;
import com.alphasta.mapper.ReportMapper;
import com.alphasta.model.CluesNum;
import com.alphasta.model.Organization;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.Report;
import com.alphasta.service.AgsService;
import com.alphasta.service.BaseService;
@Service
public class AgsServiceImpl extends BaseService implements AgsService{
	@Autowired
	private ProblemCluesMapper      problemCluesMapper;
	@Autowired
	private ReflectedPersonMapper   reflectedPersonMapper;
	@Autowired
	private ReflectedUnitMapper     reflectedUnitMapper;
	@Autowired
	private ReflectingPersonMapper  reflectingPersonMapper;
	@Autowired
	private ProgressMapper          progressMapper;
	@Autowired
	private OrganizationMapper      organizationMapper;
	@Autowired
	private ReportMapper            reportMapper;
	@Autowired 
	private CluesNumMapper          cluesNumMapper;
	//当前用户的角色
	@Override
	public ProblemClues add_clues(ProblemClues problemClues,HttpServletRequest request) {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		// TODO Auto-generated method stub
		List<ReflectedPerson> repeat = new ArrayList<ReflectedPerson>();
		if(problemClues.getReflectedPerson()!=null) {
			  repeat = reflectedPersonMapper.has_repeat(problemClues.getReflectedPerson());
		}
		 Result result = new Result();
		 String uuid = "";
		  if(problemClues !=null){
			//生成32位UUID
			uuid = GetIdUtil.getId();
			//给案件线索添加ID
			problemClues.setId(uuid);
			//部门id
			String oId = getCurrentUser().getOrganizationId().toString();
			problemClues.setOrganId(oId);
			//生成案件编号
			String p ="";
			//如果是监督室
			if(user.roleList.contains(Param.jds_roleId)){
				problemClues.setFromId(Param.jdsFrom);
				problemClues.setState("AGSTJXX");
				p=findMaxNum(Param.jds_suffix);
			}
			//党风室
			if(user.roleList.contains(Param.df_roleId)) {
				problemClues.setFromId(Param.dfsFrom);
				p=findMaxNum(Param.dfs_suffix);
			}
			//安管室
			if(user.roleList.contains(Param.ags_roleId)) {
				problemClues.setFromId(Param.agsFrom);
				p=findMaxNum(Param.ags_suffix);
			}
			//信访室
			if(user.roleList.contains(Param.xfs_roleId)) {
				problemClues.setFromId(Param.xfsFrom);
				addMaked(problemClues.getId());
				p=findMaxNum(Param.xfs_suffix);
			}
			//干部监督室
			if(user.roleList.contains(Param.qcbl_roleId)) {
				problemClues.setFromId(Param.gbjdsFrom);
				addMaked(problemClues.getId());
				p=findMaxNum(Param.gbjds_suffix);
			}
			//审理室
			if(user.roleList.contains(Param.sls_roleId)) {
				problemClues.setFromId(Param.slsFrom);
				//因为线索添加页不再设置state需要在此设置
				problemClues.setState("LATJXX");
				addMaked(problemClues.getId());
				p=findMaxNum("SLS");
			}
			problemClues.setCluesNum(p);
			problemClues.setIsGet("1");
			if(repeat!=null&&!repeat.isEmpty()) {
				//如果存在重复问题  先将案件放到上下文中
				ServletContext servletContext = request.getServletContext();
				servletContext.setAttribute(getCurrentUserId()+"P", problemClues);
				result.setMsg("repeat");
				result.setObj(problemClues.getReflectedPerson().getReflectedName());
				result.setSuccess(true);
				problemClues.setWhereFrom("repeart");
			}else {
				int addCluesMainServices =addCluesMainServices(problemClues);
				result.setSuccess(true);
				result.setObj(problemClues.getId());
				result.setMsg("线索添加成功！");
			}
			
		}		
		return problemClues;
	}
	
	@Override
	public synchronized String  findMaxNum(String suffix) {
		String number="";
		CluesNum cluesNum=new CluesNum();
		cluesNum.setSuffix(suffix);
		cluesNum.setTime(sdf_yyyyMMdd.format(new Date()));
		CluesNum c = cluesNumMapper.findCluesNumBySuffixAndTime(cluesNum);
		//当天该科室的编号是否存在
		if(c!=null) {
			int num=c.getNum()+1;
			String nn="";
			if(num<10) {
				nn="00"+num;
			}
			if(num>=10&&num<=99) {
				nn="0"+num;
			}
			if(num>99) {
				nn=""+num;
			}
			number=Param.xzqh+c.getTime().substring(2)+nn;
			cluesNum.setNum(num);
			cluesNumMapper.updateCluesNum(cluesNum);
		}else {
			//该科室的编号是否存在
			CluesNum c2 = cluesNumMapper.findCluesNumBySuffix(cluesNum);
			if(c2!=null) {
				cluesNum.setNum(1);
				cluesNumMapper.updateCluesNum(cluesNum);
				int num=1;
				number=Param.xzqh+sdf_yyyyMMdd.format(new Date()).substring(2)+"00"+num;
			}else {
				cluesNum.setNum(1);
				cluesNumMapper.addCluesNum(cluesNum);
				int num=1;
				number=Param.xzqh+sdf_yyyyMMdd.format(new Date()).substring(2)+"00"+num;
			}
			
		}
		return number;
	}
	@Override
	public int add_repeat(ProblemClues problemClues_before, Repeat_order order,HttpServletRequest request) {
		 ProblemClues problemClues=(ProblemClues)request.getServletContext().getAttribute(getCurrentUserId()+"P");
   	     String proTime=(String)request.getServletContext().getAttribute(getCurrentUserId()+"proTime");
		 int addCluesMainServices =0;
		
		switch (order) {
		case DELETE:
			//不添加线索//清楚缓存
			request.getServletContext().removeAttribute(getCurrentUserId()+"P");
			request.getServletContext().removeAttribute(getCurrentUserId()+"proTime");
			break;
        case DULI:
			 //添加线索但是不存在重复关系
       	
       	 addCluesMainServices = addCluesMainServices(problemClues);
			break;

        case SAVE:
			//添加线索并且选择与谁保留重复关系
       	 problemClues.setDuplicate("1");
       	 problemClues.setDuplicateId(problemClues_before.getId());
       	 addCluesMainServices = addCluesMainServices(problemClues);
       	 //修改线索before
       	 problemClues_before.setDuplicateId(null);
       	 problemClues_before.setDuplicate("1");
       	 problemCluesMapper.update(problemClues_before);
			break;
		}
		return addCluesMainServices;
	}
	 /**
     * 添加线索和进度
     */
	@Override
	public int addCluesMainServices(ProblemClues problemClues) {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		// TODO Auto-generated method stub
		       /*这是被反映人的id*/
		   String id = GetIdUtil.getId();
		   if(problemClues.getReflectedPerson()!=null||problemClues.getReflectedUnit()!=null) {
			   problemClues.getReflectedPerson().setId(id);
			   problemClues.getReflectingPerson().setId(GetIdUtil.getId());
			   if(problemClues.getReflectedPerson()!=null) {
				   if(problemClues.getReflectedPerson().getReflectedName()!=null&&!problemClues.getReflectedPerson().getReflectedName().equals("")) {
						reflectedPersonMapper.addReflectedPerson(problemClues.getReflectedPerson());
					}
			   }			   
				if(problemClues.getReflectedUnit()!=null) {
					problemClues.getReflectedUnit().setId(id);
					    reflectedUnitMapper.addReflectedUnit(problemClues.getReflectedUnit());
				}
				
				
				//插入反映人
				if(problemClues.getReflectingPerson().getReflectingName()!=null&&!problemClues.getReflectingPerson().getReflectingName().equals("")) {
				String id2 = GetIdUtil.getId();
				problemClues.getReflectingPerson().setId(id2);
				reflectingPersonMapper.addReflectingPerson(problemClues.getReflectingPerson());
				}
		       //案管室添加线索
			   //插入线索
				problemClues.setCbr_now(getCurrentUser().getName());
				problemCluesMapper.insertProblemClues(problemClues);
							   
		        Progress progress = new Progress();
				String id3 = GetIdUtil.getId();
				progress.setId(id3);
				progress.setCauseId(problemClues.getId());
				progress.setState(problemClues.getState());
				if(problemClues.getWhereFrom()!=null&&problemClues.getWhereFrom().indexOf("tongan")!=-1) {
				//查询最新进度
				Progress findLastProgress = progressMapper.findLastProgress(problemClues.getWhereFrom().split("_")[1]);
				progress.setPointName(findLastProgress.getPointName());
				progress.setPointValue(findLastProgress.getPointValue());
				progress.setDetail("手动录入_同案人员");
			    //如果是下发案件反馈信息
			    if(StringUtils.isEmpty(problemClues.getIsXf())) {
				List<Progress>prolist=new ArrayList<Progress>();
				prolist.add(progress);
			  }
		      }else {
			        //记录进度
		    	    progress.setDetail("手动录入");
					progress.setUser(getCurrentUser());
			  if(user.roleList.contains(Param.jds_roleId)) {
				   //监督室自行发现
				   progress.setPointName(Param.jdssl_name);
				   progress.setPointValue(Param.jdssl_value);
				   progress.setState(problemClues.getState());
				   Report report=new Report(GetIdUtil.getId(),Param.agsId,problemClues.getId(),getCurrentOrganId());
				   reportMapper.addReport(report);
				   //监督室自行发现案件 添加处置建议到期
				   ProblemClues findProblemCluesById = problemCluesMapper.findProblemCluesById(problemClues.getId());
					if(!StringUtils.isEmpty(findProblemCluesById.getResultTime())) {
						problemClues.setExpireTime(Param.czjyName+"_"+findProblemCluesById.getResultTime());
					}else {
						problemClues.setExpireTime(Param.czjyName+"_"+DateUtil.getDateFromNow(new Date(), Integer.valueOf(Param.czjyTime)));
					}

			     }else {
				//党风室发现的案件
				if(user.roleList.contains(Param.df_roleId)) {
					progress.setPointName(Param.dfssl_name);
					progress.setPointValue(Param.dfssl_value);
					progress.setState(problemClues.getState());
					Report report=new Report(GetIdUtil.getId(),Param.agsId,problemClues.getId(),getCurrentOrganId());
					reportMapper.addReport(report);
				    //干部监督室发现案件 
					//要加上监督室受理 
					//因为他发现的案件自己办理 不用分办	
				   }else if(user.roleList.contains(Param.qcbl_roleId))
				
				   {
					Report report=new Report(GetIdUtil.getId(),Param.agsId,problemClues.getId(),getCurrentOrganId());
					reportMapper.addReport(report);
					progress.setPointName(Param.gbjdsl_name);
					progress.setPointValue(Param.gbjdsl_value);
					progress.setState(problemClues.getState());
					progressMapper.addProgress(progress);
					//--------在加一条监督室的进度-------------//
					Progress peo=new Progress();
					peo.setId(GetIdUtil.getId());
					peo.setCauseId(problemClues.getId());
					peo.setPointName(Param.jdssl_name);
					peo.setPointValue(Param.jdssl_value);
					peo.setState(problemClues.getState());
					progressMapper.addProgress(peo);
					return 0;
					
				}else {
					progress.setPointName(Param.agssl_name);
					progress.setPointValue(Param.agssl_value);
					progress.setState(problemClues.getState());
				}
				
			}			
					
		}
					progressMapper.addProgress(progress);
		
        }//end if
		return 0;
	}
	
	
	
	
	
	
    /**
     * 案管室分办案件 ******************************************************************
     */
	@Override
	public int fb_clues(List<Progress> progress,ProblemClues problemClues,HttpServletRequest request) {
		     
		    //进度说明0 执纪审查专题会议时间1案管室分办 以及分办时间2基层部门受理
		    //包含两部分1.进度的保存  2.线索状态的修改
		    if(progress!=null&&!progress.isEmpty()&&!"".equals(progress.get(1).getDetail())) {
		   /**
			 * 2.市案管室分办到审查室
			 */	
		    if(progress.get(1).getDetail()!=null
		    		&&!progress.get(1).getDetail().equals("")
		    		&&Param.scsIds.indexOf(","+progress.get(1).getDetail()+",")!=-1) {
		    	
			Progress p2 = new Progress();
			p2.setPointValue("4");
			p2.setCauseId(problemClues.getId());
			
			List<Progress> findProgress = progressMapper.findProgress(p2);
			if(findProgress!=null&&!findProgress.isEmpty()) {
				//案管室再分办
				progress.get(1).setPointName(Param.agszfb_name);
				progress.get(1).setPointValue(Param.agszfb_value);
				progress.get(1).setState(problemClues.getState());
			}else {			
				//案管室分办
				progress.get(1).setPointName(Param.agsfb_name);
				progress.get(1).setPointValue(Param.agsfb_value);
				progress.get(1).setState(problemClues.getState());
			}
			
			
			Progress p_scssl=new Progress();
			p_scssl.setPointName(Param.scssl_name);
			p_scssl.setPointValue(Param.scssl_value);
			p_scssl.setState(problemClues.getState());
			p_scssl.setDetail(progress.get(1).getDetail());
			progress.add(p_scssl);
			if(progress.get(0).getTimeForday()!=null&&!"".equals(progress.get(0).getTimeForday())) {
				if(findProgress!=null&&!findProgress.isEmpty()) {
					progress.get(0).setPointName(Param.agszfb_name);
					progress.get(0).setPointValue(Param.agszfb_value);	
					progress.get(0).setState(problemClues.getState());
				}else {
					progress.get(0).setPointName(Param.agsfb_name);
					progress.get(0).setPointValue(Param.agsfb_value);
					progress.get(0).setState(problemClues.getState());
				}
				progress.get(0).setDetail("执纪审查专题会议");
			}
			
		/**
		 * 1.市案管室分办到市监督室或干部监督室
		 */
		}else if(progress.get(1).getDetail()!=null
				&&!progress.get(1).getDetail().equals("")
				&&Param.jdsIds.indexOf(","+progress.get(1).getDetail()+",")!=-1
				||Integer.valueOf(progress.get(1).getDetail())==26
				||Integer.valueOf(progress.get(1).getDetail())==27){
			if(Param.dfsId.equals(getCurrentOrganId())) {
				progress.get(1).setPointName(Param.dfsfb_name);
				progress.get(1).setPointValue(Param.dfsfb_value);
				progress.get(1).setState(problemClues.getState());
			}else {
				progress.get(1).setPointName(Param.agsfb_name);
				progress.get(1).setPointValue(Param.agsfb_value);	
				progress.get(1).setState(problemClues.getState());
			}
			Progress p_jdssl=new Progress();
			p_jdssl.setPointName(Param.jdssl_name);
			p_jdssl.setPointValue(Param.jdssl_value);
			p_jdssl.setState(problemClues.getState());
			p_jdssl.setDetail(progress.get(1).getDetail());
			progress.add(p_jdssl);
			//如果progress[0]不为空  则 执纪审查专题会议不为空  分办到  审查
			if(progress.get(0).getTimeForday()!=null&&!"".equals(progress.get(0).getTimeForday())) {
				if(Param.organId==Param.dfsId) {
					progress.get(0).setPointName(Param.dfsfb_name);
					progress.get(0).setPointValue(Param.dfsfb_value);
					progress.get(0).setState(problemClues.getState());
				}else {
					progress.get(0).setPointName(Param.agsfb_name);
					progress.get(0).setPointValue(Param.agsfb_value);
					progress.get(0).setState(problemClues.getState());
				}
				progress.get(0).setDetail("执纪审查专题会议");
			}
			
	    /**
	     * 3.市案管室分办到县案管室
	     */
		}else if(progress.get(1).getDetail()!=null
				&&!"".equals(progress.get(1).getDetail())
				&&Integer.valueOf(progress.get(1).getDetail())>30) {
			
			progress.get(1).setPointName(Param.agsfb_name);
			progress.get(1).setPointValue(Param.agsfb_value);
			progress.get(1).setCauseId(problemClues.getId());
			progress.get(1).setState(problemClues.getState());
			Progress p_xqssl=new Progress();
			p_xqssl.setPointName(Param.xq_name);
			p_xqssl.setPointValue(Param.xq_value);
			p_xqssl.setState(problemClues.getState());
			p_xqssl.setCauseId(problemClues.getId());
			p_xqssl.setState(problemClues.getState());
			p_xqssl.setDetail(progress.get(1).getDetail());
			progress.add(p_xqssl);
			if(progress.get(0).getTimeForday()!=null&&!"".equals(progress.get(0).getTimeForday())) {
				progress.get(0).setPointName(Param.agsfb_name);
				progress.get(0).setPointValue(Param.agsfb_value);
				progress.get(0).setDetail("执纪审查专题会议");
				progress.get(0).setCauseId(problemClues.getId());
				progress.get(0).setState(problemClues.getState());
			}
			 /*
			  * 分办到其他
			  */
		    }else if(progress.get(1).getDetail()!=null
					&&!"".equals(progress.get(1).getDetail())
					&&Integer.valueOf(progress.get(1).getDetail())==29) {
		    	
		    	if(Param.dfsId.equals(getCurrentOrganId())) {
					progress.get(1).setPointName(Param.dfsfb_name);
					progress.get(1).setPointValue(Param.dfsfb_value);
					progress.get(1).setState(problemClues.getState());
				}else {
					progress.get(1).setPointName(Param.agsfb_name);
					progress.get(1).setPointValue(Param.agsfb_value);	
					progress.get(1).setState(problemClues.getState());
				}
				Progress p_jdssl=new Progress();
				p_jdssl.setPointName(Param.qt_name);
				p_jdssl.setPointValue(Param.qt_value);
				p_jdssl.setState(problemClues.getState());
				p_jdssl.setDetail(progress.get(1).getDetail());
				progress.add(p_jdssl);
				//如果progress[0]不为空  则 执纪审查专题会议不为空  分办到  审查
				if(progress.get(0).getTimeForday()!=null&&!"".equals(progress.get(0).getTimeForday())) {
					if(Param.organId==Param.dfsId) {
						progress.get(0).setPointName(Param.dfsfb_name);
						progress.get(0).setPointValue(Param.dfsfb_value);
						progress.get(0).setState(problemClues.getState());
					}else {
						progress.get(0).setPointName(Param.agsfb_name);
						progress.get(0).setPointValue(Param.agsfb_value);
						progress.get(0).setState(problemClues.getState());
					}
				}
		    }
		    //保存进度
		    String id2="";	
		    Progress findLastProgress =progressMapper.findLastProgress(problemClues.getId());
		    if(findLastProgress.getId()!=null&&!"".equals(findLastProgress.getId())) {
			    id2=findLastProgress.getId();
		    }
		    
			for(int i=0;i<progress.size();i++) {
				if(progress.get(i).getPointName()!=null&&!"".equals(progress.get(i).getPointName())) {
					String id=GetIdUtil.getId();
					//存储进度
					//创建id				
					progress.get(i).setId(id);
					if(!"".equals(id2)) {
						progress.get(i).setLastPoint(id2);		
					}	
					progress.get(i).setCauseId(problemClues.getId());
				    progress.get(i).setOrganId(getCurrentOrganId());
				    progress.get(i).setUser(getCurrentUser());
				    progress.get(i).setState(problemClues.getState());
					progressMapper.addProgress(progress.get(i));
					id2=id;
				}
				
				
			}    
	        //agsAddProgressService(progress,problemClues);
		
	    }
		    
		    
		//第二部分修改线索状态   提交状态下  修改 fromId  和    organId   留存状态下修改
		if(problemClues.getState().indexOf("TJ")>=0) {
			//如果是先留存后提交需要查询最大进度
			Progress max = progressMapper.findLastProgress(problemClues.getId());
			
			
			//县区id
			if(StringUtils.isNotEmpty(max.getDetail())&&Integer.valueOf(max.getDetail())>30) {
				//保存县区的id//发送案件的时候再存成本机的ip
				    Organization org = organizationMapper.findOrganizationById(Long.parseLong(max.getDetail()));
				    problemClues.setIsXf(org.getAddress()+"D");
				    problemClues.setOrganId(max.getDetail());
				    //设置fromId
				    problemClues.setFromId(getCurrentOrganId());
			         //向该ip县区数据库插入该条数据
					//切换数据库  
					String ipk=problemClues.getIsXf().replaceAll("D", "");
					//设置ip=========================================================================
					ProblemClues p2p=problemCluesMapper.findProblemCluesById(problemClues.getId());
					p2p.setOrganId(Param.agsId);
					p2p.setIsXf(Param.ip);
					DataSourceContextHolder.setDbType(ipk);
					problemCluesMapper.insertProblemClues(p2p);
					reflectedPersonMapper.addReflectedPerson(p2p.getReflectedPerson());
					if(p2p.getReflectingPerson()!=null) {
						reflectingPersonMapper.addReflectingPerson(p2p.getReflectingPerson());
					}
					//将数据源在切换回来
					DataSourceContextHolder.setDbType("master_dataSource");
					//==============================================================================
			}
			//other_id 就是本市或县的  监督室  审查室等的id因为不用从部门表查询所以将他们归为一类
			if(StringUtils.isNotEmpty(max.getDetail())&&Param.scsIds.indexOf(","+max.getDetail()+",")!=-1) {
					//修改线索的organId移动线索。。//
					problemClues.setOrganId(max.getDetail());
					problemClues.setFromId(getCurrentOrganId());
					problemClues.setIsGet("0");
					//如果提交线索向该科室发送一条消息//
					sendMessage("您收到一条案件_"+max.getDetail()+"X", request);
			}
			if(StringUtils.isNotEmpty(max.getDetail())&&Param.jdsIds.indexOf(","+max.getDetail()+",")!=-1
					||Integer.valueOf(max.getDetail())==26||Integer.valueOf(max.getDetail())==27) {
				
					//修改organId移动线索//
					problemClues.setOrganId(max.getDetail());
					problemClues.setFromId(getCurrentOrganId());
					problemClues.setIsGet("0");
					//如果分办到监督室 开始计算处置建议时间
					//是否要结果有时间则换成他的时间
					ProblemClues findProblemCluesById = problemCluesMapper.findProblemCluesById(problemClues.getId());
					if(!StringUtils.isEmpty(findProblemCluesById.getResultTime())) {
						problemClues.setExpireTime(Param.czjyName+"_"+findProblemCluesById.getResultTime());
					}else {
						problemClues.setExpireTime(Param.czjyName+"_"+DateUtil.getDateFromNow(new Date(), Integer.valueOf(Param.czjyTime)));
					}
					if(problemClues.getState().indexOf("TJ")!=-1) {
					//发送消息到监督室
					sendMessage("您收到一条案件_"+max.getDetail()+"X", request);
					}
				
				
			}
			//分办到其他的
			if(StringUtils.isNotEmpty(max.getDetail())
					&&Integer.valueOf(max.getDetail())==29) {
				
				//修改organId移动线索//
				problemClues.setOrganId(max.getDetail());
				problemClues.setFromId(getCurrentOrganId());
				problemClues.setIsGet("0");
				
				
			}
			  /*本地案件如果分办需要统计当提交线索时才保存已办*/
			  addMaked(problemClues.getId());
		 }
		 
		
		 //不论提不提交    有没有进度需要添加   都需要修改  线索的state字段
		 problemCluesMapper.update(problemClues);
		 return 0;
	}
	@Override
	public Map<String, Object> ags_fg(String id,String ip) {
		//第一步如果需要切换数据源 则切换数据源
		/*ProblemClues pro = problemCluesMapper.findProblemCluesById(id);
		if(pro!=null) {
			if(StringUtils.isNotEmpty(pro.getIsXf())&&pro.getIsXf().endsWith("D")) {
				String ipk=pro.getIsXf().replaceAll("D", "");
				DataSourceContextHolder.setDbType(ipk);
				pro = problemCluesMapper.findProblemCluesById(id);
			}
		}	*/	
		if(ip!=null) {
			DataSourceContextHolder.setDbType(ip);
		}
		Map<String,Object>result=new HashMap<String,Object>();
		// TODO Auto-generated method stub
		if(!StringUtils.isEmpty(id)) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("pointValues", "'0','1','2','4','29'");
		map.put("causeId", id);
		List<Progress> progressByMap = progressMapper.getProgressByMap(map);
		if(progressByMap!=null&&progressByMap.size()!=0) {
			result.put("progress", progressByMap);
		}
		}
		//数据源切换回来
		if(ip!=null) {
				DataSourceContextHolder.setDbType("master_dataSource");
		}		
		return result;
	}
}
