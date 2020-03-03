package com.alphasta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alphasta.model.Organization;

public interface OrganizationMapper {
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
	 * @param organization
	 * @return
	 */
	int insert(Organization organization);

	/**
	 * 更新部门
	 * 
	 * @param organization
	 * @return
	 */
	int updateOrganization(Organization organization);

	/**
	 * 查询部门子集
	 * 
	 * @param pid
	 * @return
	 */
	List<Organization> findOrganizationByPid(@Param("pid") Long pid);

	/**
	 * 查询所有部门集合
	 * 
	 * @return
	 */
	List<Organization> findOrganizationAll();

	/**
	 * 根据id查询部门
	 * 
	 * @param id
	 * @return
	 */
	Organization findOrganizationById(Long id);

	/**
	 * 获取子部门 state open
	 * 
	 * @param id
	 * @return
	 */
	List<Organization> findOrgByPid(@Param("pid") Long pid);
	/**
	 * 获取支部
	 */
	List<Organization> findBranchbyTwo(String user);
	/**
	 * 
	 * 
	 * 
	 * 查询当前用户所在部门的结构
	 */
	 List<Organization> findDataGridThis(String  code);
	 
	 
	 
	 
	 
	 /**
	  * 
	  * 
	  * 
	  * 
	  */
	 Organization findOrganByAddress(String address);
	 
	 
	 List<Organization>findOrganizationAll2();
}