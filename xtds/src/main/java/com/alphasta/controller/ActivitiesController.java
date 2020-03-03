package com.alphasta.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.commons.utils.StringEscapeEditor;
import com.alphasta.model.Activities;
import com.alphasta.service.ActivitiesService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/activi")
public class ActivitiesController extends BaseController {
	@Autowired
	private ActivitiesService activiesService;

	@Override
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

	@RequestMapping("/main")
	public ModelAndView main() {
		ModelAndView result = new ModelAndView("/work/activiList");
		return result;
	}

	@RequestMapping("/list")
	@ResponseBody
	public PageInfo page(Activities acti, Integer page, Integer rows,
			String sort, String order) {
		Page<Activities> pagehelper = PageHelper.startPage(page, rows);
		activiesService.page(acti);
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		pageInfo.setRows(pagehelper.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));

		return pageInfo;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(String id) {
		ModelAndView result = new ModelAndView("/work/activiEdit");
		if (StringUtils.isNotEmpty(id)) {
			Activities activities = activiesService.selectByID(id);
			result.addObject("act", activities);
		}
		return result;

	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Activities acti) {
		if (StringUtils.isNotEmpty(acti.getId())) {
			activiesService.updateByPrimaryKeySelective(acti);
		} else {
			acti.setId(GetIdUtil.getId());
			acti.setDept(getCurrentUser().getDept() == null ? ""
					: getCurrentUser().getDept());

			// acti.setEtime(new Date());
			// acti.setEtime(new
			// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			if (acti.getEtime() == null || "".equals(acti.getEtime())) {
				acti.setEtime("9999-12-31 00:00:00");
			}

			activiesService.save(acti);
		}
		return renderSuccess();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String ids) {
		activiesService.deleteByID(ids);
		return renderSuccess();
	}
}
