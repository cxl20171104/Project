package com.alphasta.controller.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Article;
import com.alphasta.model.Comment;
import com.alphasta.model.User;
import com.alphasta.service.ArticleService;
import com.alphasta.service.CommentService;

/**
 * 文章评论
 * 
 * @author sjb
 * 
 */
@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private ArticleService articleService;

	/**
	 * 文章评论
	 * 
	 * @param request
	 * @param response
	 * @param content
	 *            内容
	 * @param articleId
	 *            文章ID 或者 评论对象的ID
	 * @param ctype
	 *            类型 1点赞 2 评论 3 留言
	 * @param onuser
	 *            留言时的对象人的ID
	 * @param onusername
	 *            留言时的对象的名字
	 * @param replyartid
	 *            留言时文章的Id
	 * @return
	 */
	@RequestMapping("/comment.json")
	@ResponseBody
	@AppLog
	public Result commentForArt(HttpServletRequest request,
			HttpServletResponse response, String content, String articleId,
			String ctype, String onuser, String onusername, String replyartid,String commentId) {
		User user = (User) request.getAttribute("appUser");
		Result result = new Result();
		if (user == null) {
			result.setSuccess(false);
			return result;
		}
		request.setAttribute("openType", "0");
		String userId = user.getId().toString();
		String userName = user.getName();
		Integer oran = user.getOrganizationId();

		Comment comment = null;
		String remark = "";
		String zremark = "";
		try {
			if (userId != null && articleId != null && ctype != null) {
				int ct = Integer.parseInt(ctype);
				
				
					comment = new Comment(articleId, new Date(), ct, userId);
				
				
				comment.setCusername(userName);
				comment.setOrgan(oran);
				if (ct == 1) { // 点赞
					Article article = articleService.findById(articleId);
					zremark = article.getShorttitle();
				}
				if (ct == 2) { // 评论文章
					comment.setContent(content);
					Article article = articleService.findById(articleId);
					remark = "评论了文章<" + article.getShorttitle() + ">";

				}
				;
				if (ct == 3) { // 回复评论
					Article article = articleService.findById(replyartid);
					remark = "回复了文章<" + article.getShorttitle() + ">下"
							+ onusername + "的评论";
					comment.setContent(content);
					comment.setOnuser(onuser);
					comment.setOnusername(onusername);
					comment.setReplyartid(replyartid);
				}
				;
				comment.setHascomment("0");// 0 表示没有评论回复
				comment.setCtime(new Date());
				comment.setId(GetIdUtil.getId());
				String scores = commentService.comForArt(comment); // 插入评论 获取积分

				if ("hasdo".equals(scores) && ct == 1) { // 赞过
					request.setAttribute("remark", "重复点赞了文章<" + zremark + ">");
					request.setAttribute("liveness", "0");
					result.setObj("zg");
				}
				;
				if (!("hasdo".equals(scores)) && ct == 1) { // 没赞过
					request.setAttribute("remark", "点赞了文章<" + zremark + ">");
					request.setAttribute("liveness", scores);
				}
				if ("hasdo".equals(scores) && ct != 1) { // 评论过
					request.setAttribute("remark", remark);
					request.setAttribute("liveness", "0");
				}
				;
				if (!("hasdo".equals(scores)) && ct != 1) { // 没评论过
					request.setAttribute("remark", remark);
					request.setAttribute("liveness", scores);
				}
				;
				result.setSuccess(true);
				return result;
			} else {
				request.setAttribute("remark", "评论文章失败");
				result.setSuccess(false);
				result.setMsg("评论失败");
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("remark", "评论文章失败");
			result.setSuccess(false);
			result.setMsg("评论失败");
			return result;
		}
	}
    
	
	
	@RequestMapping("editCom")
	@ResponseBody
	public Object edit(String content,String commentId){
		
			
		Result result = new Result();	
		Comment c=new Comment();
		if(commentId!=null){
			c.setId(commentId);
			c.setContent(content);
			 int editCom = commentService.editCom(c);
			 if(editCom==1){
				 result.setSuccess(true);
			 }else{
				 result.setSuccess(false);
			 }
			     return result;
		}else{
			result.setSuccess(false);
			 return result;
		}
		        
	}
	/**
	 * 获取文章评论
	 * 
	 * @param request
	 * @param response
	 * @param articleId
	 * @return
	 */
	@RequestMapping("/getArtCom.json")
	@ResponseBody
	@AppLog
	public Result getCommentList(HttpServletRequest request,
			HttpServletResponse response, String articleId,String pageSize,String pageNum) {
		    Result result = new Result();
		if (articleId != null && !"".equals(articleId)) {
			Article article = articleService.findById(articleId);
			request.setAttribute("remark", "获取文章<" + article.getShorttitle()
					+ ">的评论");
			request.setAttribute("openType", "3");
			PageInfo pageInfo = new PageInfo();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("articleId", articleId);
			map.put("ctype", 4);
			pageInfo.setCondition(map);
			if (pageSize != null && pageNum != null) {
			int pagesize = Integer.parseInt(pageSize);
			int pagenum = Integer.parseInt(pageNum);
			if (pagesize > -1 && pagenum > -1) {
				pageInfo.setFrom((pagenum - 1) * pagesize);
				pageInfo.setSize(pagesize);
			}
			}
			List<Comment> comlist = commentService.searchCommentsByArt(pageInfo);
			if (comlist.size() == 0) {
				result.setMsg("over");
			}
			
			result.setSuccess(true);
			result.setObj(comlist);
		} else {
			result.setSuccess(false);
			request.setAttribute("remark", "获取文章的评论失败");
			request.setAttribute("openType", "3");
		}
		return result;
	}

	/**
	 * 删除评论
	 * 
	 * @param commentId
	 * @return
	 */
	@RequestMapping("/deletCom.json")
	@ResponseBody
	@AppLog
	public Result deletCom(HttpServletRequest request, String commentId) {
		Result result = new Result();
		if (commentId != null) {
			commentService.deletComment(commentId);
			result.setSuccess(true);
			request.setAttribute("remark", "删除文章的评论");
			request.setAttribute("openType", "1");
			return result;
		} else {
			result.setSuccess(false);
			return result;
		}

	}
	
	
	@RequestMapping("/editCom.json")
	@ResponseBody
	public Object editCom(String content,String commentId){
		
			
		Result result = new Result();	
		Comment c=new Comment();
		if(commentId!=null){
			c.setId(commentId);
			c.setContent(content);
			 int editCom = commentService.editCom(c);
			 if(editCom==1){
				 result.setSuccess(true);
			 }else{
				 result.setSuccess(false);
			 }
			     return result;
		}else{
			result.setSuccess(false);
			 return result;
		}
		        
	}
}
