package com.alphasta.controller.index;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.service.IndexMenuService;
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	@Autowired
	private IndexMenuService indexMenuService;
    @RequestMapping(value="/indexNum",method = RequestMethod.GET)
    @ResponseBody
    public Object indexNum(String nodeName) {
    	Integer createNum = indexMenuService.createNum(nodeName);
    	Map<String,Object>data=new HashMap<String,Object>();
    	data.put("nodeName", nodeName);
    	data.put("createNum", createNum);
    	Result result=new Result();
    	result.setSuccess(true);
    	result.setObj(data);
    	return result;
    }
}
