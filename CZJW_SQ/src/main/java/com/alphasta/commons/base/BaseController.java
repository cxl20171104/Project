package com.alphasta.commons.base;

import com.alphasta.commons.result.Result;
import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.commons.utils.DesUtil;
import com.alphasta.commons.utils.PropertiesUtil;
import com.alphasta.commons.utils.StringEscapeEditor;
import com.alphasta.model.CaseClue;
import com.alphasta.model.User;
import com.alphasta.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description：基础 controller
 * @author：liyunhao
 * @date：2015/10/1 14:51
 */
public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session;  
    @Autowired
    private UserService userService;
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
      

        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }
    /**
     * 获取当前登录用户对象
     * @return
     */
    public User getCurrentUser() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        User currentUser = userService.findUserById(user.id);
        return currentUser;
    }
    public Integer getCurrentOrganId() {
    	
    	return getCurrentUser().getOrganizationId();
    }
    public Long getCurrentUserId() {
    	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	return user.id;
    }
    /**
     * 获取当前登录用户id
     * @return
     */
    public Long getUserId() {
        return this.getCurrentUser().getId();
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    public String getStaffName() {
        return this.getCurrentUser().getName();
    }

    /**
     * ajax失败
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * @return {Object}
     */
    public Object renderSuccess() {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    /**
     * ajax成功
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        return result;
    }
   /**
    * ruquest对象
    */
       @ModelAttribute  

       public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  

        this.request = request;  

        this.response = response;  

        this.session = request.getSession();  

    }  
}
