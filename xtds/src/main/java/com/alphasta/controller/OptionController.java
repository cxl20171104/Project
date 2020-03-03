package com.alphasta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.result.Tree;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Activities;
import com.alphasta.model.Options;
import com.alphasta.model.OptionsExample;
import com.alphasta.model.OptionsExample.Criteria;
import com.alphasta.service.OptionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/opt")
public class OptionController extends BaseController {
	@Autowired
	private OptionService optionService;

	@RequestMapping("/main")
	public ModelAndView toMain() {
		ModelAndView result = new ModelAndView("/work/QnaireList");
		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String id) {

		try {
			OptionsExample example = new OptionsExample();
			Criteria c = example.createCriteria();
			c.andIdEqualTo(id);
			optionService.deleteByExample(example);
			return renderSuccess("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return renderError("删除失败，请重试");
		}
	}

	@RequestMapping("/toEdit")
	public ModelAndView toEdit(String id, String questionid, String xh) {
		ModelAndView result = new ModelAndView("/work/optionEdit");
		result.addObject("xh", xh);
		result.addObject("questionid", questionid);
		if (StringUtils.isNotEmpty(id)) {
			OptionsExample example = new OptionsExample();
			example.createCriteria().andIdEqualTo(id);
			List<Options> qNaire = optionService.selectByExample(example);
			if (qNaire.size() > 0)
				result.addObject("option", qNaire.get(0));
		}
		return result;
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Options naire) {
		try {
			Result result = new Result();
			String flag = "1";
			Tree opT = new Tree();
			if (StringUtils.isNotEmpty(naire.getId())) {
				OptionsExample example = new OptionsExample();
				example.createCriteria().andIdEqualTo(naire.getId());
				optionService.updateByExample(naire, example);
			} else {
				flag = "0";
				naire.setId(GetIdUtil.getId());
				optionService.insert(naire);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			opT.setText(naire.getContent());
			opT.setIconCls("icon-company");
			map.put("type", "1");
			map.put("attr1", naire.getNum());
			map.put("attr2", "");
			map.put("attr3", naire.getIsanswer());
			map.put("attrid", naire.getId());
			opT.setAttributes(map);
			result.setSuccess(true);
			result.setMsg(flag);
			result.setObj(opT);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return renderError("保存失败，请重试");
		}
	}
}
