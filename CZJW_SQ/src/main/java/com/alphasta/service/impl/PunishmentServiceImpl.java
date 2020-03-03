package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alphasta.mapper.PunishmentMapper;
import com.alphasta.model.Punishment;
import com.alphasta.service.PunishmentService;

public class PunishmentServiceImpl implements PunishmentService{
    @Autowired 
    PunishmentMapper punishmentMapper;
	@Override
	public int addPunishment(Punishment punishment) {
		int p=punishmentMapper.addPunishment(punishment);
		return p;
	}

	@Override
	public Punishment deletePunishment(String id) {
		// TODO Auto-generated method stub
		Punishment deletePunishment = punishmentMapper.deletePunishment(id);
		return deletePunishment;
	}

	@Override
	public Punishment updatePunishment(Punishment punishment) {
		// TODO Auto-generated method stub
		Punishment updatePunishment = punishmentMapper.updatePunishment(punishment);
		return updatePunishment;
	}

	@Override
	public Punishment findPunishmentById(String id) {
		// TODO Auto-generated method stub
		Punishment findPunishmentById = punishmentMapper.findPunishmentById(id);
		return findPunishmentById;
	}

	@Override
	public List<Punishment> findPunishmentByRid(String rid) {
		// TODO Auto-generated method stub
		List<Punishment> findPunishmentByCid = punishmentMapper.findPunishmentByRid(rid);
		return findPunishmentByCid;
	}

	@Override
	public Punishment findPunishment(Punishment punishment) {
		Punishment findPunishment = punishmentMapper.findPunishment(punishment);
		return findPunishment;
	}

   
}
