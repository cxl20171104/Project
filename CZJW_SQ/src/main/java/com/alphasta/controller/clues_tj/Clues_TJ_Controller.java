package com.alphasta.controller.clues_tj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.TJFieldMapper;
import com.alphasta.mapper.TJMapper;
import com.alphasta.model.Dict;
import com.alphasta.model.Organization;
import com.alphasta.model.clues_tj.TJModel;
@Controller
@RequestMapping("/clues_tj")
public class Clues_TJ_Controller extends BaseController{
	@Autowired
	TJMapper tjMapper;
	@Autowired
	TJFieldMapper tjFieldMapper;
	@RequestMapping("/action")
	public  String  action(){
		return "clues_tj/clues_tj";
	}
	@RequestMapping("/data")
	@ResponseBody
	public Object data(TJModel tjModel) {
		int which=0;
		Map<String, Object> condition =new HashMap<String,Object>();
		//查询项
		if(!StringUtils.isEmpty(tjModel.getEnd_time())&&!tjModel.getEnd_time().equals("")){
			condition.put("end_time", tjModel.getEnd_time());
		}
        if(!StringUtils.isEmpty(tjModel.getStart_time())&&!tjModel.getStart_time().equals("")){
        	condition.put("start_time", tjModel.getStart_time());
		}
        if(!StringUtils.isEmpty(tjModel.getField_option())&&!tjModel.getField_option().equals("0")){
        	if(tjModel.getType().equals("fields")) {
        		which=1;
        	}
	        condition.put("field_option", tjModel.getField_option());
		}
        
        if(!StringUtils.isEmpty(tjModel.getProblemLand_option())&&!tjModel.getProblemLand_option().equals("0")){
        	if(tjModel.getType().equals("problemLand")) {
        		which=1;
        	}
	        condition.put("problemLand_option", tjModel.getProblemLand_option());
        }
       if(!StringUtils.isEmpty(tjModel.getRank_option())&&!tjModel.getRank_option().equals("0")){
    	   if(tjModel.getType().equals("rank")) {
       		  which=1;
       	   }
	      condition.put("rank_option", tjModel.getRank_option());
       }
       if(!StringUtils.isEmpty(tjModel.getClues_option())&&!tjModel.getClues_option().equals("0")){
    	   if(tjModel.getType().equals("clues")) {
       		  which=1;
       	   }
	      condition.put("clues_option", tjModel.getClues_option());
       }
       if(!StringUtils.isEmpty(tjModel.getSpecial_option())&&!tjModel.getSpecial_option().equals("0")){
    	   if(tjModel.getType().equals("special")) {
        		  which=1;
           }
 	      condition.put("special_option", tjModel.getSpecial_option());
        }
        if(!StringUtils.isEmpty(tjModel.getOrganId_option())&&!tjModel.getOrganId_option().equals("0")){
    	   if(tjModel.getType().equals("organId")) {
        		  which=1;
           }
 	      condition.put("organId_option", tjModel.getOrganId_option());
        }
        if(!StringUtils.isEmpty(tjModel.getType())){
	       condition.put("type", tjModel.getType());
        }
       
       
           //查询
           PageInfo pageInof=new PageInfo();
           if(tjModel.getType().equals("fields")) {
        	   //领域统计
        	   if(which==0) {
        		   List<TJModel> tj = tjFieldMapper.tj(condition);
           		   pageInof.setRows(tj);
           		   pageInof.setSize(tj.size());
        	   }else {
        		   List<TJModel> tj = tjFieldMapper.tj2(condition);
           		   pageInof.setRows(tj);
           		   pageInof.setSize(tj.size());
           		   tj.clear();
        	   }
        	  return pageInof;
           }
           
           
           
           if(which==0) {
        	   //全局统计
        	   List<TJModel> tj = tjMapper.tj(condition);
       		   pageInof.setRows(tj);
       		   pageInof.setSize(tj.size());
           }
           if(which==1) {
        	   //单项统计
        	   List<TJModel> tj = tjMapper.tj2(condition);
       		   pageInof.setRows(tj);
       		   pageInof.setSize(tj.size());
           }
		   return pageInof;
}
	
	
	
	
	    //------------------------------------------------------------
	                      //线索详情
	    //------------------------------------------------------------
        @RequestMapping("/detail")
        public ModelAndView detail(TJModel tjModel) {
        	ModelAndView mv=new ModelAndView("/clues_tj/tj_detail");
        	ServletContext servletContext = request.getServletContext();
        	List<Dict> dictList = new ArrayList<Dict>();
        	//修改基本项
        	//绝对不允许向前台传递null
        	if(tjModel.getRank_option()!=null) {
        		   dictList = (List<Dict>)servletContext.getAttribute("rank");	
        		   for(Dict d:dictList) {
        				if(d.getName().equals(tjModel.getRank_option())) {
        					tjModel.setRank_option(d.getValue());
        				}
        			}
        	}else {
        		tjModel.setRank_option("");
        	}
        	
        	if(tjModel.getClues_option()!=null) {
        		 dictList = (List<Dict>)servletContext.getAttribute("clues");	
        		 for(Dict d:dictList) {
      				if(d.getName().equals(tjModel.getClues_option())) {
      					tjModel.setClues_option(d.getValue());
      				}
      			}
        	}else {
        		tjModel.setClues_option("");
        	}
        	
        	
        	if(tjModel.getProblemLand_option()!=null) {
        		dictList = (List<Dict>)servletContext.getAttribute("problemLand");	
       		 for(Dict d:dictList) {
     				if(d.getName().equals(tjModel.getProblemLand_option())) {
     					tjModel.setProblemLand_option(d.getValue());
     				}
     			}
        	}else {
        		tjModel.setProblemLand_option("");
        	}
        	
        	if(tjModel.getProblemLand_option()!=null) {
        		dictList = (List<Dict>)servletContext.getAttribute("special");	
       		 for(Dict d:dictList) {
     				if(d.getName().equals(tjModel.getSpecial_option())) {
     					tjModel.setSpecial_option(d.getValue());
     				}
     			}
        	}else {
        		tjModel.setSpecial_option("");
        	}
        	
        	if(tjModel.getOrganId_option()!=null) {
        		List<Organization>oList = (List<Organization>)servletContext.getAttribute("oId");	
       		 for(Organization d:oList) {
     				if(d.getName().equals(tjModel.getOrganId_option())) {
     					tjModel.setOrganId_option(d.getId().toString());
     				}
     			}
        	}else {
        		tjModel.setOrganId_option("");
        	}
        	
        	if(tjModel.getField_option()!=null) {
        		List<Dict>oList = (List<Dict>)servletContext.getAttribute("fields");	
       		 for(Dict d:oList) {
     				if(d.getName().equals(tjModel.getField_option())) {
     					tjModel.setField_option(d.getValue());
     				}
     			}
        	}else {
        		tjModel.setField_option("");
        	}
        	//*-------------------------------------------------------------------//
        	
        	//修改type值
        	if(tjModel!=null&&!StringUtils.isEmpty(tjModel.getOptions())) {
        		if(tjModel.getOptions().equals("problemLand")) {
        		 dictList = (List<Dict>)servletContext.getAttribute("problemLand");
        		 for(Dict d:dictList) {
     				if(d.getName().equals(tjModel.getType())) {
     					tjModel.setProblemLand_option(d.getValue());
     				}
     			}
        		}
                if(tjModel.getOptions().equals("clues")) {
                dictList = (List<Dict>)servletContext.getAttribute("clues");	
                for(Dict d:dictList) {
     				if(d.getName().equals(tjModel.getType())) {
     					tjModel.setClues_option(d.getValue());
     				}
     			}
        		}
                if(tjModel.getOptions().equals("rank")) {
                dictList = (List<Dict>)servletContext.getAttribute("rank");	
                for(Dict d:dictList) {
     				if(d.getName().equals(tjModel.getType())) {
     					tjModel.setRank_option(d.getValue());
     				}
     			}
        		}
                
                if(tjModel.getOptions().equals("special")) {
                    dictList = (List<Dict>)servletContext.getAttribute("special");	
                    for(Dict d:dictList) {
         				if(d.getName().equals(tjModel.getType())) {
         					tjModel.setSpecial_option(d.getValue());
         				}
         			}
            	}
                
                if(tjModel.getOptions().equals("organId")) {
                	List<Organization>  oList = (List<Organization>)servletContext.getAttribute("oId");	
                    for(Organization d:oList) {
         				if(d.getName().equals(tjModel.getType())) {
         					tjModel.setOrganId_option(d.getId().toString());
         				}
         			}
            	}
                
                if(tjModel.getOptions().equals("fields")) {
                	List<Dict>  oList = (List<Dict>)servletContext.getAttribute("fields");	
                    for(Dict d:oList) {
         				if(d.getName().equals(tjModel.getType())) {
         					tjModel.setField_option(d.getValue());
         				}
         			}
            	}
               
        	}
        	
        	
        	mv.addObject("tjModel",tjModel);
        	return mv;
        }
}
