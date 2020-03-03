package com.alphasta.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.CommentMapper;
import com.alphasta.model.Comment;
import com.alphasta.service.ArticleService;
import com.alphasta.service.CommentService;

@RequestMapping("/comment")
public class CommentController extends BaseController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/addOne")
	@ResponseBody
	public Result addcom(String articleId, String onuser, String onusername,
			String id, String content) {
		Comment comment = new Comment();
		Result result = new Result();
		try {
			comment.setId(GetIdUtil.getId());
			comment.setArticleid(id);
			comment.setReplyartid(articleId);
			comment.setContent(content);
			comment.setOnuser(onuser);
			comment.setOnusername(onusername);
			comment.setCtype(3);
			comment.setHascomment("0");
			comment.setCtime(new Date());
			comment.setCuser(getUserId().toString());
			comment.setCusername(getStaffName());
			commentMapper.comForArt(comment);
			result.setSuccess(true);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setMsg("评论失败");
			return result;
		}
	}

}
