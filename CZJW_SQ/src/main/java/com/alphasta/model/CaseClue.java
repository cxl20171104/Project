package com.alphasta.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 案件线索
 * 
 * @author LiYunhao
 *
 */
public class CaseClue implements Serializable {
	private static final long serialVersionUID = 4043528860546917150L;

	private Long id;
	// 编号
	private String num;
	// 信访编号
	private String lvNum;
	// 收件日期
	private String receiveDate;
	// 信访类型
	private String lvType;
	// 反映人姓名
	private String reflectName;
	// 反映人职务
	private String reflectDuty;
	// 被反映人姓名
	private String beReflectName;
	// 被反映人职务
	private String beReflectDuty;
	// 被反映人职级
	private String beReflectRank;
	// 问题性质
	private String problemNature;
	// 问题摘要
	private String problemAbstract;
	// 领导批示
	private String leaderComment;
	// 办理方式
	private String disposeType;
	// 办理单位
	private String disposeUnit;
	// 备注
	private String remark;
	// 填表单位
	private String writeUnit;
	// 开始日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startDate;
	// 结束日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endDate;
	// 创建人
	private Long createrId;
	// 创建时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	// 创建人model
	private User createUser;
	//转来单位
	private String toCompany;
	//上级交办
	private String clue_source;
	//上级交办案件到期时间
	private Date clue_source_etime;
	//部门
	private String department;
	//受理时间
	private String accepance_time;
	//是否党员 0：否 1：是
	private String party_member;
	//在职或退休  0：在职 1：退休
	private String incumbency;
	//实名反映匿名反映  0：实名  2：匿名
	private String reflect;
	//备选项   0：移交  1：备案
	private String options;
	//是否要办理结果  0：否 1：是
	private String handing_results;
	//信访上传图片
	private String petition_photo;
	//签批意见图片上传
	private String batch_tphoto;
	//分办意见
	private String fb_view;
	//主管常委批示
	private String gzcw_view;
	//副管副书记批示
	private String fgfsj_view;
	//纪委书记批示
	private String jwsj_view;
	//书记批示
	private String swsj_view;
	//是否人大代表或政协委员 0：否  1：是
	private String deputies;
	//处置部门
	private String czbm;
	//线索状态
	private String xsstate;
	//最后更新时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date etime;
	//重复情况（如果重复了，保存重复线索的id）
	private String cfstate;
	//是否删除 0：已经删除，1：没有删除
	private String isDel;
	//是否重复是否重复（系统判定不重复0，系统判定重复1，人工判定不重复2，人工判定重复3）
	private String sfcf;
	//涵号
	private String culvert;
	//所属区域
	private String action;
	//所属行业
	private String hang_ye;
	//提醒时间
	private String remindTime; 
	
	

	public String getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(String remindTime) {
		this.remindTime = remindTime;
	}
	public String getToCompany() {
		return toCompany;
	}
	public void setToCompany(String toCompany) {
		this.toCompany = toCompany;
	}
	public Date getClue_source_etime() {
		return clue_source_etime;
	}
	public void setClue_source_etime(Date clue_source_etime) {
		this.clue_source_etime = clue_source_etime;
	}
	public String getXsstate() {
		return xsstate;
	}
	public void setXsstate(String xsstate) {
		this.xsstate = xsstate;
	}
	public String getCulvert() {
		return culvert;
	}
	public void setCulvert(String culvert) {
		this.culvert = culvert;
	}
	public String getParty_member() {
		return party_member;
	}
	public void setParty_member(String party_member) {
		this.party_member = party_member;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getLvNum() {
		return lvNum;
	}
	public void setLvNum(String lvNum) {
		this.lvNum = lvNum;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getLvType() {
		return lvType;
	}
	public void setLvType(String lvType) {
		this.lvType = lvType;
	}
	public String getReflectName() {
		return reflectName;
	}
	public void setReflectName(String reflectName) {
		this.reflectName = reflectName;
	}
	public String getBeReflectName() {
		return beReflectName;
	}
	public void setBeReflectName(String beReflectName) {
		this.beReflectName = beReflectName;
	}
	public String getBeReflectDuty() {
		return beReflectDuty;
	}
	public void setBeReflectDuty(String beReflectDuty) {
		this.beReflectDuty = beReflectDuty;
	}
	public String getBeReflectRank() {
		return beReflectRank;
	}
	public void setBeReflectRank(String beReflectRank) {
		this.beReflectRank = beReflectRank;
	}
	public String getProblemNature() {
		return problemNature;
	}
	public void setProblemNature(String problemNature) {
		this.problemNature = problemNature;
	}
	public String getProblemAbstract() {
		return problemAbstract;
	}
	public void setProblemAbstract(String problemAbstract) {
		this.problemAbstract = problemAbstract;
	}
	public String getLeaderComment() {
		return leaderComment;
	}
	public void setLeaderComment(String leaderComment) {
		this.leaderComment = leaderComment;
	}
	public String getDisposeType() {
		return disposeType;
	}
	public void setDisposeType(String disposeType) {
		this.disposeType = disposeType;
	}
	
	public String getDisposeUnit() {
		return disposeUnit;
	}
	public void setDisposeUnit(String disposeUnit) {
		this.disposeUnit = disposeUnit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWriteUnit() {
		return writeUnit;
	}
	public void setWriteUnit(String writeUnit) {
		this.writeUnit = writeUnit;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getCreaterId() {
		return createrId;
	}
	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getIncumbency() {
		return incumbency;
	}
	public void setIncumbency(String incumbency) {
		this.incumbency = incumbency;
	}
	public String getReflect() {
		return reflect;
	}
	public void setReflect(String reflect) {
		this.reflect = reflect;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	public String getHanding_results() {
		return handing_results;
	}
	public void setHanding_results(String handing_results) {
		this.handing_results = handing_results;
	}
	public String getPetition_photo() {
		return petition_photo;
	}
	public void setPetition_photo(String petition_photo) {
		this.petition_photo = petition_photo;
	}
	public String getBatch_tphoto() {
		return batch_tphoto;
	}
	public void setBatch_tphoto(String batch_tphoto) {
		this.batch_tphoto = batch_tphoto;
	}
	public String getFb_view() {
		return fb_view;
	}
	public void setFb_view(String fb_view) {
		this.fb_view = fb_view;
	}
	public String getGzcw_view() {
		return gzcw_view;
	}
	public void setGzcw_view(String gzcw_view) {
		this.gzcw_view = gzcw_view;
	}
	public String getFgfsj_view() {
		return fgfsj_view;
	}
	public void setFgfsj_view(String fgfsj_view) {
		this.fgfsj_view = fgfsj_view;
	}
	public String getJwsj_view() {
		return jwsj_view;
	}
	public void setJwsj_view(String jwsj_view) {
		this.jwsj_view = jwsj_view;
	}
	public String getSwsj_view() {
		return swsj_view;
	}
	public void setSwsj_view(String swsj_view) {
		this.swsj_view = swsj_view;
	}
	public String getDeputies() {
		return deputies;
	}
	public void setDeputies(String deputies) {
		this.deputies = deputies;
	}
	public String getCzbm() {
		return czbm;
	}
	public void setCzbm(String czbm) {
		this.czbm = czbm;
	}
	public Date getEtime() {
		return etime;
	}
	public void setEtime(Date etime) {
		this.etime = etime;
	}
	public String getCfstate() {
		return cfstate;
	}
	public void setCfstate(String cfstate) {
		this.cfstate = cfstate;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getSfcf() {
		return sfcf;
	}
	public void setSfcf(String sfcf) {
		this.sfcf = sfcf;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getAccepance_time() {
		return accepance_time;
	}
	public void setAccepance_time(String accepance_time) {
		this.accepance_time = accepance_time;
	}
	
	public String getReflectDuty() {
		return reflectDuty;
	}
	public void setReflectDuty(String reflectDuty) {
		this.reflectDuty = reflectDuty;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
	public String getHang_ye() {
		return hang_ye;
	}
	public void setHang_ye(String hang_ye) {
		this.hang_ye = hang_ye;
	}

	@Override
	public String toString() {
		return "CaseClue [id=" + id + ", num=" + num + ", lvNum=" + lvNum
				+ ", receiveDate=" + receiveDate + ", lvType=" + lvType
				+ ", reflectName=" + reflectName + ", reflectDuty="
				+ reflectDuty + ", beReflectName=" + beReflectName
				+ ", beReflectDuty=" + beReflectDuty + ", beReflectRank="
				+ beReflectRank + ", problemNature=" + problemNature
				+ ", problemAbstract=" + problemAbstract + ", leaderComment="
				+ leaderComment + ", disposeType=" + disposeType
				+ ", disposeUnit=" + disposeUnit + ", remark=" + remark
				+ ", writeUnit=" + writeUnit + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", createrId=" + createrId
				+ ", createTime=" + createTime + ", createUser=" + createUser
				+ ", toCompany=" + toCompany + ", clue_source=" + clue_source
				+ ", clue_source_etime=" + clue_source_etime + ", department="
				+ department + ", accepance_time=" + accepance_time
				+ ", party_member=" + party_member + ", incumbency="
				+ incumbency + ", reflect=" + reflect + ", options=" + options
				+ ", handing_results=" + handing_results + ", petition_photo="
				+ petition_photo + ", batch_tphoto=" + batch_tphoto
				+ ", fb_view=" + fb_view + ", gzcw_view=" + gzcw_view
				+ ", fgfsj_view=" + fgfsj_view + ", jwsj_view=" + jwsj_view
				+ ", swsj_view=" + swsj_view + ", deputies=" + deputies
				+ ", czbm=" + czbm + ", xsstate=" + xsstate + ", etime="
				+ etime + ", cfstate=" + cfstate + ", isDel=" + isDel
				+ ", sfcf=" + sfcf + ", culvert=" + culvert + ", action="
				+ action + ", hang_ye=" + hang_ye + ", remindTime="
				+ remindTime + "]";
	}
	public String getClue_source() {
		return clue_source;
	}
	public void setClue_source(String clue_source) {
		this.clue_source = clue_source;
	}
	
}
