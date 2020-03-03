package com.alphasta.controller.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.BatRun;
import com.alphasta.commons.utils.Config;
import com.alphasta.commons.utils.DesUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.commons.utils.PropertiesUtil;
import com.alphasta.model.MsgPut;
import com.alphasta.model.User;
import com.alphasta.service.MsgPutService;
import com.alphasta.service.UserService;

@Controller
@RequestMapping("/loginjm")
public class App_LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private MsgPutService msgService;

	/**
	 * 登陆验证
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@RequestMapping("/postlogin.json")
	@ResponseBody
	@AppLog
	public Object postlogin(HttpServletRequest request,
			HttpServletResponse response, String userName, String passWord) {
		DesUtil des = new DesUtil();
		PropertiesUtil p = new PropertiesUtil("/config/des.properties");
		String newowd = des.strDec(passWord, p.getProperty("key1"),
				p.getProperty("key2"), p.getProperty("key3"));
		String passWord1 = DigestUtils.md5Hex(newowd);
		// System.out.println(passWord1);
		try {
			User u = userService.findUserByLoginName(userName);

			if (u != null && u.getStatus() == 0) {
				if (userName.equals(u.getLoginname())
						&& passWord1.equals(u.getPassword())) {
					Date d = new Date();
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
					String sj = df.format(d);
					// 更新登录时间
					u.setLogintime(d);
					// 更新token的值
					int num = (int) ((Math.random() * 9 + 1) * 10000000);
					String str = "";
					u.setToken(u.getId() + sj + str.valueOf(num));
					userService.updateUser(u);
					Map<String, Object> map = new HashMap<String, Object>();
					User u1 = userService.findUserByLoginName(userName);
					String t = u1.getToken();
					map.put("token", t);
					String name = u1.getName();
					Long id = u1.getId();
					String ecode=u1.getEcode();
					map.put("userId", id);
					map.put("userName", name);
					map.put("ecode", ecode);
					String json = JSON.toJSONString(map);
					request.setAttribute("remark", "<用户:" + userName + "登录了>");
					request.setAttribute("liveness", "0");
					return json; // 用户名密码正确
				} else if (u != null && u.getStatus() == 1) {
					return Config.LOGIN_YZ_WYH;
				} else {
					request.setAttribute("remark", "<用户:" + userName + "登录失败!>");
					request.setAttribute("liveness", "0");
					return Config.LOGIN_YZ_PAWCW; // 2不正确
				}
			} else {
				return Config.LOGIN_YZ_WYH; // 3未找到该用户
			}
		} catch (Exception e) {
			e.printStackTrace();
			//执行批文件重启数据库
			Thread t=new Thread(){
				public void run(){
					String path="C:\\关闭.bat";
					 System.out.println("关闭数据库");
					     BatRun.runbat(path);
					 try {
						Thread.currentThread().sleep(20000);
						System.out.println("启动数据库");
						String path2="C:\\启动.bat";
						 BatRun.runbat(path2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 try {
						Thread.currentThread().sleep(20000);
						
						System.out.println("启动定时器");
						
						String path3="C:\\启动定时器.bat";
						BatRun.runbat(path3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			t.start();

			return "4"; // 异常返回
		}

	}

	/**
	 * 自动登录
	 * 
	 * @param token
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/zdlogin.json")
	@ResponseBody
	@AppLog
	public Object zdlogin(String token, HttpServletRequest request,
			HttpServletResponse response) {
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		df.format(d);
		// 获取7天前的时间
		Date dat = null;
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, -7);
		dat = cd.getTime();
		long dqsj = dat.getTime();
		if (token == null) {
			return Config.ZDLOGIN_STATE_BKY;// 不可以自动登录
		} else {
			User u = userService.getUserByToken(token);
			if (u == null) {
				// 没有该用户,需要前台跳转到登录页
				String json = JSON.toJSONString("w");
				return json;
			} else {
				// 判断token是否到期,到期后禁止自动登陆,并清空token时间
				long nowtime = u.getLogintime().getTime();
				if (nowtime < dqsj) {
					u.setToken("");
					userService.updateUser(u);
					return Config.ZDLOGIN_STATE_BKY;
				} else {
					// 更新登陆时间
					u.setLogintime(d);
					userService.updateUser(u);
					if (u.getToken() != null) {
						// System.out.println(u.getToken()+"1111111111111111111");
						request.setAttribute("remark", "<用户" + u.getName()
								+ "上线了>");
						request.setAttribute("liveness", "0");
						return Config.ZDLOGIN_STATE_KY; // 可以自动登录
					} else {
						return Config.ZDLOGIN_STATE_BKY;// 不可以自动登录
					}
				}
			}
		}
	}

	/**
	 * 退出登录
	 * 
	 * @param token
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/quitlogin.json")
	@ResponseBody
	public Result quitlogin(String token) {
		Result result = new Result();
		// 退出后清空token
		try {
			System.out.println("请求到了");
			User u = userService.getUserByToken(token);
			System.out.println("退出"+u);
			if (u != null) { 
				// 得到long类型当前时间
				long l = System.currentTimeMillis();
				// new日期对象
				Date date = new Date(l);
				// 转换提日期输出格式
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String str = dateFormat.format(date);
				try {
					date = dateFormat.parse(str);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				u.setLogintime(date);
				u.setToken("");
				userService.updateUser(u);
				result.setMsg("1");
			} else {
				result.setMsg("2");
			}
		} catch (Exception e) {
			result.setMsg("0");
		}
		return result;
	}

	/**
	 * 获取用户Cid 返回未读消息数
	 * 
	 * @param request
	 * @param response
	 * @param clientid
	 * @return
	 */
	@RequestMapping("/postCid.json")
	@ResponseBody
	@AppLog
	public Result postCid(HttpServletRequest request,
			HttpServletResponse response, String clientid, String token) {
		User user = userService.getUserByToken(token);
		request.setAttribute("openType", "2");
		request.setAttribute("liveness", "0");
		Result result = new Result();
		if (clientid != null) {
			user.setCid(clientid);
			userService.updateUserInfo(user);
			PageInfo pageInfo = new PageInfo();
			Map<String, Object> map = new HashMap<String, Object>();
			String userId = user.getId().toString();
			map.put("appUid", userId);
			map.put("hasread", "0");
			pageInfo.setCondition(map);
			List<MsgPut> list = msgService.getMsgPut(pageInfo);
			result.setSuccess(true);
			result.setObj(list.size());
			request.setAttribute("remark", "更新clientid成功");

		} else {
			result.setSuccess(false);
			request.setAttribute("remark", "更新clientid失败");
		}
		return result;
	}

	/**
	 * 获取未读信息数量
	 * 
	 * @param request
	 * @param token
	 * @return
	 */
	@RequestMapping("/msgnum.json")
	@ResponseBody
	@AppLog
	public Result findMsgMun(HttpServletRequest request, String token) {
		Result result = new Result();
		User user = userService.getUserByToken(token);
		PageInfo pageInfo = new PageInfo();
		String userId = user.getId().toString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appUid", userId);
		map.put("hasread", "0");
		pageInfo.setCondition(map);
		List<MsgPut> list = msgService.getMsgPut(pageInfo);
		result.setSuccess(true);
		result.setObj(list.size());
		request.setAttribute("remark", "获取未读消息数量");
		request.setAttribute("openType", "3");
		request.setAttribute("liveness", "0");
		return result;
	}

	/**
	 * 在线人数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/onlinenum.json")
	@ResponseBody
	public String getOnlineNum() {
		String num = userService.getOnlineNum();
		return num;
	}

}