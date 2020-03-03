package com.alphasta.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.ExcelField;
import com.alphasta.commons.result.QrcodeExcel;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.CreatTwoDecemensionCodeImageUtil;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.HssfUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Activities;
import com.alphasta.model.Dict;
import com.alphasta.model.Organization;
import com.alphasta.model.Qrcode;
import com.alphasta.model.QrcodeUser;
import com.alphasta.model.Score;
import com.alphasta.model.User;
import com.alphasta.service.ActivitiesService;
import com.alphasta.service.DictService;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.QrcodeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/qrcode")
public class QrcodeController extends BaseController {
	@Autowired
	private QrcodeService qrcodeService;
	@Autowired
	private DictService dictService;
	@Autowired
	private OrganizationService organizationService;

	@RequestMapping("/toQrcodePage")
	public ModelAndView getQrcodeListPage() {
		
		ModelAndView result = new ModelAndView("/work/qrcodeList");
		Integer organizationId = getCurrentUser().getOrganizationId();
		Organization organization = organizationService.findOrganizationById(organizationId.longValue());
		if(organization.getCode().length()>4){
			organization  = organizationService.findOrganizationById(organization.getPid());
			if(organization.getCode().length()>4){
				organization  = organizationService.findOrganizationById(organization.getPid());
			};
		};
		result.addObject("oid", organization.getId());
		return result;
	}

	/**
	 * 二维码列表
	 * 
	 * @param qrcode
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping("/qrcodelist")
	@ResponseBody
	public PageInfo getQrcodeList(Qrcode qrcode, Integer page, Integer rows,
			String sort, String order) {
		@SuppressWarnings("unchecked")
		Page<Qrcode> pagehelper = PageHelper.startPage(page, rows);
		qrcodeService.findQrcodelist(qrcode);
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		pageInfo.setRows(pagehelper.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));
		return pageInfo;
	}

	/**
	 * 添加二维码
	 * 
	 * @param qrcode
	 * @param request
	 * @return
	 */
	@RequestMapping("/addQrcode")
	@ResponseBody
	public Result newQrcode(Qrcode qrcode, HttpServletRequest request) {
		String id = GetIdUtil.getId();
		qrcode.setId(id);
		qrcode.setCtime(new Date());
		qrcode.setUser(getUserId().toString());
		try {
			CreatTwoDecemensionCodeImageUtil creatQrcode = new CreatTwoDecemensionCodeImageUtil();
			String basePath = request.getServletContext().getRealPath("/files")
					+ "//";
			String url = "static/qrcodeImg/" + qrcode.getId() + ".jpg";
			String path = basePath + url;
			String info = qrcode.getInfo();
			String type = qrcode.getType();
			Date etime = qrcode.getEtime();
			String etimeStr = "";
			if (etime != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				etimeStr = sdf.format(etime);
			}
			String content = "{\"info\":\"" + info + "\",\"type\":\"" + type
					+ "\",\"id\":\"" + id + "\",\"etime\":\"" + etimeStr
					+ "\"}";
			creatQrcode.encoderQRCode(content, path, "jpg", 8);
			qrcode.setUrl(url);
			qrcodeService.insertQrcode(qrcode);
			return (Result) renderSuccess("保存成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return (Result) renderError("操作失败");
		}

	}

	/**
	 * 二维码扫描人列表
	 * 
	 * @param qrcodeid
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping("/qrusers")
	@ResponseBody
	public PageInfo getHasQrcodeUsers(String qrcodeid, Integer page,
			Integer rows, String sort, String order) {
		Page<QrcodeUser> pagehelper = PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qrcodeid", qrcodeid);
		qrcodeService.findHasQrcodeUsers(map);
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		pageInfo.setRows(pagehelper.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));
		return pageInfo;

	}

	/**
	 * 签到方式选择
	 * 
	 * @return
	 */
	@RequestMapping("/qrcodetype")
	@ResponseBody
	public List<Dict> findQRcodetype() {
		Dict dict = new Dict();
		dict.setDictPid("04");
		List<Dict> list = dictService.findDictByDictPid(dict);
		return list;
	}

	/**
	 * 下载二维码
	 * 
	 * @param url
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downfile")
	public void downFile(Qrcode qrcode, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("utf-8");
		String url = qrcodeService.findQrcodelist(qrcode).get(0).getUrl();
		String basePath = request.getServletContext().getRealPath("/files")
				+ "//";
		url = basePath + url;
		try {
			int beginIndex = url.lastIndexOf("\\");
			String fileName = url.substring(beginIndex + 1);
			// 下载本地文件
			// 读到流中
			InputStream inStream = new FileInputStream(url);// 文件的存放路径
			// 设置输出的格式
			response.reset();
			response.setContentType("bin");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			// 循环取出流中的数据
			byte[] b = new byte[1024];
			int len;
			while ((len = inStream.read(b)) > 0) {
				response.getOutputStream().write(b, 0, len);
			}
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 导出扫码结果
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/outResult")
	public void outResult(String id, String info, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qrcodeid", id);
		List<QrcodeUser> users = qrcodeService.findHasQrcodeUsers(map);
		try {
			HssfUtil.writeExcel(response, info, creatQrExcel(users),
					QrcodeExcel.class);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	};
	/**
	 * 
	 * @param request
	 * @param qrcodeid
	 * @param type
	 * @return
	 */
	@RequestMapping("/addusers")
	@ResponseBody
	public Result scanQrcode(HttpServletRequest request, String qrcodeid,String dictid,String users,String info) {
		User user =getCurrentUser();
		Result result = new Result();
		try {
			if (qrcodeid != null && dictid != null) {			
				long dictv = Long.valueOf(dictid).longValue();
				Dict dict = dictService.findDictById(dictv); // 查询字典表 获取积分值
				String value = dict.getValue();
				String[] strs = users.split(",");
				String hasScan="";
				for(int i=0;i<strs.length;i++){
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("qrcodeid", qrcodeid);
							map.put("user", strs[i]);
							List<QrcodeUser> list = qrcodeService.findHasQrcodeUsers(map); // 判断是否扫描过此二维码
							if (list.size() > 0) {
								hasScan+=list.get(0).getName()+",";
								continue;
							};
							QrcodeUser qrcode = new QrcodeUser();
							qrcode.setId(GetIdUtil.getId());
							qrcode.setCtime(new Date());
							qrcode.setQrcodeid(qrcodeid);
							qrcode.setUser(strs[i]);
							
							Score score = new Score();
							score.setCtime(new Date());
							score.setId(GetIdUtil.getId());
							score.setScoresource(qrcodeid);
							score.setScorevalue(value);
							score.setDescr("活动签到<" +info+ ">");
							score.setUserid(strs[i]);
							score.setType(Integer.valueOf(dict.getDictId()));
							qrcodeService.insertQrcodeUser(qrcode, score);
			
							
						} 
						result.setSuccess(true);
						if(hasScan.length()>1){
							result.setMsg("签到成功! 其中"+hasScan+"已经签到过");
						}else{
							result.setMsg("签到成功");
						}
						
						return result;
				}else {
				
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.setSuccess(false);
		result.setMsg("添加失败!");
		return result;
		

	}

	public List<QrcodeExcel> creatQrExcel(List<QrcodeUser> users) {
		List<QrcodeExcel> qs = new ArrayList<QrcodeExcel>();
		for (QrcodeUser u : users) {
			QrcodeExcel q = new QrcodeExcel(u.getName(), u.getOname(),
					u.getCtime());
			qs.add(q);
		}
		return qs;

	}
   
}
