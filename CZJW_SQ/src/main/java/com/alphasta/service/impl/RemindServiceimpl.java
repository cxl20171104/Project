package com.alphasta.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.exception.ServiceException;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.RemindMapper;
import com.alphasta.model.Organization;
import com.alphasta.model.Remind;
import com.alphasta.model.User;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.RemindService;
import com.alphasta.service.UserService;

@Service("remindService")
public class RemindServiceimpl  implements RemindService{
	private static Logger LOGGER = LoggerFactory.getLogger(RemindServiceimpl.class);
	@Autowired
	private RemindMapper remindMapper;
	@Autowired
    private UserService userService;
	@Autowired
    private OrganizationService organizationService;
	@Override
	public List<Remind> messageList(Remind remind) {
		List<Remind> messageList = remindMapper.messageList(remind);
		return messageList;
	}
   
	@Override
	public void allMessageList(PageInfo pageInfo) {
		List<Remind> list = remindMapper.allMessageListPageCondition(pageInfo);
		System.out.println(list.size());
		for(int i = 0;i<list.size();i++){
			Long userId = Long.valueOf(list.get(i).getModifierId());
			Organization organizationById = organizationService.findOrganizationById(userId);	
			list.get(i).setModifierId(organizationById.getName());
		}
		int count = remindMapper.allMessageListPageCount(pageInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(count);
	}
	
	@Override
	public int updateState(Remind remind) {
		int result = remindMapper.updateState(remind);
		if (result != 1) {
			LOGGER.warn("更新状态失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}

	@Override
	public int insert(Remind remind) {
		int result = remindMapper.insert(remind);
		if (result != 1) {
			LOGGER.warn("新增信息失败");
			throw new ServiceException("新增失败");
		}
		return result;
	}
}
