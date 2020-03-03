package com.alphasta.controller.work.timeOver;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.service.PageService;

@Controller
@RequestMapping("/timeOver")
public class TimeOverController extends BaseController{
	@Autowired 
	private PageService pageService;
    @RequestMapping("/dq_list")
    @ResponseBody
    public Object dq_list(String which){
    	PageInfo pageIfo =null;
    	if(which.equals("LA")) {
    		Map<String,Object> condition=new HashMap<String,Object>();
    		condition.put("page", 1);
    		condition.put("rows", 10);
    		condition.put("sort", "p.cluesNum,p.createTime");
    		condition.put("order", "asc");
    		pageIfo = pageService.overTimePage_lascService(condition);
    	}
    	return pageIfo;
    }
    @RequestMapping("/dialog")
    public ModelAndView dialog(String which) {
    	ModelAndView mv=null;
    	if(which.equals("LA")) {
    		mv =new ModelAndView("/probleClues/timeOver_dialog");
    		mv.addObject("which", "LA");
    	}
    	return mv;
    }
    
}
