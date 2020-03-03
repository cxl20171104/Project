package com.alphasta.controller.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.model.Organization;
import com.alphasta.model.User;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.UserService;

/**
 * 组织架构
 * 
 * @author sjb
 * 
 */

@Controller
public class AppDeptController {

	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;

	/**
	 * 根据部门ID 查询人员
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/organUsers.json")
	@ResponseBody
	public List<User> getOrganUsers(HttpServletRequest request, Long id) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			List<User> list = new ArrayList<User>();
			if (id != 1L) {
				map.put("organization_id", id.toString());
				list = userService.getUsers(map);
			} else {
				map.put("level", "1");
				list = userService.getCenterUsers(map);  //查询市局党委领导
			}
			/*
			 * String bath=FileUtils.getBathUrl(request); for(User u:list){
			 * u.setHeadpic(bath+u.getHeadpic()); //头像地址 }
			 */
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<User>();
		}

	}
	/**
	 * 查询部门领导
	 * @param oid  部门ID
	 * @param level  1 市局  2 区县
	 * @return
	 */
	@RequestMapping("/getCenterUser.json")
	@ResponseBody
	public Result getCenterUsersByOid(String oid,String level,String grade){
		Result result=new Result();
		Map<String, String> map = new HashMap<String, String>();
		List<User> list = new ArrayList<User>();
			map.put("level", level);
			map.put("oid", oid);
			map.put("grade", grade);
		list = userService.getCenterUsers(map);
		result.setSuccess(true);
		result.setObj(list);
		return result;		
	}

	/**
	 * 根据父id 查询子部门
	 * 
	 * @param request
	 * @param id
	 * @param level 1 市局  2 区县
	 * @return
	 */
	@RequestMapping("/organlist.json")
	@ResponseBody
	public List<Organization> getSecondOran(HttpServletRequest request, Long id, String level) {
		List<Organization> list = organizationService.findOrganizationByPid(id);
		List<Organization> lists=new ArrayList<Organization>();
		if(list.size()==0){
			Organization o=organizationService.findOrganizationById(id);
			o.setState("open");
			list.add(o);
			return list;
		};
		for (Organization o : list) {
			//o.setIcon(bath + o.getIcon());
			if(o.getLevel().equals(level)){
				System.out.println(o);
				lists.add(o);
			};
		}
		return lists;
	}
	/**
	 * 根据userId查询该用户的部门概述
	 */
	@RequestMapping("/findSummarize.json")
	@ResponseBody
	public Result findSummarize(String userId){
		Result result = new Result();
		Long userid = Long.parseLong(userId);
		User user = userService.findUserById(userid);
		Integer organizationId = user.getOrganizationId();
		Organization o = organizationService.findOrganizationById(organizationId.longValue());
		if(o.getCode().length()>4){
			o = organizationService.findOrganizationById(
					o.getPid().longValue());
			if(o.getCode().length()>4){
				o = organizationService.findOrganizationById(
						o.getPid().longValue());
			}
		}
		System.out.println(o.getLevel()+","+o.getPid());
		if(o.getLevel().equals("1")){
			o = organizationService.findOrganizationById(1l);
			result.setObj(o);
		}else{
			if("1".equals(o.getLevel())&&o.getPid()==1){
				o.setName(null);
			}
			result.setObj(o);
		}
		
		return result;
	}
}
