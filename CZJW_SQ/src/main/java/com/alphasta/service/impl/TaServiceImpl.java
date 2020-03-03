package com.alphasta.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.mapper.ReflectedPersonMapper;
import com.alphasta.mapper.ReflectingPersonMapper;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.service.AgsService;
import com.alphasta.service.BaseService;
import com.alphasta.service.ProblemCluesManagerServices;
import com.alphasta.service.TaService;

@Service
public class TaServiceImpl extends BaseService implements TaService{
    @Autowired
    private ProblemCluesMapper  problemCluesMapper;
    @Autowired
    private ProgressMapper progressMapper;
    @Autowired
    private  AgsService agsService;
    @Autowired
    private  ReflectedPersonMapper reflectedPersonMapper;
    @Autowired
    private  ReflectingPersonMapper reflectingPersonMapper;
	@Override
	public Model add_ta(String id,Model model) {
		//查询出案件信息
		ProblemClues problemClues = new ProblemClues();
		if (id != null) {
			problemClues = problemCluesMapper.findProblemCluesById(id);
		}
		problemClues.setReflectedPerson(null);
		problemClues.setReflectingPerson(null);
		System.out.println(problemClues);
		model.addAttribute("problemClues", problemClues);
		String oId =getCurrentOrganId();
		String flag = "";
		if("11".equals(oId)) {
			flag = "1";
		}else {
			flag = "0";
		}
		if(id!=null&&!"".equals(id)) {
			Progress p2 = new Progress();
			p2.setPointValue("0");
			p2.setCauseId(id);
			List<Progress> findProgress = progressMapper.findProgress(p2);
			model.addAttribute("agsSLTime",findProgress.get(0).getTime());	
		}
		model.addAttribute("flag",flag);
		return model;
	}
	/**
	 * 增加的同案人员需要案管室重新分办
	 */
	@Override
	public boolean add_ta(ProblemClues problemClues) {
		Result result = new Result();
		String uuid = "";
		if(problemClues !=null){
			//part1:修改原来的线索
			problemClues.setWhereFrom("tongan"+problemClues.getId()+"lord");
			problemCluesMapper.update(problemClues);
			//part2:处理被反映人
			 if(problemClues.getReflectedPerson()!=null) {
				 problemClues.getReflectedPerson().setId(GetIdUtil.getId());
				 reflectedPersonMapper.addReflectedPerson(problemClues.getReflectedPerson());
			 }
			//part3:处理反映人
			 if(problemClues.getReflectingPerson()!=null) {
				 problemClues.getReflectingPerson().setId(GetIdUtil.getId());
		         reflectingPersonMapper.addReflectingPerson(problemClues.getReflectingPerson());
			 }
			//part4:处理线索
			String currentOrganId = getCurrentOrganId();
			problemClues.setWhereFrom("tongan"+problemClues.getId());
			problemClues.setId( GetIdUtil.getId());
			problemClues.setFromId("ta");
			problemClues.setIsGet("1");
			problemClues.setOrganId(currentOrganId);
			problemClues.setCbr_now(getCurrentUser().getName());
			problemClues.setState("CLUESTJ"+getCurrentOrganId());
			problemCluesMapper.insertProblemClues(problemClues);
			//part:5处理进度
			    Progress progress = new Progress();
				String id3 = GetIdUtil.getId();
				progress.setId(id3);
				progress.setCauseId(problemClues.getId());
				progress.setState(problemClues.getState());
				progress.setUser(getCurrentUser());
				
			if(currentOrganId.equals(Param.agsId)) {
				progress.setPointName(Param.agssl_name);
				progress.setPointValue(Param.agssl_value);
			}
            if(Param.jdsIds.indexOf(","+currentOrganId+",")>=0) {
            	   progress.setPointName(Param.jdssl_name);
				   progress.setPointValue(Param.jdssl_value);
			}
            if(Param.scsIds.indexOf(","+currentOrganId+",")>=0) {
            	  progress.setPointName(Param.scssl_name);
				  progress.setPointValue(Param.scssl_value);
			}
            if(Param.slsId.equals(currentOrganId)) {
            	   progress.setPointName(Param.slssl_name);
				   progress.setPointValue(Param.slssl_value);
			}
            if(Param.dfsId.equals(currentOrganId)) {
            	progress.setPointName(Param.dfssl_name);
				progress.setPointValue(Param.dfssl_value);
			}
            if(Param.gbjdsId.equals(currentOrganId)) {
            	progress.setPointName(Param.gbjdsl_name);
				progress.setPointValue(Param.gbjdsl_value);
				//--------在加一条监督室的进度-------------//
				Progress peo=new Progress();
				peo.setId(GetIdUtil.getId());
				peo.setCauseId(problemClues.getId());
				peo.setPointName(Param.jdssl_name);
				peo.setPointValue(Param.jdssl_value);
				peo.setState(problemClues.getState());
				peo.setUser(getCurrentUser());
				progressMapper.addProgress(peo);
			}
            progressMapper.addProgress(progress);
		}
		result.setSuccess(true);
		result.setObj(uuid);
		return true;
	}
	//案件绑定已有的案件作为同案人员
	@Override
	public boolean bang_ta(String oldId, String newId) {
		//主线索
	 ProblemClues oldClues= problemCluesMapper.findProblemCluesById(oldId);
	 ProblemClues newClues= problemCluesMapper.findProblemCluesById(newId);	
	 if(oldClues!=null&&newClues!=null) {
		 newClues.setWhereFrom("tongan"+oldClues.getId());
		 int update = problemCluesMapper.update(newClues);
		 oldClues.setWhereFrom("tongan"+oldClues.getId()+"lord");
		 int update0 = problemCluesMapper.update(oldClues);
		 if(update==1&&update0==1) {
			 return true;
		 }
	 }
		
		return false;
	}
	@Override
	public boolean refbang(String cluesId) {
		// TODO Auto-generated method stub
		ProblemClues p=new ProblemClues();
		p.setId(cluesId);
		p.setWhereFrom("NO");
		int a=problemCluesMapper.update(p);
		if(a==1) {
			return true;
		}
		return false;
	}
    
	
	
	
}
