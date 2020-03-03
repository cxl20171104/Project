package com.alphasta.controller.work.scs;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.model.Group;
import com.alphasta.model.ListParam;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.service.ScsService;
@Controller
@RequestMapping("/scs")
public class ScsController extends BaseController{
	@Autowired
	private ScsService scsService;
	/**
	 * 审查室审查操作(已加案件反馈)
	 */
	@RequestMapping(value = "/scs")
	@ResponseBody
	public Object scs(ListParam  listParam) {
		List<Group> group = listParam.getGroup();;
		List<Progress> progress = listParam.getProgress();
		ProblemClues problemClues=listParam.getProblemClues();
		problemClues.setReflectedPerson(listParam.getReflectedPerson());
		boolean result= scsService.scsAddProgressService(progress, group, problemClues,request);
		if(result) {
			  if(problemClues.getState().indexOf("LC")!=-1) {
				  return renderSuccess("分办信息已留存");
			  }
              if(problemClues.getState().indexOf("TJ")!=-1) {
            	  return renderSuccess("案件处理完毕");
			  }
			
		}
		
		return renderError("保存失败");
	}
	
	
	
	/**
	 * 审查室立案审查操作(已加反馈)
	 */
	@RequestMapping(value = "/lasc")
	@ResponseBody
	public Object lasc(ListParam  listParam) {
        ProblemClues problemClues=listParam.getProblemClues();
		boolean scs_la = scsService.scs_la(listParam,request);
		if(scs_la) {
			 if(problemClues.getState().indexOf("LC")!=-1) {
				  return renderSuccess("分办信息已留存");
			  }
             if(problemClues.getState().indexOf("TJ")!=-1) {
           	  return renderSuccess("案件处理完毕");
			  }
			
		}
		return renderError("保存失败");
	}
	
	/**
	 * 
	 * 加载最近处理的数据
	 */
	@RequestMapping(value = "/sc_data")
	@ResponseBody
	public Object sc_data(String id,String ip) {
		    Map<String, Object> jds_jb = scsService.scss_data(id,ip);
			Result result=new Result();
			result.setObj(jds_jb);
			return result;
	}
	
	
	/**
	 * 
	 * 加载最近处理的数据
	 */
	@RequestMapping(value = "/la_data")
	@ResponseBody
	public Object la_data(String id,String ip) {
		    Map<String, Object> jds_jb = scsService.la_data(id,ip);
			Result result=new Result();
			result.setObj(jds_jb);
			return result;
	}
}
