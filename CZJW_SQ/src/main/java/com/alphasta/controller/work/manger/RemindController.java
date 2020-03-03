package com.alphasta.controller.work.manger;

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
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Remind;
import com.alphasta.service.RemindService;

@Controller
@RequestMapping("/remind")
public class RemindController  extends BaseController{

	@Autowired
	private  RemindService remindService;
	
	
	
	private static final String REMIND = "/probleClues/remindMessage";

	
	/**
	 * 消息提醒详情页
	 * @return 
	 *
	 * @return
	 */
	@RequestMapping(value = "/remindMessage", method = RequestMethod.GET)
	public ModelAndView manager() {
		ModelAndView result = new ModelAndView(REMIND);
		return result;
	}

	/**
	 * 查询未读消息提醒
	 *
	 * @return
	 */
	@RequestMapping(value = "/messageList")
	@ResponseBody
	public Map<String,Object> messageList() {
		Remind remind = new Remind();
		remind.setSendState("0");
		List<Remind> messageList = remindService.messageList(remind);
		System.out.println(messageList);
		Map<String,Object> map = new HashMap<String,Object>();
		//return renderSuccess(messageList);
		map.put("messageList",messageList);
		map.put("organizationId", getCurrentUser().getId());
		return map;
	}
	
	/**
	 * 查询所有消息提醒
	 *
	 * @return
	 */
	@RequestMapping(value = "/allMessageList")
	@ResponseBody
	public Object allMessageList(Integer page, Integer rows, String sort, String order,Remind remind
			) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		remindService.allMessageList(pageInfo);
		return renderSuccess(pageInfo);
	}
	
	/**
	 * 消息已读状态更改 
	 */
	@RequestMapping(value = "/updateState")
	@ResponseBody
	public Object updateState(String id) {	
		if(!id.equals("")){
			Remind remind = new Remind();
			remind.setId(id);
			remind.setSendState("1");
			remindService.updateState(remind);
			return renderSuccess("更改成功");
		}else{
			return renderSuccess("更改失败，请重试。");
		}
	}
	
}
