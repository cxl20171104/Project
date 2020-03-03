package com.alphasta.service;

import java.util.List;

import com.alphasta.commons.result.Statistics;

public interface StatisticsService {

	/**
	 * 统计
	 * 
	 * @return
	 */
	List<Statistics> findOrderRecordStatistics(Statistics statistics);
}
