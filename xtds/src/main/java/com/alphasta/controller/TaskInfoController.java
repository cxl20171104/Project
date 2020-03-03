package com.alphasta.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.GetuiUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.MsgPut;
import com.alphasta.model.Organization;
import com.alphasta.model.TaskInfo;
import com.alphasta.model.TaskStatus;
import com.alphasta.model.User;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.TaskInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/task")
public class TaskInfoController extends BaseController {
	@Autowired
	private TaskInfoService taskInfoService;
	@Resource
	private OrganizationService organizationService;
	@RequestMapping("/taskPage")
	public ModelAndView getTackListPage() {
		ModelAndView result = new ModelAndView("/work/taskList");

		return result;
	}

	/**
	 * 发布通知公告
	 * 
	 * @return
	 */
	@RequestMapping("/sentTask")
	@ResponseBody
	public Result sentTaskInfo(HttpServletRequest request, TaskInfo info,
			String depts) {
		Result result = new Result();
		List<String> cids = null;
		MsgPut msgPut = new MsgPut();
		try {
			User u = getCurrentUser();
			info.setId(GetIdUtil.getId());
			info.setCreatTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date()));
			info.setCreator(u.getName());
			info.setDept(u.getOrganizationId().toString());
			String[] strs = depts.split(",");
			List<String> deptList = new ArrayList<String>(Arrays.asList(strs));
			cids = taskInfoService.sentTaskInfo(info, deptList);
			String msgPutId = GetIdUtil.getId();
			msgPut.setId(msgPutId);
			msgPut.setPuter(getCurrentUser());
			msgPut.setCtime(new Date());
			msgPut.setContent(info.getContent());
			msgPut.setTitle("通知通告");
			try {
				if (cids.size() == 1) {
					msgPut.setCid(cids.get(0));
					GetuiUtil.putMessageTouSingle(msgPut);
				} else if (cids.size() > 1) {
					GetuiUtil.putMsgToLlistTou(msgPut, cids);
				}
			} catch (Exception e) {

			}
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 通知公告列表
	 * 
	 * @param request
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/taskList")
	@ResponseBody
	public PageInfo getTaskInfoList(HttpServletRequest request, Integer page,
			Integer rows) {
		PageInfo pageinfo = new PageInfo();
		try {
			Page<TaskInfo> pageHelp = PageHelper.startPage(page, rows);
			Map<String, String> map = new HashMap<String, String>();
			taskInfoService.getTaskList(map);
			pageinfo.setRows(pageHelp.getResult());
			pageinfo.setTotal(Integer.valueOf(Long.valueOf(pageHelp.getTotal())
					.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageinfo;
	}

	/**
	 * 通知公告签收列表
	 * 
	 * @param request
	 * @param pageSize
	 * @param pageNum
	 * @param dept
	 * @return
	 */
	@RequestMapping("/taskStatus")
	@ResponseBody
	public PageInfo getTaskStatusList(HttpServletRequest request, Integer page,
			Integer rows, String taskid) {
		PageInfo pageinfo = new PageInfo();
		try {
			Page<TaskStatus> pageHelp = PageHelper.startPage(page, rows);
			Map<String, String> map = new HashMap<String, String>();
			map.put("taskid", taskid);
			taskInfoService.getTaskStatusList(map);
			pageinfo.setRows(pageHelp.getResult());
			pageinfo.setTotal(Integer.valueOf(Long.valueOf(pageHelp.getTotal())
					.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageinfo;
	}
	
	
	@RequestMapping("/delTask")
	@ResponseBody
	public Result delTask(String id){
		Result r=new Result();
		int i=taskInfoService.delete(id);
		if(i==1){
			r.setMsg("删除成功！");
			r.setSuccess(true);
		}else{
			r.setMsg("删除失败！");
			r.setSuccess(false);
		}
		return r;
	}
}
