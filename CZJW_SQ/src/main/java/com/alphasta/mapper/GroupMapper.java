package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Group;
import com.alphasta.model.Punishment;

public interface GroupMapper {
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
