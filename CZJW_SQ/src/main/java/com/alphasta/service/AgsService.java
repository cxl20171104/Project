package com.alphasta.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alphasta.en.Repeat_order;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;

public interface AgsService {
   public  ProblemClues            add_clues(ProblemClues problemClues,HttpServletRequest request);
   public  String          findMaxNum(String suffix);
   public  int             add_repeat(ProblemClues problemClues_before,Repeat_order order,HttpServletRequest request);
   public  int             addCluesMainServices(ProblemClues problemClues);
   public  int             fb_clues(List<Progress> progress,ProblemClues problemClues,HttpServletRequest request);
   public  Map<String,Object> ags_fg(String id,String ip);
}
