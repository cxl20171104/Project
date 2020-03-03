package com.alphasta.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Activities;
import com.alphasta.model.Catalog;
import com.alphasta.model.Organization;
import com.alphasta.model.User;
import com.alphasta.service.ActivitiesService;
import com.alphasta.service.CatalogService;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/cata")
public class CataController extends BaseController {

	@Autowired
	private CatalogService cataLogService;
	@Autowired
	private ActivitiesService activitiesService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OrganizationService oganservice;
    /**
     * 栏目页 
     * @return
     */
	@RequestMapping("/main")
	public ModelAndView main() {
		ModelAndView result = new ModelAndView("/work/cataList");
		Activities act = new Activities();
		act.setId("1");
		List<Activities> acts = activitiesService.getByCondition(act);
		result.addObject("acts", acts);
		List<Organization> organs = oganservice.findTreeGrid();
		ListIterator<Organization> orit = organs.listIterator();
		while(orit.hasNext()){
			Organization next = orit.next();
			if(next.getCode().length()!=4){
				orit.remove();
			}
		}
		result.addObject("organs", organs);	 //所属部门选项	
		return result;

	}

	@RequestMapping("/list")
	@ResponseBody
	public PageInfo page(Catalog acti, Integer page, Integer rows, String sort,
			String order) {
		PageInfo pageInfo = new PageInfo();
		Page<Catalog> pagehelper = PageHelper.startPage(page, rows);
		cataLogService.getByCondition(acti);
		pageInfo.setRows(pagehelper.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));
		return pageInfo;
	}

	@RequestMapping("/tree")
	@ResponseBody
	public Object getTree(String pid) {
		return cataLogService.tree(pid);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String id) {
		try {
			cataLogService.deleteByID(id);
			return renderSuccess("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("删除失败，请重试");

		}
	}

	@RequestMapping("/toEdit")
	public ModelAndView toEdit(String id) {
		ModelAndView result = new ModelAndView("/work/cataEdit");
		if (StringUtils.isNotEmpty(id)) {
			Catalog catalog = cataLogService.getByID(id);
			result.addObject("cata", catalog);
		}
		return result;
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Catalog cata) {
		try {
			if (StringUtils.isNotEmpty(cata.getId())) {
				cataLogService.updateByPrimaryKey(cata);
			} else {
				cata.setId(GetIdUtil.getId());
				cata.setRoot("0");
				cata.setCreator(getUserId().toString());
				cata.setArttemplate("");
				cata.setShowNextColumn("0");
				cata.setTemplate("");
				cata.setShowonindex("0"); //默认是文章发布类的文章
				cataLogService.insertSelective(cata);
			}
			return renderSuccess("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("出错，请重试");

		}
	}
	@RequestMapping("/cataTree")
	@ResponseBody
	public Object cataTree(String pid, Integer type) {
		List<Catalog> childs = cataLogService.tree(pid);
		if (type != null && type == 0) {
			List<Long> list = roleService.findRoleIdListByUserId(getUserId());
			List<Catalog> cs = new ArrayList<Catalog>();
			if (list.contains(1L)) { // 是超级管理员
				for (Catalog c : childs) {
					if ("0".equals(c.getShowonindex())) {
						cs.add(c);
					}
				}
				return cs;
		} else { // 不是超级管理员 只加载党建动态
				for (Catalog c : childs) {
					/*if ("102".equals(c.getId())) {
						cs.add(c);
					}*/
					if ("0".equals(c.getShowonindex())&&!c.getId().equals("111")) {
						cs.add(c);
					}
				}
				return cs;
			}
		}
		return childs;
	}

	@RequestMapping("/noteList")
	public ModelAndView toCataNoteList() {
		ModelAndView result = new ModelAndView("/work/noteList");
		return result;
	}

	/**
	 * 笔记统计
	 * 
	 * @param request
	 * @param page
	 * @param rows
	 * @param sort
	 * @param id
	 *            ("详情")
	 * @return
	 */
	@RequestMapping("/cataNotelist")
	@ResponseBody
	public PageInfo getCataNoteNum(HttpServletRequest request, Integer page,
			Integer rows, String sort, String id) {
		PageInfo pageInfo = new PageInfo();
		try {
			if (id == null) {
				Page<Catalog> pagehelper = PageHelper.startPage(page, rows);
				cataLogService.getNoteCata();
				pageInfo.setRows(pagehelper.getResult());
				pageInfo.setTotal(Integer.valueOf(Long.valueOf(
						pagehelper.getTotal()).toString()));
			} else {
				Page<User> pagehelper = PageHelper.startPage(page, rows);
				cataLogService.getUserNoteNum(id);
				List<User> list = pagehelper.getResult();
				pageInfo.setRows(pagehelper.getResult());
				pageInfo.setTotal(Integer.valueOf(Long.valueOf(
						pagehelper.getTotal()).toString()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageInfo;
	}
}
