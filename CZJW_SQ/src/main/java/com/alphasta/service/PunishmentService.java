package com.alphasta.service;

import java.util.List;

import com.alphasta.model.Punishment;
import com.alphasta.model.Punishment;

public interface PunishmentService {
	//
	int addPunishment(Punishment punishment);
	//
	Punishment deletePunishment (String id);
	//
	Punishment updatePunishment(Punishment punishment);
	//
	Punishment findPunishmentById(String id);
	//
	List<Punishment> findPunishmentByRid(String rid);
	//
	Punishment findPunishment(Punishment punishment);
	
}
