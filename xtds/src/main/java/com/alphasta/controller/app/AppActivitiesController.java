package com.alphasta.controller.app;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.ActivitiesMapper;
import com.alphasta.mapper.CatalogMapper;
import com.alphasta.model.Activities;
import com.alphasta.model.Catalog;
import com.alphasta.service.ActivitiesService;
import com.alphasta.service.CatalogService;

@Controller
@RequestMapping("/activities")
public class AppActivitiesController {

	@Autowired
	private ActivitiesService activitiesService;

	@Autowired
	private CatalogService catalogService;

	@RequestMapping("/activitiesList.json")
	@ResponseBody
	public Result activitiesList(HttpServletRequest request,
			HttpServletResponse response, String token, String pageSize,
			String pageNum) {
		Result result = new Result();
		String bath = FileUtils.getBathUrl(request);
		try {
			PageInfo pageInfo = new PageInfo();
			if (pageSize != null && pageNum != null) {
				int pagesize = Integer.parseInt(pageSize);
				int pagenum = Integer.parseInt(pageNum);
				pageInfo.setFrom((pagenum - 1) * pagesize);
				pageInfo.setSize(pagesize);
				List<Activities> list = activitiesService.apppage(pageInfo);
				if (list.size() <= 0) {
					result.setMsg("0");// 没有查出数据
				} else {
					for (Activities at : list) {
						at.setPicture(bath + at.getPicture());
					}
					result.setObj(list);
				}
			}
		} catch (Exception E) {
			result.setMsg("1");
		}
		return result;
	}

	@RequestMapping("catalogList.json")
	@ResponseBody
	public Result catalogList(HttpServletRequest request,
			HttpServletResponse response, String hdid) {
		Result result = new Result();
		String bath = FileUtils.getBathUrl(request);
		try {
			List<Catalog> list = catalogService.getByactivities(hdid);
			for (Catalog cl : list) {
				cl.setPic(bath + cl.getPic());
			}
			if (list.size() <= 0) {
				result.setMsg("0"); // 没有查询出数据
			} else {
				result.setObj(list);
			}
		} catch (Exception e) {
			result.setMsg("1");
		}
		return result;
	}
}
