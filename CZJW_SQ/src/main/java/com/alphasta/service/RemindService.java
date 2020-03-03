package com.alphasta.service;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.CaseClue;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Remind;

public interface RemindService {
	
	/**
	 * 查询未读消息
	 */
	List<Remind> messageList(Remind remind);
	
	/**
	 * 消息提醒分页
	 * 
	 * @param pageInfo
	 * @return
	 */
	void allMessageList(PageInfo pageInfo);
	
	/**
	 * 消息已读状态更改
	 */
	int updateState(Remind remind);
	
	/**
	 * 新添加一条消息提醒
	 * 
	 * @param Remind
	 * @return
	 */
	int insert(Remind remind);
}
