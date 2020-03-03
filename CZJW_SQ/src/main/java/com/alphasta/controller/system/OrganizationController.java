package com.alphasta.controller.system;

import java.util.ArrayList;
import java.util.List;

import com.alphasta.commons.base.BaseController;
import com.alphasta.model.BasMenuModel;
import com.alphasta.model.Organization;
import com.alphasta.service.OrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
     * 部门资源树
     *
     * @return
     */
    @RequestMapping(value = "/findOrganizationByPid", method = RequestMethod.POST)
    @ResponseBody
    public Object findOrganizationByPid(Long pid) {
        return organizationService.findOrganizationByPid(pid);
    }
    /**
     * 部门资源树
     *
     * @return
     */
    @RequestMapping(value = "/findOrganizationByPid", method = RequestMethod.GET)
    @ResponseBody
    public Object findOrganizationByPid2(Long pid) {
    	return organizationService.findOrganizationByPid(pid);
    }

    /**
     * 部门列表
     *
     * @return
     */
    @RequestMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid() {
        return organizationService.findTreeGrid();
    }

    /**
     * 添加部门页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "admin/organizationAdd";
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
        Organization organization = organizationService.findOrganizationById(id);
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
     * 菜单列表显示下属单位和基层单位时使用
     */
    @RequestMapping("/forMenu")
    @ResponseBody
    public Object forMenu(Long id) {
    	List<Organization> findOrganizationByPid = organizationService.findOrganizationByPid(id);
    	List<BasMenuModel> menuList=new ArrayList<BasMenuModel>();
    	int i=0;
    	for(Organization o:findOrganizationByPid) {
    		BasMenuModel baseMenu=new BasMenuModel();
    		//菜单的id就是部门id查询的时候就用它就可以了
    		baseMenu.setId(o.getId());
    		baseMenu.setName(o.getName());
    		baseMenu.setText(o.getName());
    		baseMenu.setState("open");
    		baseMenu.setSeq(i);
    		baseMenu.setUrl("/probleClues/mm");
    		menuList.add(baseMenu);
    		i++;
    	}
    	
    	return menuList;
    }
}
