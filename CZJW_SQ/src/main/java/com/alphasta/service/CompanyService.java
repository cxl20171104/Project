package com.alphasta.service;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Company;

public interface CompanyService {
	/**
	 * 根据分页条件查询企业信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	void findCompanyPageCondition(PageInfo pageInfo);

	/**
	 * 获取企业信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	List<Company> findCompany(Company company);

	/**
	 * 通过id查询企业信息
	 * 
	 * @param id
	 * @return
	 */
	Company findCompanyById(Long id);

	/**
	 * 插入企业信息
	 * 
	 * @param Company
	 * @return
	 */
	int insert(Company company);

	/**
	 * 更新企业信息
	 * 
	 * @param Company
	 * @return
	 */
	int update(Company company);

	/**
	 * 通过id删除企业信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByIds(String ids);

}
