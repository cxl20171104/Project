package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.BasMenuModel;
import com.alphasta.model.MenuQueryMdoel;

public interface BasMenuMapper {

	List<BasMenuModel> selectByParentId(MenuQueryMdoel queryModel);

	List<BasMenuModel> selectAdminByParentId(MenuQueryMdoel queryModel);
}
