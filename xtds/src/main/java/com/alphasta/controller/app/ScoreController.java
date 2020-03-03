package com.alphasta.controller.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.app.AppController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.GetuiUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.ExchangeMapper;
import com.alphasta.mapper.GiftMapper;
import com.alphasta.model.Article;
import com.alphasta.model.Book;
import com.alphasta.model.Dict;
import com.alphasta.model.Exchange;
import com.alphasta.model.Gift;
import com.alphasta.model.Organization;
import com.alphasta.model.Score;
import com.alphasta.model.User;
import com.alphasta.service.DictService;
import com.alphasta.service.ExchangeService;
import com.alphasta.service.GiftService;
import com.alphasta.service.ScoreService;
import com.alphasta.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 积分查询
 * 
 * @author sjb
 * 
 */
@Controller
public class ScoreController {
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private UserService userService;
	@Autowired
	private DictService dictService;
	@Autowired
	private GiftService giftService;
	@Autowired
	private GiftMapper giftMapper;
	@Autowired
	private ExchangeMapper exchangeMapper;
	@Autowired
	private ExchangeService exchangeService;
	/**
	 * 个人积分量
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/myScores.json")
	@ResponseBody
	@AppLog
	public Result getMyScore(HttpServletRequest request) {
		Result result = new Result();
		User user = (User) request.getAttribute("appUser");
		if (user != null) {
			String userId = user.getId().toString();
			result.setSuccess(true);
			result.setObj(scoreService.getMyScore(userId));
			request.setAttribute("remark", "查询用户积分数");
			request.setAttribute("openType", "3");
		} else {
			result.setSuccess(false);
			result.setObj("0");
			request.setAttribute("remark", "查询用户积分数失败");
			request.setAttribute("openType", "3");
		}
		return result;
	}

	/**
	 * 个人积分明细
	 * 
	 * @param userId
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/scoreList.json")
	@ResponseBody
	@AppLog
	public Result getScoreList(String pageSize, String pageNum,
			HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo();
		Result result = new Result();
		User user = (User) request.getAttribute("appUser");
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageSize != null && pageNum != null) {

			try {
				if (user != null) {
					String userId = user.getId().toString();
					map.put("userId", userId);
					pageInfo.setCondition(map);
					int from = (Integer.parseInt(pageNum) - 1)
							* Integer.parseInt(pageSize);
					int size = Integer.parseInt(pageSize);
					if (from > -1 && size > -1) {
						pageInfo.setFrom(from);
						pageInfo.setSize(size);
					}
					// Page<Score> page=PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
					List<Score> list = scoreService.getScoreList(pageInfo);
					result.setSuccess(true);
					if (list.size() == 0) {
						result.setMsg("over");
					} else {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						String d = "";
						for (Score s : list) {
							d = sdf.format((s.getCtime()));
							s.setCtimeStr(d);
						}
						result.setObj(list);
					}
					request.setAttribute("remark", "查询个人积分明细");
					request.setAttribute("openType", "3");
				} else {
					result.setSuccess(false);
					request.setAttribute("remark", "查询个人积分明细失败");
					request.setAttribute("openType", "3");
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
				request.setAttribute("remark", "查询个人积分明细失败");
				request.setAttribute("openType", "3");
			}
		}
		;
		return result;
	}

	/**
	 * 
	 * @param pageSize
	 * @param pageNum
	 * @param request
	 * @param type  1  年度  2  月度
	 * @param level 1 市局   2  区县
	 * @return
	 */
	@RequestMapping("/useScores.json")
	@ResponseBody
	@AppLog(openType = "3", remark = "查看积分名次")
	public Result getuserScore(String pageSize, String pageNum,
			HttpServletRequest request,String type,String level,String oid) {
		Result result = new Result();
		try {
			/*User user = (User) request.getAttribute("appUser");
			if ("1".equals(pageNum)) {
				User u = userService.getPM(user.getId().toString(),type);
				String msg = "您当前积分排名为第" + u.getEcode() + "名，积分值为："
						+ u.getLiveness();
				result.setMsg(msg);
			}
			;*/
			if (pageSize != null && pageNum != null) {
				Page<User> pageh = PageHelper.startPage(
						Integer.parseInt(pageNum), Integer.parseInt(pageSize));
				userService.getNdPm(type,level,oid);
				List<User> list = pageh.getResult();
				if (list.size() == 0) {
					result.setMsg("over");
				}
				result.setSuccess(true);
				result.setObj(list);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.setSuccess(false);
		return result;
	}

	/**
	 * 部门排名
	 * @param request
	 * @param type 1 niandu 2 yuedu
	 * @param level 1 市局  2 区县
	 * @return
	 */
	@RequestMapping("/organScores.json")
	@ResponseBody
	@AppLog(openType = "3", remark = "查看部门名次")
	public Result getScoreLIstByOrgan(HttpServletRequest request,String type,String level) {
		Result result = new Result();
		try {
			List<Organization> list = userService.getNdpmByOrgan(type,level);
			ListIterator<Organization> it = list.listIterator();
			while (it.hasNext()) {
				Organization o = it.next();
				if (o.getId() == 1L) {
					list.remove(o);
				}
			}
			result.setSuccess(true);
			result.setObj(list);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("网络繁忙,稍后再试!");
		}
		return result;
	}
	/**
	 * 个人流动红旗
	 * @return
	 */
	@RequestMapping("/grldhq.json")
	@ResponseBody
	public Result getGrldhq(String level,String time,String oid){
		Result result = new Result();
		if(time==null||"null".equals(time)){  //月累计   显示区县的累计
			result.setObj(userService.grldhqList(level,oid));
		}else{  //单月
			Page<User> pageh = PageHelper.startPage(1, 10);
			userService.grldhqpm(level, time,oid);
			result.setObj(pageh.getResult());
		}
		result.setSuccess(true);
		return result;
	}
	/**
	 * 集体流动红旗
	 * @return
	 * @param time 月份
	 * @param level 1 市局  2 区县
	 */
	@RequestMapping("/dwldhq.json")
	@ResponseBody
	public Result getDwldhq(String level,String time){
		Result result = new Result();
		if(time==null||"null".equals(time)){
			result.setObj(userService.dwldhqList(level));
		}else{
			Page<Organization> pageh = PageHelper.startPage(1, 2);
			userService.dwldhqpm(level, time);
			result.setObj(pageh.getResult());
		}
		result.setSuccess(true);
		return result;
	}
	@RequestMapping("/scoreRegulation.json")
	@ResponseBody
	public Result scoreRegulation(){
		Result result = new Result();
		Dict dict = new Dict();
		dict.setDictPid("05"); //积分办法
		dict.setDictId(null);
		List<Dict> list = dictService.findDictByDictPid(dict);
		result.setObj(list);
		System.out.println(list);
		return result;
	}
	@RequestMapping("scoreParticulars")
	@ResponseBody
	public Result scoreParticulars(String dict){
		Result result = new Result();
		Dict dicts = new Dict();
		dicts.setDictPid(dict);
		dicts.setDictId(null);
		List<Dict> list = dictService.findDictByDictPid(dicts);
		System.out.println(list);
		result.setObj(list);
		return result;
	}
	
	
	/**
	 * 用户总分排名
	 */
	@RequestMapping("/zongji.json")
	@ResponseBody
	public Result zongji(String pageSize, String pageNum,HttpServletRequest request,String type,String level,String oid){
		Result result = new Result();
		//查询该支部下的所有人
		try {
			if (pageSize != null && pageNum != null) {
				Page<User> pageh = PageHelper.startPage(
						Integer.parseInt(pageNum), Integer.parseInt(pageSize));
				userService.getZongji(level,type,oid);
				List<User> list = pageh.getResult();
				if (list.size() == 0) {
					result.setMsg("over");
				}
				result.setSuccess(true);
				result.setObj(list);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.setSuccess(false);
		return result;
	}
	
	
	
	/**
	 * 部门总分排名
	 * @param request
	 * @param type 1 niandu 2 yuedu
	 * @param level 1 市局  2 区县
	 * @return
	 */
	@RequestMapping("/organZong.json")
	@ResponseBody
	@AppLog(openType = "3", remark = "查看部门名次")
	public Result getScoreLIstByOrgan(HttpServletRequest request,String level) {
		Result result = new Result();
		
			List<Organization> list = userService.getOzon(level);
			ListIterator<Organization> it = list.listIterator();
			while (it.hasNext()) {
				Organization o = it.next();
				if (o.getId() == 1L) {
					list.remove(o);
				}
			}
			result.setSuccess(true);
			result.setObj(list);
		
		return result;
	}
	
	@RequestMapping("/giftList.json")
	@ResponseBody
	public Result redeemList(Integer pageSize, Integer pageNum,HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(pageNum, pageSize, "startTime", "desc");
		Map<String,Object>map=new HashMap<String,Object>();
		pageInfo.setCondition(map);
		giftService.findDataGrid(pageInfo);
		Result result=new Result();
		List<Gift>list=pageInfo.getRows();
		if (pageInfo.getRows().size() == 0) {
			result.setMsg("over");
		}
		String bathUrl = FileUtils.getBathUrl(request);
		for (Gift gift : list) {
			if (gift.getPic()!= null && !"".equals(gift.getPic())) {
				gift.setPic(bathUrl + gift.getPic());
			}
			gift.setStartTime(gift.getStartTime().substring(0, 10));
			gift.setEndTime(gift.getEndTime().substring(0, 10));
		}
		result.setSuccess(true);
		result.setObj(list);
		return result;
	}
	
	@RequestMapping("/exchange")
	@ResponseBody
	public Result exchange(String giftId,HttpServletRequest request) {
		//信息实体类
		Result result=new Result();
		//获取请求用户
		User user = (User) request.getAttribute("appUser");
		//获取礼品信息
		Gift gift = giftMapper.findGiftByid(giftId);
		
		Integer newScore=Integer.valueOf(user.getPhone2())-Integer.valueOf(gift.getScore());
		if(newScore>0) {
			//修改用户可用积分（phone1）
			user.setPhone2(newScore.toString());
			userService.updateUser(user);
			//创建兑换凭据
			Exchange exchange=new Exchange();
			exchange.setId(GetIdUtil.getId());
			exchange.setCreaTime(new Date());
			exchange.setGiftName(gift.getName());
			exchange.setGiftPic(gift.getPic());
			exchange.setUserId(String.valueOf(user.getId()));
			exchange.setGiftId(gift.getId());
			exchangeMapper.addExchange(exchange);
			result.setMsg("兑换成功！剩余积分："+newScore);
			result.setSuccess(true);
		}else {
			result.setMsg("你的积分不足！你的积分："+user.getPhone1()+"需要积分："+gift.getScore());
			result.setSuccess(true);
		}
		
		return result;
		
	}
	@RequestMapping("/exchangeList")
	@ResponseBody
	public Result exchangeList(Integer pageSize, Integer pageNum,HttpServletRequest request) {
		User user = (User) request.getAttribute("appUser");
		PageInfo pageInfo = new PageInfo(pageNum, pageSize, "creaTime", "desc");
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("userId", user.getId());
		pageInfo.setCondition(map);
		exchangeService.findDataGrid(pageInfo);
		Result result=new Result();
		List<Exchange>list=pageInfo.getRows();
		if (pageInfo.getRows().size() == 0) {
			result.setMsg("over");
		}
		String bathUrl = FileUtils.getBathUrl(request);
		for (Exchange exchange : list) {
			if (exchange.getGiftPic()!= null && !"".equals(exchange.getGiftPic())) {
				exchange.setGiftPic(bathUrl + exchange.getGiftPic());
			}
		}
		result.setSuccess(true);
		result.setObj(list);
		return result;
		
		
	}
	@RequestMapping("/exchangeDelete")
	@ResponseBody
	public Result exchangeDelete(String exchangeId){
		exchangeMapper.deleteExchange(exchangeId);
		Result result=new Result();
		result.setMsg("凭证删除成功！");
		result.setSuccess(true);
		return result;
	}
}
