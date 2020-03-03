package com.alphasta.service;

import java.util.List;

import com.alphasta.model.Lien;

public interface LienService {
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
