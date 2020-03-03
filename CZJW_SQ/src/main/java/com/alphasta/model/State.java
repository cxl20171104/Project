package com.alphasta.model;

import java.util.Date;

public class State {
    private Long id;  //主键

    private String name;  //状态名称   1.初始导入 2. 移交承办 3.承办签收 4. 谈话函询 5.初步核实 6.暂存待查 7.给予了结

    private String czbm; //处置部门

    private Date updateime; //更新时间

    private Date dtime; //更新到期时间

    private String ajnum;  //案件编号

    private String updatename;  //更新人

    private Integer attcount;  //附件数量

    private Long ajxsid; //案件id

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
        this.name = name == null ? null : name.trim();
    }

    public String getCzbm() {
        return czbm;
    }

    public void setCzbm(String czbm) {
        this.czbm = czbm == null ? null : czbm.trim();
    }

    public Date getUpdateime() {
        return updateime;
    }

    public void setUpdateime(Date updateime) {
        this.updateime = updateime;
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public String getAjnum() {
        return ajnum;
    }

    public void setAjnum(String ajnum) {
        this.ajnum = ajnum == null ? null : ajnum.trim();
    }

    public String getUpdatename() {
        return updatename;
    }

    public void setUpdatename(String updatename) {
        this.updatename = updatename == null ? null : updatename.trim();
    }

    public Integer getAttcount() {
        return attcount;
    }

    public void setAttcount(Integer attcount) {
        this.attcount = attcount;
    }

    public Long getAjxsid() {
        return ajxsid;
    }

    public void setAjxsid(Long ajxsid) {
        this.ajxsid = ajxsid;
    }
}