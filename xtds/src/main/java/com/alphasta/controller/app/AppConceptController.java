package com.alphasta.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alphasta.commons.result.Result;
import com.alphasta.model.Concept;
import com.alphasta.service.ConceptService;
/**
 * 理念
 * @author sjb
 *
 */
@Controller
public class AppConceptController {
	@Autowired
	private ConceptService conceptService;

	@RequestMapping("/getconcept.json")
	@ResponseBody
	public Result getConcept(HttpServletRequest rerquest) {
		Result result = new Result();
		Map<String, String> map = new HashMap<String, String>();
		map.put("useing", "1");
		List<Concept> list = conceptService.getUseing(map);
		if (list.size() > 0) {
			result.setSuccess(true);
			result.setObj(list.get(0).getContent());
			return result;
		}
		result.setSuccess(false);
		return result;
	}

}
