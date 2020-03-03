package com.alphasta.controller.work.repeat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.model.ProblemClues;
@Controller
@RequestMapping("/repeat")
public class RepeatController extends BaseController{
	@Autowired
	private ProblemCluesMapper problemCluesMapper;
    @RequestMapping("/repeatList")
    public ModelAndView repeatList(String id) {
    	ModelAndView mv=new ModelAndView("/probleClues/repeatList");
    	mv.addObject("cluesId",id);
    	mv.addObject("pageName","repeatList");
    	mv.addObject("organId", getCurrentOrganId());
    	return mv;
    }
    
    
    @RequestMapping("/cancelRepeat")
    @ResponseBody
    public Object cancelRepeat(ProblemClues problemClues) {
    	String message="";
    	if(problemClues!=null) {
    	//part0:查询这条线索
    	ProblemClues pro = problemCluesMapper.findProblemCluesById(problemClues.getId());
    	//part1:将本条线索的duplicate 设置为0 duplicateId设置为空
    	problemClues.setDuplicate("0");
    	problemClues.setDuplicateId("cancelRepeat");
    	int update2 = problemCluesMapper.update(problemClues);
    	if(update2==1) {
    		message="本条线索重复关系解除！";
    	}
    	//part2:判读父线索是否还有其他重复的
    	List<ProblemClues> rpeatList = problemCluesMapper.findProblemCluesByDuplicateId(pro.getDuplicateId());
    	if(rpeatList!=null&&!rpeatList.isEmpty()) {
    		System.out.println("还有其他重复的案件！");
    	}else {
    		System.out.println("没有重复件了");
    		//part3:将父线索的duplicate也设置为0
    		String id=pro.getDuplicateId();
    		problemClues.setId(id);
    		problemClues.setDuplicate("0");
        	problemClues.setDuplicateId("");
    		int update = problemCluesMapper.update(problemClues);
    		if(update==1) {
    			message="本条线索(含父线索)重复关系解除！";
    		}
    	}
    	}else {
    		message="线索不存在";
    	}
    	//part4:返回结果
    	return renderSuccess(message);
    	
    	
    }
    
    
    @RequestMapping("/repeatListAll")
    public ModelAndView repeatListAll(String id,String reflectedName) {
    	ModelAndView mv =new ModelAndView("/probleClues/repeatListAll");
    	mv.addObject("cluesId",id);
    	mv.addObject("reflectedName",reflectedName);
    	mv.addObject("pageName","repeatListAll");
    	return mv;
    }
    @RequestMapping("/addRepeat")
    @ResponseBody
    public Object addRepeat(String id,String inId) {
    	
    	//part0:处理要加入的线索
    	ProblemClues problemClues=new ProblemClues();
    	problemClues.setId(id);
    	problemClues.setDuplicate("1");
    	problemClues.setDuplicateId(inId);
    	int update = problemCluesMapper.update(problemClues);
    	//part1:处理被加入的线索
    	problemClues.setId(inId);
    	problemClues.setDuplicate("1");
    	problemClues.setDuplicateId("");
    	int update2 = problemCluesMapper.update(problemClues);
    	if(update==1&&update2==1) {
    		return renderSuccess("重复关系已建立");
    	}else {
    		return renderError("程序错误关系未建立");
    	}
    	
    	
    }
}
