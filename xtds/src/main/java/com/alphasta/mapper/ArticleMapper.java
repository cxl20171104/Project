package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Article;

/**
 * 文章管理
 * 
 * @author sjb
 * 
 */
public interface ArticleMapper {
	/**
	 * 查询
	 * 
	 * @param id
	 * @return
	 */
	public Article findById(String id);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deletArt(String id);

	/**
	 * 添加
	 * 
	 * @param art
	 */
	public void addArt(Article art);

	/**
	 * 更新
	 * 
	 * @param art
	 */
	public void update(Article art);

	/**
	 * 根据条件查询
	 * 
	 * @param pageInfo
	 * @return
	 */
	public List<Article> searchArtList(PageInfo pageInfo);

	/**
	 * 根据条件查询文章数量
	 * 
	 * @param pageInfo
	 * @return
	 */
	public Integer countArtList(PageInfo pageInfo);

	/**
	 * 点赞数 或者评论数加一
	 */
	public void addCommentNum(Map map);

	public List<Article> page(Article article);

	public Object findArticleByParam(Map<String, Object> map);
	/**
	 * 通过用户id修改文章的栏目id
	 * @param uid
	 * @param cataid
	 */
	public void updateArtCata(@Param("uid")String uid, @Param("cataid")String cataid);
	/**
	 * 
	 */
   public int delNo_P(String aid);
}
