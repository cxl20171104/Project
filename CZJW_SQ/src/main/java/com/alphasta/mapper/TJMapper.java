package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.model.clues_tj.TJModel;

public interface TJMapper {
    List<TJModel>tj(Map<String,Object>condition);
    List<TJModel>tj2(Map<String,Object>condition);
}
