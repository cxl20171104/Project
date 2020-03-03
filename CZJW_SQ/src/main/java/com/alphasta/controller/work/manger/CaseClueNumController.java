	package com.alphasta.controller.work.manger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.CaseClue;
import com.alphasta.service.CaseClueService;

@Controller
@RequestMapping("/sumTj")
public class CaseClueNumController  extends BaseController{
	private static final String CASECLUENUM = "/caseclueNum/caseclueNum";
	@Autowired
	private  CaseClueService caseClueService;
	
    private static final String DETAIL = "/caseclueNum/caseNumDetail";
    private static final String CASECLUE = "/caseclue/caseClue";
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView manager() {
		ModelAndView mv = new ModelAndView(CASECLUENUM);
		System.out.println("请求到了");
		
		return mv;
	}
	@RequestMapping(value = "/numTj")
	@ResponseBody
	public Object numTj(String conditions,String czbm,String xsstate,String toCompany,String action,String startDate,String endDate) {
		System.out.println(czbm+","+xsstate+","+toCompany+","+action+","+startDate+","+endDate);
		//开始查询
		PageInfo pageInfo = new PageInfo(1, 15, "", "");
		Map<String,Object> map = new HashMap<String, Object>();
		Integer oid = getCurrentUser().getOrganizationId();
		//主要条件
		
		if("0".equals(conditions)){
			map.put("condition", "czbm");
		}else if(!"".equals(conditions) && conditions != null){
			map.put("condition", conditions);
		}else if("".equals(conditions) || conditions == null){
			map.put("condition", "czbm");
		}
		if(!"".equals(czbm) && czbm != null){
			map.put("czbm", czbm);
		}
		if(!"".equals(xsstate) && xsstate != null){
			map.put("xsstate", xsstate);
		}
		if(!"".equals(toCompany) && toCompany!=null){
			map.put("toCompany", toCompany);
		}
		if(!"".equals(action) && action!=null){
			map.put("action", action);
		}
		if(!"".equals(startDate) && startDate!=null){
			map.put("startDate", startDate);
		}
		if(!"".equals(endDate) && endDate!=null){
			map.put("endDate", endDate);
		}
		map.put("oid", oid);
	    List<Map<String,Object>> list = caseClueService.findCaseClueCountNum(map);
	    pageInfo.setTotal(list.size());
	    pageInfo.setRows(list);
	  /* 
	    List<Map<String, Object>> l = new ArrayList<Map<String,Object>>();
	    l.add(list);
	    */
	    System.out.println(list);
	    return pageInfo;
	}
	
	@RequestMapping(value = "/detail")
	@ResponseBody
	public ModelAndView detail(String bl,String conditions,String czbm,String xsstate,String toCompany,String action,String startDate,String endDate) {
		Integer oid = getCurrentUser().getOrganizationId();
		
		ModelAndView mv = new ModelAndView(CASECLUE);
		//bl为下拉选的值
		if(bl.equals("0")){
			bl="czbm";
		}
		if(!"".equals(bl)){
			mv.addObject("bl", bl);
		}
		if(!"".equals(conditions)){
		mv.addObject("conditions", conditions);
		}
		if(!"".equals(czbm)){
		mv.addObject("czbm", czbm);
		}
		if(!"".equals(xsstate)){
		mv.addObject("xsstate", xsstate);
		}
		if(!"".equals(toCompany)){
		mv.addObject("toCompany", toCompany);
		}
		if(!"".equals(action)){
		mv.addObject("action", action);
		}
		if(!"".equals(startDate)){
		mv.addObject("startDate", startDate);
		}
		if(!"".equals(endDate)){
		mv.addObject("endDate", endDate);
		}
		mv.addObject("oid", oid);
		return mv;
	}
	
}
