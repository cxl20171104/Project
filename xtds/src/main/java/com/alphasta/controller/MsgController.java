package com.alphasta.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.BorthMsg;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.result.UserVo;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.GetuiUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.UserMapper;
import com.alphasta.model.MsgPut;
import com.alphasta.model.MsgReply;
import com.alphasta.model.Organization;
import com.alphasta.model.Score;
import com.alphasta.model.User;
import com.alphasta.service.MsgPutService;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/msg")
public class MsgController extends BaseController {
	@Autowired
	private MsgPutService msgService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;

	@RequestMapping("/showpage")
	public ModelAndView getScorePage() {
		ModelAndView mav = new ModelAndView("/work/msgList");
		Integer organizationId = getCurrentUser().getOrganizationId();
		Organization organization = organizationService.findOrganizationById(organizationId.longValue());
		if(organization.getCode().length()>4){
			organization  = organizationService.findOrganizationById(organization.getPid());
			if(organization.getCode().length()>4){
				organization  = organizationService.findOrganizationById(organization.getPid());
			};
		};
		mav.addObject("oid", organization.getId());
		mav.addObject("oname", organization.getName());
		return mav;
	}

	/**
	 * 信息发送列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param userName
	 *            发布人姓名
	 * @return
	 */
	@RequestMapping("/getMsgList")
	@ResponseBody
	public PageInfo getMsgPutList(HttpServletRequest request,
			HttpServletResponse response, Integer page, Integer rows,
			String userName,String oid) {
		Page<MsgPut> pageh = PageHelper.startPage(page, rows);
		PageInfo pageInfo = new PageInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(userName)) {
			map.put("name", userName);
		}
		if (!StringUtils.isEmpty(oid)) {
			map.put("oid", oid);
		}
		pageInfo.setCondition(map);
		msgService.getMsgPut(pageInfo);
		pageInfo.setRows(pageh.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pageh.getTotal())
				.toString()));
		return pageInfo;
	}

	/**
	 * 消息回复列表
	 * 
	 * @param page
	 * @param rows
	 * @param geterId
	 *            消息接受者(全体,部门,个人)ID
	 * @param type
	 *            消息类型
	 * @param id
	 *            消息id
	 * @return
	 */
	@RequestMapping("/remindList")
	@ResponseBody
	public PageInfo getUserScoreList(Integer page, Integer rows, String id) {

		PageInfo pageInfo = new PageInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msgid", id);
		Page<MsgReply> pageh = PageHelper.startPage(page, rows);
		msgService.getMsgReplylist(map);
		pageInfo.setRows(pageh.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pageh.getTotal())
				.toString()));
		return pageInfo;
	}

	/**
	 * 发送消息
	 * 
	 * @param content
	 * @param geterId
	 *            消息发送的人
	 * @return
	 */
	@RequestMapping("/sentMsg")
	@ResponseBody
	public Result sentMsg(String content, String geterId) {
		MsgPut msgPut = new MsgPut();
		String msgPutId = GetIdUtil.getId();
		msgPut.setId(msgPutId);
		msgPut.setPuter(getCurrentUser());
		msgPut.setCtime(new Date());
		msgPut.setContent(content);
		msgPut.setTitle("消息通知");
		String[] userIds = geterId.split(",");
		List<String> list = userService.getCidsByUids(userIds);
		if (list.size() == 1) {
			msgPut.setCid(list.get(0));
			try {
				//GetuiUtil.putMsgToSingle(msgPut);
				GetuiUtil.putMessageTouSingle(msgPut);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (list.size() > 1) {
			try {
				//GetuiUtil.putMsgToLlist(msgPut, list);
				GetuiUtil.putMsgToLlistTou(msgPut, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		MsgReply msgReply = new MsgReply();
		msgReply.setMsgId(msgPutId);
		msgReply.setHasRead("0");
		for (String userid : userIds) {
			if (userid != null && !"".equals(userid)) {
				msgReply.setId(GetIdUtil.getId());
				msgReply.setUserId(userid);
				msgService.insetReply(msgReply);
			}
		}
		msgPut.setGeterId("000");
		msgService.putOutMsg(msgPut);
		return (Result) renderSuccess();
	}

}
