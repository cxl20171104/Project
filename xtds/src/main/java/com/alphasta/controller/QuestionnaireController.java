package com.alphasta.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Activities;
import com.alphasta.model.Dict;
import com.alphasta.model.Questionnaire;
import com.alphasta.model.QuestionnaireExample;
import com.alphasta.model.QuestionnaireExample.Criteria;
import com.alphasta.service.DictService;
import com.alphasta.service.QuestionnaireService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/qnaire")
public class QuestionnaireController extends BaseController {
	@Autowired
	private QuestionnaireService questionnaireService;
	@Autowired
	private DictService dictService;

	@RequestMapping("/main")
	public ModelAndView toMain() {
		ModelAndView result = new ModelAndView("/work/QnaireList");
		return result;
	}

	@RequestMapping("/list")
	@ResponseBody
	public PageInfo page(Questionnaire qnaire, Integer page, Integer rows,
			String sort, String order) {
		Page<Activities> pagehelper = PageHelper.startPage(page, rows);
		QuestionnaireExample example = new QuestionnaireExample();
		example.setOrderByClause(sort + " " + order);
		example.setDistinct(false);
		Criteria c = example.createCriteria();
		if (StringUtils.isNotEmpty(qnaire.getName())) {
			c.andNameLike("%" + qnaire.getName() + "%");
		}
		questionnaireService.selectByExample(example);
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		pageInfo.setRows(pagehelper.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));
		return pageInfo;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String id) {

		try {
			QuestionnaireExample example = new QuestionnaireExample();
			Criteria c = example.createCriteria();
			c.andIdEqualTo(id);
			questionnaireService.deleteByExample(example);
			return renderSuccess("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return renderError("删除失败，请重试");
		}
	}

	@RequestMapping("/toEdit")
	public ModelAndView toEdit(String id) {
		ModelAndView result = new ModelAndView("/work/qnaireEdit");
		if (StringUtils.isNotEmpty(id)) {
			QuestionnaireExample example = new QuestionnaireExample();
			example.createCriteria().andIdEqualTo(id);
			List<Questionnaire> qNaire = questionnaireService
					.selectByExample(example);
			if (qNaire.size() > 0) {
				result.addObject("qnaire", qNaire.get(0));
				result.addObject("btime", qNaire.get(0).getBtime());
				result.addObject("etime", qNaire.get(0).getEtime());
			}
		}
		return result;
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Questionnaire naire) {
		try {
			if (StringUtils.isNotEmpty(naire.getId())) {
				QuestionnaireExample example = new QuestionnaireExample();
				example.createCriteria().andIdEqualTo(naire.getId());
				Dict d = dictService.findDictById(47L);
				if (d != null) {
					naire.setScores(d.getValue());
				}
				// 只要编辑过问卷,就重置答题人数
				naire.setNums(0);
				questionnaireService.updateByExample(naire, example);
			} else {
				Dict d = dictService.findDictById(47L);
				if (d != null) {
					naire.setScores(d.getValue());
				}
				naire.setId(GetIdUtil.getId());
				naire.setCtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
				naire.setPubshlisher(getStaffName());
				naire.setNums(0);
				questionnaireService.insert(naire);
			}
			return renderSuccess("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return renderError("保存失败，请重试");
		}
	}

}
