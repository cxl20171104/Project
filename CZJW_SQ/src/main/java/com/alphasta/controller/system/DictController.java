package com.alphasta.controller.system;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Tree;
import com.alphasta.model.Dict;
import com.alphasta.model.Role;
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
    
	/**
	 * 用于加载combotree
	 */
	/*@RequestMapping(value = "/dictCombotree")
	@ResponseBody
	public Object dictCombotree(Dict dict) {
		List<Dict> list = dictService.findDictByDictPid(dict);
		List<Tree> trees = new ArrayList<Tree>();
		for (Dict role : list) {
            Tree tree = new Tree();
            tree.setId(role.getId());
            tree.setText(role.getName());

            trees.add(tree);
        }
		System.out.println(trees.toString());
		return trees;
	}*/
	@RequestMapping(value = "/dictCombotree")
	@ResponseBody
	public String dictCombotree(Dict dict) {
		List<Dict> list = dictService.findDictByDictPid(dict);
		System.out.println(list.toString());
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}
}
