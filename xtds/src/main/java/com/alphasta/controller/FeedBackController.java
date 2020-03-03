package com.alphasta.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Attachment;
import com.alphasta.model.Comment;
import com.alphasta.model.User;
import com.alphasta.service.CommentService;
import com.alphasta.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/feedBack")
public class FeedBackController extends BaseController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;

	@RequestMapping("/main")
	public ModelAndView main() {
		ModelAndView result = new ModelAndView("/work/feedBack");
		return result;
	}

	@RequestMapping("/list")
	@ResponseBody
	public Object page(Comment comment, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("ctype", "5");
		condition.put("catalogId", "feedBack");
		pageInfo.setCondition(condition);
		Page<Attachment> pagehelper = PageHelper.startPage(page, rows);
		commentService.searchCommentsByArt(pageInfo);

		pageInfo.setRows(pagehelper.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));
		return pageInfo;
	}

	@RequestMapping("/detail")
	public ModelAndView detail(String commentId) {
		ModelAndView result = new ModelAndView("/work/feedBackDetail");

		PageInfo pageInfo = new PageInfo();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("ctype", "5");
		condition.put("catalogId", commentId);
		pageInfo.setCondition(condition);
		List<Comment> list = commentService.searchCommentsByArt(pageInfo);
		Comment comment = list.get(0);
		result.addObject("comment", list);
		result.addObject("cuser", getCurrentUser());
		result.addObject("user",
				userService.findUserById(Long.valueOf(comment.getCuser())));
		return result;
	}

	@RequestMapping("/reply")
	@ResponseBody
	public Object reply(String content, String comid) {
		try {
			User cuser = getCurrentUser();
			Comment example = new Comment();
			example.setId(comid);
			Comment comment = commentService.findByCondition(example).get(0);
			Comment insert = new Comment();
			insert.setId(GetIdUtil.getId());
			insert.setCtime(new Date());
			insert.setContent(content);
			insert.setCtype(5);
			insert.setArticleid(comid);
			insert.setCuser(cuser.getId().toString());
			insert.setCusername(cuser.getName());
			insert.setCuserpic(cuser.getHeadpic());
			insert.setHascomment("0");
			insert.setOnuser(comment.getCuser());
			insert.setOnusername(comment.getCusername());
			insert.setReplyartid(comment.getReplyartid());
			commentService.insert(insert);
			return renderSuccess();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return renderError("服务器故障，请重试");
		}
	}

}
