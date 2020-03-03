package com.alphasta.controller.app;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.app.AppController;
import com.alphasta.commons.result.CommentNum;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.Config;
import com.alphasta.commons.utils.CreatTwoDecemensionCodeImageUtil;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.model.Organization;
import com.alphasta.model.Score;
import com.alphasta.model.User;
import com.alphasta.service.CommentService;
import com.alphasta.service.DictService;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.ScoreService;
import com.alphasta.service.UserService;
import com.sun.tools.javac.util.List;

@Controller
public class AppUserController extends AppController {

	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private CommentService commentService;
	@Autowired
	public DictService dictService;
	@Autowired
	public ScoreService scoreService;
	/**
	 * 获取用户的信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public User getUserInfo(HttpServletRequest request,
			HttpServletResponse response, String json) {
		User user = (User) request.getAttribute("appUser");
		if (user.getQrcode() == null || "".equals(user.getQrcode())) {
			// 生成二维码
			CreatTwoDecemensionCodeImageUtil creatQrcode = new CreatTwoDecemensionCodeImageUtil();
			String path = request.getServletContext().getRealPath("/files")
					+ "/static/qrcodeImg/" + user.getId().toString() + ".jpg";
			String content = DigestUtils.md5Hex(user.getId().toString());
			creatQrcode.encoderQRCode(content, path, "jpg", 4);
			user.setQrcode("static/qrcodeImg/" + user.getId().toString()
					+ ".jpg");
			userService.updateUserInfo(user);
		};
		String organName;
		Organization o = organizationService.findOrganizationById(user
				.getOrganizationId().longValue());
		
		if (o.getCode().length() != 4) {
			 o = organizationService.findOrganizationById(
						o.getPid().longValue());
			 if (o.getCode().length() != 4) {
				 o = organizationService.findOrganizationById(
							o.getPid().longValue());
				 
			};			 
		};
		organName=o.getName();
		user.setEcode(o.getLevel());
		user.setOrganName(organName);
		user.setOrganizationId(Integer.valueOf(String.valueOf(o.getId())));
		user.setPhone2(o.getGrade());
		CommentNum num = commentService.commentByuserid(user.getId());
		if(num!=null){
			user.setCommentNum(num);
		}else{
			user.setCommentNum(new CommentNum("0","0","0"));
		}
		user.setOrganName(organName);
		request.setAttribute("remark", "查看个人信息");
		request.setAttribute("openType", "3");
		return user;
	}

	/**
	 * 用户修改资料
	 */
	public Result editUserInfo(HttpServletRequest request,
			HttpServletResponse response, String json) {
		User user = (User) request.getAttribute("appUser");
		JSONObject jsonObject = JSONObject.parseObject(json);
		String paramName = jsonObject.get("id").toString();
		String newValue = jsonObject.get("value").toString();

		request.setAttribute("openType", "2");
		if (paramName.equals("email")) {
			user.setEmail(newValue);
			request.setAttribute("remark", "更换了邮箱");
		}
		if (paramName.equals("phone")) {
			user.setPhone(newValue);
			request.setAttribute("remark", "更换了手机号");
		}
		//签到
		if (paramName.equals("phone1")) {
			user.setPhone1(newValue);
			//用户签到后要加积分
			user.setLiveness(null);
			user.setPhone2(null);
			String scoreNum = dictService.findDictById(89L).getValue();
			Score score = new Score();
			score.setCtime(new Date());
			score.setId(GetIdUtil.getId());
			score.setScoresource("");
			score.setScorevalue(scoreNum);
			score.setDescr("App签到");			
			score.setUserid(user.getId().toString());
			score.setType(Config.QIAN_DAO); // 发布文章
			scoreService.addScore(score); // 给予发布文章的积分
			request.setAttribute("remark", "用户签到");
		}
		if (paramName.equals("motto")) {
			user.setMotto(newValue);
			request.setAttribute("remark", "更换了座右铭");
		}
		if (paramName.equals("headpic")) {
			user.setHeadpic(newValue);
			request.setAttribute("remark", "更换了头像图片");
		}
		if (paramName.equals("loginname")) {
			user.setLoginname(newValue);
			request.setAttribute("remark", "更换了登录名");
		}
		
		Result result = new Result();
		try {
			userService.updateUserInfo(user);
			result.setMsg("修改成功");
			result.setSuccess(true);
		} catch (Exception e) {
			request.setAttribute("remark", "修改个人资料失败");
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 用户修改密码
	 * 
	 * @param request
	 * @param response
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	public Result changePwd(HttpServletRequest request,
			HttpServletResponse response, String json) {
		Result result = new Result();
		try {
			JSONObject jsonObject = JSONObject.parseObject(json);
			String oldPwd = jsonObject.get("oldPwd").toString();
			String newPwd = jsonObject.get("newPwd").toString();
			User user = (User) request.getAttribute("appUser");
			request.setAttribute("openType", "2");
			if (!user.getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
				result.setSuccess(false);
				result.setMsg("旧密码输入错误");
				request.setAttribute("remark", "修改登录密码失败");
			} else {
				userService.updateUserPwdById(user.getId(),
						DigestUtils.md5Hex(newPwd));
				request.setAttribute("remark", "修改登录密码");
				result.setSuccess(true);
				result.setMsg("修改完成");
			}

			return result;

		} catch (Exception e) {
			result.setSuccess(false);
			return result;
		}

	}
	
	/**
	 * 
	 * 是否可以签收
	 */
	public Result isCanTask(HttpServletRequest request,
			HttpServletResponse response,String json){
		Result result = new Result();
		JSONObject jsonObject = JSONObject.parseObject(json);
		String passWord = jsonObject.get("passWord").toString();
		String organId = jsonObject.get("organId").toString();
		//查询到当前部门管理员
				Organization o = organizationService.findOrganizationById(Long.valueOf(organId));
				//转换成管理员下的该部门
				o=organizationService.findOrganByAddress(String.valueOf(o.getId()));
				//找到管理员
				java.util.List<User> ul = userService.getUsersByOrgId(o.getId());
				//
				if (ul!=null&&ul.size()>0){
					
					User u=ul.get(0);
					System.out.println(DigestUtils.md5Hex(u.getPassword()));
					if (u.getPassword().equals(DigestUtils.md5Hex(passWord))) {
						System.out.println("密码正确");
						result.setSuccess(true);
					}else{
						
        				System.out.println("密码错误");
						result.setSuccess(false);
					}
				}
		return result;
		
	}
	
	
}
