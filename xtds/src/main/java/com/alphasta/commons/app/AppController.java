package com.alphasta.commons.app;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.utils.DesUtil;
import com.alphasta.commons.utils.PropertiesUtil;
import com.alphasta.model.User;
import com.alphasta.service.AppLogService;
import com.alphasta.service.UserService;

public abstract class AppController {
	@Autowired
	private AppLogService appLogService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/app/{method}", method = RequestMethod.GET, produces = { "application/json; charset=utf-8" })
	@ResponseBody
	@AppLog
	public void getService(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String method,
			@RequestParam("param") String param) throws IOException {
		String token = request.getParameter("token");
		User user = userService.getUserByToken(token);
		request.setAttribute("appUser", user);
		PropertiesUtil p = new PropertiesUtil("/config/des.properties");
		DesUtil des = new DesUtil();
		String[] postMethod = { "postSendCode", "postlogin", "postRegister",
				"postRZ", "postAppointment", "postWsjb", "postWj", "postWskg",
				"postWsss", "postWsts", "postCx", "postAdvice", "postReply",
				"postWsmxss", "postWspc", "postPush" };
		if (com.alphasta.commons.utils.OthersUtil.isHave(postMethod, method)) {
			JSONObject json = new JSONObject();
			json.accumulate("status", 1);
			json.accumulate("msg", "非法请求");
			json.accumulate("result", "[]");
			json.accumulate("total", 0);
			String str = des.strEnc(json.toString(), p.getProperty("key1"),
					p.getProperty("key2"), p.getProperty("key3"));
			response.getWriter().println(str);
			return;
		}
		if (method.equals("getArdetails") || method.equals("getWsdetails")
				|| method.equals("getZxdetails") || method.equals("getCity")
				|| method.equals("getDmList") || method.equals("getUnit")) {
			try {
				String dec = des.strDec(param, p.getProperty("key1"),
						p.getProperty("key2"), p.getProperty("key3"));
				JSONObject json = JSONObject.fromObject(dec);
				Object result = "";
				Method call = this.getClass().getMethod(method,
						JSONObject.class);
				result = call.invoke(this, json);
				response.setCharacterEncoding("UTF-8"); // 设置编码格式
				response.setContentType("text/html"); // 设置数据格式
				result = JSONObject.fromObject(result);
				String str = result.toString();
				response.getWriter().println(str);
			} catch (Exception e) {
				e.printStackTrace();
				JSONObject json = new JSONObject();
				json.accumulate("status", 1);
				json.accumulate("msg", "请求出错");
				json.accumulate("result", "[]");
				json.accumulate("total", 0);
				String str = json.toString();
				response.setCharacterEncoding("UTF-8"); // 设置编码格式
				response.setContentType("text/html"); // 设置数据格式
				response.getWriter().println(str);
			}
		} else {
			try {
				String dec = des.strDec(param, p.getProperty("key1"),
						p.getProperty("key2"), p.getProperty("key3"));
				Object result = "";
				Method call = this.getClass().getMethod(method,
						HttpServletRequest.class, HttpServletResponse.class,
						String.class);
				result = call.invoke(this, request, response, dec);
				result = JSON.toJSON(result);
				response.setCharacterEncoding("UTF-8"); // 设置编码格式
				response.setContentType("text/html"); // 设置数据格式
				String str = des.strEnc(result.toString(),
						p.getProperty("key1"), p.getProperty("key2"),
						p.getProperty("key3"));
				response.getWriter().println(str);
			} catch (Exception e) {
				e.printStackTrace();
				JSONObject json = new JSONObject();
				json.accumulate("status", 1);
				json.accumulate("msg", "请求出错");
				json.accumulate("result", "[]");
				json.accumulate("total", 0);
				String str = des.strEnc(json.toString(), p.getProperty("key1"),
						p.getProperty("key2"), p.getProperty("key3"));
				response.setCharacterEncoding("UTF-8"); // 设置编码格式
				response.setContentType("text/html"); // 设置数据格式
				response.getWriter().println(str);
			}
		}
	}

	@RequestMapping(value = "/app/{method}", method = RequestMethod.POST, produces = { "application/json; charset=utf-8" })
	@ResponseBody
	@AppLog
	public void postService(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String method,
			@RequestParam("param") String param) throws IOException {
		String token = request.getParameter("token");
		User user = userService.getUserByToken(token);
		request.setAttribute("appUser", user);
		if (method.equals("postPush")) {
			Object result = "";
			try {
				Method call = this.getClass().getMethod(method,
						JSONObject.class);
				result = call.invoke(this, JSONObject.fromObject(param));
				result = JSONObject.fromObject(result);
				response.setCharacterEncoding("UTF-8"); // 设置编码格式
				response.setContentType("text/html"); // 设置数据格式

			} catch (Exception e) {
				JSONObject json = new JSONObject();
				json.accumulate("status", 1);
				json.accumulate("result", "请求出错");
				result = json;
			}
			response.getWriter().println(result.toString());
			return;
		}
		com.alphasta.commons.utils.PropertiesUtil p = new com.alphasta.commons.utils.PropertiesUtil(
				"/config/des.properties");
		com.alphasta.commons.utils.DesUtil des = new com.alphasta.commons.utils.DesUtil();
		try {
			String dec = des.strDec(param, p.getProperty("key1"),
					p.getProperty("key2"), p.getProperty("key3"));
			// JSON json = JSON.toJSON(dec);
			Object result = "";
			Method call = this.getClass().getMethod(method,
					HttpServletRequest.class, HttpServletResponse.class,
					String.class);
			result = call.invoke(this, request, response, dec);
			result = JSON.toJSON(result);
			response.setCharacterEncoding("UTF-8"); // 设置编码格式
			response.setContentType("text/html"); // 设置数据格式
			String str = des.strEnc(result.toString(), p.getProperty("key1"),
					p.getProperty("key2"), p.getProperty("key3"));
			response.getWriter().println(str);
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject json = new JSONObject();
			json.accumulate("status", 1);
			json.accumulate("msg", "请求出错");
			json.accumulate("result", "[]");
			json.accumulate("total", 0);
			String str = des.strEnc(json.toString(), p.getProperty("key1"),
					p.getProperty("key2"), p.getProperty("key3"));
			response.setCharacterEncoding("UTF-8"); // 设置编码格式
			response.setContentType("text/html"); // 设置数据格式
			response.getWriter().println(str);
		}
	}

	public static void main(String[] args) {
		PropertiesUtil p = new PropertiesUtil("config/des.properties");
		String property = p.getProperty("key0");
		System.out.println(property);
	}
}
