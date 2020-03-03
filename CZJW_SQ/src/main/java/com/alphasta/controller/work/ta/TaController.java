package com.alphasta.controller.work.ta;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.service.TaService;

@Controller
@RequestMapping("/ta")
public class TaController extends BaseController{
	@Autowired
	private TaService taService;
	/**
	 * 打开添加同案人员页面
	 */
	@RequestMapping(value = "/tongan", method = RequestMethod.GET)
	public String tongan(String id,Model model) throws ParseException {
		Model add_ta = taService.add_ta(id, model);
		model.addAttribute("pageName", "tongan");
		model.addAttribute("organId", getCurrentOrganId());
		return "/part/saveClues";
	}
	
	
	/**
	 * 同案人员添加(已加反馈)
	 */
	@RequestMapping(value = "/tonAdd")
	@ResponseBody
	public Object tonAdd(ProblemClues problemClues) throws ParseException {
        boolean add_ta = taService.add_ta(problemClues);
        if(add_ta) {
        	return renderSuccess("同案人员添加成功");
        }
        return renderError("同案人员添加失败");
	}
	
	@RequestMapping(value="/getTonan",method=RequestMethod.GET)
	public ModelAndView tonganList(String cluesId) {
		ModelAndView result=new ModelAndView("/probleClues/tonganList");
		result.addObject("cluesId",cluesId);
		result.addObject("pageName","tonganList");
		return result;
	}
	//绑定同案人员   对现有案件进行处理   具体操作如下  点击 绑定同案人员     打开案件列表  显示所有案件   查询  案件   勾选  案件   点击绑定    绑定完成
		@RequestMapping(value="/a")
		public ModelAndView bangTonan(String id) {
			//打开线索列表页面
			ModelAndView mv=new ModelAndView("/probleClues/ADDTONGA");
			mv.addObject("cluesId",id);
			mv.addObject("pageName","ADDTONGA");
			return mv;
		}
		
		@RequestMapping(value="/bang")
		@ResponseBody
		public Result bang(String oldId,String newId) {
			Result result=new Result();
			
			boolean bang_ta = taService.bang_ta(oldId, newId);
			if(bang_ta) {
				result.setMsg("绑定成功");
				result.setSuccess(true);
			}else {
				result.setMsg("绑定失败");
				result.setSuccess(false);
			}
			
			return result;
		}
	
		@RequestMapping(value="/refbang")
		@ResponseBody
		public Result refbang(String cluesId,String whereFrom) {
			Result result=new Result();
			boolean refbang = taService.refbang(cluesId);
			if(refbang) {
				result.setMsg("解绑成功");
				result.setSuccess(true);
			}else {
				result.setMsg("解绑失败");
				result.setSuccess(false);
			}
			return result;
		}
}
