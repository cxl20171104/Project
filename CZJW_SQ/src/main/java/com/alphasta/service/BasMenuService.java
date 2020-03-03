package com.alphasta.service;

import java.util.List;

import com.alphasta.model.BasMenuModel;
import com.alphasta.model.MenuQueryMdoel;

/**
 * 
 * @author gengzhuang
 *
 */
public interface BasMenuService {

	List<BasMenuModel> getAdminMenu(MenuQueryMdoel queryModel);

	List<BasMenuModel> getLoginMenu(MenuQueryMdoel queryModel);
	
}
