package com.alphasta.controller.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.Config;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Dict;
import com.alphasta.model.Qrcode;
import com.alphasta.model.QrcodeUser;
import com.alphasta.model.Score;
import com.alphasta.model.User;
import com.alphasta.service.DictService;
import com.alphasta.service.QrcodeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
public class AppQrcodeController {

	@Autowired
	private QrcodeService qrcodeService;
	@Autowired
	private DictService dictService;
    /**
     * 扫码
     * @param request
     * @param qrcodeid
     * @param currentLon
     * @param currentLat
     * @param type
     * @return
     */
	@RequestMapping("/scanQrcode.json")
	@ResponseBody
	@AppLog
	public Result scanQrcode(HttpServletRequest request, String qrcodeid, String type) {
		User user = (User) request.getAttribute("appUser");
		Result result = new Result();
		if (qrcodeid != null && type != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qrcodeid", qrcodeid);
			map.put("user", user.getId().toString());
			map.put("date", new Date());
			List<QrcodeUser> list = qrcodeService.findHasQrcodeUsers(map); // 判断是否扫描过此二维码
			if (list.size() > 0) {
				result.setSuccess(false);
				result.setMsg("今天已成功扫描过此二维码");
				return result;
			}
			QrcodeUser qrcode = new QrcodeUser();
			qrcode.setId(GetIdUtil.getId());
			qrcode.setCtime(new Date());
			qrcode.setQrcodeid(qrcodeid);
			qrcode.setUser(user.getId().toString());

			long dictid = Long.valueOf(type).longValue();
			Dict dict = dictService.findDictById(dictid); // 查询字典表 获取积分值
			String value = dict.getValue();
			Score score = new Score();
			score.setCtime(new Date());
			score.setId(GetIdUtil.getId());
			score.setScoresource(qrcodeid);
			score.setScorevalue(value);
			score.setDescr("二维码<" + dict.getName() + ">");
			score.setUserid(user.getId().toString());
			score.setType(Integer.valueOf(dict.getDictId()));
			qrcodeService.insertQrcodeUser(qrcode, score);

			request.setAttribute("remark", "扫描二维码");
			request.setAttribute("openType", "0");
			result.setSuccess(true);
			return result;
		} else {
			request.setAttribute("remark", "扫描失败");
			request.setAttribute("openType", "0");
			result.setSuccess(false);
			result.setMsg("扫描失败");
			return result;
		}

	}
    /**
     * 活动列表
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
	@RequestMapping("/codelist.json")
	@ResponseBody
	@AppLog
	public Result codeList(HttpServletRequest request,Long oid,String level,
			String pageNum, String pageSize) {
		Result result = new Result();
		if (pageNum != null && pageSize != null) {
			int pagesize = Integer.parseInt(pageSize);
			int pagenum = Integer.parseInt(pageNum);
			Page<Qrcode> pagehelper = PageHelper.startPage(pagenum, pagesize);
			Qrcode qrcode=new Qrcode();
			if(oid!=null){
				qrcode.setOrganid(oid);
			}else{
				qrcode.setOrganid(1L);
			}
			if(level!=null){
				qrcode.setScanNum(level);
			}
			qrcodeService.findQrcodelist(qrcode);
			result.setSuccess(true);
			result.setObj(pagehelper.getResult());
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 活动签到人列表
	 * @param request
	 * @param qrcodeid
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/qrcode.json")
	@ResponseBody
	public Result getQrcodebyId(HttpServletRequest request,String qrcodeid,Integer pageSize,Integer pageNum){
		Result result=new Result();
	
		try {
			Page<QrcodeUser> pagehelper = PageHelper.startPage(pageNum, pageSize);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qrcodeid", qrcodeid);
			qrcodeService.findHasQrcodeUsers(map);		
			result.setObj(pagehelper.getResult()); 
			result.setMsg(Long.valueOf(pagehelper.getTotal()).toString());
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;		
	}
}
