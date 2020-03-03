package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Punishment;

public interface PunishmentMapper {
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
