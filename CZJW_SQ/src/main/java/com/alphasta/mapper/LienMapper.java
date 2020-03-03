package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Lien;

public interface LienMapper {
	//
	int addLien(Lien lien);
	//
	Lien deleteLien (String id);
	//
	Lien updateLien(Lien lien);
	//
	Lien findLienById(String id);
	//
	List<Lien> findLienByRid(String rid);
	//
	Lien findLien(Lien lien);
}
