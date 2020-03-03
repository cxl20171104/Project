package com.alphasta.model;

import com.alphasta.commons.annotation.ExcelFild;

public class ExcelPojo {
	//线索方面
	@ExcelFild(title="线索id")
	private String id;
	@ExcelFild(title="案件编号")
	private String cluesNum;
	@ExcelFild(title="是否单位或事件事故")
	private String belongToId;
	@ExcelFild(title="是否重要问题")
	private String isImport;
	@ExcelFild(title="是否要结果")
	private String isResult;
	//private String isImport;
	@ExcelFild(title="收件日期")
	private String receiveDate; //收件日期
	@ExcelFild(title="线索来源")
	private String clues; //线索来源================================单选
	@ExcelFild(title="来文编号")//来文编号
    private String lwbh;
	@ExcelFild(title="办理期限")
	private String resultTime;
	@ExcelFild(title="类型")
	private String type;
	@ExcelFild(title="监督检查活动时间")
	private String superviseTestTime;
	@ExcelFild(title="是否督办件")
	private String collectionMoney;//收缴违纪资金
	@ExcelFild(title="所属领域")
	private String fields;//所属领域==============================多选
	@ExcelFild(title="挽回经济损失")
	private String toSaveMoney;//挽回经济损失
	@ExcelFild(title="专项行动")
	private String special;//专项行动===========================单选
	@ExcelFild(title="承办单位")
	private String  orgaName;
	@ExcelFild(title="线索进度")
	private String  progress;
	
	
	
	
	
	
	
	
	
	
	
	
	//被反映人方面
	@ExcelFild(title="被反映人姓名")
	private String reflectedName; //被反映人姓名
	@ExcelFild(title="被反映人职务")
	private String duty; //被反映人单位职务
	@ExcelFild(title="职级")
	private String rank; //职级================================单选
	@ExcelFild(title="出生年月")
	private String birthday; //出生年月
	@ExcelFild(title="被反映人性别")
	private String sex;
	@ExcelFild(title="学历")
	private String xl; 
	@ExcelFild(title="身份")
	private String civilServant;
	@ExcelFild(title="是否中共党员")
	private String partyMember; //是否中共党员================================单选
	@ExcelFild(title="政治面貌")
	private String political;
	@ExcelFild(title="入党时间")
	private String intime; //入党时间
	@ExcelFild(title="任职时间")
	private String worktime;
	@ExcelFild(title="部门分类")
	private String departmenType;
	@ExcelFild(title="企业性质")
	private String natureOfenterprise;
	@ExcelFild(title="企业级别")
	private String classOfenterprise;
	@ExcelFild(title="岗位")
	private String post;
	@ExcelFild(title="企业人员类别")
	private String posType;
	@ExcelFild(title="一把手违纪违法")
	private String topDiscipline;
	@ExcelFild(title="干部管理权限")
	private String cadre;
	@ExcelFild(title="中共党代表")
	private String partyRepresent;
	@ExcelFild(title="证件类型")
	private String documentType;
	@ExcelFild(title="证件号码")
	private String identificationNumber; //证件号码
	@ExcelFild(title="被反映人民族")
	private String nation; //民族
	@ExcelFild(title="被反映人单位")
	private String workPosition; //工作单位
	@ExcelFild(title="人大代表")
	private String np; //是否人大代表================================单选
	@ExcelFild(title="政协委员")
	private String zx; //是否政协委员================================单选
	@ExcelFild(title="党委委员")
	private String dwMember;
	@ExcelFild(title="纪委委员")
	private String jwMember;
	@ExcelFild(title="是否违反中央八项规定精神")
	private String eightSpirit;
	@ExcelFild(title="是否国家监察对象")
	private String iSupervision; //是否国家监察对象================================单选
	@ExcelFild(title="国家监察对象类型")
	private  String supervision;
	@ExcelFild(title="是否非党员非监察对象")
	private String isPMSupervisoryObject; //是否非党员非检查对象================================单选
	@ExcelFild(title="非党员非监察对象类型")
	private  String pMSupervisoryObject;
	@ExcelFild(title="涉法行为")
	private  String fylegalAct;
	@ExcelFild(title="反映违纪性质")
	private String fyzvviolation; //是否政协委员================================单选
	//反映人方面
	
	@ExcelFild(title="反映人姓名")
	private String reflectingName; //反映人姓名
	@ExcelFild(title="反映人单位")
	private String reflectingDept;//反映人单位
	@ExcelFild(title="反映人联系方式")
	private String tell;//反映人电话
	
	//处置方面
	@ExcelFild(title="违纪行为")
	private String zyName; //违纪行为=================多选
	@ExcelFild(title="职务违法犯罪行为")
	private String legalact_zw; //职务违法犯罪行为=================多选
	@ExcelFild(title="职务违法犯罪行为")
	private String legalact_qt; //职务违法犯罪行为=================多选
	@ExcelFild(title="处置方式")
	private String czMethod_all; //处置方式=================多项
	@ExcelFild(title="组织措施")
	private String sls_meaResult; //组织措施=================多选
	
	@ExcelFild(title="问题属地")
	private String problemLand;//问题属地===========================单选
	@ExcelFild(title="问题描述")
	private String problemDescribe;//问题描述
	@ExcelFild(title="备注")
	private String remarks;//备注
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCluesNum() {
		return cluesNum;
	}
	public void setCluesNum(String cluesNum) {
		this.cluesNum = cluesNum;
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
	public String getCollectionMoney() {
		return collectionMoney;
	}
	public void setCollectionMoney(String collectionMoney) {
		this.collectionMoney = collectionMoney;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getToSaveMoney() {
		return toSaveMoney;
	}
	public void setToSaveMoney(String toSaveMoney) {
		this.toSaveMoney = toSaveMoney;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getReflectedName() {
		return reflectedName;
	}
	public void setReflectedName(String reflectedName) {
		this.reflectedName = reflectedName;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPartyMember() {
		return partyMember;
	}
	public void setPartyMember(String partyMember) {
		this.partyMember = partyMember;
	}
	public String getIntime() {
		return intime;
	}
	public void setIntime(String intime) {
		this.intime = intime;
	}
	public String getiSupervision() {
		return iSupervision;
	}
	public void setiSupervision(String iSupervision) {
		this.iSupervision = iSupervision;
	}
	public String getIsPMSupervisoryObject() {
		return isPMSupervisoryObject;
	}
	public void setIsPMSupervisoryObject(String isPMSupervisoryObject) {
		this.isPMSupervisoryObject = isPMSupervisoryObject;
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getWorkPosition() {
		return workPosition;
	}
	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}
	public String getNp() {
		return np;
	}
	public void setNp(String np) {
		this.np = np;
	}
	public String getZx() {
		return zx;
	}
	public void setZx(String zx) {
		this.zx = zx;
	}
	public String getReflectingName() {
		return reflectingName;
	}
	public void setReflectingName(String reflectingName) {
		this.reflectingName = reflectingName;
	}
	public String getReflectingDept() {
		return reflectingDept;
	}
	public void setReflectingDept(String reflectingDept) {
		this.reflectingDept = reflectingDept;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getZyName() {
		return zyName;
	}
	public void setZyName(String zyName) {
		this.zyName = zyName;
	}
	public String getLegalact_zw() {
		return legalact_zw;
	}
	public void setLegalact_zw(String legalact_zw) {
		this.legalact_zw = legalact_zw;
	}
	public String getLegalact_qt() {
		return legalact_qt;
	}
	public void setLegalact_qt(String legalact_qt) {
		this.legalact_qt = legalact_qt;
	}
	public String getCzMethod_all() {
		return czMethod_all;
	}
	public void setCzMethod_all(String czMethod_all) {
		this.czMethod_all = czMethod_all;
	}
	public String getSls_meaResult() {
		return sls_meaResult;
	}
	public void setSls_meaResult(String sls_meaResult) {
		this.sls_meaResult = sls_meaResult;
	}
	public String getOrgaName() {
		return orgaName;
	}
	public void setOrgaName(String orgaName) {
		this.orgaName = orgaName;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getFyzvviolation() {
		return fyzvviolation;
	}
	public void setFyzvviolation(String fyzvviolation) {
		this.fyzvviolation = fyzvviolation;
	}
	public String getBelongToId() {
		return belongToId;
	}
	public void setBelongToId(String belongToId) {
		this.belongToId = belongToId;
	}
	public String getIsImport() {
		return isImport;
	}
	public void setIsImport(String isImport) {
		this.isImport = isImport;
	}
	public String getIsResult() {
		return isResult;
	}
	public void setIsResult(String isResult) {
		this.isResult = isResult;
	}
	public String getLwbh() {
		return lwbh;
	}
	public void setLwbh(String lwbh) {
		this.lwbh = lwbh;
	}
	public String getResultTime() {
		return resultTime;
	}
	public void setResultTime(String resultTime) {
		this.resultTime = resultTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSuperviseTestTime() {
		return superviseTestTime;
	}
	public void setSuperviseTestTime(String superviseTestTime) {
		this.superviseTestTime = superviseTestTime;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getCivilServant() {
		return civilServant;
	}
	public void setCivilServant(String civilServant) {
		this.civilServant = civilServant;
	}
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	public String getDepartmenType() {
		return departmenType;
	}
	public void setDepartmenType(String departmenType) {
		this.departmenType = departmenType;
	}
	public String getNatureOfenterprise() {
		return natureOfenterprise;
	}
	public void setNatureOfenterprise(String natureOfenterprise) {
		this.natureOfenterprise = natureOfenterprise;
	}
	public String getClassOfenterprise() {
		return classOfenterprise;
	}
	public void setClassOfenterprise(String classOfenterprise) {
		this.classOfenterprise = classOfenterprise;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getPosType() {
		return posType;
	}
	public void setPosType(String posType) {
		this.posType = posType;
	}
	public String getTopDiscipline() {
		return topDiscipline;
	}
	public void setTopDiscipline(String topDiscipline) {
		this.topDiscipline = topDiscipline;
	}
	public String getCadre() {
		return cadre;
	}
	public void setCadre(String cadre) {
		this.cadre = cadre;
	}
	public String getPartyRepresent() {
		return partyRepresent;
	}
	public void setPartyRepresent(String partyRepresent) {
		this.partyRepresent = partyRepresent;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDwMember() {
		return dwMember;
	}
	public void setDwMember(String dwMember) {
		this.dwMember = dwMember;
	}
	public String getJwMember() {
		return jwMember;
	}
	public void setJwMember(String jwMember) {
		this.jwMember = jwMember;
	}
	public String getEightSpirit() {
		return eightSpirit;
	}
	public void setEightSpirit(String eightSpirit) {
		this.eightSpirit = eightSpirit;
	}
	public String getSupervision() {
		return supervision;
	}
	public void setSupervision(String supervision) {
		this.supervision = supervision;
	}
	public String getpMSupervisoryObject() {
		return pMSupervisoryObject;
	}
	public void setpMSupervisoryObject(String pMSupervisoryObject) {
		this.pMSupervisoryObject = pMSupervisoryObject;
	}
	
	
	
}
