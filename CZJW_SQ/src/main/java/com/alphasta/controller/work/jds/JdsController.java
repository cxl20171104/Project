package com.alphasta.controller.work.jds;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.model.ListParam;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.User;
import com.alphasta.service.JdsService;
@Controller
@RequestMapping("/jds")
public class JdsController extends BaseController{
	 @Autowired
	private  JdsService jdsService;
	/**监督室处置建议和处置决定保存(已加反馈)**/
	@RequestMapping(value="/jdsworking")
	@ResponseBody
	public Object jdsworking(ListParam  listParam) {
		List<Progress> progress = listParam.getProgress();
		ProblemClues problemClues=listParam.getProblemClues();
		boolean jds_clues = jdsService.Jds_clues(progress,problemClues, request);
		if(jds_clues) {
			 if(problemClues.getState().indexOf("LC")!=-1) {
				  return renderSuccess("办理信息已留存");
			  }
             if(problemClues.getState().indexOf("TJ")!=-1) {
            	   return renderSuccess("线索办理完毕");
			  }
			
		}
		return renderError("办理失败");
	}
	
	/**
     * 监督室下方到基层单位(已加县区下发)
     */
	@RequestMapping("/xfjcdw")
	@ResponseBody
	public Object xfjcdw(ListParam  listParam) {
		ProblemClues problemClues=listParam.getProblemClues();
		User user=listParam.getUser();
		boolean jds_xf =false;
		if(StringUtils.isNotEmpty(problemClues.getOrganId())&&!problemClues.getOrganId().equals("0")) {
		    jds_xf = jdsService.jds_xf(problemClues, user);
		}
		if(jds_xf) {
			
			  if(problemClues.getState().indexOf("LC")!=-1) {
				  return renderSuccess("分办信息已留存");
			  }
             if(problemClues.getState().indexOf("TJ")!=-1) {
            	    return renderSuccess("案件分办成功");
			  }
			
		}
		return renderError("下发失败");
	}
	/**
     * 监督室派驻纪检组
     */
	@RequestMapping("/pz")
	@ResponseBody
	public Object pz(ListParam  listParam) {
		ProblemClues problemClues=listParam.getProblemClues();
		List<Progress> progress=listParam.getProgress();
		boolean jds_xf=  jdsService.pz_xf(problemClues,progress);
		if(jds_xf) {
			
			  if(problemClues.getState().indexOf("LC")!=-1) {
				  return renderSuccess("派驻信息已留存");
			  }
             if(problemClues.getState().indexOf("TJ")!=-1) {
            	    return renderSuccess("案件派驻成功");
			  }
			
		}
		return renderError("派驻失败");
	}
	
	
	/**
	 * 监督室向案管室转发案件(已加反馈)
	 */
	@RequestMapping("/jdsToAgs")
	@ResponseBody
	public Object jdsToAgs(ProblemClues problemClues ) {
		boolean jds_toAg = jdsService.jds_toAg(problemClues,request);
		if(jds_toAg) {
			return renderSuccess("转发成功");
		}
		
		return renderError("转发失败");
	}
	@RequestMapping("/jds_zc")
	@ResponseBody
	public Object jds_zc(String id,String ip) {
		 Map<String, Object> jds_zc = jdsService.jds_zc(id,ip);
		Result result=new Result();
		result.setObj(jds_zc);
		return result;
	}
	
	@RequestMapping("/jds_jb")
	@ResponseBody
	public Object jds_jb(String id) {
		Map<String, Object> jds_jb = jdsService.jds_jb(id);
		Result result=new Result();
		result.setObj(jds_jb);
		return result;
	}
	
	//更新上级处置决定
	@RequestMapping("/updateCzjd")
	@ResponseBody
	public Object updateCzjd(String cluesId,String pointValue) {
		//查询出 监督室处置决定 detail为数字的
		Result result=new Result();
		boolean update = jdsService.updateProgressCzjd(cluesId, pointValue);
		result.setSuccess(update);
		return result;
		
	}
	
}
