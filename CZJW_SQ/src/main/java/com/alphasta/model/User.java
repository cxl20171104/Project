package com.alphasta.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author gengzhuang
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 6700813629656881143L;

    private Long id;

    private String loginname;

    private String name;

    private String password;

	private Integer sex;

    private Integer age;

    private Integer usertype;

    private Integer status;

    private Integer organizationId;  //所属部门

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdate;

    private String phone;
    
    private String phone1;
    
    private String phone2;
    
    private String email;
    
    private Long positionId;

    private Integer isAdmin;
    
	private String qrcode;    //二维码
    
    private String liveness;  //默认0取操作日志的和
    
  
   
	private String ecode;    //员工编号
    
    private String post;      //职务
    
    private String idcard;   //身份证号码
    
    private String motto;   //座右铭(签名)
    
    private String headpic;   //头像URL
    
    private String dept;   
    
    private String organName;  //部门名称
    
    public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	private String token;
    
    private String cid;  //客户端ID
    
    public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date logintime; //登录时间
    
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


     
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

    @Override
	public String toString() {
		return "User [id=" + id + ", loginname=" + loginname + ", name=" + name
				+ ", password=" + password + ", sex=" + sex + ", age=" + age
				+ ", usertype=" + usertype + ", status=" + status
				+ ", organizationId=" + organizationId + ", createdate="
				+ createdate + ", phone=" + phone + ", phone1=" + phone1
				+ ", phone2=" + phone2 + ", email=" + email + ", positionId="
				+ positionId + ", isAdmin=" + isAdmin + ", qrcode=" + qrcode
				+ ", liveness=" + liveness + ", ecode=" + ecode + ", post="
				+ post + ", idcard=" + idcard + ", motto=" + motto
				+ ", headpic=" + headpic + ", dept=" + dept + ", token="
				+ token + ", logintime=" + logintime + "]";
	}



	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	  private String scores;
	    
	    public String getScores() {
			return scores;
		}

		public void setScores(String scores) {
			this.scores = scores;
		}
		private Date ctime;

		public Date getCtime() {
			return ctime;
		}

		public void setCtime(Date ctime) {
			this.ctime = ctime;
		}
}