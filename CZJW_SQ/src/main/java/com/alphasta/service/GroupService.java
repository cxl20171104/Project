package com.alphasta.service;

import java.util.List;

import com.alphasta.model.Group;

public interface GroupService {
	//
	int addGroup(Group group);
	//
	Group deleteGroup(String id);
	//
	Group updateGroup(Group group);
	//
	Group findGroupById(String id);
	//
	List<Group> findGroupByRid(String rid);
	//
	Group findGroup(Group group);
	
}
