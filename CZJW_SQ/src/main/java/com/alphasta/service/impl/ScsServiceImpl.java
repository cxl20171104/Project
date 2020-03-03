package com.alphasta.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.alphasta.en.Stage;
import com.alphasta.en.State;
import com.alphasta.mapper.AccessoriesMapper;
import com.alphasta.mapper.GroupMapper;
import com.alphasta.mapper.LegalActMapper;
import com.alphasta.mapper.LienMapper;
import com.alphasta.mapper.MakedMapper;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.mapper.PunishmentMapper;
import com.alphasta.mapper.ReflectedPersonMapper;
import com.alphasta.mapper.ZyViolationMapper;
import com.alphasta.model.Accessories;
import com.alphasta.model.Group;
import com.alphasta.model.LegalAct;
import com.alphasta.model.Lien;
import com.alphasta.model.ListParam;
import com.alphasta.model.Maked;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.Punishment;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.User;
import com.alphasta.model.ZyViolation;
import com.alphasta.service.BaseService;
import com.alphasta.service.ScsService;
@Service
public class ScsServiceImpl extends BaseService implements ScsService{
	@Autowired
	private ProgressMapper progressMapper;
	@Autowired
	private GroupMapper    groupMapper;
	@Autowired
	private ProblemCluesMapper problemCluesMapper;
	@Autowired
	private ZyViolationMapper zyViolationMapper;
	@Autowired
	private PunishmentMapper   punishmentMapper;
	@Autowired
	private LegalActMapper     legalActMapper;
	@Autowired
	private ReflectedPersonMapper reflectedPersonMapper;
	@Autowired
	private LienMapper  lienMapper;
	@Autowired   
	AccessoriesMapper accessoriesMapper;
	 /**
     * 审查室案件处理情况
     */
	@Override
	public  boolean scsAddProgressService(List<Progress> progress,List<Group> group,ProblemClues problemClues,HttpServletRequest request) {
	    //part1:过滤掉为null的进度
		//当前用户
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		//审查室处置决定
		String scJd="";
		String id2="";	
	    Progress findLastProgress = progressMapper.findLastProgress(problemClues.getId());
	    if(findLastProgress.getId()!=null&&!"".equals(findLastProgress.getId())) {
		    id2=findLastProgress.getId();
	    }
	    //part2:添加小组
	    for(int i=0;i<group.size();i++) {
			group.get(i).setCauseId(problemClues.getId());
			group.get(i).setId(GetIdUtil.getId());
			groupMapper.addGroup(group.get(i));
		}
	    //part3:添加进度
		for(int i=0;i<progress.size();i++) {
			String timeForday = progress.get(i).getTimeForday();
			System.out.println(timeForday);
			if((progress.get(i).getDetail()!=null
					&&!"0".equals(progress.get(i).getDetail()))
					||(progress.get(i).getTimeForday()!=null
					&&!"".equals(progress.get(i).getTimeForday()))) {
				String id=GetIdUtil.getId();
				//存储进度
				//创建id				
				progress.get(i).setId(id);
				if(!"".equals(id2)) {
					progress.get(i).setLastPoint(id2);		
				}								
			    progress.get(i).setOrganId(getCurrentOrganId());
			    progress.get(i).setUser(getCurrentUser());
			    progress.get(i).setCauseId(problemClues.getId());
			    progress.get(i).setState(problemClues.getState());
			    //留置处置决定
			    if(progress.get(i).getPointName().equals("审查室会议决定")) {
			    	scJd=progress.get(i).getDetail();
			    }
			    //如果最后进度的detail是 2予以了结 3谈话提醒4暂存待查5移送有关党组织处理  案件结束
			    //如果最后进度的detail是6诫勉谈话转到审理室
				progressMapper.addProgress(progress.get(i));
				id2=id;
			}
		}
		
		       
				
		
		
		//part4:设置线索属性 前提条件是 必须 处置决定有值不为0
		if(!scJd.equals("0")) {
	    //如果最后进度的detail是 2予以了结 3谈话提醒4暂存待查5移送有关党组织处理  案件结束
	    if(!scJd.equals("1")) {
				problemClues.setFinalState("-1");
				problemClues.setState(problemClues.getState()+"JA");
				problemCluesMapper.update(problemClues);
	    }	
		//part5:添加立案审查到期
		if(scJd.equals("1")) {
			problemClues.setFinalState("LA");
			if(problemClues.getResultTime()!=null) {
				problemClues.setExpireTime(Param.lascName+"_"+problemClues.getResultTime());
			}else {
				problemClues.setExpireTime(Param.lascName+"_"+DateUtil.getDateFromNow(new Date(), Integer.valueOf(Param.lascTime)));
			}
		}
		}
		//part6：修改线索状态
		if(!problemClues.getFromId().equals(Param.gbjdsFrom)&&!problemClues.getOrganId().equals(Param.gbjdsId)) {
			problemCluesMapper.update(problemClues);
		}
		if(user.roleList.contains(Param.qcbl_roleId)){
			ProblemClues p=new ProblemClues();
			p.setId(problemClues.getId());
			p.setState(problemClues.getState());
			p.setFinalState(problemClues.getFinalState());
			problemCluesMapper.update(p);
		}
		if(problemClues.getReflectedPerson()!=null) {
			reflectedPersonMapper.updateReflectedPerson(problemClues.getReflectedPerson());
		}
		//part7:添加已办件
		addMaked(problemClues.getId());
		return true;
	}
	/**
	 * 添加留置
	 */
	@Override
	public String laAddLienService(List<Lien> lien,String reflectedPersonId) {
		// part1:添加留置信息
		String id=GetIdUtil.getId();
		if(lien!=null&&!lien.isEmpty()) {
			lien.get(0).setId(id);
			lien.get(0).setReflectedId(reflectedPersonId);
			lienMapper.addLien(lien.get(0));
		}
			
		return "0";
	}
	/**
	 * 添加处分
	 */
	@Override
	public String laAddPunishment(Punishment punishment,String reflectedPersonId) {
		// TODO Auto-generated method stub
		Result result=new Result();	
		Punishment punishment2 = new Punishment();				 
		System.out.println(punishment.getCfName()+":::"+punishment.getCfResult());
		if(punishment.getCfName()!=null&&!"".equals(punishment.getCfName())) {
			String cfName = punishment.getCfName();
			punishment2.setCfName(cfName);
		}
		if(punishment.getCfResult()!=null&&!"".equals(punishment.getCfResult())) {
			String cfResult = punishment.getCfResult();	
			 punishment2.setCfResult(cfResult);
		}
			 SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
			  String cfTime = tempDate.format(new java.util.Date()); 
			  punishment2.setCfTime(cfTime);
			  punishment2.setReflectedId(reflectedPersonId);
			  punishment2.setId(GetIdUtil.getId());
			  punishmentMapper.addPunishment(punishment2);
		//  }
		return "0";
	}
    /**
     * 添加小组成员
     */
	@Override
	public String scsCHAddGroup(List<Group> group, String problemCluesId) {
		for(int i=0;i<group.size();i++) {
			group.get(i).setCauseId(problemCluesId);
			group.get(i).setId(GetIdUtil.getId());
			groupMapper.addGroup(group.get(i));
		}
		return "0";
	}
	/**
	 * 添加主要违纪
	 * @param zyViolation
	 * @param reflectedPersonId
	 * @return
	 */
	public String laAddZyViolationService(ZyViolation zyViolation,String reflectedPersonId) {
		// TODO Auto-generated method stub
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
		String cfTime = tempDate.format(new java.util.Date()); 
		String id=GetIdUtil.getId();
		zyViolation.setId(id);
		zyViolation.setReflectedId(reflectedPersonId);
		zyViolation.setZyTime(cfTime);
		zyViolationMapper.addZyViolation(zyViolation);
		return "0";
	}
	/**
	 * 添加犯罪信息
	 * @param legalAct
	 * @param reflectedPersonId
	 * @return
	 */
	public String laAddLegalActService(List<LegalAct> legalAct,String reflectedPersonId) {
		// TODO Auto-generated method stub
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
		String cfTime = tempDate.format(new java.util.Date()); 
		String id=GetIdUtil.getId();
		legalAct.get(0).setId(id);
		legalAct.get(0).setReflectedId(reflectedPersonId);
		legalAct.get(0).setLawTime(cfTime);
		legalActMapper.addLegalAct(legalAct.get(0));	
		return "0";
	}


    /**
     * //立案结果的处理
     */
	@Override
	public boolean scs_la(ListParam listParam,HttpServletRequest request) {
		// part1:设置被反映人犯罪信息
		ProblemClues problemClues = listParam.getProblemClues();
		ReflectedPerson reflectedPerson = listParam.getReflectedPerson();
		//reflectedPerson.setCrimeCharge(reflectedPerson.getCrimeCharge().substring(1, reflectedPerson.getCrimeCharge().length()));
		if(reflectedPerson!=null) {
			reflectedPerson.setCrimeCharge(reflectedPerson.getCrimeCharge());
		}
		
		    //part2:添加审理室进度
		    List<Progress> progress = listParam.getProgress();
			Progress p2 = new Progress();
			p2.setCauseId(problemClues.getId());
			p2.setPointValue("13");
			p2.setPointName("审理室受理");
			p2.setDetail("30");	
			p2.setState(problemClues.getState());
			progress.add(p2);		
		
		
		
		listParam.setReflectedPerson(reflectedPerson);
		listParam.setProgress(progress);
		
		
		List<Group>group=listParam.getGroup();
		List<Lien>lien=listParam.getLien();
		List<LegalAct> legalAct = listParam.getLegalAct();
		ZyViolation zyViolation = listParam.getZyViolation();
		Punishment punishment=listParam.getPunishment();
		
		
		// part3:向数据库保存进度
	    String id2="";		   
	    Progress findLastProgress = progressMapper.findLastProgress(problemClues.getId());
	    if(findLastProgress.getId()!=null&&!"".equals(findLastProgress.getId())) {
		    id2=findLastProgress.getId();
	    }
		for(int i=0;i<progress.size();i++) {
			if(progress.get(i)!=null&&progress.get(i).getPointName()!=null&&!"".equals(progress.get(i).getPointName())) {
				String id=GetIdUtil.getId();
				progress.get(i).setId(id);
				if(!"".equals(id2)) {
					progress.get(i).setLastPoint(id2);		
				}	
				if("".equals(progress.get(i).getTimeForday())) {
					progress.get(i).setTimeForday(null);		
				}
			    progress.get(i).setOrganId(getCurrentOrganId());
			    progress.get(i).setUser(getCurrentUser());
			    progress.get(i).setCauseId(problemClues.getId());
			    progress.get(i).setState(problemClues.getState());
				progressMapper.addProgress(progress.get(i));
				id2=id;
				
			}
		}
		
		
		
		
		
		if(reflectedPerson!=null) {
		//part4：向数据库保存留置信息
		if("1".equals(reflectedPerson.getMeasures())) {
			String laAddLienService = laAddLienService(lien,reflectedPerson.getId());
		}
		//part5：向数据库保存处置信息
		if(punishment!=null) {
			String laAddPunishment = laAddPunishment(punishment,listParam.getReflectedPerson().getId());
		}
		//part6：向数据库保存工作小组信息
		if(!group.isEmpty()) {
	                scsCHAddGroup(group,problemClues.getId());
		}
		//part7：向数据库保存犯罪信息
		if(legalAct!=null) {
			String laAddLegalActService = laAddLegalActService(legalAct,reflectedPerson.getId());
		}
		//part8：向数据库保存主要违纪性质信息
		if(zyViolation!=null) {
			String laAddZyViolationService = laAddZyViolationService(zyViolation,reflectedPerson.getId());
		}
		}
		
		
		
		
		//注意：只有当线索提交后才设置 转出的属性
		if(problemClues.getState().indexOf(State.TJ.toString())!=-1) {
			//part9:设置线索转向信息
			problemClues.setFromId(getCurrentOrganId());
			problemClues.setOrganId(Param.slsId);
			problemClues.setIsGet("0");
			addMaked(problemClues.getId());
			//part9.1:立案结果处理后  删除  审查到期时间
			problemClues.setExpireTime("del");
			//给审理室发送一条消息
			sendMessage("您收到一条案件_"+Param.slsId+"X", request);	
		}
		
		//part10:修改被反映人信息
		if(reflectedPerson!=null) {
			reflectedPersonMapper.updateReflectedPerson(reflectedPerson);
		}
		//part11:修改线索状态
		problemCluesMapper.update(problemClues);
		return true;
	}



	@Override
	public Map<String, Object> scss_data(String id,String ip) {
		if(StringUtils.isNotEmpty(ip)) {
			DataSourceContextHolder.setDbType(ip);
		}
		ProblemClues ppp = problemCluesMapper.findProblemCluesById(id);
		Map<String,Object>result=new HashMap<String,Object>();
		if(!StringUtils.isEmpty(id)) {
		// TODO Auto-generated method stub
		Map<String,Object>map=new HashMap<String,Object>();
		Progress chprogress=null;
		map.put("pointValues", "'9','10','20','24','25','26'");
		//查询出初核时间
		Progress progress = new Progress();
		progress.setCauseId(id);
		progress.setDetail("30");
		progress.setPointValue("13");
		chprogress = progressMapper.XinfindProgress(progress);
		Map<String,Object>accMap=new HashMap<String,Object>();
		Accessories acc=new Accessories();
		acc.setCaseId(id);
		accMap.put("Accessories", acc);
		//查询出初核报告
		accMap.put("type", "上传初核请示");
		List<Accessories> chqs = accessoriesMapper.getAccByMap(accMap);
		if(!chqs.isEmpty()) {
			result.put("chqs", chqs.get(0));
		}
		accMap.put("type", "初核方案");
		List<Accessories> chfa = accessoriesMapper.getAccByMap(accMap);
		if(!chfa.isEmpty()) {
			result.put("chfa", chfa.get(0));
		}
		accMap.put("type", "审查报告");
		List<Accessories> scbg = accessoriesMapper.getAccByMap(accMap);
		if(!scbg.isEmpty()) {
			result.put("scbg", scbg.get(0));
		}
		accMap.put("type", "审查工作方案报告");
		List<Accessories> scgzfa = accessoriesMapper.getAccByMap(accMap);
		if(!scgzfa.isEmpty()) {
			result.put("scgzfa", scgzfa.get(0));
		}
		
		//办案小组
		Group group=new Group();
		group.setCauseId(id);
		group.setState("1");
		group.setGroupIdentity("1");
		Group zu_zhang = groupMapper.findGroup(group);
		result.put("zu_zhang", zu_zhang);
		group.setState("1");
		group.setGroupIdentity("2");
		Group zu_yuan = groupMapper.findGroup(group);
		result.put("zu_yuan", zu_yuan);
		
		map.put("causeId", id);
		List<Progress> progressByMap = progressMapper.getProgressByMap(map);
		if(chprogress!=null) {
     	   progressByMap.add(chprogress);
			}
			if(progressByMap!=null&&progressByMap.size()!=0) {
				result.put("progress", progressByMap);
			}
		}
		//党组织
		if(ppp!=null&&ppp.getReflectedPerson()!=null) {
			String ysPartyOrgan = ppp.getReflectedPerson().getYsPartyOrgan();
			if(ysPartyOrgan!=null&&!ysPartyOrgan.equals("")) {
				result.put("ysPartyOrgan", ysPartyOrgan);
			}
		}
		       //把数据源再切换回来
				if(StringUtils.isNotEmpty(ip)) {
					DataSourceContextHolder.setDbType("master_dataSource");
				}
		return result;
	}



	@Override
	public Map<String, Object> la_data(String id,String ip) {
		if(StringUtils.isNotEmpty(ip)) {
			DataSourceContextHolder.setDbType(ip);
		}
		ProblemClues ppp = problemCluesMapper.findProblemCluesById(id);
		Map<String,Object>result=new HashMap<String,Object>();
		if(!StringUtils.isEmpty(id)) {
			
		
		List<Accessories> lascbg =null;
		List<Accessories> dcbg =null;
		
		// TODO Auto-generated method stub
		Map<String,Object>map=new HashMap<String,Object>();
		Map<String,Object>accMap=new HashMap<String,Object>();
		Accessories acc=new Accessories();
		acc.setCaseId(id);
		accMap.put("Accessories", acc);
			map.put("pointValues", "'21','25'");
			//查询立案审查报告
			accMap.put("type", "立案审查(调查)报告");
			lascbg = accessoriesMapper.getAccByMap(accMap);
			if(lascbg!=null&&!lascbg.isEmpty()) {
				result.put("lascbg", lascbg);
			}
			accMap.put("type", "审查(调查)报告");
			dcbg = accessoriesMapper.getAccByMap(accMap);
			if(dcbg!=null&&!dcbg.isEmpty()) {
				result.put("dcbg", dcbg);
			}
			//处分  包括党纪处分等
			List<Punishment> punishment= punishmentMapper.findPunishmentByRid(ppp.getReflectedPerson().getId());
			if(!punishment.isEmpty()&&StringUtils.isNotEmpty(punishment.get(0).getCfResult())) {
				String[]ids=punishment.get(0).getCfResult().split(",");
				result.put("cfResult", ids);
			}
			//犯罪行为
			String crimeCharge = ppp.getReflectedPerson().getCrimeCharge();
			if(!StringUtils.isEmpty(crimeCharge)) {
				String[]crimes=crimeCharge.split(",");	
				result.put("crimes", crimes);
			}
			//办案小组
			Group group=new Group();
			group.setCauseId(id);
			group.setState("2");
			group.setGroupIdentity("1");
			Group zu_zhang2 = groupMapper.findGroup(group);
			result.put("zu_zhang2", zu_zhang2);
			group.setState("2");
			group.setGroupIdentity("2");
			Group zu_yuan2 = groupMapper.findGroup(group);
			result.put("zu_yuan2", zu_yuan2);
			//留置
			List<Lien> liens = lienMapper.findLienByRid(ppp.getReflectedPerson().getId());
			if(liens!=null&&!liens.isEmpty()) {
				result.put("lien1", liens.get(0));
			}
			//违纪行为
			List<ZyViolation> findZyViolationByRid = zyViolationMapper.findZyViolationByRid(ppp.getReflectedPerson().getId());
			if(findZyViolationByRid!=null&&!findZyViolationByRid.isEmpty()) {
				result.put("zyViolation1", findZyViolationByRid.get(0).getZyResult().split(","));
			}
			//涉法行为
			List<LegalAct> findLegalActByRid = legalActMapper.findLegalActByRid(ppp.getReflectedPerson().getId());				
			if(!findLegalActByRid.isEmpty()) {
				String[]ids=findLegalActByRid.get(0).getLawResult().split(",");
                result.put("legalAct", ids);
		        }
			    map.put("causeId", id);
			    List<Progress> progressByMap = progressMapper.getProgressByMap(map);
				if(progressByMap!=null&&progressByMap.size()!=0) {
					result.put("progress", progressByMap);
				}
		}
		//切换回原来的数据源
		if(StringUtils.isNotEmpty(ip)) {
			DataSourceContextHolder.setDbType("master_dataSource");
		}
		return result;
	}
	
	
	
}
