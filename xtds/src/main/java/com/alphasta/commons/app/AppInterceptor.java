package com.alphasta.commons.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alphasta.commons.scan.SpringUtils;
import com.alphasta.model.User;
import com.alphasta.service.UserService;
import com.alphasta.service.impl.UserServiceImpl;

public class AppInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String token = request.getParameter("token");
		if (StringUtils.isEmpty(token) || "undefined".equals(token)) {
			response.sendRedirect(request.getContextPath() + "/com/error.json");
			return false;
		} else {
			User user = userService.getUserByToken(token);
			request.setAttribute("appUser", user);
		}

		return true;
	}
}
