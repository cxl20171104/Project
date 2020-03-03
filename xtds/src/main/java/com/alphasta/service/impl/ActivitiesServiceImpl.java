package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.ActivitiesMapper;
import com.alphasta.model.Activities;
import com.alphasta.service.ActivitiesService;

@Service("activitiesService")
public class ActivitiesServiceImpl implements ActivitiesService {
	@Autowired
	private ActivitiesMapper activitiesMapper;

	@Override
	public List<Activities> page(Activities act) {

		return activitiesMapper.page(act);
	}

	public List<Activities> apppage(PageInfo pageInfo) {

		return activitiesMapper.apppage(pageInfo);
	}

	@Override
	public Activities selectByID(String id) {
		// TODO Auto-generated method stub
		return activitiesMapper.selectByID(id);
	}

	@Override
	public void updateByPrimaryKeySelective(Activities act) {
		activitiesMapper.updateByPrimaryKeySelective(act);

	}

	@Override
	public void save(Activities act) {
		activitiesMapper.save(act);

	}

	@Override
	public void deleteByID(String id) {
		String[] split = id.split(",");
		for (String s : split) {
			activitiesMapper.deleteByID(s);
		}
	}

	@Override
	public List<Activities> getByCondition(Activities act) {
		// TODO Auto-generated method stub
		return activitiesMapper.getByCondition(act);
	}

}
