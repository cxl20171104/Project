package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.Organization;
import com.alphasta.model.SchoolModel;

public interface SchoolMapper {
	/**
	 * 删除部门
	 * 
	 * @param id
	 * @return
	 */
	int deleteOrganizationById(Long id);

	/**
	 * 添加部门
	 * 
	 * @param schoolModel
	 * @return
	 */
	int insert(SchoolModel schoolModel);

	/**
	 * 更新部门
	 * 
	 * @param organization
	 * @return
	 */
	int updateOrganization(Organization organization);

	/**
	 * 查询一级部门
	 * 
	 * @return
	 */
	List<Organization> findOrganizationAllByPidNull();

	/**
	 * 查询部门子集
	 * 
	 * @param pid
	 * @return
	 */
	List<Organization> findOrganizationAllByPid(Long pid);

	/**
	 * 查询所有部门集合
	 * 
	 * @return
	 */
	List<SchoolModel> findSchoolGrid();

	/**
	 * 根据id查询部门
	 * 
	 * @param id
	 * @return
	 */
	Organization findOrganizationById(Long id);
}