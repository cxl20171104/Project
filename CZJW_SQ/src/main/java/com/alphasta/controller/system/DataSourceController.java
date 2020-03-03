package com.alphasta.controller.system;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;

@Controller
@RequestMapping("/dataSource")
public class DataSourceController extends BaseController{
	@RequestMapping(value = "/control", method = RequestMethod.GET)
	public ModelAndView control() {
		ModelAndView mv =new ModelAndView("/util/dataSourceControl");
		return mv;
	}
	@RequestMapping(value ="/save",method = RequestMethod.POST)
	@ResponseBody
	public Result save(String choice) {
		Result result=new Result();
		if(StringUtils.isNotEmpty(choice)) {
			ServletContext ctx = request.getServletContext();
			ctx.setAttribute("dataSourceControl", ","+choice+",");
			result.setSuccess(true);
			result.setMsg("保存成功");
		}
		return result;
	}
	
}
