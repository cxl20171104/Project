package com.alphasta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.model.Position;
import com.alphasta.service.PositionService;

/**
 * 职位管理
 * 
 * @author LiYunhao
 * 
 */
@Controller
@RequestMapping("/position")
public class PositionController extends BaseController {

	@Autowired
	private PositionService positionService;

	private static final String POSITION = "/admin/position";
	private static final String INFO = "/admin/positionInfo";

	/**
	 * 职位管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView manager() {
		ModelAndView result = new ModelAndView(POSITION);
		return result;
	}

	/**
	 * 职位信息页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(Long id) {
		ModelAndView result = new ModelAndView(INFO);
		Position position = new Position();
		if (id != null) {
			position = positionService.findPositionById(id);
		}
		result.addObject("position", position);
		return result;
	}

	/**
	 * 新增职位信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Position position) {
		position.setCreaterId(this.getUserId());
		positionService.insert(position);
		return renderSuccess("新增成功");
	}

	/**
	 * 更新职位信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Object edit(Position position) {
		positionService.update(position);
		return renderSuccess("更新成功");
	}

	/**
	 * 查询职位信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findPosition")
	@ResponseBody
	public Object findPosition() {
		List<Position> list = positionService.findPosition();
		return renderSuccess(list);
	}

	/**
	 * 更新职位信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(String ids) {
		positionService.deleteByIds(ids);
		return renderSuccess("删除成功");
	}

}
