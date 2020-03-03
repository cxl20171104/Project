package com.alphasta.model;

import java.io.Serializable;

public class Zjk implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id; 
	private String name;
	private String sex;
	private String over_unit;                 //工作单位
	private String education;                //学历
	private String major;                   //专业
	private String specialty;              //办案专长
	private String place;                 //籍贯
	private String bir_place;            //出生地
	private String working_time;        //从业时间
	private String party_member;       //是否党员
	private String party_time;        //入党时间
	private String is_health;        //是否健康
	private String tell;            //联系方式
	private String quan_ri_zhi;    //全日制教育
	private String zai_zhi;       //在职教育
	private String graduated;    //毕业院校
	private String experience;         //工作简历
	private String ji_lv_shen_cha;    //纪律审查方面的简历
	private String jiang_cheng;      //奖惩情况
	private String delete_status;   //删除状态
	private String createrId;      //创建人ID
	private String createTime;    //创建时间
	private String post;         //职务
	private String rank;        //职级
	private String birthday;   //专家出生年月
	private String title;                  //职称
	private String father;                //父亲姓名
	private String mother;               //母亲姓名
	private String father_unit;         //父亲工作单位
	private String mother_unit;        //母亲工作单位
	private String spouse;            //配偶姓名
	private String spouse_unit;      //配偶工作单位
	private String children;        //子女信息
	private String relative;       //近亲属信息
	private String filePath;      //保存路径
	private String nation;       //民族
	private String remark;      //备注
	private String nowstate;   //现在情况 
	
	
	public String getNowstate() {
		return nowstate;
	}
	public void setNowstate(String nowstate) {
		this.nowstate = nowstate;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDelete_status() {
		return delete_status;
	}
	public void setDelete_status(String delete_status) {
		this.delete_status = delete_status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOver_unit() {
		return over_unit;
	}
	public void setOver_unit(String over_unit) {
		this.over_unit = over_unit;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getWorking_time() {
		return working_time;
	}
	public void setWorking_time(String working_time) {
		this.working_time = working_time;
	}
	public String getParty_member() {
		return party_member;
	}
	public void setParty_member(String party_member) {
		this.party_member = party_member;
	}
	public String getGraduated() {
		return graduated;
	}
	public void setGraduated(String graduated) {
		this.graduated = graduated;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public String getCreaterId() {
		return createrId;
	}
	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public String getFather_unit() {
		return father_unit;
	}
	public void setFather_unit(String father_unit) {
		this.father_unit = father_unit;
	}
	public String getMother_unit() {
		return mother_unit;
	}
	public void setMother_unit(String mother_unit) {
		this.mother_unit = mother_unit;
	}
	public String getSpouse() {
		return spouse;
	}
	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}
	public String getSpouse_unit() {
		return spouse_unit;
	}
	public void setSpouse_unit(String spouse_unit) {
		this.spouse_unit = spouse_unit;
	}
	public String getChildren() {
		return children;
	}
	public void setChildren(String children) {
		this.children = children;
	}
	public String getRelative() {
		return relative;
	}
	public void setRelative(String relative) {
		this.relative = relative;
	}
	
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBir_place() {
		return bir_place;
	}
	public void setBir_place(String bir_place) {
		this.bir_place = bir_place;
	}
	public String getParty_time() {
		return party_time;
	}
	public void setParty_time(String party_time) {
		this.party_time = party_time;
	}
	public String getIs_health() {
		return is_health;
	}
	public void setIs_health(String is_health) {
		this.is_health = is_health;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getQuan_ri_zhi() {
		return quan_ri_zhi;
	}
	public void setQuan_ri_zhi(String quan_ri_zhi) {
		this.quan_ri_zhi = quan_ri_zhi;
	}
	public String getZai_zhi() {
		return zai_zhi;
	}
	public void setZai_zhi(String zai_zhi) {
		this.zai_zhi = zai_zhi;
	}
	public String getJi_lv_shen_cha() {
		return ji_lv_shen_cha;
	}
	public void setJi_lv_shen_cha(String ji_lv_shen_cha) {
		this.ji_lv_shen_cha = ji_lv_shen_cha;
	}
	public String getJiang_cheng() {
		return jiang_cheng;
	}
	public void setJiang_cheng(String jiang_cheng) {
		this.jiang_cheng = jiang_cheng;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Zjk [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", over_unit=" + over_unit + ", education=" + education
				+ ", major=" + major + ", specialty=" + specialty + ", place="
				+ place + ", bir_place=" + bir_place + ", working_time="
				+ working_time + ", party_member=" + party_member
				+ ", party_time=" + party_time + ", is_health=" + is_health
				+ ", tell=" + tell + ", quan_ri_zhi=" + quan_ri_zhi
				+ ", zai_zhi=" + zai_zhi + ", graduated=" + graduated
				+ ", experience=" + experience + ", ji_lv_shen_cha="
				+ ji_lv_shen_cha + ", jiang_cheng=" + jiang_cheng
				+ ", delete_status=" + delete_status + ", createrId="
				+ createrId + ", createTime=" + createTime + ", post=" + post
				+ ", rank=" + rank + ", birthday=" + birthday + ", title="
				+ title + ", father=" + father + ", mother=" + mother
				+ ", father_unit=" + father_unit + ", mother_unit="
				+ mother_unit + ", spouse=" + spouse + ", spouse_unit="
				+ spouse_unit + ", children=" + children + ", relative="
				+ relative + ", filePath=" + filePath + ", nation=" + nation
				+ ", remark=" + remark + ", nowstate=" + nowstate + "]";
	}

	
	

	
	
	
	
}
