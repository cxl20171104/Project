package com.alphasta.service;

import com.alphasta.commons.result.Tree;
import com.alphasta.model.Organization;
import com.alphasta.model.SchoolModel;

import java.util.List;

/**
 * 学校管理
 * 
 * @author gengzhuang
 * 
 */
public interface SchoolService {
	/**
	 * 查询部门资源树
	 * 
	 * @return
	 */
	List<Tree> findTree();

	/**
	 * 查询部门数据表格
	 * 
	 * @return
	 */
	List<SchoolModel> findSchoolGrid();

	/**
	 * 添加学校
	 * 
	 * @param schoolModel
	 */
	void addSchool(SchoolModel schoolModel);

	/**
	 * 根据id查找部门
	 * 
	 * @param id
	 * @return
	 */
	Organization findOrganizationById(Long id);

	/**
	 * 更新部门
	 * 
	 * @param organization
	 */
	void updateOrganization(Organization organization);

	/**
	 * 根据id删除部门
	 * 
	 * @param id
	 */
	void deleteOrganizationById(Long id);

}
