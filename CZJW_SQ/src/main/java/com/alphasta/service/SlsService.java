package com.alphasta.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alphasta.model.ListParam;

public interface SlsService {
   boolean sls_clues(ListParam  listParam,HttpServletRequest request);
   public  Map<String,Object>sls_data(String id,String ip);
}
