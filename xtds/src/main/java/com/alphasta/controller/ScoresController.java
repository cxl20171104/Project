package com.alphasta.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.ExcelField;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.HssfUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.ExchangeMapper;
import com.alphasta.model.Article;
import com.alphasta.model.Exchange;
import com.alphasta.model.Gift;
import com.alphasta.model.Organization;
import com.alphasta.model.Score;
import com.alphasta.model.ScoreTj;
import com.alphasta.model.Statistics;
import com.alphasta.model.User;
import com.alphasta.service.ExchangeService;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.ScoreService;
import com.alphasta.service.UserService;
import com.gexin.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/score")
public class ScoresController extends BaseController {
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private ExchangeMapper exchangeMapper;
	/**
	 * 积分列表
	 * 
	 * @return
	 */
	@RequestMapping("/showpage")
	public ModelAndView getScorePage() {
		
		ModelAndView mav = new ModelAndView("/work/scoreList");
		Integer organizationId = getCurrentUser().getOrganizationId();
		Organization organization = organizationService.findOrganizationById(organizationId.longValue());
		String level=organization.getLevel();
		if(level!=null&&"2".equals(level)){
			if(organization.getCode().length()>4){
				organization  = organizationService.findOrganizationById(organization.getPid());
				if(organization.getCode().length()>4){
					organization  = organizationService.findOrganizationById(organization.getPid());
				};
			};
			mav.addObject("oid", organization.getId());//区县部门
		};
		mav.addObject("level", organization.getLevel());
		return mav;
	}

	/**
	 * 积分统计
	 * 
	 * @return
	 */
	@RequestMapping("/scoreSearch")
	public ModelAndView qrcodeSearch() {
		
		ModelAndView mav = new ModelAndView("/work/scoreSearch");
		Integer organizationId = getCurrentUser().getOrganizationId();
		Organization organization = organizationService.findOrganizationById(organizationId.longValue());
		String level=organization.getLevel();
		if(level!=null&&"2".equals(level)){
			if(organization.getCode().length()>4){
				organization  = organizationService.findOrganizationById(organization.getPid());
				if(organization.getCode().length()>4){
					organization  = organizationService.findOrganizationById(organization.getPid());
				};
			};
			mav.addObject("oid", organization.getId());//区县部门
		};
		mav.addObject("level", organization.getLevel());
		
		return mav;
	}

	/**
	 * 积分办法
	 * 
	 * @return
	 */
	@RequestMapping("/scoreAdd")
	public ModelAndView scoreAdd() {
		ModelAndView mav = new ModelAndView("/work/scoreAdd");
		Integer organizationId = getCurrentUser().getOrganizationId();
		Organization organization = organizationService.findOrganizationById(organizationId.longValue());
		String level=organization.getLevel();
		if(level!=null&&"2".equals(level)){
			if(organization.getCode().length()>4){
				organization  = organizationService.findOrganizationById(organization.getPid());
				if(organization.getCode().length()>4){
					organization  = organizationService.findOrganizationById(organization.getPid());
				};
			};
			mav.addObject("oid", organization.getId());//区县部门
		};
		mav.addObject("level", organization.getLevel());
		return mav;
	}

	/**
	 * 首页积分查询
	 * 
	 * @param page
	 * @param rows
	 * @param userName
	 * @param type
	 * @param organ
	 * @param users
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping("/scoreshow")
	@ResponseBody
	public PageInfo scoreshow(Integer page, Integer rows, String userName,String level,String oid,
			String type, String organ, String users, String sort, String order,
			String year) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (year == null || "".equals(year)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			year = sdf.format(new Date());
		};
		if(oid!=null&&!"".equals(oid)&&(organ==null||"".equals(organ))){
			organ=oid;
		};
		if(type==null||"".equals(type)){
			type="2";
		}
		PageInfo pageInfo = new PageInfo();
		if ("1".equals(organ) && (users == null || "".equals(users))) { // 党组织排名
			Page<Organization> pageh = PageHelper.startPage(page, rows);
			map.put("organ", organ);
			map.put("type", type);
			map.put("sort", sort);
			map.put("order", order);
			map.put("year", year);
			map.put("level", level);
			map.put("oid", oid);
			scoreService.getListByOrgan(map);
			pageInfo.setRows(pageh.getResult());
			pageInfo.setTotal(Integer.valueOf(Long.valueOf(pageh.getTotal())
					.toString()));
			return pageInfo;
		}
		;
		Page<Statistics> pageh = PageHelper.startPage(page, rows);
		if (organ != null && !"".equals(organ)
				&& (users == null || "".equals(users))) { // 某党组织下人员积分的排名 年度或者月度
			map.put("type", type);
			map.put("organ", organ);
			map.put("year", year);
			map.put("sort", sort);
			map.put("order", order);
			scoreService.getStateByType(map);
			pageInfo.setRows(pageh.getResult());
			pageInfo.setTotal(Integer.valueOf(Long.valueOf(pageh.getTotal())
					.toString()));
			return pageInfo;

		} else if (users != null && !"".equals(users)) { // 个人 年度或者月度
			map.put("type", type);
			map.put("users", users);
			map.put("year", year);
			map.put("sort", sort);
			map.put("order", order);
			scoreService.getStateByType(map);
			pageInfo.setRows(pageh.getResult());
			pageInfo.setTotal(Integer.valueOf(Long.valueOf(pageh.getTotal())
					.toString()));
			return pageInfo;

		} else if ((organ == null || "".equals(organ))
				&& (users == null || "".equals(users))) { // 所有用户排名 年度或者月度
			if (type == null) {
				type = "2"; // 默认是月度
			}
			map.put("type", type);
			map.put("year", year);
			map.put("sort", sort);
			map.put("order", order);
			map.put("level", level);
			scoreService.getStateByType(map);
			pageInfo.setRows(pageh.getResult());

			pageInfo.setTotal(Integer.valueOf(Long.valueOf(pageh.getTotal())
					.toString()));
			return pageInfo;

		}
		return pageInfo;
	}

	/**
	 * 第二页后的积分查询
	 * 
	 * @param page
	 * @param rows
	 * @param type
	 * @param organ
	 * @param users
	 * @param id
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping("/secordPage")
	@ResponseBody
	public PageInfo secondPage(Integer page, Integer rows, String type,
			String organ, String users, String id, String sort, String order,
			String ctime, Integer classType, String year) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo pageInfo = new PageInfo();
		if (year == null || "".equals(year)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			year = sdf.format(new Date());
		}
		if ("1".equals(type)) { // 首页是年度查询
			if ("1".equals(organ) && (users == null || "".equals(users))
					&& classType == 1) { // 没有选择人 首页是党组织年度 应加载 该组织当年1-12月度积分
											// 组织ID为id
				Page<Organization> pageh = PageHelper.startPage(page, rows);
				map.put("dept", id);
				map.put("type", "2");
				map.put("sort", sort);
				map.put("order", order);
				map.put("year", year);
				scoreService.getListByOrgan(map);
				pageInfo.setRows(pageh.getResult());
				pageInfo.setTotal(Integer.valueOf(Long
						.valueOf(pageh.getTotal()).toString()));
				return pageInfo;
			} else { // 人的年度 应加载人的当年1-12月度 用户Id为id
				Page<Statistics> pageh = PageHelper.startPage(page, rows);
				map.put("Months", "yes");
				map.put("type", "2");
				map.put("users", id);
				map.put("year", year);
				map.put("sort", sort);
				map.put("order", order);
				scoreService.getStateByType(map);
				List<Statistics> result = pageh.getResult();
				pageInfo.setRows(pageh.getResult());
				pageInfo.setTotal(Integer.valueOf(Long
						.valueOf(pageh.getTotal()).toString()));
				return pageInfo;
			}

		}
		;
		if ("2".equals(type)) {
			if ("1".equals(organ) && (users == null || "".equals(users))
					&& classType == 1) { // 部门选 局党委 每个部门的本月度积分 加载 部门所有人员的本月度积分
											// id为部门ID
				Page<Statistics> pageh = PageHelper.startPage(page, rows);

				map.put("type", type);
				map.put("organ", id);
				map.put("year", year);
				map.put("sort", sort);
				map.put("order", order);
				if (ctime != null && !"".equals(ctime)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Date date;
					try {
						date = sdf.parse(ctime);
						map.put("ctime", date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				scoreService.getStateByType(map);
				
				pageInfo.setRows(pageh.getResult());
				pageInfo.setTotal(Integer.valueOf(Long
						.valueOf(pageh.getTotal()).toString()));
				return pageInfo;
			} else { // 查看详情 加载所选个人本月积分详情 id为用户ID
				Page<Score> pageh = PageHelper.startPage(page, rows);
				map.put("user", id);
				map.put("sort", "SCOREVALUE");
				map.put("order", order);
				if (ctime != null && !"".equals(ctime)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Date date;
					try {
						date = sdf.parse(ctime);
						map.put("ctime", date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				scoreService.getUserScoresByMonth(map);
				List<Score> result0 = pageh.getResult();
				List<Score> result1=new ArrayList<Score>();
				Map<String,Object>data=new HashMap<String,Object>();
				for(Score s:result0) {
					if(data.get(s.getType()+"_VALUE")!=null){
						if(data.get(s.getType()+"_VALUE") instanceof String) {
							String value=(String)data.get(s.getType()+"_VALUE");
							data.put(s.getType()+"_VALUE", Integer.valueOf(value)+Integer.valueOf(s.getScores()));
						}else {
							Integer value=(Integer)data.get(s.getType()+"_VALUE");
							data.put(s.getType()+"_VALUE", value+Integer.valueOf(s.getScores()));
						}
						
					}else {
						data.put(s.getType()+"_VALUE", s.getScores());
						data.put(s.getType()+"_NAME",  s.getName());
						data.put(s.getType()+"_DESCR", s.getDescr());
						data.put(s.getType()+"_CTIME", s.getCtime());
						data.put(s.getType()+"_TYPE",  s.getType());
						
					}
				}
				Set<String> keySet = data.keySet();
				for(String key:keySet){
					if(key.endsWith("TYPE")) {
						Score s=new Score();
						if(data.get(key.replace("TYPE", "VALUE")) instanceof String){
							s.setScores((String)data.get(key.replace("TYPE", "VALUE")));
						}else {
							Integer i=(Integer)data.get(key.replace("TYPE", "VALUE"));
							s.setScores(i.toString());
						}
						
						s.setCtime((Date)data.get(key.replace("TYPE", "CTIME")));
						s.setDescr((String)data.get(key.replace("TYPE", "DESCR")));
						s.setName((String)data.get(key.replace("TYPE", "NAME")));
						result1.add(s);
					}
				}
				pageInfo.setRows(result1);
				pageInfo.setTotal(Integer.valueOf(Long.valueOf(pageh.getTotal()).toString()));
				return pageInfo;
			}
		}
		return pageInfo;
	}

	@RequestMapping("/downfile")
	public void scoreDown(HttpServletRequest request,
			HttpServletResponse response, String type, String organ,String level,
			String users, String sort, String order, String year)
			throws IllegalArgumentException, IOException,
			IllegalAccessException {
		// sort="scores";
		// order="desc";
		if (year == null || "".equals(year)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			year = sdf.format(new Date());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("1".equals(organ) && (users == null || "".equals(users))) { // 党组织排名
			map.put("organ", organ);
			map.put("type", type);
			map.put("year", year);
			map.put("sort", sort);
			map.put("order", order);
			map.put("level", level);
			List<Organization> Olist = scoreService.getListByOrgan(map);
			HssfUtil.writeExcel(response, "积分查询结果", creatExcels(Olist),
					ExcelField.class);
			return;
		}
		;
		List<Statistics> list = null;
		if (organ != null && !"".equals(organ)
				&& (users == null || "".equals(users))) { // 某党组织下人员积分的排名 年度或者月度
			map.put("type", type);
			map.put("organ", organ);
			map.put("year", year);
			map.put("sort", sort);
			map.put("order", order);
			list = scoreService.getStateByType(map);

		} else if (users != null && !"".equals(users)) { // 个人 年度或者月度
			map.put("type", type);
			map.put("users", users);
			map.put("year", year);
			map.put("sort", sort);
			map.put("order", order);
			list = scoreService.getStateByType(map);

		} else if ((organ == null || "".equals(organ))
				&& (users == null || "".equals(users))) { // 所有用户排名 年度或者月度
			if (type == null) {
				type = "2"; // 默认是月度
			}
			map.put("type", type);
			map.put("year", year);
			map.put("sort", sort);
			map.put("order", order);
			map.put("level", level);
			list = scoreService.getStateByType(map);
		}
		;
		HssfUtil.writeExcel(response, "积分查询结果", creatExcel(list),
				ExcelField.class);
	}

	public List<ExcelField> creatExcel(List<Statistics> list) {
		List<ExcelField> Excel = new ArrayList<ExcelField>();
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat msdf = new SimpleDateFormat("yyyy-MM");
		Integer i = 0;
		for (Statistics s : list) {
			i++;
			ExcelField e = new ExcelField();
			e.setId(i + "");
			e.setScores(s.getScores());
			e.setName(s.getName());
			if (s.getType() == 1) {
				e.setType("年度积分");
				e.setCtime(ysdf.format(s.getCtime()));

			}
			;
			if (s.getType() == 2) {
				e.setType("月度积分");
				e.setCtime(msdf.format(s.getCtime()));
			}
			Excel.add(e);
		}
		return Excel;

	}

	public List<ExcelField> creatExcels(List<Organization> list) {
		List<ExcelField> Excel = new ArrayList<ExcelField>();
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat msdf = new SimpleDateFormat("yyyy-MM");
		Integer i = 0;
		for (Organization s : list) {
			i++;
			ExcelField e = new ExcelField();
			e.setId(i + "");
			e.setSum(s.getSum());
			e.setScores(s.getScores());
			e.setName(s.getName());
			if (s.getType() == 1) {
				e.setType("年度积分");
				e.setCtime(s.getCtime().substring(0, 4));

			}
			;
			if (s.getType() == 2) {
				e.setType("月度积分");
				e.setCtime(s.getCtime().substring(0, 4));
			}
			
			Excel.add(e);
		}
		return Excel;

	}

	/**
	 * 用户积分列表
	 * 
	 * @param page
	 * @param rows
	 * @param userName
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo getScoreList(Integer page, Integer rows, String userName,String level,String oid,
			String nd, String yf, String sort, String order) {
		PageInfo pageInfo = new PageInfo();
		if (StringUtils.isNotEmpty(nd) || StringUtils.isNotEmpty(yf)) {

			Page<Statistics> pageh = PageHelper.startPage(page, rows);
			Map<String, String> map = new HashMap<String, String>();
			if (StringUtils.isNotEmpty(userName)) {
				map.put("userName", userName);
			}
			if (StringUtils.isNotEmpty(nd)) {
				map.put("nd", nd);
			}
			if (StringUtils.isNotEmpty(yf)) {
				map.put("yf", yf);
			}
			if (StringUtils.isNotEmpty(sort)) {
				map.put("sort", sort);
			}
			if (StringUtils.isNotEmpty(order)) {
				map.put("order", order);
			}
			if(StringUtils.isNoneEmpty(level)){
				map.put("level", level);
			}
			if(StringUtils.isNoneEmpty(oid)){
				map.put("oid", oid);
			}
			scoreService.getAllStatis(map);
			pageInfo.setRows(pageh.getResult());
			pageInfo.setTotal(Integer.valueOf(Long.valueOf(pageh.getTotal())
					.toString()));
			return pageInfo;
		} else {
			@SuppressWarnings("unchecked")
			Page<User> pageh = PageHelper.startPage(page, rows);
			Map<String, String> map = new HashMap<String, String>();
			if (StringUtils.isNotEmpty(userName)) {
				map.put("userName", userName);
			}
			if (StringUtils.isNotEmpty(sort)) {
				map.put("sort", "liveness");
			}
			if (StringUtils.isNotEmpty(order)) {
				map.put("order", order);
			}
			if(StringUtils.isNoneEmpty(level)){
				map.put("level", level);
			}
			if(StringUtils.isNoneEmpty(oid)){
				map.put("oid", oid);
			}
			userService.getUsers(map);
			List<User> list = pageh.getResult();
			for (User u : list) {
				u.setScores(u.getLiveness());
				u.setCtime(new Date());
			}
			pageInfo.setRows(list);
			pageInfo.setTotal(Integer.valueOf(Long.valueOf(pageh.getTotal())
					.toString()));
			return pageInfo;
		}

	}

	/**
	 * 个人积分详情
	 * 
	 * @param page
	 * @param rows
	 * @param userId
	 * @return
	 */
	@RequestMapping("/scoreList")
	@ResponseBody
	public PageInfo getUserScoreList(Integer page, Integer rows, String userId,
			String nd, String yf) {
		Page<Score> pageh = PageHelper.startPage(page, rows);
		PageInfo pageInfo = new PageInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		if (StringUtils.isNotEmpty(nd)) {
			map.put("nd", nd);
		}
		if (StringUtils.isNotEmpty(yf)) {
			map.put("yf", yf);
		}
		pageInfo.setCondition(map);
		scoreService.getScoreList(pageInfo);
		pageInfo.setRows(pageh.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pageh.getTotal())
				.toString()));
		return pageInfo;
	}

	/**
	 * 添加积分
	 * 
	 * @param score
	 * @return
	 */
	@RequestMapping("/addScoreByDict")
	@ResponseBody
	public Result insertScore(Score score) {
		try {
			score.setId(GetIdUtil.getId());
			score.setCtime(new Date());
			score.setType(6);
			scoreService.addScore(score);
			Result re = new Result();
			re.setSuccess(true);
			re.setMsg("获取奖励积分成功!");
			return re;
		} catch (Exception e) {
			e.printStackTrace();
			Result re = new Result();
			re.setSuccess(true);
			re.setMsg("获取奖励积分失败!");
			return re;
		}
	}
	
	@RequestMapping("/export")
	public String export() {
		
		return "/score/exportPage";
	}
	@RequestMapping("/tj")
	@ResponseBody
	public Result tj(ScoreTj scoreTj) {
		Map<String, List<Score>> scoreTj2 = scoreService.scoreTj(scoreTj);
		Result result=new Result();
		result.setObj(scoreTj2);
		result.setSuccess(true);
		return result;
	}
	@RequestMapping("/exchangePage")
	public String exchangePage() {
		return "/score/exchangePage";
	}
	@RequestMapping("/exchangeData")
	@ResponseBody
	public Object exchangeData(Exchange exchange, Integer page, Integer rows,String sort, String order) {
		
		PageInfo pageInfo = new PageInfo(page, rows,sort,order);
		Map<String, Object> condition = new HashMap<String, Object>();

		if (StringUtils.isNoneBlank(exchange.getUserId())) {
			condition.put("userId", exchange.getUserId());
		}
		
		if (exchange.getStartTime() != null) {
			condition.put("startTime", exchange.getStartTime());
		}
		if (exchange.getEndTime() != null) {
			condition.put("endTime", exchange.getEndTime());
		}
		
		
		pageInfo.setCondition(condition);
		exchangeService.findDataGrid(pageInfo);
		return pageInfo;
		
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
