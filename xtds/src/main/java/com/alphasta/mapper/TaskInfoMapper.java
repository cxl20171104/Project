package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.TaskInfo;
import com.alphasta.model.TaskStatus;

public interface TaskInfoMapper {
	/**
	 * 发布通知公告
	 * 
	 * @param taskInfo
	 */
	public void insertTaskInfo(TaskInfo taskInfo);

	/**
	 * 向某支部发送公告
	 * 
	 * @param taskStatus
	 */
	public void insertTaskStatus(TaskStatus taskStatus);

	/**
	 * 签收公告
	 */
	public void updateTaskStatus(TaskStatus taskStatus);

	/**
	 * 公告列表
	 * 
	 * @param map
	 * @return
	 */
	public List<TaskInfo> searchTaskInfoList(Map<String, String> map);

	/**
	 * 公告签收结果列表
	 * 
	 * @param map
	 * @return
	 */
	public List<TaskStatus> searhTaskStatus(Map<String, String> map);

	/**
	 * 查看公告
	 * 
	 * @param taskInfo
	 * @return
	 */
	public TaskInfo searchTaskInfo(String id);

	/**
	 * 未读数量
	 */
	public Integer countUnTaskNum(Map<String, String> map);
    
	
	
	int delete(String id);
}
