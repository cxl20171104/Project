package com.alphasta.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.mapper.BasMenuMapper;
import com.alphasta.mapper.UserRoleMapper;
import com.alphasta.model.BasMenuModel;
import com.alphasta.model.MenuQueryMdoel;
import com.alphasta.service.BasMenuService;

@Service
public class BasMenuServiceImpl implements BasMenuService {

	@Autowired
	public BasMenuMapper basMenuMapper;
	@Autowired
	public UserRoleMapper userRoleMapper;

	@Override
	public List<BasMenuModel> getAdminMenu(MenuQueryMdoel queryModel) {
		return basMenuMapper.selectAdminByParentId(queryModel);
	}

	@Override
	public List<BasMenuModel> getLoginMenu(MenuQueryMdoel queryModel) {
		List<Long> roleIdList = userRoleMapper
				.findRoleIdListByUserId(queryModel.getUserId());
		String roleIds = "";
		for (Long roleId : roleIdList) {
			roleIds = roleIds + roleId + ",";
		}
		queryModel.setRoleIds(roleIds.substring(0, roleIds.length() - 1));
		return basMenuMapper.selectByParentId(queryModel);
	}

}
