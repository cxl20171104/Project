package com.alphasta.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.exception.ServiceException;
import com.alphasta.mapper.PositionMapper;
import com.alphasta.model.Position;
import com.alphasta.service.PositionService;

/**
 * 职位service
 * 
 * @author LiYunhao
 *
 */
@Service("positionService")
public class PositionServiceImpl implements PositionService {
	private static Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);
	@Autowired
	private PositionMapper positionMapper;

	@Override
	public List<Position> findPosition() {
		return positionMapper.findPosition();
	}

	@Override
	public Position findPositionById(Long id) {
		return positionMapper.findPositionById(id);
	}

	@Override
	public int insert(Position position) {
		int result = positionMapper.insert(position);
		if (result != 1) {
			LOGGER.warn("更新职位信息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}

	@Override
	public int update(Position position) {
		int result = positionMapper.update(position);
		if (result != 1) {
			LOGGER.warn("更新职位信息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}

	@Override
	public int deleteByIds(String ids) {
		return positionMapper.deleteByIds(ids);
	}

}
