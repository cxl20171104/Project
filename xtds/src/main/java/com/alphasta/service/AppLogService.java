package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.model.AppLog;

public interface AppLogService {
	public void addLog(AppLog log);

	public List<AppLog> selectByCondition(AppLog log);

	public List<Map<String, String>> searchCzlist(Long operater, int start,
			int end);

	public List<AppLog> findCzByTimeAndOperater(String otime, Long operater);

	public List<Map<String, Object>> getEveryThing(Long operater, String start,
			String end);
}
