package com.alphasta.mapper;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Remind;

public interface RemindMapper {
   
	/**
	 * 查询未读消息
	 * 
	 */
	List<Remind> messageList(Remind remind);
	
	/**
	 * 消息提醒分页
	 * 
	 * @param pageInfo
	 * @return
	 */
	List<Remind> allMessageListPageCondition(PageInfo pageInfo);
	
	/**
	 * 消息提醒数量
	 */
	int allMessageListPageCount(PageInfo pageInfo);
	
	/**
	 * 信息状态更新
	 */
	int updateState(Remind remind);

	/**
	 * 新增一条消息提醒
	 * @param remind
	 * @return
	 */
	int insert(Remind remind);
}
