package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.Maked;

public interface MakedMapper {
    int addMaked(Maked maked);
    List<Maked>findMakedByCidAndOrganId(Maked maked);
}
