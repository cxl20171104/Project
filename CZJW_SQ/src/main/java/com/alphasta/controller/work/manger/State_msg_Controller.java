package com.alphasta.controller.work.manger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.model.CaseClue;
import com.alphasta.model.Dict;
import com.alphasta.model.State_msg;
import com.alphasta.service.CaseClueService;
import com.alphasta.service.DictService;
import com.alphasta.service.State_msg_Service;


@Controller
@RequestMapping("/state_msg")
public class State_msg_Controller extends BaseController {
	@Autowired
	 State_msg_Service state_msg_Service;
	 @Autowired
	 DictService dictService;
	 @RequestMapping("/msg")
	 @ResponseBody
	 public  Object  state_msg(){
		 List<State_msg> findState_msg = state_msg_Service.findState_msg();
		 Result result = new Result();
		 //获取部门id
		 Integer organizationId = this.getCurrentUser().getOrganizationId();
			if(findState_msg!=null&&findState_msg.size()>0){
				String str1="";
				String str2="";
				String str3="";
				for(State_msg s:findState_msg){
					if("1".equals(s.getStyle())&&organizationId!=null&&String.valueOf(organizationId).equals("11")){
						//状态提醒
						String msg="";
						CaseClue caseclue=s.getCaseClue();
						if(caseclue==null){
							continue;
						}
						
						 String value=caseclue.getXsstate();	
						    if("2".equals(value)||"1".equals(value)){
						    	
						    	continue;
						    	
						    }			
						if("3".equals(value)){
    		        		msg= "承办签收";
    		        	};
    		        	if("4".equals(value)){
    		        		msg= "谈话函询";
    		        	};
    		        	if("5".equals(value)){
    		        		msg= "初步核实 ";
    		        	};
    		        	if("6".equals(value)){
    		        		msg= "暂存待查";
    		        	};
    		        	if("7".equals(value)){
    		        		msg= "给予了结";
    		        	}
						
						
						
						str1+="编号"+s.getCaseClue().getNum()+"线索 "+msg+"<br/>";
						//msg1Num++;
					}
					
					if("2".equals(s.getStyle())&&organizationId!=null&&String.valueOf(organizationId).equals(s.getMsgfor())){
					    str2+="编号"+s.getCaseClue().getNum()+"线索 即将到期<br/>";
						
					}
					if("3".equals(s.getStyle())&&organizationId!=null&&String.valueOf(organizationId).equals("11")){
						str3+="编号"+s.getCaseClue().getNum()+"的上级交办案件线索 即将到期</br>";
												
					}
					
					state_msg_Service.deleteState_msg(s.getId());
				}
				
				
				String str="";
				if(str1!=""){
					str+=str1;
				}
				if(str2!=""){
					str+=str2;
				}
				if(str3!=""){
					str+=str3;
				}
				if("".equals(str)){
					result.setSuccess(false);	
					return result;
				}
				result.setMsg(str);
				result.setSuccess(true);
				return result;				
			}
			//没有新消息	
			result.setSuccess(false);	
			return result;
		 
			
		 
		 
		 
	 }
	
	
}
