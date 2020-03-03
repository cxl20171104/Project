package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.ZyViolation;

public interface ZyViolationMapper {
	//
	int addZyViolation(ZyViolation zyViolation);
	//
	ZyViolation deleteZyViolation (String id);
	//
	ZyViolation updateZyViolation(ZyViolation zyViolation);
	//
	ZyViolation findZyViolationById(String id);
	//
	List<ZyViolation> findZyViolationByRid(String rid);
	//
	ZyViolation findZyViolation(ZyViolation zyViolation);
}
