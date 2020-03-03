package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

public interface StaticticsMapper {
	/*总量统计*/
    Integer  addTotal(Map<String,Object>param);
    int  updateTotal(Map<String,Object>param);
    List<Map<String,Object>>selectTotal(Map<String,Object>param);
    /*基本信息统计*/
    Integer addBasic(Map<String,Object>param);
    int updateBasic(Map<String,Object>param);
    List<Map<String,Object>>selectBasic(Map<String,Object>param);
    /*监督室处理情况统计*/
    List<Map<String,Object>>selectCzfs(Map<String,Object>param);
    Integer addCzfs(Map<String,Object>param);
    int updateCzfs(Map<String,Object>param);
    /*审查室立案情况*/
    List<Map<String,Object>>selectLa(Map<String,Object>param);
    Integer addLa(Map<String,Object>param);
    int updateLa(Map<String,Object>param);
    /*审理室案件处理*/
    List<Map<String,Object>>selectCf(Map<String,Object>param);
    Integer addCf(Map<String,Object>param);
    int updateCf(Map<String,Object>param);
    /**结案**/
    List<Map<String,Object>>select_ja(Map<String,Object>param);
    Integer add_ja(Map<String,Object>param);
    int update_ja(Map<String,Object>param);
    /**领域分类统计*/
    List<Map<String,Object>>select_field(Map<String,Object>param);
    Integer add_field(Map<String,Object>param);
    int update_field(Map<String,Object>param);
    /**职级违纪行为统计**/
    List<Map<String,Object>>select_wj(Map<String,Object>param);
    Integer add_wj(Map<String,Object>param);
    int update_wj(Map<String,Object>param);
}
