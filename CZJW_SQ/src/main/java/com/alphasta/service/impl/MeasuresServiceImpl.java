package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alphasta.mapper.MeasuresMapper;
import com.alphasta.model.Measures;
import com.alphasta.service.MeasuresService;

public class MeasuresServiceImpl implements MeasuresService{
    @Autowired 
    MeasuresMapper measuresMapper;
	@Override
	public int addMeasures(Measures measures) {
		int p=measuresMapper.addMeasures(measures);
		return p;
	}

	@Override
	public Measures deleteMeasures(String id) {
		// TODO Auto-generated method stub
		Measures deleteMeasures = measuresMapper.deleteMeasures(id);
		return deleteMeasures;
	}

	@Override
	public Measures updateMeasures(Measures measures) {
		// TODO Auto-generated method stub
		Measures updateMeasures = measuresMapper.updateMeasures(measures);
		return updateMeasures;
	}

	@Override
	public Measures findMeasuresById(String id) {
		// TODO Auto-generated method stub
		Measures findMeasuresById = measuresMapper.findMeasuresById(id);
		return findMeasuresById;
	}

	@Override
	public List<Measures> findMeasuresByRid(String rid) {
		// TODO Auto-generated method stub
		List<Measures> findMeasuresByCid = measuresMapper.findMeasuresByRid(rid);
		return findMeasuresByCid;
	}

	@Override
	public Measures findMeasures(Measures measures) {
		Measures findMeasures = measuresMapper.findMeasures(measures);
		return findMeasures;
	}

   
}
