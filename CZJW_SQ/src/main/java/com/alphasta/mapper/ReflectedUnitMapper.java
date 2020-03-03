package com.alphasta.mapper;

import com.alphasta.model.ReflectedUnit;

public interface ReflectedUnitMapper {
	ReflectedUnit    getReflectedUnit(String id);
	int                addReflectedUnit(ReflectedUnit reflectedUnit);
	int                updateReflectedUnit(ReflectedUnit reflectedUnit);
}
