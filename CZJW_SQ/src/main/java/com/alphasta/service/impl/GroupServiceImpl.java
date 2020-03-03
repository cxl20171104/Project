package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alphasta.mapper.GroupMapper;
import com.alphasta.model.Group;
import com.alphasta.service.GroupService;

public class GroupServiceImpl implements GroupService{
    @Autowired 
    GroupMapper groupMapper;
	@Override
	public int addGroup(Group group) {
		int p=groupMapper.addGroup(group);
		return p;
	}

	@Override
	public Group deleteGroup(String id) {
		// TODO Auto-generated method stub
		Group deleteGroup = groupMapper.deleteGroup(id);
		return deleteGroup;
	}

	@Override
	public Group updateGroup(Group group) {
		// TODO Auto-generated method stub
		Group updateGroup = groupMapper.updateGroup(group);
		return updateGroup;
	}

	@Override
	public Group findGroupById(String id) {
		// TODO Auto-generated method stub
		Group findGroupById = groupMapper.findGroupById(id);
		return findGroupById;
	}

	@Override
	public List<Group> findGroupByRid(String rid) {
		// TODO Auto-generated method stub
		List<Group> findGroupByCid = groupMapper.findGroupByRid(rid);
		return findGroupByCid;
	}

	@Override
	public Group findGroup(Group group) {
		Group findGroup = groupMapper.findGroup(group);
		return findGroup;
	}

   
}
