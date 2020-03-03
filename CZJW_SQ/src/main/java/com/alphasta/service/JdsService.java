package com.alphasta.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.User;

public interface JdsService {
   boolean  Jds_clues(List<Progress> progress,ProblemClues problemClues,HttpServletRequest request);
   boolean  jds_xf(ProblemClues problemClues,User user);
   boolean  pz_xf(ProblemClues problemClues,List<Progress> progress);
   boolean  jds_toAg(ProblemClues problemClues,HttpServletRequest reqeust);
   public   Map<String,Object> jds_zc(String id,String ip);
   public   Map<String,Object> jds_jb(String id);
   public  boolean updateProgressCzjd(String cluesId,String pointValue);
}
