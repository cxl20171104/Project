package com.alphasta.controller.app;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Pay;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.PayService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
public class AppPayController extends BaseController {
	
	@Autowired
	private PayService payService;
	@Autowired
	private OrganizationService organizationService;
	
    /**
     * 手机端用查询当前用户的缴费记录列表
     */
		@RequestMapping(value = "/pay.json")
		@ResponseBody	
		public Object  paylist(String userid,int pageSize,int pageNum){
			PageInfo pageInfo=new PageInfo(pageNum, pageSize);
			  Map<String, Object> condition = new HashMap<String, Object>();
			  condition.put("userid", userid);
			  pageInfo.setCondition(condition);
			List<Pay>paylist=payService.findPayByUserId(pageInfo);	
			if(paylist!=null&&paylist.size()>0){
				for(Pay p:paylist){
					if(("1").equals(p.getState())){
						p.setState("未交");
					}
					if(("2").equals(p.getState())){
						p.setState("已交");
					}
					if(("3").equals(p.getState())){
						p.setState("待交");
					}
				}
			}			
			return renderSuccess(paylist);
		}
		@RequestMapping("/organPay.json")
		@ResponseBody
		public Object paylistOrgan(String organ,int pageSize,int pageNum){	
			  Map<String, Object> condition = new HashMap<String, Object>();
			  condition.put("oid", organ);
			  condition.put("year", Calendar.getInstance().get(Calendar.YEAR));
			  condition.put("month", Calendar.getInstance().get(Calendar.MONTH)+1);
			Page<Pay> pageh=  PageHelper.startPage(pageNum, pageSize);
			payService.findPayByMonth(condition);
			List<Pay>paylist=pageh.getResult();
			if(paylist!=null&&paylist.size()>0){
				for(Pay p:paylist){
					if(("1").equals(p.getState())){
						p.setState("未交");
					}
					if(("2").equals(p.getState())){
						p.setState("已交");
					}
					if(("3").equals(p.getState())){
						p.setState("待交");
					}
				}
			};			
			return renderSuccess(paylist);			
		}

}
