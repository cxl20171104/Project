package com.alphasta.commons.result;

import com.alphasta.model.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description：UserVo
 * @author：liyunhao
 * @date：2015/10/1 14:51
 */
public class UserVo implements Serializable {
	private Long id;

	private String loginname;

	private String name;

	private String password;
    private String seq;
    
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	private Integer sex;

	private Integer age;

	private Integer usertype;

	private Integer status;

	private Integer organizationId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdate;

	private String phone;

	private String phone1;

	private String phone2;

	private String email;

	private Long positionId;
	private Long positionTwoId;
	

	public Long getPositionTwoId() {
		return positionTwoId;
	}

	public void setPositionTwoId(Long positionTwoId) {
		this.positionTwoId = positionTwoId;
	}

	private List<Role> rolesList;

	private String organizationName;

	private String positionName;
	private String positionTwoName;

	public String getPositionTwoName() {
		return positionTwoName;
	}

	public void setPositionTwoName(String positionTwoName) {
		this.positionTwoName = positionTwoName;
	}

	private String roleIds;

	private Date createdateStart;
	private Date createdateEnd;

	private Integer isAdmin;

	private String qrcode; // 二维码

	private String liveness; // 默认0取操作日志的和

	private String ecode; // 员工编号

	private String post; // 职务

	private String idcard; // 身份证号码

	private String motto; // 座右铭(签名)

	private String headpic; // 头像URL

	private String dept; // 所属部门

	private String token;

	private String intime; // 入党时间
	
	
	
	private String nation; //民族 
	private String workingTime;//工作时间
	private String xue_li; //学历
	private String xue_wei; //学位
	private String love;
	private String ji_shu_zi_ge; //技术资格
	private String is_tui;  //是否退休
    
	public String getLove() {
		return love;
	}

	public void setLove(String love) {
		this.love = love;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}

	public String getXue_li() {
		return xue_li;
	}

	public void setXue_li(String xue_li) {
		this.xue_li = xue_li;
	}

	public String getXue_wei() {
		return xue_wei;
	}

	public void setXue_wei(String xue_wei) {
		this.xue_wei = xue_wei;
	}

	public String getJi_shu_zi_ge() {
		return ji_shu_zi_ge;
	}

	public void setJi_shu_zi_ge(String ji_shu_zi_ge) {
		this.ji_shu_zi_ge = ji_shu_zi_ge;
	}

	public String getIs_tui() {
		return is_tui;
	}

	public void setIs_tui(String is_tui) {
		this.is_tui = is_tui;
	}


	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date logintime; // 登录时间

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getLiveness() {
		return liveness;
	}

	public void setLiveness(String liveness) {
		this.liveness = liveness;
	}

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getHeadpic() {
		return headpic;
	}

	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private static final long serialVersionUID = 1L;

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname == null ? null : loginname.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public List<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Date getCreatedateStart() {
		return createdateStart;
	}

	public void setCreatedateStart(Date createdateStart) {
		this.createdateStart = createdateStart;
	}

	public Date getCreatedateEnd() {
		return createdateEnd;
	}

	public void setCreatedateEnd(Date createdateEnd) {
		this.createdateEnd = createdateEnd;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
}