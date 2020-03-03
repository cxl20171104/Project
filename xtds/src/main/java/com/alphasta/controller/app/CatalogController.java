package com.alphasta.controller.app;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.app.AppController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.ActivitiesMapper;
import com.alphasta.model.Article;
import com.alphasta.model.Catalog;
import com.alphasta.service.CatalogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 栏目管理
 * 
 * @author sjb
 * 
 */
@Controller
public class CatalogController {
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private ActivitiesMapper activitiesmapper;

	/**
	 * 所有栏目
	 * 
	 * @return
	 */
	@RequestMapping("/allCatalog.json")
	@ResponseBody
	@AppLog
	public Result getAllCatalog(HttpServletRequest request,
			HttpServletResponse response) {
		Result result = new Result();
		result.setSuccess(true);
		List<Catalog> list = catalogService.getAllCatalog();
		String bath = FileUtils.getBathUrl(request);
		if (list.size() > 0) {
			for (Catalog ca : list) {
				if (ca.getPic() != null && !"".equals(ca.getPic())) {
					String bathurl = bath + ca.getPic();
					ca.setPic(bathurl);
				}
			}
		}
		result.setObj(list);
		request.setAttribute("remark", "所有栏目");
		request.setAttribute("openType", "3");
		return result;
	}

	/**
	 * 
	 * get子栏目
	 * 
	 * @return
	 */
	@RequestMapping("/cata.json")
	@ResponseBody
	public Result getIndexCatalog(HttpServletRequest request,
			HttpServletResponse response, String catalogId, String pageSize,String level,
			String pageNum) {
		Result result = new Result();
		PageInfo pageInfo = new PageInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		if (catalogId != null && !"".equals(catalogId)) {
			map.put("showOnApp", "yes");
			map.put("catalogId", catalogId);
			map.put("isEnable", '0');
			if (catalogId.equals("112")) {
				map.put("artNum", "yes");
			}
			pageInfo.setCondition(map);
		}
		;
		if (pageSize != null && pageNum != null) {
			try {
				int pagesize = Integer.parseInt(pageSize);
				int from = (Integer.parseInt(pageNum) - 1) * pagesize;
				if (pagesize > -1 && from > -1) {
					// Page<Catalog>
					// page=PageHelper.startPage(pagenum,pagesize);
					pageInfo.setFrom(from);
					pageInfo.setSize(pagesize);
					List<Catalog> list = catalogService
							.getIndexCatalog(pageInfo);
					if (list.size() == 0) {
						result.setMsg("over");
					};
					if("100".equals(catalogId)&&"2".equals(level)){
						Iterator<Catalog> it = list.iterator();
						while(it.hasNext()){
							Catalog c=it.next();
							if("131".equals(c.getId())){
								it.remove();
							}
						}
					}
					result.setSuccess(true);
					result.setObj(list);

				} else {
					List<Catalog> list = catalogService
							.getIndexCatalog(pageInfo);
					if (list.size() == 0) {
						result.setMsg("over");
					}
					result.setSuccess(true);
					result.setObj(list);

				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
				result.setMsg("加载失败");
			}
		} else {
			List<Catalog> list = catalogService.getIndexCatalog(pageInfo);
			result.setSuccess(true);
			result.setObj(list);

		}
		if (result.getObj() != null) {
			List<Catalog> list = (List<Catalog>) result.getObj();
			String bath = FileUtils.getBathUrl(request);
			if (list.size() > 0) {
				for (Catalog ca : list) {
					if (ca.getPic() != null && !"".equals(ca.getPic())) {
						String bathurl = bath + ca.getPic();
						ca.setPic(bathurl);
					}
				}
			}
		}
		return result;
	}

}
