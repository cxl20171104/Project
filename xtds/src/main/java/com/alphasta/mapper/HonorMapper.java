package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Honor;

public interface HonorMapper {
    public Honor findByid(Map<String,Object> map);
    public List<Honor>  findByPageInfo(PageInfo pageinfo);
    public void insertOne(Honor honor);
    public void updateOne(Honor honor);
    public void deleteOne(String id);
    public List<Honor> getCountHonor(Map<String,Object> map);
}
