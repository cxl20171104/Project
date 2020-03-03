package com.alphasta.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.model.User;
import com.alphasta.service.UserService;

@Controller
@RequestMapping("/sta")
public class UserStaticsController {
	@Autowired
	private UserService userService;
    @RequestMapping("/statics")
    public ModelAndView statics(){
    	ModelAndView m=new ModelAndView("/work/userTj");
    	List<Map<String,Object>>lmap=userService.groupUser();
    	int count =userService.countUser();
    	m.addObject("lmap",lmap);
    	m.addObject("count",count);
    	return m;
    }
}
