package com.alphasta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Combobox;
import com.alphasta.model.Organization;
import com.alphasta.model.User;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description：部门管理
 * @author：liyunhao
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

	@Autowired
	@Resource
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;

	/**
	 * 部门管理主页
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "admin/organization";
	}
	/**
	 * 支部管理主页
	 * 
	 * @return
	 */
	@RequestMapping("/branch")
	public String branch() {
		
		return "admin/branch";
	}
	/**
	 * 支部概述管理
	 */
	
	@RequestMapping("/gaishu")
	public String gaishu() {
		return "admin/gaishu";
	}
	/**
	 * 部门资源树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findOrganizationByPid", method = RequestMethod.POST)
	@ResponseBody
	public Object findOrganizationByPid(Long pid,Long orgid) {
		if(orgid!=null){       //支部用户管理树根节点
			Organization or= organizationService.findOrganizationById(orgid);
			//转换id
			String code=or.getCode();
			if("02".equals(code.substring(0,2))){
				String id=or.getAddress();
				or =organizationService.findOrganizationById(Long.valueOf(id));
				
			}
			or.setState("closed");
			or.setText(or.getName());
			List<Organization> o=new ArrayList<Organization>();
			o.add(or);
			return o;
		};
		return organizationService.findOrganizationByPid(pid);
	}

	/**
	 * 部门列表
	 * 
	 * @return
	 */
	@RequestMapping("/treeGrid")
	@ResponseBody
	public Object treeGrid(String user) {
		if(user!=null){
		Integer getOrganizationId = getCurrentUser().getOrganizationId();
		Organization o = organizationService.findOrganizationById(getOrganizationId.longValue());
		//转换数据
		String code =o.getCode();
		if("02".equals(code.substring(0,2))){
			             o = organizationService.findOrganizationById(Long.valueOf(o.getAddress()));
		}
		if(o.getCode().length()>4){
			o = organizationService.findOrganizationById(
					o.getPid().longValue());
			if(o.getCode().length()>4){
				o = organizationService.findOrganizationById(
						o.getPid().longValue());
			}
		}
		System.out.println(o.getId());
		return organizationService.findBranchbyTwo(o.getId()+"");
		}
		return organizationService.findTreeGridAll();
	}
    /**
     * 
     * 陈晓亮添加
     * 用于显示当前用户所在部门的下级
     * 
     * 
     */
	@RequestMapping("/selfO")
	@ResponseBody
	public Object selfO() {
		//查出当前用户 部门的pid
		Integer getOrganizationId = getCurrentUser().getOrganizationId();
		Organization o = organizationService.findOrganizationById(getOrganizationId.longValue());
		
		
		//根据pid查询下级列表
		//地址=id
		String code=o.getCode();
		if("02".equals(code.substring(0,2))){
			String id=o.getAddress();
			Organization b =organizationService.findOrganizationById(Long.valueOf(id));
			code=b.getCode();
			
		}
		//查询部门
		
		List<Organization> findDataGridThis = organizationService.findDataGridThis(String.valueOf(code));
		return findDataGridThis;
		
	
	}
	
	
	/**
	 * 添加部门页
	 * 
	 * @return
	 */
	@RequestMapping("/addPage")
	public ModelAndView addPage() {
		Integer getOrganizationId = getCurrentUser().getOrganizationId();
		Organization o = organizationService.findOrganizationById(getOrganizationId.longValue());
		ModelAndView mv=new ModelAndView("admin/organizationAdd");
		mv.addObject("level", o.getLevel());
		return mv;
	}

	/**
	 * 添加部门
	 * 
	 * @param organization
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(Organization organization) {
		//添加关联id
		organizationService.addOrganization(organization);
		return renderSuccess("添加成功！");
	}

	/**
	 * 编辑部门页
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		Organization organization = organizationService
				.findOrganizationById(id);
		request.setAttribute("organization", organization);
		return "admin/organizationEdit";
	}

	/**
	 * 编辑部门
	 * 
	 * @param organization
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(Organization organization) {
		organizationService.updateOrganization(organization);
		return renderSuccess("编辑成功！");
	}

	/**
	 * 删除部门
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		organizationService.deleteOrganizationById(id);
		return renderSuccess("删除成功！");
	}

	/**
	 * tree 部门或人员
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/ptree")
	@ResponseBody
	public Object ptree(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		List<Organization> list = organizationService.getChildOrgs(id);
		if (list.size() == 0) {
			// 没有子部门 查询部门人员
			return userService.getUsersByOrgId(id);
		}
		return list;
	}
    /**
     *   
     * @param request
     * @param id  
     * @param level 1 市局  2 区县
     * @param oid 区县的支部id
     * @return
     */
	@RequestMapping("/organlist")
	@ResponseBody
	public List<Organization> getSecondOran(HttpServletRequest request, Long id,String level,String oid) {
		if(oid!=null&&!"".equals(oid)){
			id=Long.valueOf(oid);
		};
		List<Organization> list = organizationService.findOrganizationByPid(id);
		
		ListIterator<Organization> iterator = list.listIterator();
		if (id != null && id == 1L&&level!=null) {  //用于积分统计 机关党委下支部
			while(iterator.hasNext()){
				Organization next = iterator.next();
				if(level.equals(next.getLevel())){
					next.setState("open");
				}else{
					iterator.remove();
				}
				
			}
		}else if(id != null && id == 1L){ //机关党委下属
			while(iterator.hasNext()){
				Organization next = iterator.next();
					next.setState("open");
			}
		}
		List<Organization> to =new ArrayList<Organization>();
        if(list.size()==2){
        	if("02".equals(list.get(0).getId())){
        		to.add(list.get(1));
        		return  to;
        	}else{
        		to.add(list.get(0));
        		return to;
        	}
        }
		return list;
	}
	/**
	 *   活动举办部门
	 * @param request
	 * @param id  查询其子部门
	 * @param oid 区县的支部id
	 * @return
	 */
	@RequestMapping("/organlist2")
	@ResponseBody
	public List<Organization> getSecondOran2(HttpServletRequest request, Long id,String oid) {
		List<Organization> list=new ArrayList<Organization>();
		if(id==null){ //首次加载
			id=Long.valueOf(oid);
			Organization o=organizationService.findOrganizationById(id);
			
			if("02".equals(o.getCode().substring(0,2))){
				o=organizationService.findOrganizationById(Long.valueOf(o.getAddress()));
			}
			
			o.setText(o.getName());
			o.setState("closed");
			list.add(o);
			return list;
		};
		 list = organizationService.findOrganizationByPid(id);
		return list;
	}
	/**
	 *   时代先锋
	 * @param request
	 * @param id  查询其子部门
	 * @return
	 */
	@RequestMapping("/organlist3")
	@ResponseBody
	public List<Organization> getSecondOran3(HttpServletRequest request, Long id) {
		List<Organization> list=new ArrayList<Organization>();
		list = organizationService.findOrganizationByPid(id);
		List<Organization> to =new ArrayList<Organization>();
		if(list.size()==2){
        	if("02".equals(list.get(0).getId())){
        		to.add(list.get(1));
        		return  to;
        	}else{
        		to.add(list.get(0));
        		return to;
        	}
        }
		return list;
	}

	@RequestMapping("/organUsers")
	@ResponseBody
	public List<User> getOrganUsers(HttpServletRequest request, Long id) {
		return userService.getUsersByOrgId(id);
	}

}
