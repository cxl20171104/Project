package com.alphasta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alphasta.model.Back;

public interface BackMapper {
    int add_back(Back back);
    List<Back>select_back(Back back);
    Back select_back_id(@Param("id")String id);
}
