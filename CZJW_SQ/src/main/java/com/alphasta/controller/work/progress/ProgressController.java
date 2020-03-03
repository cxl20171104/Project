package com.alphasta.controller.work.progress;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.mapper.OrganizationMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.model.Dict;
import com.alphasta.model.Organization;
import com.alphasta.model.Progress;
@Controller
@RequestMapping("/progress")
public class ProgressController extends BaseController{
	 @Autowired
	 private ProgressMapper progressMapper;
	 @Autowired
	 private OrganizationMapper organizationMapper;
	/**
	 * 打开案件进展界面
	 */
	@RequestMapping(value="/progress" ,method=RequestMethod.GET)	
	public ModelAndView progress(String id) {
		ModelAndView result=new ModelAndView("/probleClues/progress");
		result.addObject("id",id);
		return result;
	}
	
	private static final String DATAPROGRESS = "/probleClues/progress";
    @RequestMapping("/getData")
    @ResponseBody
    public Object getData(String id) {
   	 Result result=new Result();
   	 List<Progress> findProgressByCid = progressMapper.findProgressByCid(id);
   	 result.setObj(findProgressByCid);
   	 return result;
    }
    
    /**
	 * 案件线索进度页
	 *
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/getDataProgress", method = RequestMethod.GET)
	public ModelAndView getDataProgress(String id) throws ParseException {
		ModelAndView result = new ModelAndView(DATAPROGRESS);
		List<Progress> findProgressByCid = progressMapper.findProgressByCid(id);
		result.addObject("progress", findProgressByCid);
		return result;
	}
	
	   @RequestMapping("/getProgressAll")
	   public ModelAndView getProgressAll(String id) {
		   ModelAndView mv =new ModelAndView("/probleClues/getProgressAll");
		   Map<String,Object>map=new HashMap<String,Object>();
		   List<Progress> result=new ArrayList<Progress>();
		   map.put("causeId", id);
		   List<Progress> progress = progressMapper.getProgressByMap(map);
		   String organCan="案管室分办,审查室受理,监督室受理,监督室下发案件,各区县受理";
		   String schyCan="审查室处置建议,审查室会议决定,审查室领导审批意见";
		   String jdsCan="监督室处置建议,监督室执纪监督会议决定,监督室处置决定";
		   for(Progress p:progress) {
			   if(organCan.indexOf(p.getPointName())>=0) {
				   if(p.getDetail().length()<4) {
					   Organization organ = organizationMapper.findOrganizationById(Long.valueOf(p.getDetail()));
					   p.setDetail(organ.getName());
				   }
			   }
			   if(schyCan.indexOf(p.getPointName())>=0) {
				   if(p.getDetail().length()<4) {
					   List<Dict> dict = (List<Dict>)request.getServletContext().getAttribute("scsMethod");
					   for(Dict d:dict) {
						  if(d.getValue().equals(p.getDetail())) {
							  p.setDetail(d.getName());
						  }
					   }
				   }
			   }
			   if(jdsCan.indexOf(p.getPointName())>=0) {
				   if(p.getDetail().length()<4) {
					   List<Dict> dict = (List<Dict>)request.getServletContext().getAttribute("czMethod");
					   for(Dict d:dict) {
						  if(d.getValue().equals(p.getDetail())) {
							  p.setDetail(d.getName());
						  }
					   }
				   }
			   }
		   }
		   
		   //审查室会议
		   
		  /* map.put("pointValues", 0);
		   //案件添加进度
		   List<Progress> add_progress = progressMapper.getProgressByMap(map);
		   if(add_progress!=null&&!add_progress.isEmpty()) {
			   result.add(add_progress.get(0));
		   }
		   //分办进度
		   map.put("pointValues", 1);
		   List<Progress> fb_progress = progressMapper.getProgressByMap(map);
		   if(fb_progress!=null&&!fb_progress.isEmpty()) {
			   result.add(fb_progress.get(0));
		   }
		   //监督室受理时间
		   map.put("pointValues", 3);
		   List<Progress> jds_progress = progressMapper.getProgressByMap(map);
		   if(jds_progress!=null&&!jds_progress.isEmpty()) {
			   result.add(jds_progress.get(0));
		   }
		   //监督室处置时间
		   map.put("pointValues", 3.6);
		   List<Progress> jds_cz_progress = progressMapper.getProgressByMap(map);
		   if(jds_cz_progress!=null&&!jds_cz_progress.isEmpty()) {
			   result.add(jds_cz_progress.get(0));
		   }
		   //监督室初核时间
		   map.put("pointValues", 4);
		   List<Progress> ch_progress = progressMapper.getProgressByMap(map);
		   if(ch_progress!=null&&!ch_progress.isEmpty()) {
			   result.add(ch_progress.get(0));
		   }
		   //审查室受理时间
		   map.put("pointValues", 9);
		   List<Progress> scs_progress = progressMapper.getProgressByMap(map);
		   if(scs_progress!=null&&!scs_progress.isEmpty()) {
			   result.add(scs_progress.get(0));
		   }
		   //审查室处置时间
		   map.put("pointValues",11);
		   List<Progress> scs_cz_progress = progressMapper.getProgressByMap(map);
		   if(scs_cz_progress!=null&&!scs_cz_progress.isEmpty()) {
			   result.add(scs_cz_progress.get(0));
		   }
		   //审查室立案
		   map.put("pointValues",21);
		   List<Progress> scs_la_progress = progressMapper.getProgressByMap(map);
		   if(scs_la_progress!=null&&!scs_la_progress.isEmpty()) {
			   result.add(scs_la_progress.get(0));
		   }
		   //审理室受理时间
		   map.put("pointValues",13);
		   List<Progress> sls_progress = progressMapper.getProgressByMap(map);
		   if(sls_progress!=null&&!sls_progress.isEmpty()) {
			   result.add(sls_progress.get(0));
		   }
		   //结案时间
		   map.put("pointValues",14);
		   List<Progress> ja_progress = progressMapper.getProgressByMap(map);
		   if(ja_progress!=null&&!ja_progress.isEmpty()) {
			   result.add(ja_progress.get(0));
		   }
		   map.put("pointValues",-1);
		   List<Progress> ja_progress_ja = progressMapper.getProgressByMap(map);
		   if(ja_progress_ja!=null&&!ja_progress_ja.isEmpty()) {
			   result.add(ja_progress_ja.get(0));
		   }*/
		   mv.addObject("progress",progress);
		   return mv;
	   }
}
