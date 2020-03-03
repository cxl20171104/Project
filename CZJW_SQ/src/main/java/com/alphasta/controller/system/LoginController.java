package com.alphasta.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.shiro.ShiroDbRealm;
import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.mapper.OrganizationMapper;
import com.alphasta.model.BasMenuModel;
import com.alphasta.model.MenuQueryMdoel;
import com.alphasta.model.Organization;
import com.alphasta.model.Param;
import com.alphasta.model.User;
import com.alphasta.service.BasMenuService;
import com.alphasta.service.IndexMenuService;
import com.alphasta.service.UserService;
import com.alphasta.service.impl.IndexMenuServiceImpl;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @description：登录退出
 * @author：liyunhao
 * @date：2015/10/1 14:51
 */
@Controller
public class LoginController extends BaseController {
	
	@Autowired
	private BasMenuService basMenuService;
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private IndexMenuService indexMenuService;
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/index";
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/select")
    public ModelAndView select(Model model) {
    	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    	List<Long> roleList = user.roleList;
    	if(roleList.contains(9L)||user.id==1l||roleList.contains(18l)) {
    		ModelAndView mv1=new ModelAndView("/select");
            return mv1;
    	}else {
    		ModelAndView result = new ModelAndView("index");
 	        result.addObject("version","1");
 	    	result.addObject("name","线索管理系统");
 	        result.addObject("organId",getCurrentOrganId()); 
 	        Integer organId=getCurrentOrganId();
 	        Organization organization = organizationMapper.findOrganizationById(Long.valueOf(organId.toString()));
 	        result.addObject("orgaName",organization.getName());
 	        result.addObject("sjw_value",Param.sjwd_value);
 	        result.addObject("dfsId",Param.dfsId);
 	        return result;
    	}
    }

    //系统模块跳转页面
    @RequestMapping(value = "/index")
    public ModelAndView index(Model model,String version,String name) { 
    	ModelAndView result = new ModelAndView("index");
    	result.addObject("version",version);
    	result.addObject("name",name);
        result.addObject("organId",getCurrentOrganId()); 
        Integer organId=getCurrentOrganId();
        Organization organization = organizationMapper.findOrganizationById(Long.valueOf(organId.toString()));
        result.addObject("orgaName",organization.getName());
        result.addObject("sjw_value",Param.sjwd_value);
        result.addObject("dfsId",Param.dfsId);
        return result;
    }
    
    /**
     * GET 登录
     * @return {String}
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.info("GET请求登录");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    /**
     * POST 登录 shiro 写法
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object loginPost(String username, String password) {
        logger.info("POST请求登录");

        if (StringUtils.isBlank(username)) {
            return renderError("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return renderError("密码不能为空");
        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
        token.setRememberMe(true);
        try {
            user.login(token);
        } catch (UnknownAccountException e) {
            logger.error("账号不存在：{}", e);
            return renderError("账号不存在");
        } catch (DisabledAccountException e) {
            logger.error("账号被锁定，请联系管理员", e);
            return renderError("账号被锁定，请联系管理员。");
        } catch (IncorrectCredentialsException e) {
        	logger.error("密码错误：{}", e);
            return renderError("密码错误");
        } catch(ExcessiveAttemptsException e){
        	logger.error("密码错误，连续登录5次失败，账户将被锁定！", e);
            return renderError("密码错误，连续登录5次失败，账户将被锁定！");
        }catch (RuntimeException e) {
            logger.error("未知错误,请联系管理员：{}", e);
            return renderError("未知错误,请联系管理员");
        }
        //登陆成功清空map的值
        Param.organId=getCurrentOrganId().toString();
    	ShiroDbRealm.map.clear();
        return renderSuccess();
    }

    /**
     * 未授权
     * @return {String}
     */
    @RequestMapping(value = "/unauth")
    public String unauth() {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/login";
        }
        return "unauth";
    }

    /**
     * 退出
     * @return {Result}
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Object logout() {
        logger.info("登出");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return renderSuccess();
    }
    
    @RequestMapping(value = "/getMenu")
    @ResponseBody
    public Object getMenu(MenuQueryMdoel queryModel)
    {
		logger.debug("获取菜单");
		List<BasMenuModel> list = null;
		try {
			User cu = getCurrentUser();
			queryModel.setUserId(cu.getId());
			if ((cu != null) && (cu.getIsAdmin() != null) && (cu.getIsAdmin().equals(1))) {
				list = basMenuService.getAdminMenu(queryModel);
			} else {
				//今天要把列表显示数字一笔勾销
				List<BasMenuModel> listA = basMenuService.getLoginMenu(queryModel);
				list=new ArrayList<BasMenuModel>();
				String noShow=",市级,基层单位,区县线索库,线索查询（全网）,直接下发,间接下发,业务管理,系统管理,菜单权限管理,日志管理,角色管理,用户管理,部门管理,职位管理,数据字典,模板制作,线索统计,统计表,";
				for(BasMenuModel ba:listA) {
					//根据权限名称查询数量
					if(noShow.indexOf(","+ba.getText()+",")>=0) {
						ba.setText(ba.getName());
					}else {
						Integer createNum = indexMenuService.createNum(ba.getText());
						ba.setText(ba.getName()+"["+createNum+"]");
						
					}
					
					list.add(ba);
				}
			}
		} catch (Exception e) {
			logger.error("发生未知的异常:", e);
		}
		return list;
	}
}
