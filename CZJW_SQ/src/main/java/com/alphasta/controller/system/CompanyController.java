package com.alphasta.controller.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Company;
import com.alphasta.service.CompanyService;

/**
 * 企业管理
 * 
 * @author LiYunhao
 *
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

	@Autowired
	private CompanyService companyService;

	private static final String COMPANY = "/admin/company";
	private static final String INFO = "/admin/companyInfo";

	/**
	 * 企业管理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView manager() {
		ModelAndView result = new ModelAndView(COMPANY);
		return result;
	}

	/**
	 * 企业信息页
	 *
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(Long id) {
		ModelAndView result = new ModelAndView(INFO);
		Company company = new Company();
		if (id != null) {
			company = companyService.findCompanyById(id);
		}
		result.addObject("company", company);
		return result;
	}

	/**
	 * 新增企业信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Company company) {
		company.setCreaterId(this.getUserId());
		companyService.insert(company);
		return renderSuccess("新增成功");
	}

	/**
	 * 更新企业信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Object edit(Company company) {
		companyService.update(company);
		return renderSuccess("更新成功");
	}

	/**
	 * 查询企业信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/findCompanyPageCondition")
	@ResponseBody
	public Object findCustomerPageCondition(Integer page, Integer rows, String sort, String order, String name) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("name", name);
		pageInfo.setCondition(condition);
		companyService.findCompanyPageCondition(pageInfo);
		return renderSuccess(pageInfo);
	}

	/**
	 * 删除企业信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(String ids) {
		companyService.deleteByIds(ids);
		return renderSuccess("删除成功");
	}

}
