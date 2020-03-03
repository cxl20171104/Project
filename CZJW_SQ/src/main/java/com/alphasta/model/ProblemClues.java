package com.alphasta.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.alphasta.commons.annotation.ExcelFild;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ProblemClues implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4063038855106797221L;
	private String id;//主键
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ExcelFild(title="收件日期")
	private String receiveDate; //收件日期
	@ExcelFild(title="线索来源")
	private String clues; //线索来源
	@ExcelFild(title="问题属地")
	private String problemLand; //问题属地
	@ExcelFild(title="问题描述")
	private String problemDescribe; //问题描述
	private String whereFrom; //办理进展/线索状态
	@ExcelFild(title="所属领域")
	private String fields;//领域
	@ExcelFild(title="备注")
	private String remarks;//remarks
	//更新时间/创建时间
	private Date createTime;
	// 是否单位或事件事故
	private String belongToId; 
	// 是否重复件
	private String duplicate; 
	// 重复案件所属ID
	private String duplicateId;
	//上传线索的部门
	private String organId;
	//专项行动
	@ExcelFild(title="专项行动")
	private String special;
	//关联类、
	private ReflectedPerson reflectedPerson;
	//被反映单位
	private ReflectedUnit reflectedUnit;
	//反映人
	private ReflectingPerson reflectingPerson;
	//案件编号
	private String cluesNum;
	//是否要结果
	private String isResult;
	//是否重要问题
	private String isImport;
	//类型
	private String type;
	//办理期限（是否要结果）
	private String resultTime;
	//监督室是否要结果
	private String isResult_jds;
	//监督室是否要结果期限
	private String resultTime_jds;
	//监督室交办日期
	private String giveTime_jds;
	//零件人
    private String givePersion_jds;
    //监督检查活动时间
    private String superviseTestTime;
    //进程时间
    private String expireTime;
    //线索来源id
    private String fromId;
    //是否签收案件
    private String isGet;
    //线索最终状态
    private String finalState;
    //来文编号
    private String lwbh;
    //是否为交办案件
    private String isXf;
    //当前承办人
    private String cbr_now;
    //线索留存状态
    private String state;
    //是否删除了
    private String isDel;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getClues() {
		return clues;
	}
	public void setClues(String clues) {
		this.clues = clues;
	}
	public String getProblemLand() {
		return problemLand;
	}
	public void setProblemLand(String problemLand) {
		this.problemLand = problemLand;
	}
	public String getProblemDescribe() {
		return problemDescribe;
	}
	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}
	public String getWhereFrom() {
		return whereFrom;
	}
	public void setWhereFrom(String whereFrom) {
		this.whereFrom = whereFrom;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getBelongToId() {
		return belongToId;
	}
	public void setBelongToId(String belongToId) {
		this.belongToId = belongToId;
	}
	public String getDuplicate() {
		return duplicate;
	}
	public void setDuplicate(String duplicate) {
		this.duplicate = duplicate;
	}
	public String getDuplicateId() {
		return duplicateId;
	}
	public void setDuplicateId(String duplicateId) {
		this.duplicateId = duplicateId;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public ReflectedPerson getReflectedPerson() {
		return reflectedPerson;
	}
	public void setReflectedPerson(ReflectedPerson reflectedPerson) {
		this.reflectedPerson = reflectedPerson;
	}	
	public ReflectedUnit getReflectedUnit() {
		return reflectedUnit;
	}
	public void setReflectedUnit(ReflectedUnit reflectedUnit) {
		this.reflectedUnit = reflectedUnit;
	}
	public ReflectingPerson getReflectingPerson() {
		return reflectingPerson;
	}
	public void setReflectingPerson(ReflectingPerson reflectingPerson) {
		this.reflectingPerson = reflectingPerson;
	}
	public String getCluesNum() {
		return cluesNum;
	}
	public void setCluesNum(String cluesNum) {
		this.cluesNum = cluesNum;
	}
	public String getIsResult() {
		return isResult;
	}
	public void setIsResult(String isResult) {
		this.isResult = isResult;
	}
	public String getIsImport() {
		return isImport;
	}
	public void setIsImport(String isImport) {
		this.isImport = isImport;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResultTime() {
		return resultTime;
	}
	public void setResultTime(String resultTime) {
		this.resultTime = resultTime;
	}
	public String getIsResult_jds() {
		return isResult_jds;
	}
	public void setIsResult_jds(String isResult_jds) {
		this.isResult_jds = isResult_jds;
	}
	public String getResultTime_jds() {
		return resultTime_jds;
	}
	public void setResultTime_jds(String resultTime_jds) {
		this.resultTime_jds = resultTime_jds;
	}
	public String getGiveTime_jds() {
		return giveTime_jds;
	}
	public void setGiveTime_jds(String giveTime_jds) {
		this.giveTime_jds = giveTime_jds;
	}
	public String getGivePersion_jds() {
		return givePersion_jds;
	}
	public void setGivePersion_jds(String givePersion_jds) {
		this.givePersion_jds = givePersion_jds;
	}
	public String getSuperviseTestTime() {
		return superviseTestTime;
	}
	public void setSuperviseTestTime(String superviseTestTime) {
		this.superviseTestTime = superviseTestTime;	
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	
	public String getIsGet() {
		return isGet;
	}
	public void setIsGet(String isGet) {
		this.isGet = isGet;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getLwbh() {
		return lwbh;
	}
	public void setLwbh(String lwbh) {
		this.lwbh = lwbh;
	}
	public String getIsXf() {
		return isXf;
	}
	public void setIsXf(String isXf) {
		this.isXf = isXf;
	}
	public String getFinalState() {
		return finalState;
	}
	public void setFinalState(String finalState) {
		this.finalState = finalState;
	}
	public String getCbr_now() {
		return cbr_now;
	}
	public void setCbr_now(String cbr_now) {
		this.cbr_now = cbr_now;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	
	
	
}
