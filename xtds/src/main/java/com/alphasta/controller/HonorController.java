package com.alphasta.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.commons.utils.StringEscapeEditor;
import com.alphasta.model.Article;
import com.alphasta.model.Honor;
import com.alphasta.model.Score;
import com.alphasta.model.Score_organization;
import com.alphasta.service.HonorService;
import com.alphasta.service.ScoreOrganService;
import com.alphasta.service.ScoreService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("honor")
public class HonorController extends BaseController{
	
	@Autowired
    public HonorService honorService;
	@Autowired
	public ScoreService scoreService;
	@Autowired
	public ScoreOrganService scoreOrganService;
	
	@RequestMapping("/honorPage")
	public String getPage(){
		
		return "work/honorlist";
	}
	@RequestMapping("/organHonorPage")
	public ModelAndView getPages(HttpServletRequest request,ModelAndView result){
		result.setViewName("work/OrganHonorlist");
		return result;
	}
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));

		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true,
				false));
	}
	/**
	 * 
	 * @param honor
	 * @param scorevalue 积分值
	 * @param honorName   荣誉级别
	 * @param desc 级别描述
	 * @return
	 */
	@RequestMapping("/addHonor")
	@ResponseBody
	public Result addHonor(Honor honor,String scorevalue,String hName,String descr){
		
		
		
		Result result=new Result();
		if("2".equals(honor.getType())){  //集体荣誉
			honor.setUserid(honor.getOrgan_id());  //获得荣誉就是所属部门
			//添加集体荣誉积分
			Score_organization so=new Score_organization();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			so.setCtime(sdf.format(new Date()));
			so.setDescr(descr+":"+hName);
			so.setId(GetIdUtil.getId());
			so.setOrganization_id(String.valueOf(honor.getOrgan_id()));
			so.setScoresource(String.valueOf(honor.getDictid()));
			so.setScorevalue(scorevalue);
			so.setType(String.valueOf(honor.getDictid()));
			scoreOrganService.addOrganScore(so);
		};
		if("1".equals(honor.getType())){ //个人荣誉
			//添加积分
			Score score = new Score();
			score.setCtime(new Date());
			score.setId(GetIdUtil.getId());
			score.setScoresource(String.valueOf(honor.getDictid()));
			score.setScorevalue(scorevalue);
			score.setDescr(descr+":"+hName);
			score.setUserid(String.valueOf(honor.getUserid()));
			score.setType(Integer.valueOf(String.valueOf(honor.getDictid())));
			scoreService.addScore(score);	
		}
		honor.setCtime(new Date());
		honor.setId(GetIdUtil.getId());
		honor.setLruser(getUserId());
		honorService.insertOne(honor);
		result.setSuccess(true);
		result.setMsg("添加荣誉成功!");
		return result;
	}
	
	@RequestMapping("/editHonor")
	@ResponseBody
	public Result editHonor(Honor honor){
		Result result=new Result();
		honor.setLruser(getUserId());
		honorService.updateOne(honor);
		result.setSuccess(true);
		result.setMsg("修改荣誉成功!");
		return result;
	};
	
	
	/**
	 * 
	 * @param page
	 * @param rows
	 * @param type
	 * @param userid
	 * @param organid
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping("/honorlist")
	@ResponseBody
	public PageInfo getHonorlist(Integer page, Integer rows,String type, String userid,String organid,
			String sort, String order){
		PageInfo pageinfo=new PageInfo();
	    Map<String,Object> map=new HashMap<String, Object>();
	    map.put("type", type);
	    if(userid!=null&&!"".equals(userid)){
	    	map.put("userid", userid);
	    };
	    if(organid!=null&&!"".equals(organid)){
	    	map.put("organid", organid);
	    };
	    pageinfo.setCondition(map);
		Page<Honor> pagehelper = PageHelper.startPage(page, rows);
		pagehelper.setOrderBy(sort + " "+order);
		honorService.getByPageInfo(pageinfo);
		pageinfo.setRows(pagehelper.getResult());
		pageinfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));
		return pageinfo;
	}
   
}
