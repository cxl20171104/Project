package com.alphasta.commons.utils;

import java.io.IOException;

import com.alphasta.model.MsgPut;

public class MsyToAllUser {
	
	private MsgPut msgPut;

	public MsgPut getMsgPut() {
		return msgPut;
	}

	public void setMsgPut(MsgPut msgPut) {
		this.msgPut = msgPut;
	}

	public MsyToAllUser(MsgPut msgPut) {
		this.msgPut = msgPut;
	}
    /**
     *  党费通知专用
     * @param title
     * @param content
     */
	public MsyToAllUser(String title,String content) {
		MsgPut m=new MsgPut();
		m.setTitle(title);
		m.setContent("党费通知:"+content);
		this.msgPut=m;		
	}
	public void sentOut(){
		try {
			GetuiUtil.putMsgToAll(this.msgPut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
