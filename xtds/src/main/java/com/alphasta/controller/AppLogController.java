package com.alphasta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.AppLog;
import com.alphasta.model.Dict;
import com.alphasta.service.AppLogService;
import com.alphasta.service.DictService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/applog")
public class AppLogController extends BaseController {

	@Autowired
	private AppLogService appLogService;
	@Autowired
	private DictService dictService;

	@RequestMapping("/main")
	public ModelAndView toMain() {
		org.springframework.web.servlet.ModelAndView result = new ModelAndView(
				"/work/appLogList");
		/*
		 * Dict dict = new Dict(); dict.setDictId("02");
		 * result.addObject("dict", dictService.findDictByDictPid(dict));
		 */
		return result;
	}

	@RequestMapping("/list")
	@ResponseBody
	public Object list(AppLog log, Integer page, Integer rows, String sort,
			String order) {
		Page<AppLog> pagehelper = PageHelper.startPage(page, rows);
		appLogService.selectByCondition(log);
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		pageInfo.setRows(pagehelper.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));
		return pageInfo;
	}
}
