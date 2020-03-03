package com.alphasta.service;

import java.util.List;

import com.alphasta.model.Measures;

public interface MeasuresService {
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
