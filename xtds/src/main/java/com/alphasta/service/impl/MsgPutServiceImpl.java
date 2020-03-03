package com.alphasta.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.MsgPutMapper;
import com.alphasta.model.MsgPut;
import com.alphasta.model.MsgReply;
import com.alphasta.service.MsgPutService;

@Service
public class MsgPutServiceImpl implements MsgPutService {

	@Autowired
	private MsgPutMapper msgPutMapper;

	/**
	 * 查询发布的信息
	 */
	@Override
	public List<MsgPut> getMsgPut(PageInfo pageInfo) {
		// TODO Auto-generated method stub

		return msgPutMapper.findMsgPut(pageInfo);
	}

	/**
	 * 发送一条信息
	 */

	public void putOutMsg(MsgPut msgPut) {
		msgPutMapper.putMsg(msgPut);

	}

	@Override
	public List<MsgReply> getMsgReplylist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return msgPutMapper.findMsgReply(map);
	}

	@Override
	public void insetReply(MsgReply msgReply) {
		msgPutMapper.insetReply(msgReply);

	}

	@Override
	public void replyMsg(MsgReply msgReply) {
		// TODO Auto-generated method stub
		msgPutMapper.replyMsg(msgReply);

	}

}
