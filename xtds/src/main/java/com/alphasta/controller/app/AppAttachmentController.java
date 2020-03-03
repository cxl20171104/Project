package com.alphasta.controller.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.utils.Config;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.service.AttachmentService;

@Controller
public class AppAttachmentController {
	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 首页图片 (没用上)
	 * 
	 * @param request
	 * @param cataId
	 * @return
	 */
	@RequestMapping("/indexImg")
	@ResponseBody
	@AppLog
	public List<String> getIndexImg(HttpServletRequest request, String cataId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (cataId != null) {
			map.put("owner", cataId);
			map.put("type", Config.ATTACHMENT_TYPE_CATa);
			map.put("filetype", Config.ATTACHMENT_FILLTYPE_IMG);
		}
		String bath = FileUtils.getBathUrl(request);
		List<String> list = attachmentService.getAppIndexImg(map, bath);
		return list.subList(0, 4);
	}

}
