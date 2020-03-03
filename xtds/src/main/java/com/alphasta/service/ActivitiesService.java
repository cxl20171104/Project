package com.alphasta.service;

import java.util.List;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Activities;

public interface ActivitiesService {
	public List<Activities> page(Activities act);

	public List<Activities> apppage(PageInfo pageInfo);

	public Activities selectByID(String id);

	public void updateByPrimaryKeySelective(Activities act);

	public void save(Activities act);

	public void deleteByID(String id);

	public List<Activities> getByCondition(Activities act);
}
