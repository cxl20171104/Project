package com.alphasta.service;

import java.util.Map;

import com.alphasta.commons.utils.PageInfo;

public interface HoleWeb {
	PageInfo holeSearch(Map<String, Object> condition);
	int      getPage(String  id);
	String      getIp(String id);
}
