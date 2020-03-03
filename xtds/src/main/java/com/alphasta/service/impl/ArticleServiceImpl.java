package com.alphasta.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.Config;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.ArticleMapper;
import com.alphasta.mapper.AttachmentMapper;
import com.alphasta.mapper.CommentMapper;
import com.alphasta.mapper.ScoreMapper;
import com.alphasta.mapper.UserMapper;
import com.alphasta.model.Article;
import com.alphasta.model.Comment;
import com.alphasta.model.Score;
import com.alphasta.model.User;
import com.alphasta.service.ArticleService;
import com.alphasta.service.AttachmentService;
import com.alphasta.service.DictService;
import com.alphasta.service.ScoreService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 文章管理
 * 
 * @author sjb
 * 
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	public ArticleMapper articMapper;
	@Autowired
	public ScoreService scoreService;
	@Autowired
	public ScoreMapper scoreMapper;
	@Autowired
	public UserMapper userMapper;
	@Autowired
	public CommentMapper commentMapper;
	@Autowired
	public AttachmentMapper attachmentMapper;
	@Autowired
	public AttachmentService attachmentService;
	@Autowired
	public DictService dictService;

	@Override
	public Article findById(String articleId) {

		return articMapper.findById(articleId);
	}

	@Override
	public void delet(String articleId) {

		articMapper.deletArt(articleId);

	}

	@Override
	public void addArt(Article art) {
		String userId = art.getInputer();
		List<String> list = userMapper.allResoureByUserid(userId);
		String scoreNum = dictService.findDictById(46L).getValue();
		if ("1".equals(userId)||list.contains("/article/auth")) {
			art.setState(1);
			art.setAudituser(userId);
			if (art.getScoretype() != 1) { // 借用scoreType 存部门ID
				Score score = new Score();
				score.setCtime(new Date());
				score.setId(GetIdUtil.getId());
				score.setScoresource(art.getId());
				score.setScorevalue(scoreNum);
				score.setDescr("发布文章<" + art.getTitle() + ">");
				score.setUserid(userId);
				score.setType(Config.ARTICLE_PUTE); // 发布文章
				scoreService.addScore(score); // 给予发布文章的积分
			}
		}
		articMapper.addArt(art);

	}

	@Override
	public void updateArt(Article art) {
		articMapper.update(art);
	}

	/**
	 * 栏目下的文章app
	 */
	@Override
	public List<Article> searchArticles(PageInfo pageInfo, User user) {

		List<Article> artList = articMapper.searchArtList(pageInfo);
		Map<String, Object> cmap = new HashMap<String, Object>();
		cmap.put("userid", user.getId().toString());
		cmap.put("ctype", Config.ARTICLE_SEE);
		// 查询用户看过的文章
		List<String> list = commentMapper.getHasComArt(cmap);
		Map<String, Object> gmap = new HashMap<String, Object>();
		gmap.put("userid", user.getId().toString());
		gmap.put("ctype", Config.ARTICLE_GREAT);
		// 查询用户赞过的文章
		List<String> greatlist = commentMapper.getHasComArt(gmap);
		String bathUrl = (String) pageInfo.getCondition().get("bathurl");
		if (artList.size() > 0) {
			for (Article art : artList) {
				if (list.contains(art.getId())) {
					art.setHasseen("1"); // 用户看过
				} else {
					art.setHasseen("0"); // 没有看过
				}
				if (greatlist.contains(art.getId())) {
					art.setHasGreat("1"); // 赞过
				} else {
					art.setHasGreat("0"); // 没赞过
				}
				if (art.getHeadpic() != null && !"".equals(art.getHeadpic())) {
					art.setHeadpic(bathUrl + art.getHeadpic());
				}
				if (art.getInputerpic() != null
						&& !"".equals(art.getInputerpic())) {
					art.setInputerpic(bathUrl + art.getInputerpic());
				}

			}
		}
		return artList;
	}

	@Override
	public Integer countArtList(PageInfo pageInfo) {

		return articMapper.countArtList(pageInfo);
	}

	/**
	 * 查看文章
	 * 
	 * @throws ParseException
	 */
	@Override
	public Result searchArticle(User user, String articleId, String hasseen,
			String bath, String refresh) {
		Result result = new Result();
		String userId = user.getId().toString();
		Integer organid = user.getOrganizationId();
		if ("0".equals(hasseen)) {
			Comment comment = new Comment();
			comment.setArticleid(articleId);
			comment.setCuser(userId);
			List<Comment> list = commentMapper.findByCondition(comment);
			if (list.size() > 0) {
				hasseen = "1";
			}
		}
		;
		if (articleId != null && !"".equals(articleId)) {

			Article article = this.findById(articleId);
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("owner", articleId);
			maps.put("type", Config.ATTACHMENT_TYPE_ART);
			maps.put("filetype", Config.ATTACHMENT_FILLTYPE_IMG);
			List<String> urls = attachmentService.getAppIndexImg(maps, bath); // 附件表找图片
			if (urls.size() > 0) {
				article.setAllurl(listToString(urls));
			}
			if (article == null) {
				result.setSuccess(false);
				result.setMsg("文章未找到");
			} else {
				String scoreNum = dictService.findDictById(12L).getValue();
				article.setScores(scoreNum);
				if (article.getHeadpic() != null
						&& !"".equals(article.getHeadpic())) {
					article.setHeadpic(bath + article.getHeadpic());
				}
				if (!"0".equals(refresh) && organid != 1) { // 查看文章 非游客
					article.setHits(article.getHits() + 1);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ctype", 0);
					map.put("articleid", article.getId());
					articMapper.addCommentNum(map); // 文章点击量+1
					if (hasseen != null && "0".equals(hasseen)) { // 未查看过该文章
						Score score = new Score();
						score.setCtime(new Date());
						score.setId(GetIdUtil.getId());
						score.setScoresource(articleId);
						score.setScorevalue(scoreNum);
						score.setDescr("查看文章<" + article.getTitle() + ">");
						score.setUserid(userId);
						score.setType(Config.ARTICLE_SEE); // 文章查看
						if(scoreNum!=null&&!"0".equals(scoreNum))
						scoreService.addScore(score); // 积分记录表添一条
					}
					// 添加查看类型的评论
					Comment comment = new Comment(articleId, new Date(),
							Config.ARTICLE_SEE, userId);
					comment.setId(GetIdUtil.getId());
					comment.setCusername(user.getName());
					comment.setHascomment("0"); // 评论未回复
					commentMapper.comForArt(comment);
				}

			}
			result.setObj(article);
			result.setSuccess(true);

		} else {
			result.setSuccess(false);
			result.setMsg("文章未找到");
		}
		return result;
	}

	@Override
	public List<Article> page(Article article) {
		// TODO Auto-generated method stub
		return articMapper.page(article);
	}

	public String listToString(List<String> stringList) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	@Override
	public List<Article> getArticleByTimes(String time) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createtime", time);
		return (List<Article>) articMapper.findArticleByParam(map);
	}

	@Override
	public List<String> getHeadpics(PageInfo pageInfo, String bath) {
		// TODO Auto-generated method stub
		List<Article> artList = articMapper.searchArtList(pageInfo);
		List<String> list = new ArrayList<String>();

		for (Article a : artList) {
			if (a.getHeadpic() != null && !"".equals(a.getHeadpic())) {
				list.add(bath + a.getHeadpic());
			}
			if (list.size() >= 4) {
				break;
			}
		}
		while (list.size() < 4) {
			list.add(list.get(0));
		}
		return list;
	}

	@Override
	public void updateArtCata(String uid, String cataid) {
		articMapper.updateArtCata(uid,cataid);
		
	}

	
	
	

}
