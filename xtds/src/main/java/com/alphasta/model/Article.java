package com.alphasta.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Article {
	private String id;

	private String title;

	private String subtitle;

	private String shorttitle;

	private String inputer; // 发布人

	private String inputerpic; // 发布人头像

	private String inputerName;

	private String author; // 作者 不填写则为发布人

	private String authorName;

	private Integer hits; // 点击量

	private Integer thumb; // 点赞数

	private Integer comnum; // 评论数

	private String thumber; // 点赞人
	private Date createtime;

	private Integer area;

	private Integer comentable;

	private String expire;

	private String catalogs;  

	private String catalogName; // 所属栏目的名字

	private String refusecontent;

	private String audituser;

	private String audituserName; // 审核人的名字

	private Integer scoretype; //文章所属活动类型  1 活动方案  2  活动图片 3. 制度规定4.学习资料

	private String scores;

	private Integer state;

	private String remaind1;

	private String content;

	private String hasseen; // 文章是否被当前用户查看过 0 没有 1 看过了

	private String hasGreat; // 文章是否点赞 0 没有 1 有

	private String actname; // 所属活动
	
	private String organid;

	public String getActname() {
		return actname;
	}

	public void setActname(String actname) {
		if (this.catalogName == null || "".equals(this.catalogName)) {
			this.catalogName = actname;
		}
		this.actname = actname;
	}

	public String getHasGreat() {
		return hasGreat;
	}

	public void setHasGreat(String hasGreat) {
		this.hasGreat = hasGreat;
	}

	private String headpic; // 文章封面

	private String allurl; // 文章图片路径
	private String arcid;//修改文章使用的id

	public String getArcid() {
		return arcid;
	}

	public void setArcid(String arcid) {
		this.arcid = arcid;
	}

	public String getAllurl() {
		return allurl;
	}

	public void setAllurl(String allurl) {
		this.allurl = allurl;
	}

	public String getHeadpic() {
		return headpic;
	}

	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getAudituserName() {
		return audituserName;
	}

	public void setAudituserName(String audituserName) {
		this.audituserName = audituserName;
	}

	public String getInputerpic() {
		return inputerpic;
	}

	public void setInputerpic(String inputerpic) {
		this.inputerpic = inputerpic;
	}

	public String getHasseen() {
		return hasseen;
	}

	public void setHasseen(String hasseen) {
		this.hasseen = hasseen;
	}

	public String getInputerName() {
		return inputerName;
	}

	public void setInputerName(String inputerName) {
		this.inputerName = inputerName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle == null ? null : subtitle.trim();
	}

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle == null ? null : shorttitle.trim();
	}

	public String getInputer() {
		return inputer;
	}

	public void setInputer(String inputer) {
		this.inputer = inputer == null ? null : inputer.trim();
		// this.audituser=this.audituser==null?inputer.trim():this.audituser;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getThumb() {
		return thumb;
	}

	public void setThumb(Integer thumb) {
		this.thumb = thumb;
	}

	public Integer getComnum() {
		return comnum;
	}

	public void setComnum(Integer comnum) {
		this.comnum = comnum;
	}

	public String getThumber() {
		return thumber;
	}

	public void setThumber(String thumber) {
		this.thumber = thumber == null ? null : thumber.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}
	public String getCtime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(this.createtime!=null){
			return sdf.format(this.createtime);
		}
		return sdf.format(new Date());
	}

	public void setCreatetime(Date createtime) {
		
		this.createtime = createtime;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getComentable() {
		return comentable;
	}

	public void setComentable(Integer comentable) {
		this.comentable = comentable;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(String catalogs) {
		this.catalogs = catalogs == null ? null : catalogs.trim();
	}

	public String getRefusecontent() {
		return refusecontent;
	}

	public void setRefusecontent(String refusecontent) {
		this.refusecontent = refusecontent == null ? null : refusecontent
				.trim();
	}

	public String getAudituser() {
		return audituser;
	}

	public void setAudituser(String audituser) {
		this.audituser = audituser == null ? null : audituser.trim();
	}

	public Integer getScoretype() {
		return scoretype;
	}

	public void setScoretype(Integer scoretype) {
		this.scoretype = scoretype;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores == null ? null : scores.trim();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemaind1() {
		return remaind1;
	}

	public void setRemaind1(String remaind1) {
		this.remaind1 = remaind1 == null ? null : remaind1.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getOrganid() {
		return organid;
	}

	public void setOrganid(String organid) {
		this.organid = organid;
	}
	
}