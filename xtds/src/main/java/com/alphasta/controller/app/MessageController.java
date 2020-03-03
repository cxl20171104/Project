package com.alphasta.controller.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.result.BorthMsg;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.MsgPut;
import com.alphasta.model.MsgReply;
import com.alphasta.model.User;
import com.alphasta.service.MsgPutService;
import com.alphasta.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
public class MessageController {
	@Autowired
	private MsgPutService msgService;
	@Autowired
	private UserService userService;

	/**
	 * App 消息列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/msglist.json")
	@ResponseBody
	@AppLog
	public Result getMsgList(HttpServletRequest request,
			HttpServletResponse response, Integer pageNum, Integer pageSize) {
		User user = (User) request.getAttribute("appUser");
		Result result = new Result();
		try {
			PageInfo pageInfo = new PageInfo();
			Map<String, Object> map = new HashMap<String, Object>();
			String userId = user.getId().toString();
			map.put("appUid", userId);
			pageInfo.setCondition(map);
			Page<MsgPut> page = PageHelper.startPage(pageNum, pageSize);
			msgService.getMsgPut(pageInfo);
			request.setAttribute("remark", "查看消息列表");
			request.setAttribute("openType", "3");
			String bath = FileUtils.getBathUrl(request);
			List<MsgPut> list = page.getResult();
			for (MsgPut m : list) {
				String hp = m.getPuter().getHeadpic();
				if (hp != null && !"".equals(hp)) {
					m.getPuter().setHeadpic(bath + hp);
				}
			}
			result.setObj(list);
			result.setSuccess(true);
			return result;

		} catch (Exception e) {
			result.setSuccess(false);
			return result;
		}

	}

	/**
	 * 查看信息
	 */
	@RequestMapping("/replyMsg.json")
	@ResponseBody
	@AppLog
	public Result replyMsg(HttpServletRequest request,
			HttpServletResponse response, String msgPutId) {
		User user = (User) request.getAttribute("appUser");
		Result result = new Result();
		try {
			MsgReply msgReply = new MsgReply();
			msgReply.setHasRead("1");
			msgReply.setMsgId(msgPutId);
			msgReply.setReadTime(new Date());
			msgReply.setUserId(user.getId().toString());
			msgService.replyMsg(msgReply);

			result.setSuccess(true);
			return result;

		} catch (Exception e) {
			result.setSuccess(false);
			return result;
		}

	}

	/**
	 * 获取政治生日消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/borthUser.json")
	@ResponseBody
	@AppLog
	public Result getBorthUser(HttpServletRequest request,String oid,String level) {
		Result result = new Result();
		try {
			Map<String,String>  map =new HashMap<String, String>();
			if(StringUtils.isNotEmpty(oid)){
				map.put("oid", oid);
			};
			if(StringUtils.isNotEmpty(level)){
				map.put("level", level);
			};
			List<BorthMsg> users = userService.getBorthUsers(map);
			if (users.size() > 0) {
				String msg = "";
				BorthMsg b;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				for (int i = 0; i < users.size(); i++) {
					b = users.get(i);
					msg += sdf.format(sdf2.parse(b.getIntime())) + ","
							+ b.getName() + "同志光荣地加入了中国共产党,今天是" + b.getName()
							+ "同志入党" + b.getAgeNum() + "周年纪念日";
					if (i == users.size() - 1 && users.size() != 1) {
						msg = msg
								+ ";感谢你们为地税事业的辛勤付出! 党组织向你们表示热烈的祝贺,希望你们立足岗位,牢记宗旨,坚定信念,不忘初心,继续前进! "
								+ "</br></br>中共衡水市地方税务局直属单位委员会 </br>"
								+ sdf.format(new Date()) + "    ";

					} else if (i == users.size() - 1 && users.size() == 1) {
						msg = msg
								+ ";感谢您为地税事业的辛勤付出! 党组织向您表示热烈的祝贺,希望您立足岗位,牢记宗旨,坚定信念,不忘初心,继续前进!"
								+ "</br></br>中共邢台市地方税务局直属单位委员会 </br> "
								+ sdf.format(new Date()) + "";
					} else {
						msg = msg + ",";
					}
				}
				result.setSuccess(true);
				result.setObj(msg);
				return result;

			} else {
				result.setSuccess(false);
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			return result;
		}
	}

	/**
	 * 获取政治生日消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/borth.json")
	@ResponseBody
	@AppLog
	public Result getBorth(HttpServletRequest request,String oid,String level) {
		Result result = new Result();
		try {
			Map<String,String>  map =new HashMap<String, String>();
			if(StringUtils.isNotEmpty(oid)){
				map.put("oid", oid);
			};
			if(StringUtils.isNotEmpty(level)){
				map.put("level", level);
			};
			List<BorthMsg> users = userService.getBorthUsers(map);
			if (users.size() > 0) {
				String msg = "";
				BorthMsg b;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				for (int i = 0; i < users.size(); i++) {
					b = users.get(i);
					msg += sdf.format(sdf2.parse(b.getIntime())) + ","
							+ b.getName() + "同志光荣地加入了中国共产党,今天是" + b.getName()
							+ "同志入党" + b.getAgeNum() + "周年纪念日";
					if (i == users.size() - 1 && users.size() != 1) {
						msg = msg
								+ ";感谢你们为地税事业的辛勤付出! 党组织向你们表示热烈的祝贺,希望你们立足岗位,牢记宗旨,坚定信念,不忘初心,继续前进! ";
					} else if (i == users.size() - 1 && users.size() == 1) {
						msg = msg
								+ ";感谢您为地税事业的辛勤付出! 党组织向您表示热烈的祝贺,希望您立足岗位,牢记宗旨,坚定信念,不忘初心,继续前进!";
					} else {
						msg = msg + ";<\n>";
					}
				}
				result.setSuccess(true);
				result.setObj(msg);
				return result;

			} else {
				result.setSuccess(false);
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			return result;
		}
	}

	/**
	 * 获取政治生日消息或者当月的政治生日
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/borthUsers.json")
	@ResponseBody
	@AppLog
	public Result getBorthUsers(HttpServletRequest request) {
		Result result = new Result();
		try {
			List<BorthMsg> usersByMonth = userService.getBorthUsersByMonth();
			result.setSuccess(true);
			result.setObj(usersByMonth);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			return result;
		}
	}

}
