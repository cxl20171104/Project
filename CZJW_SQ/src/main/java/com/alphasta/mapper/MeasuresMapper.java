package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Measures;

public interface MeasuresMapper {
	//
	int addMeasures(Measures measures);
	//
	Measures deleteMeasures (String id);
	//
	Measures updateMeasures(Measures measures);
	//
	Measures findMeasuresById(String id);
	//
	List<Measures> findMeasuresByRid(String rid);
	//
	Measures findMeasures(Measures measures);
}
