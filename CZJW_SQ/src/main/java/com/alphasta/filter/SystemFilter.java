package com.alphasta.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;



public class SystemFilter  implements   Filter {

	
	
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		  HttpServletRequest request = (HttpServletRequest) servletRequest;
	        HttpServletResponse response = (HttpServletResponse) servletResponse;
	        System.out.println(request.getRequestURL());
	        String basePath = request.getContextPath();
	        request.setAttribute("basePath", basePath);
	        if (!SecurityUtils.getSubject().isAuthenticated()) {
	            //判断session里是否有用户信息
	            if (request.getHeader("x-requested-with") != null
	                    && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
	                //如果是ajax请求响应头会有，x-requested-with
	                response.setHeader("session-status", "timeout");//在响应头设置session状态
	                return;
	            }
	        }
	        filterChain.doFilter(request, servletResponse);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
