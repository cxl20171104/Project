package com.alphasta.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alphasta.model.Group;
import com.alphasta.model.Lien;
import com.alphasta.model.ListParam;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.Punishment;

public interface ScsService {
	 public boolean scsAddProgressService(List<Progress> progress,List<Group> group,ProblemClues problemClues,HttpServletRequest request);
	 public String  laAddPunishment(Punishment punishment,String reflectedPersonId);
	 public String laAddLienService(List<Lien> lien,String reflectedPersonId);
	 public boolean scs_la(ListParam  listParam,HttpServletRequest request);
	 public Map<String,Object>scss_data(String id,String ip);
	 public Map<String,Object>la_data(String id,String ip);
}
