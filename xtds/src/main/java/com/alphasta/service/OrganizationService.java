package com.alphasta.service;

import java.util.List;

import com.alphasta.model.Organization;

/**
 * @description：部门管理
 * @author：liyunhao
 * @date：2015/10/1 14:51
 */
public interface OrganizationService {
	/**
	 * 查询部门资源树
	 * 
	 * @return
	 */
	List<Organization> findOrganizationByPid(Long pid);

	/**
	 * 查询部门数据表格
	 * 
	 * @return
	 */
	List<Organization> findTreeGrid();
    
	
	/**
	 * 查询所有部门包括管理员部门
	 * @return
	 */
	List<Organization> findTreeGridAll();
	/**
	 * 添加部门
	 * 
	 * @param organization
	 */
	void addOrganization(Organization organization);

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

	/**
	 * 获取子部门
	 */
	List<Organization> getChildOrgs(Long id);
	/**
	 * 获取支部
	 */
	List<Organization> findBranchbyTwo(String user);
	/**
	 * 查询当前用户所在部门的部门结构
	 */
	List<Organization> findDataGridThis(String code);
	/**
	 * 
	 * 
	 * 
	 * 根据地址 通过地址映射的id
	 */
	Organization findOrganByAddress(String address);
}
