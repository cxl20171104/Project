package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.AppLog;

public interface AppLogMapper {
	public void addLog(AppLog log);

	public List<AppLog> selectByCondition(AppLog log);

	public List<Map<String, String>> searchCzlist(Map<String, Object> map);

	public List<AppLog> findCzByTimeAndOperater(Map<String, Object> map);
}
