package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.result.Tree;
import com.alphasta.model.User;
import com.alphasta.service.ResourceService;
import com.alphasta.service.RoleService;

@Service
public class ComService implements com.alphasta.service.ComService {
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private RoleService roleService;

	public boolean hasPurview(User user, String purview) {
		if (user.getName().equals("admin"))
			return true;
		List<Tree> resTree = resourceService.findTree(user);
		for (Tree t : resTree) {
			if (t.getAttributes().equals(purview))
				return true;
		}
		return false;
	}
}
