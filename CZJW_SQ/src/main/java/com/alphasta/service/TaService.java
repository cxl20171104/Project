package com.alphasta.service;

import org.springframework.ui.Model;

import com.alphasta.model.ProblemClues;

public interface TaService {
	public Model add_ta(String id,Model model);
	public boolean add_ta(ProblemClues problemClues);
	public boolean bang_ta(String oldId,String newId);
	public boolean refbang(String cluesId);
}
