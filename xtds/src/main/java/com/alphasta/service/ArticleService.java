package com.alphasta.service;

import java.util.List;

import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Article;
import com.alphasta.model.User;

/**
 * 文章管理
 * 
 * @author sjb
 * 
 */
public interface ArticleService {
	/**
	 * 根据Id查询
	 * 
	 * @param articleId
	 * @return
	 */
	public Article findById(String articleId);

	/**
	 * 删除
	 * 
	 * @param articleId
	 */
	public void delet(String articleId);

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
	public void updateArt(Article art);

	/**
	 * 查询条件查询 分页
	 * 
	 * @param pageInfo
	 * @return
	 */
	public List<Article> searchArticles(PageInfo pageInfo, User user);

	/**
	 * 查询数量
	 * 
	 * @param pageInfo
	 * @return
	 */
	public Integer countArtList(PageInfo pageInfo);

	/**
	 * 用户查看文章
	 * 
	 * @param user
	 * @param articleId
	 * @return
	 */
	public Result searchArticle(User user, String articleId, String hasseen,
			String bath, String refresh);

	public List<Article> page(Article article);

	// time格式:2017-03-02
	public List<Article> getArticleByTimes(String time);

	/**
	 * 首页滑动的四张图片
	 * 
	 * @param pageInfo
	 * @return
	 */
	public List<String> getHeadpics(PageInfo pageInfo, String bath);
	/**
	 * 修改文章栏目id
	 */
	public void updateArtCata(String uid,String cataid);
	

}
