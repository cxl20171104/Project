package com.alphasta.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.result.CommentNum;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.ArticleMapper;
import com.alphasta.mapper.CatalogMapper;
import com.alphasta.mapper.CommentMapper;
import com.alphasta.mapper.ScoreMapper;
import com.alphasta.model.Article;
import com.alphasta.model.Catalog;
import com.alphasta.model.Comment;
import com.alphasta.model.Score;
import com.alphasta.service.CommentService;
import com.alphasta.service.DictService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ScoreMapper scoreMapper;
	@Autowired
	public DictService dictService;
	@Autowired
	public CatalogMapper catalogMapper;

	@Override
	public String comForArt(Comment comment) {
		int ctype = comment.getCtype();
		String articleID = comment.getArticleid();
		if (ctype == 3) {
			articleID = comment.getReplyartid();
			commentMapper.commentAnther(comment.getArticleid()); // 回复评论 状态改为已评论
			Map<String, Object> map = new HashMap<String, Object>(); // 文章评论数增加1;
			map.put("ctype", 2);
			map.put("articleid", comment.getReplyartid());
			articleMapper.addCommentNum(map); // 添加评论数

		}
		;
		if (ctype == 2) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ctype", 2);
			map.put("articleid", comment.getArticleid());
			articleMapper.addCommentNum(map); // 评论数+1
		}
		;
		Map<String, Object> cmap = new HashMap<String, Object>();
		cmap.put("userid", comment.getCuser());
		cmap.put("ctype", ctype);
		cmap.put("articleID", articleID);
		List<String> list = commentMapper.getHasComArt(cmap); // 先查评论表中
																// 登录人的有无此操作过
		if (ctype != 1) {
			commentMapper.comForArt(comment); // 不是点赞 评论表insert评论
		}
		if (list.size() > 0) {
			return "hasdo"; // 评论过 或者 赞过
		} else if (ctype == 1) {
			Map<String, Object> map = new HashMap<String, Object>();
			Article article = articleMapper.findById(comment.getArticleid());
			map.put("ctype", 1);
			map.put("articleid", comment.getArticleid());
			int thumb = article.getThumb();
			if (thumb < 3) {
				map.put("thumname", comment.getCusername()); // 前三个点赞的 
			};
			Catalog catalog = catalogMapper.getByID(article.getCatalogs());
			if(catalog!=null){
				String pid = catalog.getParentcata();
				if ("112".equals(pid) && thumb != 0 //文章被点赞数每10个给积分
						&& thumb % 10 == 9) {
					String scores = dictService.findDictById(53L).getValue();
					getScoreFormCom(comment.getArticleid(), 4, scores,
							article.getInputer(), article.getTitle());
				};
				
			};			
			articleMapper.addCommentNum(map); // 文章点赞+1
			commentMapper.comForArt(comment); // 还未点赞 插入点赞评论
			// 给积分
			if (comment.getOrgan() != 1) {
				String scoreNum = dictService.findDictById(13L).getValue();
				getScoreFormCom(comment.getArticleid(), 1, scoreNum,
						comment.getCuser(), article.getTitle());
				return scoreNum;
			}

		} else if (ctype == 2 && comment.getOrgan() != 1) { // 评论文章
			// 给积分
			Article article = articleMapper.findById(comment.getArticleid());
			String scoreNum = dictService.findDictById(14L).getValue();
			getScoreFormCom(comment.getArticleid(), 2, scoreNum,
					comment.getCuser(), article.getTitle());
			return scoreNum;
		} else if (ctype == 3 && comment.getOrgan() != 1) { // 回复评论
			// 给分
			Article article = articleMapper.findById(comment.getReplyartid());
			String scoreNum = dictService.findDictById(14L).getValue();
			getScoreFormCom(comment.getArticleid(), 3, scoreNum,
					comment.getCuser(), article.getTitle());
			return scoreNum;
		}
		return "0";

	}

	/**
	 * 
	 * @param articleId
	 *            文章或评论ID
	 * @param artCtype
	 *            文章的给分类型
	 * @param Ctype
	 *            操作的类型
	 * @param scoreNum
	 *            积分值
	 * @param userId
	 *            操作人ID
	 */
	public void getScoreFormCom(String articleId, int type, String scoreNum,
			String userId, String title) {

		Score score = new Score();
		score.setCtime(new Date());
		score.setId(GetIdUtil.getId());
		score.setScoresource(articleId);
		title = title.length() < 10 ? title : title.substring(0, 9) + "...";

		if (type == 1) {
			score.setDescr("点赞文章<" + title + ">");
		} else if (type == 4) {
			score.setDescr("心得被点赞<" + title + ">");
		} else {
			score.setDescr("评论文章<" + title + ">");
		}
		score.setScorevalue(scoreNum);
		score.setUserid(userId);
		score.setType(type); //
		if(scoreNum!=null&&!"0".equals(scoreNum))
		scoreMapper.addScore(score); // 积分记录表添一条

	}

	@Override
	public int commentNum(String articleId, int ctype) {
		// TODO Auto-generated method stub
		return commentMapper.commentNum(articleId, ctype);
	}

	@Override
	public Integer[] artCommentNum(String articleId) {
		Integer[] nums = new Integer[2];
		nums[0] = commentMapper.commentNum(articleId, 1);// 点赞数
		nums[1] = commentMapper.commentNum(articleId, 2);// 评论数
		return nums;
	}

	@Override
	public List<Comment> searchCommentsByArt(PageInfo pageInfo) {

		return commentMapper.searchComForArt(pageInfo);
	}

	@Override
	public List<Comment> findByCondition(Comment comment) {
		// TODO Auto-generated method stub
		return commentMapper.findByCondition(comment);
	}

	public void insert(Comment comment) {
		commentMapper.comForArt(comment);
	}

	@Override
	public void deletComment(String commentId) {
		commentMapper.deletComment(commentId);

	}

	/**
	 * 后台评论
	 */
	@Override
	public void commemtFormHt(Comment comment) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotEmpty(comment.getOnuser())) { // 回复评论
			commentMapper.commentAnther(comment.getArticleid());
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ctype", 2);
			map.put("articleid", comment.getArticleid());
			articleMapper.addCommentNum(map);
		}
		commentMapper.comForArt(comment);
	}

	@Override
	public CommentNum commentByuserid(Long userid) {
		// TODO Auto-generated method stub
		return commentMapper.getNums(userid);
	}

	@Override
	public int editCom(Comment comment) {
		
		return commentMapper.editCom(comment);
	}
	
	
}
