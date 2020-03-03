package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.MsgPut;
import com.alphasta.model.MsgReply;

public interface MsgPutMapper {
	/**
	 * 信息发送列表
	 * 
	 * @param pageInfo
	 * @return
	 */
	public List<MsgPut> findMsgPut(PageInfo pageInfo);

	/**
	 * 发送的信息 insert
	 * 
	 * @param msgPut
	 */
	public void putMsg(MsgPut msgPut);

	/**
	 * 回复信息列表
	 * 
	 * @param map
	 * @return
	 */
	public List<MsgReply> findMsgReply(Map<String, Object> map);

	/**
	 * 回查看
	 * 
	 * @param msgReply
	 */
	public void insetReply(MsgReply msgReply);

	/**
	 * 查看信息
	 * 
	 * @param msgReply
	 */
	public void replyMsg(MsgReply msgReply);

}
