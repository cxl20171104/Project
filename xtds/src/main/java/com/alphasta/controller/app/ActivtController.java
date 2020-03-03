package com.alphasta.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Qrcode;
import com.alphasta.service.DictService;
import com.alphasta.service.QrcodeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Controller
public class ActivtController {
	@Autowired
	private QrcodeService qrcodeService;
	@Autowired
	private DictService dictService;
	
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
	@RequestMapping("/qrcodelist.json")
	@ResponseBody
	public Result getQrcodeList(Qrcode qrcode, Integer pageSize, Integer pageNum,
			String sort, String order) {
		Result result = new Result();
		try {
			Page<Qrcode> pagehelper = PageHelper.startPage(pageNum, pageSize);
			qrcodeService.findQrcodelist(qrcode);
			result.setObj(pagehelper.getResult());
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

}
