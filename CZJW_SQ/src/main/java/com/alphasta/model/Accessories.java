package com.alphasta.model;

import java.util.Date;

public class Accessories {
    private Long id;

    private String name;

    private String url;

    private String title;

    private String content;

    private String uploader;

    private String uploadername;

    private Date uploadate;

    private String caseId;

    private Integer attcount;

    private String isdel;
    
    private String attacNum;//附件的数量
    
    private Integer organId;
    
    private String pointValue;
    
    private String type;

    public String getAttacNum() {
		return attacNum;
	}

	public void setAttacNum(String attacNum) {
		this.attacNum = attacNum;
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
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader == null ? null : uploader.trim();
    }

    public String getUploadername() {
        return uploadername;
    }

    public void setUploadername(String uploadername) {
        this.uploadername = uploadername == null ? null : uploadername.trim();
    }

    public Date getUploadate() {
        return uploadate;
    }

    public void setUploadate(Date uploadate) {
        this.uploadate = uploadate;
    }

    

  

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public Integer getAttcount() {
        return attcount;
    }

    public void setAttcount(Integer attcount) {
        this.attcount = attcount;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }



	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public String getPointValue() {
		return pointValue;
	}

	public void setPointValue(String pointValue) {
		this.pointValue = pointValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
    
    
}