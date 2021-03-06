package com.alphasta.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.UserVo;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Catalog;
import com.alphasta.model.Organization;
import com.alphasta.model.Role;
import com.alphasta.model.User;
import com.alphasta.service.ArticleService;
import com.alphasta.service.CatalogService;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.UserService;

/**
 * @description：用户管理
 * @author：liyunhao
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private ArticleService articleService;


	/**
	 * 用户管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "admin/user";
	}
	/**
	 * 用户管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selforg", method = RequestMethod.GET)
	public ModelAndView selforg(ModelAndView model) {
		Integer orgid = getCurrentUser().getOrganizationId();
		Organization organization = organizationService.findOrganizationById(orgid.longValue());
		if(organization.getCode().length()>4){
			organization  = organizationService.findOrganizationById(organization.getPid());
			if(organization.getCode().length()>4){
				organization  = organizationService.findOrganizationById(organization.getPid());
			};
		};
		model.addObject("orgid", organization.getId());
		model.setViewName("admin/selforg");
		return model;
	}

	/**
	 * 用户选择页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String select() {
		return "admin/userSelect";
	}

	/**
	 * 用户管理列表
	 * 
	 * @param userVo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public Object dataGrid(UserVo userVo, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows,sort,order);
		Map<String, Object> condition = new HashMap<String, Object>();

		if (StringUtils.isNoneBlank(userVo.getName())) {
			condition.put("name", userVo.getName());
		}
		if (userVo.getOrganizationId() != null) {
			condition.put("organizationId", userVo.getOrganizationId());
		}
		if (userVo.getCreatedateStart() != null) {
			condition.put("startTime", userVo.getCreatedateStart());
		}
		if (userVo.getCreatedateEnd() != null) {
			condition.put("endTime", userVo.getCreatedateEnd());
		}
		if(StringUtils.isNoneBlank(userVo.getXue_li())){
			condition.put("xue_li", userVo.getXue_li());
		}
		if(StringUtils.isNoneBlank(userVo.getLove())){
			condition.put("love", userVo.getLove());
		}
		pageInfo.setCondition(condition);
		userService.findDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 添加用户页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "admin/userAdd";
	}

	/**
	 * 添加用户
	 * 
	 * @param userVo
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(UserVo userVo) {
		User u = userService.findUserByLoginName(userVo.getLoginname());
		if (u != null) {
			return renderError("用户名已存在!");
		}
		userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
		userVo.setLiveness("0");
		userService.addUser(userVo);
		return renderSuccess("添加成功");
	}

	/**
	 * 编辑用户页
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(Long id, Model model) {
		UserVo userVo = userService.findUserVoById(id);
		List<Role> rolesList = userVo.getRolesList();
		List<Long> ids = new ArrayList<Long>();
		for (Role role : rolesList) {
			ids.add(role.getId());
		}
		model.addAttribute("roleIds", ids);
		model.addAttribute("user", userVo);
		return "admin/userEdit";
	}

	/**
	 * 编辑用户
	 * 
	 * @param userVo
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(UserVo userVo) {
		User user = userService.findUserByLoginName(userVo.getLoginname());

		if (user != null
				&& user.getId().longValue() + 1 != userVo.getId().longValue() + 1) {
			return renderError("用户名已存在!");
		}
		// userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
		//修改部门同时修改学习心声的位置
		if(userVo.getOrganizationId()!=null){
			Organization o = organizationService.findOrganizationById(userVo.getOrganizationId().longValue());
			if (o.getCode().length() > 4) {
				 o = organizationService.findOrganizationById(
							o.getPid().longValue());
				 if (o.getCode().length() > 4) {
					 o = organizationService.findOrganizationById(
								o.getPid().longValue());
				}		 
			}
			Catalog cl = catalogService.findCatalogByoid(o.getId()+"");
			if(cl!=null){
				final Long id=userVo.getId();
				final String id2=cl.getId();
				Thread t=new Thread() {
					public void run() {
						articleService.updateArtCata(id+"",id2);
					}
				};
				t.start();
				
			}
		}
		userService.updateUser(userVo);
		return renderSuccess("修改成功！");
	}

	/**
	 * 修改密码页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPwdPage", method = RequestMethod.GET)
	public String editPwdPage() {
		return "admin/userEditPwd";
	}

	/**
	 * 修改密码
	 * 
	 * @param oldPwd
	 * @param pwd
	 * @return
	 */
	@RequestMapping("/editUserPwd")
	@ResponseBody
	public Object editUserPwd(String oldPwd, String pwd) {
		if (!getCurrentUser().getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
			return renderError("老密码不正确!");
		}

		userService.updateUserPwdById(getUserId(), DigestUtils.md5Hex(pwd));
		return renderSuccess("密码修改成功！");
	}

	/**
	 * 重置密码
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/resetUserPwd")
	@ResponseBody
	public Object resetUserPwd(Long id) {
		userService.updateUserPwdById(id, DigestUtils.md5Hex("123456"));
		return renderSuccess("密码重置成功！");
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		userService.deleteUserById(id);
		return renderSuccess("删除成功！");
	}

	@RequestMapping("/getAll")
	@ResponseBody
	public List<User> getAll(String level,String oid) {
		Map<String,String> map =new HashMap<String, String>();
		if(level!=null&&!"".equals(level)){
			map.put("level", level);
		};
		if(oid!=null&&!"".equals(oid)){
			map.put("oid", oid);
		}
		return userService.getAll(map);
	}
}
