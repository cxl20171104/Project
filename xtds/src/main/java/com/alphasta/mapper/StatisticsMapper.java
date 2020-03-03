package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Organization;
import com.alphasta.model.Statistics;

public interface StatisticsMapper {

	public List<Statistics> getStatList(Map<String, String> map);

	public List<Statistics> getStatResult(Map<String, Object> map);

	public List<Organization> getlistByOrg(Map<String, Object> map);

}
