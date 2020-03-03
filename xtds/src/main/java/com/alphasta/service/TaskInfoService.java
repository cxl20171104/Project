package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.model.TaskInfo;
import com.alphasta.model.TaskStatus;

public interface TaskInfoService {
	/**
	 * 发布通知公告
	 * 
	 * @param taskInfo
	 *            通知公告
	 * @param depts
	 *            接受部门IDs
	 */
	public List<String> sentTaskInfo(TaskInfo taskInfo, List<String> depts);

	/**
	 * 通知公告列表
	 * 
	 * @param map
	 * @return
	 */
	public List<TaskInfo> getTaskList(Map<String, String> map);

	/**
	 * 签收列表
	 * 
	 * @param map
	 * @return
	 */
	public List<TaskStatus> getTaskStatusList(Map<String, String> map);

	/**
	 * 签收公告
	 * 
	 * @param taskStatus
	 */
	public void updateTaskStatus(TaskStatus taskStatus);

	/**
	 * 未读数量
	 * 
	 * @param map
	 * @return
	 */
	public Integer getUnReadtask(Map<String, String> map);
     
	
	
	int delete(String id);
}
