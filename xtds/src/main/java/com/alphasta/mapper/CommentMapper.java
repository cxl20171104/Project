package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.result.CommentNum;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Comment;

public interface CommentMapper {

	public void comForArt(Comment comment);

	public Integer commentNum(String articleId, int ctype);

	public void commentAnther(String commentId);

	public List<Comment> searchComForArt(PageInfo pageInfo);

	public List<String> getHasComArt(Map<String, Object> map);

	public List<Comment> findByCondition(Comment comment);

	public void deletComment(String commentId);
	
	public CommentNum getNums(Long userid);
	
	public int editCom(Comment comment);
}
