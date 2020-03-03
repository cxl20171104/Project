package com.alphasta.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.mapper.TaskInfoMapper;
import com.alphasta.mapper.UserMapper;
import com.alphasta.model.TaskInfo;
import com.alphasta.model.TaskStatus;
import com.alphasta.service.TaskInfoService;

@Service
public class TaskInfoServiceImpl implements TaskInfoService {
	@Autowired
	public TaskInfoMapper taskInfoMapper;
	@Autowired
	public UserMapper userMapper;

	@Override
	public List<String> sentTaskInfo(TaskInfo taskInfo, List<String> depts) {
		taskInfoMapper.insertTaskInfo(taskInfo);
		TaskStatus t = new TaskStatus();
		t.setTaskid(taskInfo.getId());
		for (String str : depts) {
			t.setId(GetIdUtil.getId());
			t.setDept(str);
			t.setStatus("0");
			taskInfoMapper.insertTaskStatus(t);
		}
		// 消息推送
		List<String> cids = userMapper.getCidsBydepts(depts);
		return cids;

	}

	@Override
	public List<TaskInfo> getTaskList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return taskInfoMapper.searchTaskInfoList(map);
	}

	@Override
	public List<TaskStatus> getTaskStatusList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return taskInfoMapper.searhTaskStatus(map);
	}

	@Override
	public void updateTaskStatus(TaskStatus taskStatus) {
		if (taskStatus.getTaskid() != null && taskStatus.getDept() != null) {
			taskInfoMapper.updateTaskStatus(taskStatus);
		}

	}

	@Override
	public Integer getUnReadtask(Map<String, String> map) {
		// TODO Auto-generated method stub
		return taskInfoMapper.countUnTaskNum(map);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return taskInfoMapper.delete(id);
	}
     
}
