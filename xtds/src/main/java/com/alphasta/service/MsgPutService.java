package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.MsgPut;
import com.alphasta.model.MsgReply;

public interface MsgPutService {

	public List<MsgPut> getMsgPut(PageInfo pageInfo);

	public void putOutMsg(MsgPut msgPut);

	public List<MsgReply> getMsgReplylist(Map<String, Object> map);

	public void insetReply(MsgReply msgReply);

	public void replyMsg(MsgReply msgReply);

}
