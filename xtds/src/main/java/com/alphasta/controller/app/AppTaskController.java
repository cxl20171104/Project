package com.alphasta.controller.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Organization;
import com.alphasta.model.TaskInfo;
import com.alphasta.model.TaskStatus;
import com.alphasta.model.User;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.RoleService;
import com.alphasta.service.TaskInfoService;
import com.alphasta.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
public class AppTaskController {
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	@RequestMapping("tasklist.json")
	@ResponseBody
	@AppLog(openType = "3", remark = "查看通知公告栏目")
	public PageInfo getTaskList(HttpServletRequest request, Integer pageNum,
			Integer pageSize, String organId) {
		PageInfo pageinfo = new PageInfo();
		try {
			User user = (User) request.getAttribute("appUser");
			// 是否是管理员或超级管理员
			List<Long> list = roleService.findRoleIdListByUserId(user.getId());
			Boolean isManger = (list.contains(1L) || list.contains(9L));
			/*
			 * List<String> readList=new ArrayList<String>(); if(isManger){
			 * Map<String,String> map=new HashMap<String,String>();
			 * map.put("userid", user.getId().toString()); //所有签收的通告
			 * List<TaskStatus> statusList =
			 * taskInfoService.getTaskStatusList(map); for(TaskStatus
			 * t:statusList){ readList.add(t.getTaskid()); } }
			 */
			Page<TaskInfo> pageHelp = PageHelper.startPage(pageNum, pageSize);
			Map<String, String> map = new HashMap<String, String>();
			map.put("oid", organId);
			taskInfoService.getTaskList(map);
			List<TaskInfo> result = pageHelp.getResult();
			/*if (!isManger) { // 不是管理员  不论管理员还是普通用户都可以看到签收字样
				for (TaskInfo t : result) {
					t.setIsComplete(null);
				}
			}*/
			pageinfo.setRows(result);
			pageinfo.setTotal(Integer.valueOf(Long.valueOf(pageHelp.getTotal())
					.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageinfo;
	};

	/**
	 * 签收列表
	 * 
	 * @param request
	 * @param taskid
	 * @return
	 */
	@RequestMapping("/taskStatus.json")
	@ResponseBody
	@AppLog(openType = "3", remark = "获取签收列表")
	public PageInfo getTaskStatusList(HttpServletRequest request, String taskid) {
		PageInfo pageinfo = new PageInfo();
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("taskid", taskid);
			List<TaskStatus> statusList = taskInfoService
					.getTaskStatusList(map);
			pageinfo.setRows(sort(statusList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageinfo;
	}
    //对list排序将机关党委的放到前面
	public List<TaskStatus>sort(List<TaskStatus> statusList){
		Map<Integer,TaskStatus>list0=new HashMap<Integer,TaskStatus>();
		List<TaskStatus>list1=new ArrayList<TaskStatus>();
		List<TaskStatus>list3=new ArrayList<TaskStatus>();
		for(TaskStatus t:statusList){
			
		    if(t.getDeptName().indexOf("邢台市地方税务局机关第一党支部")!=-1){
				list0.put(0,t);
			}
			else if(t.getDeptName().indexOf("邢台市地方税务局机关第二党支部")!=-1){
				list0.put(1,t);
			}
			else if(t.getDeptName().indexOf("邢台市地方税务局机关第三党支部")!=-1){
				list0.put(2,t);
			}
			else if(t.getDeptName().indexOf("邢台市地方税务局机关第四党支部")!=-1){
				list0.put(3,t);
			}
			else if(t.getDeptName().indexOf("邢台市地方税务局机关第五党支部")!=-1){
				list0.put(4,t);
			}
			else if(t.getDeptName().indexOf("邢台市地税局税源监控")!=-1){
				list0.put(5,t);
			}
			else if(t.getDeptName().indexOf("邢台市地方税务局机关老干部")!=-1){
				list0.put(6,t);
			}else{
				
				list1.add(t);
			}
		    
		}
		for(int i=0;i<7;i++){
			if(list0.get(i)!=null){
				
				list3.add(list0.get(i));
			}
	    }
	    for(TaskStatus t1:list1){
	    	list3.add(t1);
	    }
		return list3;
		
	}
	
	
	/**
	 * 签收通告
	 * 
	 * @param request
	 * @param taskid
	 * @return
	 */
	@RequestMapping("/replyTask.json")
	@ResponseBody
	@AppLog(openType = "0", remark = "签收通知公告")
	public Result sureTast(HttpServletRequest request, String taskid,
			String organId) {
		Result result = new Result();
		try {
			User user = (User) request.getAttribute("appUser");
			TaskStatus t = new TaskStatus();
			t.setTaskid(taskid);
			t.setDept(organId);
			t.setRtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date()));
			t.setReceiver(user.getId().toString());
			t.setReceiverName(user.getName());
			t.setStatus("1");
			taskInfoService.updateTaskStatus(t);
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			return result;
		}
	}

	/**
	 * 
	 * @param request
	 * @param id
	 *            栏目id
	 * @return
	 */
	@RequestMapping("/tasknum.json")
	@ResponseBody
	@AppLog(openType = "3", remark = "查询未签收通告数量")
	public Result getUnReadTotal(HttpServletRequest request, String id,
			String organId) {
		Result result = new Result();
		try {
			User user = (User) request.getAttribute("appUser");
			// 是否是管理员或超级管理员
			List<Long> list = roleService.findRoleIdListByUserId(user.getId());
			Boolean isManger = true;
			if (isManger) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("oid", organId);
				Integer unRreadNum = taskInfoService.getUnReadtask(map);
				result.setSuccess(true);
				result.setObj(unRreadNum);
				return result;
			} else {
				result.setSuccess(false);
				return result;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
			return result;

		}
	}
}
