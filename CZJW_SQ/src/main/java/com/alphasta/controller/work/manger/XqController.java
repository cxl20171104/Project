package com.alphasta.controller.work.manger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alphasta.commons.base.BaseController;
import com.alphasta.model.Organization;
import com.alphasta.service.XqService;
@Controller
@RequestMapping("/xq")
public class XqController extends BaseController{
	@Autowired
	private XqService  xqService;
	@RequestMapping("/main")
	public String main() {
		return "/xq/main";
	}
	@RequestMapping("/findOrganTree")
	public Object findOrganTree(){
		List<Organization> findOrganTree = xqService.findOrganTree();
		return findOrganTree;
		
	}
	
}
