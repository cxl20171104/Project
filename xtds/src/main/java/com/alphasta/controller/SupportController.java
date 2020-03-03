package com.alphasta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;

@Controller
public class SupportController extends BaseController{
	@RequestMapping("/support")
   public ModelAndView support(){
		ModelAndView mv=new ModelAndView("work/support");
	    return mv;
   }
}
