package com.alphasta.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jsqlparser.schema.Server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.Config;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.commons.utils.StringEscapeEditor;
import com.alphasta.model.Activities;
import com.alphasta.model.Article;
import com.alphasta.model.Attachment;
import com.alphasta.model.Catalog;
import com.alphasta.model.Comment;
import com.alphasta.model.Organization;
import com.alphasta.model.Score;
import com.alphasta.model.User;
import com.alphasta.service.ActivitiesService;
import com.alphasta.service.ArticleService;
import com.alphasta.service.AttachmentService;
import com.alphasta.service.CatalogService;
import com.alphasta.service.ComService;
import com.alphasta.service.CommentService;
import com.alphasta.service.DictService;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.ScoreService;
import com.alphasta.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @author cxl
 * 该类的用途：文章管理
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {
	
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true,
				false));
	}

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ComService comService;
	@Autowired
	public DictService dictService;
	@Autowired
	public ScoreService scoreService;
	@Autowired
	private CommentService commentService;
	@Autowired
	public AttachmentService attachmentService;
	@Autowired
	private ActivitiesService activitiesService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private CatalogService catalogService;
	/**
	 * 打开文章列表（所有文章）的方法
	 * 只有admin有这个权限
	 * @return
	 */
	@RequestMapping("/toArtPage")
	public ModelAndView getArticleListPage() {
		ModelAndView result = new ModelAndView("/work/articleList");

		return result;
	}
   
	/**
	 * 打开文章列表(当前用户的文章)的方法
	 * @param req
	 * @return
	 */
	@RequestMapping("/toMyArticle")
	public ModelAndView toMyArticle(HttpServletRequest req) {
		String isB=req.getParameter("cxl");
		if(!StringUtils.isBlank(isB)){
			
			ModelAndView result = new ModelAndView("/add_photo/myArticleList");
			return result;
		}else{
			
			ModelAndView result = new ModelAndView("/work/myArticleList");
			return result;
		}
        
		
	}
    /**
     * 打开文章审核界面
     * @return
     */
	@RequestMapping("/auth")
	public ModelAndView auth() {
		ModelAndView result = new ModelAndView("/work/authList");

		return result;
	}
    /**
     * 文章点击评论信息
     * @param request
     * @param articleId
     * @return
     */
	@RequestMapping("/grideTree")
	@ResponseBody
	public List<Comment> grideTree(HttpServletRequest request, String articleId) {
		PageInfo pageInfo = new PageInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleId", articleId);
		map.put("ctype", 4);
		pageInfo.setCondition(map);
		List<Comment> comlist = commentService.searchCommentsByArt(pageInfo);
		return comlist;
	}
    /**
     * 保存文章
     * @param request
     * @param article
     * @return
     */
	@RequestMapping("/save")
	@ResponseBody
	public Object addArt(HttpServletRequest request,Article article) {
			System.out.println(article.getCreatetime());
		try {
			if (StringUtils.isNotEmpty(article.getId())) {
				// 修改后需要重新审核
				article.setState(Config.ARTICLE_STATE_WSH);
				articleService.updateArt(article);
			} else {
				article.setId(GetIdUtil.getId());
				// 作者 不填写则为发布人
				article.setAuthor(getCurrentUser().getId().toString());
				// 评论数
				article.setComnum(0);
				article.setCreatetime(article.getCreatetime());
				article.setHits(0);
				
				article.setArea(Integer.valueOf(new SimpleDateFormat("yyyyMM").format(new Date())));
				//发布人
				article.setInputer(getCurrentUser().getId().toString());
				//文章所属活动类型  1 活动方案  2  活动图片 3. 制度规定4.学习资料
				article.setScoretype(userService.findUserById(
				 getCurrentUser().getId()).getOrganizationId()); // 部门ID;
				
				article.setState(Config.ARTICLE_STATE_WSH);
				 // 点赞数
				article.setThumb(0);
				User user = getCurrentUser();
				Organization o = organizationService.findOrganizationById(user
						.getOrganizationId().longValue());
				
				if (o.getCode().length() > 4) {
					 o = organizationService.findOrganizationById(
								o.getPid().longValue());
					 if (o.getCode().length() > 4) {
						 o = organizationService.findOrganizationById(
									o.getPid().longValue());
					};			 
				};
				//所属部门
				article.setOrganid(String.valueOf(o.getId()));
				articleService.addArt(article);
			}
			return renderSuccess("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return renderError("保存失败，请重试");
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delArt(String aid) {
		try {
			if(aid.endsWith("cxl")){
				Article article=new Article();
				article.setId(aid.split("_")[0]);
				article.setExpire("2099");
				articleService.updateArt(article);;
			}else{
				
				articleService.delet(aid);
			}
			return renderSuccess("删除成功");
		} catch (Exception e) {

			e.printStackTrace();
			return renderError("请重试");
		}

	}
    /**
     * 打开文章发布界面
     * @param aid
     * @return
     */
	@RequestMapping("/toEdit")
	public ModelAndView toEdit(String aid) {
		ModelAndView result = new ModelAndView("/work/publish");
		if (StringUtils.isNotEmpty(aid)) {    //编辑文章内容
			Article article = articleService.findById(aid);
			result.addObject("article", article);
			result.addObject("content", article.getContent());
			Activities act = new Activities();
			act.setId("1");
			List<Activities> acts = activitiesService.getByCondition(act);
			result.addObject("acts", acts);
		}else{
			Activities act = new Activities();
			act.setId("1");
			List<Activities> acts = activitiesService.getByCondition(act);
			result.addObject("acts", acts);
			
		}
		
		return result;
	}

	/**
	 * 查看文章
	 * 
	 * @param aid
	 * @return
	 */
	@RequestMapping("/toSee")
	public ModelAndView toSee(String aid, HttpServletRequest request) {
		ModelAndView result = new ModelAndView("/work/showArticle");
		if (StringUtils.isNotEmpty(aid)) {
			Article article = articleService.findById(aid);
			result.addObject("article", article);
			result.addObject("content", article.getContent());
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("owner", aid);
			maps.put("type", Config.ATTACHMENT_TYPE_ART);
			maps.put("filetype", Config.ATTACHMENT_FILLTYPE_IMG);
			String bath = FileUtils.getBathUrl(request);
			List<String> urls = attachmentService.getAppIndexImg(maps, bath); // 附件表找图片
			if (urls.size() > 0) {
				result.addObject("urls", urls);
			}

		}
		return result;
	}

	/**
	 * 查看文章及评论
	 * 
	 * @param aid
	 * @return
	 */
	@RequestMapping("/SeeArt")
	public ModelAndView SeeArt(String aid, HttpServletRequest request) {
		ModelAndView result = new ModelAndView("/work/artAndComment");
		if (StringUtils.isNotEmpty(aid)) {
			Article article = articleService.findById(aid);
			result.addObject("article", article);
			result.addObject("content", article.getContent());
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("owner", aid);
			maps.put("type", Config.ATTACHMENT_TYPE_ART);
			maps.put("filetype", Config.ATTACHMENT_FILLTYPE_IMG);
			String bath = FileUtils.getBathUrl(request);
			List<String> urls = attachmentService.getAppIndexImg(maps, bath); // 附件表找图片
			if (urls.size() > 0) {
				result.addObject("urls", urls);
			}
		}
		return result;
	}
    /**
     * 文章显示列表用的（datagrid）
     * @param article
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
	@RequestMapping("/list")
	@ResponseBody
	public Object page(Article article, Integer page, Integer rows,
			String sort, String order) {
		article.setState(Config.ARTICLE_STATE_YSH);
		Page<Article> pagehelper = PageHelper.startPage(page, rows);
		pagehelper.setOrderBy(sort + "   desc");
		articleService.page(article);
		
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		List<Article> result = pagehelper.getResult();
		List<Article> result0=new ArrayList<Article>(10);
		//处理栏目名称问题
		StringBuffer suf=new StringBuffer();
		for(Article a:result) {
			if(a.getCatalogs().contains(",")) {
				String[]catalogs=a.getCatalogs().split(",");
				for(String c:catalogs) {
					Catalog catalog = catalogService.getByID(c);
					suf.append(catalog.getName()+"|");
				}
				a.setCatalogName(suf.toString());
			}else {
				result0.add(a);
			}
			
			
		}
		
		pageInfo.setRows(pagehelper.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));
		return pageInfo;
	}

	@RequestMapping("/myArticle")
	@ResponseBody
	public Object myArticle(Article article, Integer page, Integer rows,
			String sort, String order,HttpServletRequest req) {
		
		if(!StringUtils.isBlank(req.getParameter("cxl"))){
			article.setExpire("cxl");
		}
		article.setInputer(getUserId().toString());
		Page<Article> pagehelper = PageHelper.startPage(page, rows);
		pagehelper.setOrderBy(sort + "   desc");
		articleService.page(article);
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		pageInfo.setRows(pagehelper.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));
		return pageInfo;
	}

	@RequestMapping("/aulist")
	@ResponseBody
	public Object aulist(Article article, Integer page, Integer rows,
			String sort, String order) {
		article.setState(Config.ARTICLE_STATE_WSH);
		Page<Article> pagehelper = PageHelper.startPage(page, rows);
		pagehelper.setOrderBy(sort + "   desc");
		articleService.page(article);
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		pageInfo.setRows(pagehelper.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));
		return pageInfo;
	}

	@RequestMapping("/refusePage")
	public ModelAndView refusePage(String type, String id) {
		ModelAndView result = new ModelAndView("/work/refusePage");
		Article article = articleService.findById(id);
		result.addObject("refusecontent", article.getRefusecontent());
		result.addObject("type", type);
		result.addObject("id", id);

		return result;
	}

	@RequestMapping("/audit")
	@ResponseBody
	public Object audit(String type, String articleId, String refuse) {
		try {
			Article article = articleService.findById(articleId);
			if (type.equals("1")) {
				article.setAudituser(getCurrentUser().getId().toString());
				article.setState(Config.ARTICLE_STATE_YSH);
				String scoreNum = dictService.findDictById(46L).getValue();
				Score score = new Score();
				score.setCtime(new Date());
				score.setId(GetIdUtil.getId());
				score.setScoresource(article.getId());
				score.setScorevalue(scoreNum);
				score.setDescr("发布文章<" + article.getTitle() + ">");
				score.setUserid(article.getInputer());
				score.setType(Config.ARTICLE_PUTE); // 发布文章
				scoreService.addScore(score); // 给予发布文章的积分
			} else {
				article.setRefusecontent(refuse);
				article.setAudituser(getCurrentUser().getId().toString());
				article.setState(Config.ARTICLE_STATE_BBH);
			}
			articleService.updateArt(article);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return renderError("服务器故障，请重试");
		}

	}

	/**
	 * 管理员评论文章
	 * 
	 * @param articleId
	 * @param onuser
	 * @param onusername
	 * @param id
	 * @param content
	 * @return
	 */
	@RequestMapping("/addOne")
	@ResponseBody
	public Result addcom(String articleId, String onuser, String onusername,
			String id, String content) {
		Comment comment = new Comment();
		Result result = new Result();
		try {
			comment.setId(GetIdUtil.getId());
			comment.setContent(content);
			comment.setHascomment("0");
			comment.setCtime(new Date());
			comment.setCuser(getUserId().toString());
			comment.setCusername("【管理员】");

			if (StringUtils.isNotEmpty(onuser)) {
				comment.setArticleid(id);
				comment.setReplyartid(articleId);
				comment.setOnuser(onuser);
				comment.setOnusername(onusername);
				comment.setCtype(3);
			} else {
				comment.setArticleid(articleId);
				comment.setCtype(2);
			}
			commentService.commemtFormHt(comment);
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
