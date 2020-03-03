package com.alphasta.mapper;


import com.alphasta.model.ReflectingPerson;

public interface ReflectingPersonMapper {
	ReflectingPerson    getReflectingPerson(String id);
	int                 addReflectingPerson(ReflectingPerson reflectingPerson);
	int                 updateReflectingPerson(ReflectingPerson reflectingPerson);
	int                 delReflecting(String id);
}
