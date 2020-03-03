package com.alphasta.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.commons.utils.DateUtil;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.dataSource.DataSourceContextHolder;
import com.alphasta.en.Stage;
import com.alphasta.en.State;
import com.alphasta.mapper.AccessoriesMapper;
import com.alphasta.mapper.MakedMapper;
import com.alphasta.mapper.OrganizationMapper;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.mapper.ReflectedPersonMapper;
import com.alphasta.mapper.ReflectingPersonMapper;
import com.alphasta.model.Accessories;
import com.alphasta.model.Maked;
import com.alphasta.model.Organization;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.User;
import com.alphasta.service.BaseService;
import com.alphasta.service.JdsService;
@Service
public class JdsServiceImpl extends BaseService implements JdsService{
    @Autowired
    private ProgressMapper progressMapper;
    @Autowired
    private ProblemCluesMapper problemCluesMapper;
    @Autowired
    private MakedMapper  makedMapper;
    @Autowired
    private AccessoriesMapper accessoriesMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private ReflectedPersonMapper reflectedPersonMapper;
	@Autowired
	private ReflectingPersonMapper reflectingPersonMapper;
	@Override
	public boolean Jds_clues(List<Progress> progress,ProblemClues problemClues,HttpServletRequest request) {
		boolean result=true;
		// TODO Auto-generated method stub
		//当前用户
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		
		boolean progressMainCanAdd=false;
		if(progress!=null&&progress.size()!=0) {
			progressMainCanAdd=true;
		}
		if(progressMainCanAdd) {
			
			
		int index=0;
		String last="";
		List<Progress>rem=new ArrayList<Progress>();
		Progress findLastProgress = progressMapper.findLastProgress(problemClues.getId());
		for(Progress p:progress) {
			if((StringUtils.isEmpty(p.getPointName())&&StringUtils.isEmpty(p.getPointValue()))) {
				 continue;
			 }else if(StringUtils.isNotEmpty(p.getPointName())&&p.getPointName().equals("恢复查办时间")) {
				 findLastProgress.setTimeForday(p.getTimeForday());
				 continue;
			 }else {
				 rem.add(p);
			 }
		}
		
		if(findLastProgress!=null&&!progress.isEmpty()) {
			if(StringUtils.isNotEmpty(rem.get(0).getDetail())) {
				if(rem.get(0).getDetail().equals("谈话函询时间")) {
					findLastProgress.setDetail("1");
				}
                if(rem.get(0).getDetail().equals("执纪监督专题会议")) {
                	findLastProgress.setDetail("2");
				}
                if(rem.get(0).getDetail().equals("暂存待查时间")) {
                	findLastProgress.setDetail("3");
				}
                if(rem.get(0).getDetail().equals("予以了结")) {
                	findLastProgress.setDetail("4");
				}
                if(rem.get(0).getDetail().equals("给予组织处理")) {
	                findLastProgress.setDetail("5");
				}
                if(rem.get(0).getDetail().equals("了结澄清")) {
	               findLastProgress.setDetail("6");
	            }
                if(rem.get(0).getDetail().equals("谈话提醒")) {
	               findLastProgress.setDetail("7");
	            }
                if(rem.get(0).getDetail().equals("诫勉谈话")) {
	               findLastProgress.setDetail("8");
	            }
                if(rem.get(0).getDetail().equals("责令检查批评教育")) {
	               findLastProgress.setDetail("9");
	            }
				progressMapper.updateProgress(findLastProgress);
			}
			
		}
		for(Progress p:rem) {
			if(p!=null) {
			String id=GetIdUtil.getId();
			//存储进度
			//创建id
			p.setId(id);
			if(index==0) {
				p.setLastPoint(findLastProgress.getId());
			}else {
				p.setLastPoint(last);
			}
			last=id;
			
			 p.setOrganId(getCurrentOrganId());
			 p.setUser(getCurrentUser());
			 p.setCauseId(problemClues.getId());
			 p.setState(problemClues.getState());
			 progressMapper.addProgress(p);
		}
			index++;
		}
		}else {
			//如果进度不存在也要修改状态
			problemCluesMapper.update(problemClues);
		}
		
		
		
		
		
		if(!"".equals(problemClues.getId())) {
			    //检测最大进度是否是初步核实如果是那么再加一条数据发送到案管室
			    Progress findLastProg = progressMapper.findLastProgress(problemClues.getId());
			    //本例用来介绍一般步骤书写方式
			    if(findLastProg!=null&&findLastProg.getType().equals("初步核实")) {
			    ProblemClues pro=new ProblemClues();
			    //非全程办理情况 如果当前用户的roleId为全程办理的那么不用进行如下步骤
			    if(!user.roleList.contains(Param.qcbl_roleId)) {
			    //进度是否可以添加
			    boolean progressCanAdd=false;
			    //线索是否可以修改
			    boolean problemCluesCanUpdate=false;
			    
			    
			    if(problemClues.getState().indexOf("TJ")>=0) {
			    	progressCanAdd=true;
			    }
			    
			    if(progressCanAdd) {
			    	//将案件分配到案管室
					Progress p=new Progress();
					p.setId(GetIdUtil.getId());
					p.setCauseId(problemClues.getId());
					p.setDetail("初步核实确定");
					p.setLastPoint(findLastProg.getId());
					p.setPointName(Param.fhags_name);
					p.setPointValue(Param.fhags_value);
					p.setOrganId("13");
					p.setState(problemClues.getState());
					//将这条进度添加到总进度中
					p.setUser(getCurrentUser());
					progressMapper.addProgress(p);
			    }
			    if(problemClues.getState().indexOf("TJ")>=0) {
			    	problemCluesCanUpdate=true;
			    }
			    if(problemCluesCanUpdate) {
					if(pro.getResultTime()!=null) {
						pro.setExpireTime(Param.resultName+"_"+problemClues.getResultTime());
					}else {
						pro.setExpireTime("del");
					}
					
					pro.setId(problemClues.getId());
					pro.setFromId(getCurrentOrganId());
					pro.setOrganId(Param.agsId);
					pro.setState(problemClues.getState());
					if(pro!=null&&StringUtils.isEmpty(pro.getState())&&pro.getState().indexOf("TJ")!=-1) {
						//给安管室发送一条消息
						sendMessage("您收到一条案件_"+Param.agsId+"X", request);
					}
			    }
			    }
				//全程办理情况
			    if(user.roleList.contains(Param.qcbl_roleId)) {
			    	pro.setId(problemClues.getId());
			    	pro.setState(problemClues.getState());
			    }
			    problemCluesMapper.update(pro);
			}
			    
			//诫勉谈话
			if(findLastProg!=null&&findLastProg.getPointValue().equals("3.6")&&"5".equals(findLastProg.getDetail())) {
				//将案件发送到审理室
				Progress p=new Progress();
				p.setId(GetIdUtil.getId());
				p.setCauseId(problemClues.getId());
				p.setDetail("诫勉谈话");
				p.setLastPoint(findLastProg.getId());
				p.setPointName(Param.slssl_name);
				p.setPointValue(Param.slssl_value);
				p.setOrganId(getCurrentOrganId());
				p.setUser(getCurrentUser());
				p.setState(problemClues.getState());
				progressMapper.addProgress(p);
				ProblemClues pro=new ProblemClues();
				//清除过期时间
				if(pro.getResultTime()!=null) {
					pro.setExpireTime(Param.resultName+"_"+problemClues.getResultTime());
				}else {
					pro.setExpireTime("del");
				}
				pro.setId(problemClues.getId());
				pro.setFromId(getCurrentOrganId());
				pro.setOrganId(Param.slsId);
				pro.setState(problemClues.getState());
				problemCluesMapper.update(pro);
				if(pro.getState().indexOf("TJ")!=-1) {
				//给安管室发送一条消息
				sendMessage("您收到一条案件_"+Param.slsId+"X", request);
				}
			}
			//如果室暂存待查或谈话函询需要设置过期期限
			if(findLastProg!=null&&findLastProg.getType().equals("谈话函询")) {
				    ProblemClues pro=new ProblemClues();
				    pro.setId(problemClues.getId());
				    pro.setState(problemClues.getState());
					if(problemClues.getResultTime()!=null) {
						pro.setExpireTime(Param.thhxName+"_"+problemClues.getResultTime());
					}else {
						pro.setExpireTime(Param.thhxName+"_"+DateUtil.getDateFromNow(new Date(), Integer.valueOf(Param.thhxTime)));
					}
					problemCluesMapper.update(pro);
			}
			if(findLastProg!=null&&findLastProg.getType().equals("暂存待查")) {
				    ProblemClues pro=new ProblemClues();
			    	   pro.setId(problemClues.getId());
					   pro.setState(problemClues.getState());
					if(problemClues.getResultTime()!=null) {
						pro.setExpireTime(Param.zcdcName+"_"+problemClues.getResultTime());
					}else {
						pro.setExpireTime(Param.zcdcName+"_"+DateUtil.getDateFromNow(new Date(), Integer.valueOf(Param.zcdcTime)));
					}
				    problemCluesMapper.update(pro);
			}
			//监督室案件了结
			if(findLastProg!=null&&Param.jds_jd_ja.indexOf(findLastProg.getType())!=-1) {
				ProblemClues pro=new ProblemClues();
				pro.setExpireTime("del");
				pro.setId(problemClues.getId());
				pro.setState(problemClues.getState());
				pro.setFinalState("-1");
				problemCluesMapper.update(pro);
			}
			
		}
		
		        addMaked(problemClues.getId());
		
		        return result;
	    }
	
	
	
	
	     /**
	      * 下发案件到县区
	      */
		public boolean jds_xf(ProblemClues problemClues,User user) {
			
			//******************将案件发送到县区如果留存保存进度不修改状态
			//**********************如果提交  保存进度 修改状态
			// TODO Auto-generated method stub
		    //下放案件
			//修改案件的部门id
			//1是成功0是失败
			//增加进度 1.监督室下发 2.案管室受理
			Progress prg=new Progress();
			prg.setId(GetIdUtil.getId());
			prg.setCauseId(problemClues.getId());
			Progress findLastProgress = progressMapper.findLastProgress(problemClues.getId());
			prg.setLastPoint(findLastProgress.getId());
			prg.setOrganId(getCurrentOrganId());
			prg.setPointName(Param.jdsxf_name);
			prg.setPointValue(Param.jdsxf_value);
			prg.setDetail(problemClues.getOrganId());
			prg.setUser(user);
			prg.setState(problemClues.getState());
			progressMapper.addProgress(prg);
			
			if(problemClues.getState().indexOf(State.TJ.toString())!=-1) {
			addMaked(problemClues.getId());
			Organization org = organizationMapper.findOrganizationById(Long.parseLong(problemClues.getOrganId()));
			problemClues.setIsXf(org.getAddress()+"D");
			//下发案件的当前部门id
			problemClues.setFromId(getCurrentOrganId());
			problemCluesMapper.update(problemClues);
		         //向该ip县区数据库插入该条数据
				//切换数据库  
			    problemClues=problemCluesMapper.findProblemCluesById(problemClues.getId());
				String ipk=problemClues.getIsXf().replaceAll("D", "");
				DataSourceContextHolder.setDbType(ipk);
				problemClues.setOrganId(Param.agsId);
				problemClues.setIsXf(Param.ip);
				problemCluesMapper.insertProblemClues(problemClues);
				reflectedPersonMapper.addReflectedPerson(problemClues.getReflectedPerson());
				if(problemClues.getReflectingPerson()!=null) {
					reflectingPersonMapper.addReflectingPerson(problemClues.getReflectingPerson());
				}
				//将数据源在切换回来
				DataSourceContextHolder.setDbType("master_dataSource");
			}else {
				//案件留存organId 不能修改
				problemClues.setOrganId(null);
				problemCluesMapper.update(problemClues);
				
			}
			return true;
		    }
	    //分办到派驻纪检组处理	不涉及organid和fromid的变化
		
			@Override
		public boolean pz_xf(ProblemClues problemClues,List<Progress> progress) {
			// 保存进度
			String cluesId="";
			try {
			for(Progress p:progress) {
				//添加进度需要条件 ：该条进度是否达到记录的目的
				boolean canStore=false;
				//派驻纪检组名称
				if(p.getPointName().equals("派驻纪检组处置")&&StringUtils.isNotEmpty(p.getRemark())) {
					canStore=true;
				}
				if(p.getPointName().equals("派驻纪检组立案")&&StringUtils.isNotEmpty(p.getDetail())&&!p.getDetail().equals("0")) {
					canStore=true;
				}
				if(p.getPointName().equals("派驻纪检组处理")&&StringUtils.isNotEmpty(p.getDetail())) {
					canStore=true;
				}
				if(canStore) {
					p.setId(GetIdUtil.getId());
					//如果新增完线索后立即派驻  派驻界面的线索id还未赋值  所以  在此设置一下
					p.setCauseId(problemClues.getId());
					progressMapper.addProgress(p);
					if(StringUtils.isNotEmpty(p.getCauseId())){
						cluesId=p.getCauseId();
					}
				}
				
				
			}
			
			}catch(Exception e) {
				return false;
			}
			
			problemCluesMapper.update(problemClues);
			addMaked(cluesId);
			return true;
		}




			/**案件移送 暂时移动到案管室**/
			@Override
			public boolean jds_toAg(ProblemClues problemClues,HttpServletRequest request) {
				//part0:修改线索的state 修改fromId为-1
				problemClues=problemCluesMapper.findProblemCluesById(problemClues.getId());
				problemClues.setOrganId(Param.agsId);
				problemClues.setFromId("0");
				problemClues.setIsGet("0");
				problemClues.setState(problemClues.getState()+"JDSZL");
				int update = problemCluesMapper.update(problemClues);
				//part1:添加进度
				if(update==1) {
					Progress last = progressMapper.findLastProgress(problemClues.getId());
					String lastPoint=last.getId();
					Progress p=new Progress(GetIdUtil.getId(), problemClues.getId(), Param.agssl_name, Param.agssl_value, "手动录入", null, lastPoint, getCurrentOrganId(), null, null,null);
					p.setUser(getCurrentUser());
					p.setState(problemClues.getState());
					progressMapper.addProgress(p);
					
					//part2:添加已办件
					addMaked(problemClues.getId());
					
					if(problemClues.getState().indexOf(State.TJ.toString())!=-1) {
						//part3:给给安管室发送一条消息
						sendMessage("您收到一条案件_"+Param.agsId+"X", request);
					}
					return true;
				}
				return false;
			}
			@Override
			public Map<String, Object> jds_zc(String id,String ip) {
				
				if(StringUtils.isNotEmpty(ip)) {
					DataSourceContextHolder.setDbType(ip);
				}
				Map<String,Object>result=new HashMap<String,Object>();
				if(!StringUtils.isEmpty(id)) {
				// TODO Auto-generated method stub
				List<Accessories> thhx_bg =null;
				List<Accessories> thhx_fa =null;
				List<Accessories> cbhs_czjy =null;
				List<Accessories> cbhs_bg =null;
				List<Accessories> zcdc_bg =null;
				List<Accessories>zcdc_czjy=null;
				List<Accessories> yylj_bg =null;
				List<Accessories> zzcl_bg =null;
				List<Accessories> ljcq_bg =null;
				List<Accessories> thtx_bg =null;
				List<Accessories> zljc_bg =null;
				List<Accessories> jmth_bg =null;
				Map<String,Object>map=new HashMap<String,Object>();
				Map<String,Object>accMap=new HashMap<String,Object>();
				Accessories acc=new Accessories();
				acc.setCaseId(id);
				accMap.put("Accessories", acc);
				map.put("pointValues", "'3','3.5','3.6'");
				//谈话函询处置报告
				accMap.put("type", "谈话函询处置情况报告");
				thhx_bg = accessoriesMapper.getAccByMap(accMap);
				//谈话函询处置方案
				accMap.put("type", "谈话函询处置方案");
				thhx_fa = accessoriesMapper.getAccByMap(accMap);
				//初步核实处置情况报告
				accMap.put("type", "初步核实处置情况报告");
				cbhs_bg = accessoriesMapper.getAccByMap(accMap);
				//关于初步核实的处置意见
				accMap.put("type", "关于初步核实的处置意见");
				cbhs_czjy = accessoriesMapper.getAccByMap(accMap);
				//予以了结处置情况报告
				accMap.put("type", "予以了结处置情况报告");
				yylj_bg = accessoriesMapper.getAccByMap(accMap);
				//暂存待查处置情况报告
				accMap.put("type", "暂存待查处置情况报告");
				zcdc_bg = accessoriesMapper.getAccByMap(accMap);
				//
				accMap.put("type", "关于暂存待查的处置意见");
				zcdc_czjy = accessoriesMapper.getAccByMap(accMap);
				//给予组织处理处置情况报告
				accMap.put("type", "给予组织处理处置情况报告");
				zzcl_bg = accessoriesMapper.getAccByMap(accMap);
				//了结澄清处置情况报告
				accMap.put("type", "了结澄清处置情况报告");
				ljcq_bg = accessoriesMapper.getAccByMap(accMap);
				//谈话提醒处置情况报告
				accMap.put("type", "谈话提醒处置情况报告");
				thtx_bg = accessoriesMapper.getAccByMap(accMap);
				//责令检查批评教育处置情况报告
				accMap.put("type", "责令检查批评教育处置情况报告");
				zljc_bg = accessoriesMapper.getAccByMap(accMap);
				//诫勉谈话处置情况报告
				accMap.put("type", "诫勉谈话处置情况报告");
				jmth_bg = accessoriesMapper.getAccByMap(accMap);
				map.put("causeId", id);
				List<Progress> progressByMap = progressMapper.getProgressByMap(map);
					if(progressByMap!=null&&progressByMap.size()!=0) {
						result.put("progress", progressByMap);
					}
					if(thhx_bg!=null&&thhx_bg.size()!=0) {
						result.put("thhx_bg", thhx_bg);
					}
					if(thhx_fa!=null&&thhx_fa.size()!=0) {
						result.put("thhx_fa", thhx_fa);
					}
					
					if(cbhs_czjy!=null&&cbhs_czjy.size()!=0) {
						result.put("cbhs_czjy", cbhs_czjy);
					}
					if(cbhs_bg!=null&&cbhs_bg.size()!=0) {
						result.put("cbhs_bg", cbhs_bg);
					}
					if(yylj_bg!=null&&yylj_bg.size()!=0) {
						result.put("yylj_bg", yylj_bg);
					}
					if(zzcl_bg!=null&&zzcl_bg.size()!=0) {
						result.put("zzcl_bg", zzcl_bg);
					}
					if(ljcq_bg!=null&&ljcq_bg.size()!=0) {
						result.put("ljcq_bg", ljcq_bg);
					}
					if(thtx_bg!=null&&thtx_bg.size()!=0) {
						result.put("thtx_bg", thtx_bg);
					}
					if(zljc_bg!=null&&zljc_bg.size()!=0) {
						result.put("zljc_bg", zljc_bg);
					}
					if(jmth_bg!=null&&jmth_bg.size()!=0) {
						result.put("jmth_bg", jmth_bg);
					}
					if(zcdc_bg!=null&&zcdc_bg.size()!=0) {
						result.put("zcdc_bg", zcdc_bg);
					}
					if(zcdc_czjy!=null&&zcdc_czjy.size()!=0) {
						result.put("zcdc_czyj", zcdc_czjy);
					}
					
					
				}
				//切换回原来的数据源
				if(StringUtils.isNotEmpty(ip)) {
					DataSourceContextHolder.setDbType("master_dataSource");
				}
				return result;
			}
			@Override
			public Map<String, Object> jds_jb(String id) {
				// TODO Auto-generated method stub
				Map<String,Object>map=new HashMap<String,Object>();
				Map<String,Object>result=new HashMap<String,Object>();
				if(StringUtils.isEmpty(id)) {
					return result;
				}
				map.put("causeId", id);
				map.put("pointValues", "'-1','3.52','3.51'");
				List<Progress> progressByMap = progressMapper.getProgressByMap(map);
				map.clear();
				map.put("causeId", id);
				map.put("pointValues", "'3.7'");
				List<Progress> progressByMap2 = progressMapper.getProgressByMap(map);
				
				  
					if(progressByMap!=null&&progressByMap.size()!=0) {
						result.put("progress", progressByMap);
					}
					if(progressByMap2!=null&&progressByMap2.size()!=0) {
						result.put("progress2", progressByMap2);
					}
				return result;
			}




			@Override
			public boolean updateProgressCzjd(String cluesId, String pointValue) {
				// 查询进度 为 监督室处置决定的
				//最大进度必须是 监督室处置决定
				Progress last = progressMapper.findLastProgress(cluesId);
				if(last!=null&&StringUtils.isNotEmpty(last.getPointName())&&last.getPointName().equals("监督室处置决定")) {
					Map<String,Object>map=new HashMap<String,Object>();
					map.put("causeId",cluesId);
					map.put("pointName","'监督室处置决定'");
					List<Progress> czjd = progressMapper.getProgressByMap(map);
					for(Progress p:czjd) {
						if(p.getDetail().length()<2) {
							Progress pp=new Progress();
							pp.setId(p.getId());
							pp.setDetail(pointValue);
							progressMapper.updateProgress(pp);
							return true;
						}
					}
					
				}
				return false;
			}
		    
}
