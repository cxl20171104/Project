package com.alphasta.commons.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.model.User;
import com.alphasta.service.AppLogService;
import com.alphasta.service.UserService;

@Component
@Aspect
@Order(0)
public class AppLogAop {

	@Autowired
	private AppLogService appLogService;
	@Autowired
	private UserService userService;
	private String isOpen = "1";

	/**
	 * controller执行完毕后输出执行日志
	 * 
	 * @param point
	 * @param result
	 */
	/**
	 * @param point
	 * @param result
	 * @throws Throwable
	 */
	@AfterReturning(pointcut = "execution(* com.alphasta.controller.app..*(..))", returning = "result")
	public void logAfterExecution(JoinPoint point, Object result)
			throws Throwable {

		if (isOpen.equals("1")) {
			AppLog AppLog = getMethodRemark(point);

			if (AppLog != null) {

				com.alphasta.model.AppLog log = new com.alphasta.model.AppLog();
				log.setId(GetIdUtil.getId());
				log.setDetail(AppLog.remark());
				log.setType(Integer.valueOf(AppLog.openType()));
				log.setLiveness("0");
				log.setOperater("");
				log.setOtime(new Date());

				Object remark = getRequest(point).getAttribute("remark");
				if (null != remark) {
					log.setDetail(remark.toString());
				}
				Object openType = getRequest(point).getAttribute("openType");
				if (null != openType) {
					log.setType(Integer.parseInt(openType.toString()));
				}
				Object liveness = getRequest(point).getAttribute("liveness");
				if (null != liveness)
					log.setLiveness(liveness.toString());
				User operater = (User) getRequest(point)
						.getAttribute("appUser");

				if (null != operater)
					log.setOperater(operater.getId().toString());

				appLogService.addLog(log);
			}
		}
	}

	@AfterReturning(pointcut = "execution(* com.alphasta.commons.app.AppController.*(..))", returning = "result")
	public void logAfterExecutionByMd5(JoinPoint point, Object result)
			throws Throwable {

		if (isOpen.equals("1")) {
			AppLog AppLog = getMethodRemark(point);

			if (AppLog != null) {

				com.alphasta.model.AppLog log = new com.alphasta.model.AppLog();
				log.setId(GetIdUtil.getId());
				log.setDetail(AppLog.remark());
				log.setType(Integer.valueOf(AppLog.openType()));
				log.setLiveness("0");
				log.setOperater("");
				log.setOtime(new Date());

				Object remark = getRequest(point).getAttribute("remark");
				if (null != remark) {
					log.setDetail(remark.toString());
				}
				Object openType = getRequest(point).getAttribute("openType");
				if (null != openType) {
					log.setType(Integer.parseInt(openType.toString()));
				}
				Object liveness = getRequest(point).getAttribute("liveness");
				if (null != liveness)
					log.setLiveness(liveness.toString());
				User operater = (User) getRequest(point)
						.getAttribute("appUser");

				if (null != operater)
					log.setOperater(operater.getId().toString());

				appLogService.addLog(log);
			}
		}
	}

	/**
	 * 获取方法的中文备注____用于记录用户的操作日志描述
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	private AppLog getMethodRemark(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();

		Class targetClass = Class.forName(targetName);
		Method[] method = targetClass.getMethods();
		for (Method m : method) {
			if (m.getName().equals(methodName)) {
				Class[] tmpCs = m.getParameterTypes();
				if (tmpCs.length == arguments.length) {
					AppLog methodCache = m.getAnnotation(AppLog.class);
					if (methodCache != null) {
						return methodCache;
					}
					break;
				}
			}
		}
		return null;
	}

	/**
	 * 获取参数request
	 * 
	 * @param point
	 * @return
	 */
	private HttpServletRequest getRequest(JoinPoint point) {
		Object[] args = point.getArgs();
		for (Object obj : args) {
			if (obj instanceof HttpServletRequest)
				return (HttpServletRequest) obj;
		}
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		return request;
	}

}
