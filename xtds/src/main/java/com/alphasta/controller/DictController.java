package com.alphasta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.model.Dict;
import com.alphasta.service.DictService;

/**
 * 数据字典管理
 * 
 * @author LiYunhao
 * 
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;

	private static final String DICT = "/admin/dict";
	private static final String INFO = "/admin/info";

	/**
	 * 数据字典管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView manager() {
		ModelAndView result = new ModelAndView(DICT);
		return result;
	}

	/**
	 * 数据字典信息页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(Long id) {
		ModelAndView result = new ModelAndView(INFO);
		Dict dict = new Dict();
		if (id != null) {
			dict = dictService.findDictById(id);
		}
		result.addObject("dict", dict);
		return result;
	}

	/**
	 * 新增数据字典信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Object save(Dict dict) {
		dict.setCreaterId(this.getUserId());
		dictService.save(dict);
		return renderSuccess("保存成功");
	}

	/**
	 * 查询数据字典信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findDictByDictPid")
	@ResponseBody
	public Object findDictByDictPid(Dict dict) {
		List<Dict> list = dictService.findDictByDictPid(dict);
		return renderSuccess(list);
	}

	/**
	 * 查询数据字典信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/treegridByDictPid")
	@ResponseBody
	public List<Dict> findDictByDictPid(String id) {
		Dict dict = new Dict();
		if (id == null || "".equals(id)) {
			dict.setDictId("05"); //积分办法
			dict.setDictPid("0");
		} else if("07".equals(id)){
			dict.setDictId("07"); //个人荣誉级别
			dict.setDictPid("0");	
	
		} else if("08".equals(id)){
			dict.setDictId("08"); //集体荣誉级别
			dict.setDictPid("0");	
		}else  if("09".equals(id)){
			dict.setDictId("09"); //学历
			dict.setDictPid("0");	
		}else  if("010".equals(id)){
			dict.setDictId("010"); //爱好
			dict.setDictPid("0");				
		}else{
			Long longid = Long.valueOf(id);
			dict = dictService.findDictById(longid);
			dict.setDictPid(dict.getDictId());
			dict.setDictId(null);
		}
		List<Dict> list = dictService.findDictByDictPid(dict);
		return list;
	}

	/**
	 * 删除数据字典信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(String ids) {
		dictService.deleteByIds(ids);
		return renderSuccess("删除成功");
	}

}
