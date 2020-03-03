package com.alphasta.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Tree;
import com.alphasta.commons.utils.Config;
import com.alphasta.commons.utils.ExcelUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Activities;
import com.alphasta.model.Question;
import com.alphasta.model.QuestionExample;
import com.alphasta.model.QuestionExample.Criteria;
import com.alphasta.service.QuestionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/quest")
public class QuestionController extends BaseController {

	@Autowired
	private QuestionService questionService;

	@RequestMapping("/main")
	public ModelAndView toMain(String naireid) {
		ModelAndView result = new ModelAndView("/work/question");
		result.addObject("naireid", naireid);
		return result;
	}

	@RequestMapping("/list")
	@ResponseBody
	public PageInfo page(Question qnaire, Integer page, Integer rows,
			String sort, String order) {
		Page<Activities> pagehelper = PageHelper.startPage(page, rows);
		QuestionExample example = new QuestionExample();
		example.setOrderByClause(sort + " " + order);
		example.setDistinct(false);
		Criteria c = example.createCriteria();
		if (StringUtils.isNotEmpty(qnaire.getTopic())) {
			c.andTopicLike("%" + qnaire.getTopic() + "%");
		}
		questionService.selectByExample(example);
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
			QuestionExample example = new QuestionExample();
			Criteria c = example.createCriteria();
			c.andIdEqualTo(id);
			questionService.deleteByExample(example);
			return renderSuccess("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return renderError("删除失败，请重试");
		}
	}

	@RequestMapping("/toEdit")
	public ModelAndView toEdit(String qid, String id, String xh) {
		ModelAndView result = new ModelAndView("/work/questionEdit");
		result.addObject("xh", xh != null ? Integer.valueOf(xh) + 1 : null);
		result.addObject("questionnaire", qid);
		if (StringUtils.isNotEmpty(id)) {
			QuestionExample example = new QuestionExample();
			example.createCriteria().andIdEqualTo(id);
			List<Question> qNaire = questionService.selectByExample(example);
			result.addObject("quest", qNaire.get(0));
		}

		return result;
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Question naire) {
		try {
			naire.setMethod(0);
			naire.setType(0);
			if (StringUtils.isNotEmpty(naire.getId())) {
				QuestionExample example = new QuestionExample();
				example.createCriteria().andIdEqualTo(naire.getId());
				questionService.updateByExample(naire, example);
			} else {
				naire.setId(GetIdUtil.getId());

				questionService.insert(naire);
			}
			return renderSuccess("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return renderError("保存失败，请重试");
		}
	}

	@RequestMapping("/tree")
	@ResponseBody
	public Object tree(String naireid) {
		List<Tree> tree = questionService.getAllOption(naireid);
		System.out.println(JSON.toJSON(tree).toString());
		return tree;
	}

	@RequestMapping("/import")
	@ResponseBody
	public Object importXls(String questionnaire, MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String path = "c://upload//";
			String fileName = file.getOriginalFilename();
			String type = fileName.substring(fileName.lastIndexOf("."));

			// String fileName = new Date().getTime()+".jpg";
			File targetFile = new File(path, new Date().getTime() + type);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			return renderSuccess(questionService.importXls(targetFile,
					questionnaire));

		} catch (Exception e) {
			e.printStackTrace();
			return renderError("服务器故障");
		}

	}

	@RequestMapping("/downfile")
	public void downFile(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("utf-8");
		try {
			String path = request.getServletContext().getRealPath(
					"/files/qnaireTemplate.xls");
			InputStream inStream = new FileInputStream(path);// 文件的存放路径
			String fileName = "qnaireTemplate.xls";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
