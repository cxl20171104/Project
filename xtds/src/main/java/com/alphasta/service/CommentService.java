package com.alphasta.service;

import java.util.List;

import com.alphasta.commons.result.CommentNum;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Comment;

public interface CommentService {

	public String comForArt(Comment comment);

	public int commentNum(String articleId, int ctype);

	public Integer[] artCommentNum(String articleId);

	public List<Comment> searchCommentsByArt(PageInfo pageInfo);

	public List<Comment> findByCondition(Comment comment);

	public void insert(Comment comment);

	public void deletComment(String commentId);

	public void commemtFormHt(Comment comment);
	
	public CommentNum commentByuserid(Long userid);
	
	public int editCom(Comment comment);
}
