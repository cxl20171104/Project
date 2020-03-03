package com.alphasta.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.PicPathMaker;
import com.alphasta.model.User;
import com.alphasta.service.AppLogService;

@Controller
public class AppCzController {
	@Autowired
	AppLogService appLogService;

	@RequestMapping("/czlist.json")
	@ResponseBody
	@AppLog
	public Result cz(HttpServletRequest request, HttpServletResponse response,
			String start, String end) {
		User user = (User) request.getAttribute("appUser");
		Result result = new Result();
		if (user == null) {
			result.setSuccess(false);
			return result;
		}
		//PicPathMaker.path = request.getRequestURL().substring(0,
				//request.getRequestURL().lastIndexOf("/"));
		List<Map<String, Object>> everyThing = appLogService.getEveryThing(user.getId(), start, end);
		if (everyThing != null && everyThing.size() > 0) {
			request.setAttribute("remark", "查看<" + user.getName() + ">日志");
			request.setAttribute("openType", "3");
			result.setMsg("1");

		} else {
			result.setMsg("0");
		}
		result.setSuccess(true);
		result.setObj(everyThing);
		return result;
	}

}
