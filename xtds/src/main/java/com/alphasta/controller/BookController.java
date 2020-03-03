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
import com.alphasta.mapper.BookMapper;
import com.alphasta.mapper.GiftMapper;
import com.alphasta.model.Book;
import com.alphasta.model.Gift;
import com.alphasta.service.BookService;
import com.alphasta.service.GiftService;
@Controller
@RequestMapping("/book")
public class BookController extends BaseController{
   private String BOOK_PAGE="book/bookPage";
   @Autowired
   private BookService bookService;
   @Autowired
   private BookMapper bookMapper;
   //打开界面
   @RequestMapping("/bookPage")
   public ModelAndView bookPage() {
	   return new ModelAndView(BOOK_PAGE);
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
  	public Object dataGrid(Book book, Integer page, Integer rows,
  			String sort, String order) {
  		PageInfo pageInfo = new PageInfo(page, rows,sort,order);
  		Map<String, Object> condition = new HashMap<String, Object>();

  		if (StringUtils.isNoneBlank(book.getName())) {
  			condition.put("name", book.getName());
  		}
  		
  		if (book.getStartTime() != null) {
  			condition.put("startTime", book.getStartTime());
  		}
  		if (book.getEndTime() != null) {
  			condition.put("endTime", book.getEndTime());
  		}
  		
  		
  		pageInfo.setCondition(condition);
  		bookService.findDataGrid(pageInfo);
  		return pageInfo;
  	}
  	
  	
  	
  	
  	/**
  	 * 添加礼品页面
  	 * 
  	 * @return
  	 */
  	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
  	public String addPage() {
  		return "book/addPage";
  	}
  	/**
  	 * 添加礼品数据
  	 * 
  	 * @param 
  	 * @return
  	 */
  	@RequestMapping(value = "/add", method = RequestMethod.POST)
  	@ResponseBody
  	public Object add(Book book) {
  		try {
  			book.setId(GetIdUtil.getId());
  			bookMapper.addBook(book);
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
  		Book book = bookMapper.findBookByid(id);
  		model.addAttribute("book", book);
  		return "book/editPage";
  	}

  	/**
  	 * 编辑图书
  	 * 
  	 * @param 
  	 * @return
  	 */
  	@RequestMapping("/edit")
  	@ResponseBody
  	public Object edit(Book book) {
  		//try {
  			bookMapper.updateBook(book);
  			return renderSuccess("修改成功！");
  		//}catch(Exception e) {
  			//return renderSuccess("修改失败！");
  		//}
  		
  	}
  	
  	/**
  	 * 删除图书
  	 *
  	 * @return
  	 */
  	@RequestMapping(value = "/delete")
  	@ResponseBody
  	public Object delete(String id) {
  		try {
  			bookMapper.deleteBook(id);
  			return renderSuccess("删除成功");
  		}catch(Exception e) {
  			return renderSuccess("删除失败");
  		}
  		
  	}
}
