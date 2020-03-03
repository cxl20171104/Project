package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.test.context.jdbc.Sql;

import com.alphasta.model.State;

public interface StateMapper {

    int deleteByPrimaryKey(Long id);

    int insert(State record);

    int insertSelective(State record);

    State selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(State record);

    int updateByPrimaryKey(State record);
    
    List<State> selectStateList(Map<String,Object> map);
}