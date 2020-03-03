package com.alphasta.commons.result;

import java.io.Serializable;

/**
 * @description：操作结果集
 * @author：liyunhao
 * @date：2015/10/1 14:51
 */
public class Result implements Serializable {

    public static final int SUCCESS = 1;
    public static final int FAILURE = -1;

    private static final long serialVersionUID = 5576237395711742681L;

    private boolean success = false;

    private String msg = "";

    private Object obj = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public Result(String msg) {
		super();
		this.msg = msg;
	}
   
}
