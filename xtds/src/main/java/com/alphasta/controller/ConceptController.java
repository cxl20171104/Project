package com.alphasta.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Article;
import com.alphasta.model.Concept;
import com.alphasta.service.ConceptService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/cept")
public class ConceptController {

	@Autowired
	private ConceptService conceptService;

	@RequestMapping("/toConceptlist")
	public ModelAndView toMyArticle() {
		ModelAndView result = new ModelAndView("/work/conceptlist");

		return result;
	}

	@RequestMapping("/getall")
	@ResponseBody
	public PageInfo getConcept(HttpServletRequest rerquest, Integer page,
			Integer rows) {
		PageInfo pageInfo = new PageInfo();
		try {
			Map<String, String> map = new HashMap<String, String>();
			Page<Concept> pagehelper = PageHelper.startPage(page, rows);
			conceptService.getUseing(map);
			pageInfo.setRows(pagehelper.getResult());
			pageInfo.setTotal(Integer.valueOf(Long.valueOf(
					pagehelper.getTotal()).toString()));
			return pageInfo;

		} catch (Exception e) {
			e.printStackTrace();
			return pageInfo;
		}
	}

	@RequestMapping("/addOrUpdate")
	@ResponseBody
	public Result addone(HttpServletRequest rerquest, Concept concept) {
		Result result = new Result();
		try {
			if (StringUtils.isNotEmpty(concept.getId())) {
				concept.setCtime(new Date());
				conceptService.updateUseing(concept);
			} else {
				concept.setId(GetIdUtil.getId());
				concept.setCtime(new Date());
				conceptService.addUseing(concept);
			}
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			return result;

		}
	}

}
