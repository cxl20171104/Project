package com.alphasta.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.GiftMapper;
import com.alphasta.model.Gift;
import com.alphasta.service.GiftService;
/**
 * 礼品管理
 * @author cxl
 *
 */
@Controller
@RequestMapping("/gift")
public class GiftController extends BaseController{
   private String GIFT_PAGE="gift/giftPage";
   @Autowired
   private GiftService giftService;
   @Autowired
   private GiftMapper giftMapper;
   //打开界面
   @RequestMapping("/giftPage")
   public ModelAndView giftPage() {
	   return new ModelAndView(GIFT_PAGE);
   } 
  
   /**
	 * 礼品管理列表
	 * 
	 * @param userVo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public Object dataGrid(Gift gift, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows,sort,order);
		Map<String, Object> condition = new HashMap<String, Object>();

		if (StringUtils.isNoneBlank(gift.getName())) {
			condition.put("name", gift.getName());
		}
		
		if (gift.getStartTime() != null) {
			condition.put("startTime", gift.getStartTime());
		}
		if (gift.getEndTime() != null) {
			condition.put("endTime", gift.getEndTime());
		}
		
		
		pageInfo.setCondition(condition);
		giftService.findDataGrid(pageInfo);
		return pageInfo;
	}
	
	
	
	
	/**
	 * 添加礼品页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "gift/addPage";
	}
	/**
	 * 添加礼品数据
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(Gift gift) {
		try {
			gift.setId(GetIdUtil.getId());
			giftMapper.addGift(gift);
			return renderSuccess("添加成功");
		}catch(Exception e) {
			return renderSuccess("添加失败");
		}
		
	}
	
	/**
	 * 编辑礼品页
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(String id, Model model) {
		Gift gift = giftMapper.findGiftByid(id);
		model.addAttribute("gift", gift);
		return "gift/editPage";
	}

	/**
	 * 编辑礼品
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(Gift gift) {
		//try {
			giftMapper.updateGift(gift);
			return renderSuccess("修改成功！");
		//}catch(Exception e) {
			//return renderSuccess("修改失败！");
		//}
		
	}
	
	/**
	 * 删除礼品
	 *
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(String id) {
		try {
			giftMapper.deleteGift(id);
			return renderSuccess("删除成功");
		}catch(Exception e) {
			return renderSuccess("删除失败");
		}
		
	}
}
