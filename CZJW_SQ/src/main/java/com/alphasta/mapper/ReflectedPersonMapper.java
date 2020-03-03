package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.ReflectedPerson;

public interface ReflectedPersonMapper {
	//part1:查询出被反映人（不含转换）
	ReflectedPerson    getReflectedPerson(String id);
	//part2:查询出背反影任（含转换）
	ReflectedPerson    getReflectedPerson2(String id);
	int                addReflectedPerson(ReflectedPerson reflectedPerson);
	int                updateReflectedPerson(ReflectedPerson reflectedPerson);
	List<ReflectedPerson>has_repeat(ReflectedPerson reflectedPerson);
	int                delReflected(String id);
}
