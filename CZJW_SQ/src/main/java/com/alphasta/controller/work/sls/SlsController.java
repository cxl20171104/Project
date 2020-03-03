package com.alphasta.controller.work.sls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.model.ListParam;
import com.alphasta.model.ProblemClues;
import com.alphasta.service.SlsService;

@Controller
@RequestMapping("/sls")
public class SlsController extends BaseController{
	@Autowired
	private SlsService slsService;
	/**
	 * 案件审理操作
	 */
	@RequestMapping(value = "/ajsl")
	@ResponseBody
	public Object ajsl(ListParam  listParam) {
		ProblemClues problemClues=listParam.getProblemClues();
		boolean sls_clues = slsService.sls_clues(listParam,request);
		if(sls_clues) {
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
	 * 审理室加载进度
	 */
	@RequestMapping(value = "/sls_data")
	@ResponseBody
	public Object la_data(String id,String ip) {
		    Map<String, Object> jds_jb = slsService.sls_data(id,ip);
			Result result=new Result();
			result.setObj(jds_jb);
			return result;
	}
}
