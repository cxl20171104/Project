package com.alphasta.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.result.Statistics;
import com.alphasta.mapper.OrderRecordMapper;
import com.alphasta.service.StatisticsService;

/**
 * 统计service
 * 
 * @author LiYunhao
 * 
 */
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
	private static Logger LOGGER = LoggerFactory
			.getLogger(StatisticsServiceImpl.class);

	@Autowired
	private OrderRecordMapper orderRecordMapper;

	@Override
	public List<Statistics> findOrderRecordStatistics(Statistics statistics) {
		List<Statistics> list = orderRecordMapper
				.findOrderRecordStatistics(statistics);
		return list;
	}
}
