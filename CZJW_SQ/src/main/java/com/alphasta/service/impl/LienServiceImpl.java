package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alphasta.mapper.LienMapper;
import com.alphasta.model.Lien;
import com.alphasta.service.LienService;

public class LienServiceImpl implements LienService{
    @Autowired 
    LienMapper lienMapper;
	@Override
	public int addLien(Lien lien) {
		int p=lienMapper.addLien(lien);
		return p;
	}

	@Override
	public Lien deleteLien(String id) {
		// TODO Auto-generated method stub
		Lien deleteLien = lienMapper.deleteLien(id);
		return deleteLien;
	}

	@Override
	public Lien updateLien(Lien lien) {
		// TODO Auto-generated method stub
		Lien updateLien = lienMapper.updateLien(lien);
		return updateLien;
	}

	@Override
	public Lien findLienById(String id) {
		// TODO Auto-generated method stub
		Lien findLienById = lienMapper.findLienById(id);
		return findLienById;
	}

	@Override
	public List<Lien> findLienByRid(String rid) {
		// TODO Auto-generated method stub
		List<Lien> findLienByCid = lienMapper.findLienByRid(rid);
		return findLienByCid;
	}

	@Override
	public Lien findLien(Lien lien) {
		Lien findLien = lienMapper.findLien(lien);
		return findLien;
	}

   
}
