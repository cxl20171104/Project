package com.alphasta.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.commons.utils.StringEscapeEditor;
import com.alphasta.model.Footprint;
import com.alphasta.service.FootprintService;

@Controller
@RequestMapping("footprint")
public class FootprintController extends BaseController{
	
	@Autowired
	private FootprintService footprintService;
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));

		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true,
				false));
	}
	/**
	 * 足迹首页
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ModelAndView list(){
		ModelAndView result = new ModelAndView("/admin/footprint");
		return result;
	}
	/**
	 * 足迹列表
	 * @param userVo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public Object dataGrid(String organizationId, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (organizationId != null) {
			condition.put("organizationId", organizationId);
		}
		pageInfo.setCondition(condition);
		footprintService.findDataGrid(pageInfo);
		return pageInfo;
	}
   
}
