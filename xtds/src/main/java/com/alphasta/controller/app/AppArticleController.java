package com.alphasta.controller.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.aggregates.PageSettingsBlock;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.app.AppController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.Config;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.commons.utils.StringEscapeEditor;
import com.alphasta.mapper.BookMapper;
import com.alphasta.model.Activities;
import com.alphasta.model.Article;
import com.alphasta.model.Attachment;
import com.alphasta.model.Book;
import com.alphasta.model.Catalog;
import com.alphasta.model.Organization;
import com.alphasta.model.Score;
import com.alphasta.model.User;
import com.alphasta.service.ActivitiesService;
import com.alphasta.service.ArticleService;
import com.alphasta.service.AttachmentService;
import com.alphasta.service.CatalogService;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.ScoreService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 文章模块
 * 
 * @author sjb
 * 
 */
@Controller
public class AppArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private ActivitiesService activitiesService;
	@Autowired
	private BookMapper bookMapper;
	/**
	 * 某栏目下的文章 或 最新文章
	 * 
	 * @param catalogId 栏目id
	 * @param pageSize
	 * @param pageNum
	 * @param  type   1 活动方案  2  活动图片 3. 制度规定4.学习资料
	 * @return
	 */
	@RequestMapping("/articlelist.json")
	@ResponseBody
	public Result getlistByCatalogId(HttpServletRequest request,
			HttpServletResponse response, String catalogId,String type, String pageSize,String title,
			String pageNum,String organid,String level) {
		User user = (User) request.getAttribute("appUser");
		Result result = new Result();
		if (user == null) {
			result.setSuccess(false);
			return result;
		}
		PageInfo pageInfo = new PageInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		/**
		 * 此处修改  修改内容  ： 专题活动 不共享  志愿服务（123） 不共享  
		 * 
		 */
		/*if(organid!=null&&"2".equals(level)){
			Activities act=new Activities();
			//查询所有专题活动
			List<Activities> activities=activitiesService.getByCondition(act);
			boolean flag=true;
			for (Activities a:activities){
				if(a.getId().equals(catalogId)||"102".equals(catalogId)){
					flag=false;
					break;
				}
			}
			if(flag){
				map.put("organid", organid);
			}
			
		}*/
		Activities act=new Activities();
		//所有不公用的栏目
		List<Activities> activities=activitiesService.getByCondition(act);
		for (Activities a:activities){
			if(a.getId().equals(catalogId)||"123".equals(catalogId)){
				Long oid=237l;
				Organization o= organizationService.findOrganizationById(Long.valueOf(organid));
				
				if(!"1".equals(o.getLevel())){
				
				    o= organizationService.findOrganByAddress(organid);
					oid=o.getId();
				}
				
				map.put("organid", oid);
				break;
			}
		}
		String bathurl = FileUtils.getBathUrl(request);
		map.put("bathurl", bathurl);
		if (catalogId != null && !"".equals(catalogId)) {
			// Catalog catalog = catalogService.getByID(catalogId);
			map.put("catalogId", catalogId);
			if(title!=null&&!"".equals(title))map.put("title", title);
			if(type!=null&&!"undefined".equals(type)) map.put("type", type);
			map.put("state", Config.ARTICLE_STATE_YSH); // 审核
			pageInfo.setCondition(map);
		} else {
			map.put("state", Config.ARTICLE_STATE_YSH); // 加载首页最新文章
			pageInfo.setCondition(map);
		}
		;
		if (pageSize != null && pageNum != null) {
			try {
				int pagesize = Integer.parseInt(pageSize);
				int pagenum = Integer.parseInt(pageNum);
				if (pagesize > -1 && pagenum > -1) {
					pageInfo.setFrom((pagenum - 1) * pagesize);
					pageInfo.setSize(pagesize);
				}
				
				List<Article> list = articleService.searchArticles(pageInfo,user);
				if (list.size() == 0) {
					result.setMsg("over");
				}
				result.setSuccess(true);
				result.setObj(list);
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
			}
		} else {
			result.setSuccess(false);

		}
		return result;
	};

	/**
	 * 查看文章
	 * 
	 * @param articleId
	 * @return
	 */
	@RequestMapping("/getArtById.json")
	@ResponseBody
	@AppLog
	public Result getArticleById(HttpServletRequest request,
			HttpServletResponse response, String articleId, String hasseen,
			String refresh) {
		User user = (User) request.getAttribute("appUser");
		Result result = new Result();
		if (user == null) {
			result.setSuccess(false);
			return result;
		}
		;
		String bath = FileUtils.getBathUrl(request);
		result = articleService.searchArticle(user, articleId, hasseen, bath,
				refresh);	
		Article article = (Article) result.getObj();
		if (article != null && "0".equals(hasseen)) { // 未看过此文章
			request.setAttribute("liveness", article.getScores());
		} else {
			request.setAttribute("liveness", "0");
		}
		if (article != null) {
			request.setAttribute("remark", "查看文章<" + article.getTitle() + ">");
		} else {
			request.setAttribute("remark", "查看文章失败");
		}
		request.setAttribute("openType", "3");
		return result;
	}

	/**
	 * 文章编辑
	 * 
	 * @param request
	 * @param response
	 * @param article
	 * @return
	 */
	@RequestMapping("/editArt.json")
	@ResponseBody
	@AppLog
	public Result editArt(HttpServletRequest request,
			HttpServletResponse response, Article article) {
		Result result = new Result();
		if (article != null) {
			articleService.updateArt(article);
			result.setSuccess(true);
			request.setAttribute("remark", "编辑文章<" + article.getTitle() + ">");
			request.setAttribute("openType", "2");
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 文章发布
	 * 
	 * @param request
	 * @param response
	 * @param article
	 * @return
	 */
	@RequestMapping("/addArticle.json")
	@ResponseBody
	@AppLog
	public Result addArt(HttpServletRequest request,
			HttpServletResponse response, Article article) {
		User user = (User) request.getAttribute("appUser");
		Result result = new Result();
		if (user == null) {
			result.setSuccess(false);
			return result;
		};
		if(!article.getArcid().equals("abc")){
			if (article.getAllurl() != null
					&& !article.getAllurl().equals("null")) { // 文章附件
				String[] urls = article.getAllurl().split(",");
				attachmentService.deleteattbyOwner(article.getArcid());
				for (String url : urls) {
					Attachment att = new Attachment();
					att.setId(GetIdUtil.getId());
					att.setUtime(new Date());
					att.setUrl(url);
					att.setType(Config.ATTACHMENT_TYPE_ART); // 文章
					att.setFiletype(Config.ATTACHMENT_FILLTYPE_IMG);// 图片
					att.setUploader(user.getId().toString()); // 上传人
					att.setOwner(article.getArcid()); // 使用者 文章ID
					att.setAsize("0");
					att.setName("暂无");
					attachmentService.save(att);
				}
				
			}
			String title = article.getTitle();
			title = title.length() < 10 ? title : title.substring(0, 9) + "...";
			article.setShorttitle(title);
			Date creatTime = new Date();
			article.setCreatetime(creatTime);
			
			String content = StringEscapeEditor.replaceBlank(article
					.getContent());
			article.setContent("&lt;p&gt;" + content + "&lt;/p&gt;");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			article.setArea(Integer.parseInt(sdf.format(creatTime)));
			article.setCatalogs(null);
			articleService.updateArt(article);
			result.setSuccess(true);
			request.setAttribute("remark", "发布文章<" + title + ">");
			request.setAttribute("openType", "0");
			return result;
		}
		Integer oid = user.getOrganizationId();
		if (article != null) {
			article.setId(GetIdUtil.getId());
			article.setInputer(user.getId().toString());
			article.setScoretype(oid);
			if (article.getAuthor() == null) { // 作者如果不写 则默认为发布人
				article.setAuthor(user.getId().toString());
			}
			;
			if (article.getComentable() == null) { // 可以评论
				article.setComentable(1);
			}
			;
			if (article.getAllurl() != null
					&& !article.getAllurl().equals("null")) { // 文章附件
				String[] urls = article.getAllurl().split(",");
				for (String url : urls) {
					Attachment att = new Attachment();
					att.setId(GetIdUtil.getId());
					att.setUtime(new Date());
					att.setUrl(url);
					att.setType(Config.ATTACHMENT_TYPE_ART); // 文章
					att.setFiletype(Config.ATTACHMENT_FILLTYPE_IMG);// 图片
					att.setUploader(user.getId().toString()); // 上传人
					att.setOwner(article.getId()); // 使用者 文章ID
					att.setAsize("0");
					att.setName("暂无");
					attachmentService.save(att);
				}
			}
			String title = article.getTitle();
			title = title.length() < 10 ? title : title.substring(0, 9) + "...";
			article.setShorttitle(title);
			Date creatTime = new Date();
			article.setCreatetime(creatTime);
			String content = StringEscapeEditor.replaceBlank(article
					.getContent());
			article.setContent("&lt;p&gt;" + content + "&lt;/p&gt;");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			article.setArea(Integer.parseInt(sdf.format(creatTime)));
			article.setState(0); // 当前状态未审核
			Organization o = organizationService.findOrganizationById(user
					.getOrganizationId().longValue());
			
			if (o.getCode().length() != 4) {
				 o = organizationService.findOrganizationById(
							o.getPid().longValue());
				 if (o.getCode().length() != 4) {
					 o = organizationService.findOrganizationById(
								o.getPid().longValue());
				};			 
			};
			article.setOrganid(String.valueOf(o.getId()));
			articleService.addArt(article);
			result.setSuccess(true);
			request.setAttribute("remark", "发布文章<" + title + ">");
			request.setAttribute("openType", "0");
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 首页图片
	 * 
	 * @param request
	 * @param cataId
	 * @return
	 */
	@AppLog
	@RequestMapping("/headpics.json")
	@ResponseBody
	public List<String> getArticleHeadPics(HttpServletRequest request,
			String cataId) {
		PageInfo pageInfo = new PageInfo();
		if (cataId == null || "".equals(cataId)) {
			return new ArrayList<String>();
		}
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("catalogId", cataId);
		pageInfo.setCondition(maps);
		String bath = FileUtils.getBathUrl(request);
		List<String> list = articleService.getHeadpics(pageInfo, bath);
		if (list.size() == 4) {
			return list;
		}
		return list.subList(0, 4);
	}
   @RequestMapping("/bookList.json")
   @ResponseBody
   public Result bookList(
		   HttpServletRequest request,
		   String catalogId,String type, String pageSize,String title,String pageNum) {
	   User user = (User) request.getAttribute("appUser");
		Result result = new Result();
		if (user == null) {
			result.setSuccess(false);
			return result;
		}
		PageInfo pageInfo = new PageInfo();
		Map<String,Object>map=new HashMap<String,Object>();
		pageInfo.setSort(" startTime ");
		pageInfo.setCondition(map);
		if (pageSize != null && pageNum != null) {
			try {
				int pagesize = Integer.parseInt(pageSize);
				int pagenum = Integer.parseInt(pageNum);
				if (pagesize > -1 && pagenum > -1) {
					pageInfo.setFrom((pagenum - 1) * pagesize);
					pageInfo.setSize(pagesize);
				}
				String bathUrl = FileUtils.getBathUrl(request);
				List<Book> list = bookMapper.findGiftPageCondition(pageInfo);
				for (Book book : list) {
					if (book.getHeadPic()!= null && !"".equals(book.getHeadPic())) {
						book.setHeadPic(bathUrl + book.getHeadPic());
					}
					book.setStartTime(book.getStartTime().substring(0, 10));
				}
				if (list.size() == 0) {
					result.setMsg("over");
				}
				result.setSuccess(true);
				result.setObj(list);
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
			}
		} else {
			result.setSuccess(false);

		}
		return result;
	   
   }
}
