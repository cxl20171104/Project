package com.alphasta.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.exception.ServiceException;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.CompanyMapper;
import com.alphasta.model.Company;
import com.alphasta.service.CompanyService;

/**
 * 企业service
 * 
 * @author LiYunhao
 * 
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
	private static Logger LOGGER = LoggerFactory
			.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CompanyMapper companyMapper;

	@Override
	public void findCompanyPageCondition(PageInfo pageInfo) {
		List<Company> list = companyMapper.findCompanyPageCondition(pageInfo);
		int count = companyMapper.findCompanyPageCount(pageInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(count);
	}

	@Override
	public List<Company> findCompany(Company company) {
		return companyMapper.findCompany(company);
	}

	@Override
	public Company findCompanyById(Long id) {
		return companyMapper.findCompanyById(id);
	}

	@Override
	public int insert(Company company) {
		int result = companyMapper.insert(company);
		if (result != 1) {
			LOGGER.warn("新增企业信息失败");
			throw new ServiceException("新增失败");
		}
		return result;
	}

	@Override
	public int update(Company company) {
		int result = companyMapper.update(company);
		if (result != 1) {
			LOGGER.warn("更新企业信息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}

	@Override
	public int deleteByIds(String ids) {
		return companyMapper.deleteByIds(ids);
	}

}
