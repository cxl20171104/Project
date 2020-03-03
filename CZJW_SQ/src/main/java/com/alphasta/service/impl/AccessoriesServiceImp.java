package com.alphasta.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.mapper.AccessoriesMapper;
import com.alphasta.model.Accessories;
import com.alphasta.model.User;
import com.alphasta.service.AccessoriesService;
@Service
public class AccessoriesServiceImp implements AccessoriesService{
    @Autowired
	private AccessoriesMapper accessoriesMapper; 
	@Override
	public int countByExample(Accessories example) {
		return accessoriesMapper.countByExample(example);
	}


	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return accessoriesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Accessories record) {
		// TODO Auto-generated method stub
		return accessoriesMapper.insert(record);
	}

	@Override
	public int insertSelective(Accessories record) {
		// TODO Auto-generated method stub
		return accessoriesMapper.insertSelective(record);
	}
	@Override
	public int insertSelective2(Accessories record) {
		// TODO Auto-generated method stub
		return accessoriesMapper.insertSelective2(record);
	}

	@Override
	public Accessories selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return accessoriesMapper.selectByPrimaryKey(id);
	}
	@Override
	public Accessories selectByPrimaryKey2(Long id) {
		// TODO Auto-generated method stub
		return accessoriesMapper.selectByPrimaryKey2(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Accessories record) {
		// TODO Auto-generated method stub
		return accessoriesMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Accessories record) {
		// TODO Auto-generated method stub
		return accessoriesMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int updateByPrimaryKey2(Accessories record) {
		// TODO Auto-generated method stub
		return accessoriesMapper.updateByPrimaryKey2(record);
	}


	@Override
	public List<Accessories> searAccByCaseid(Long id) {
		// TODO Auto-generated method stub
		return accessoriesMapper.selectByCaseid(id);
	}
	@Override
	public List<Accessories> searAccByCaseid2(String id) {
		// TODO Auto-generated method stub
		return accessoriesMapper.selectByCaseid2(id);
	}


	@Override
	public Map<String,Object>getReportListService(Integer page, Integer rows, String sort, String order,
			Accessories acc) {
		// TODO Auto-generated method stub
		Map<String,Object>result=new HashMap<String,Object>();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("Accessories", acc);
		int nowPage;
		int nowPageSize;
		 if (page < 0) {
			 nowPage = 1;
	        } else {
	            // 当前页
	        	nowPage = page;
	        }
	        // 记录每页显示的记录数  
	        if (rows < 0) {
	        	nowPageSize = 10;
	        } else {
	        	nowPageSize = rows;
	        }
	        // 计算开始的记录和结束的记录  
	        int from = (nowPage - 1) * nowPageSize;
	        int size = nowPageSize;
	        map.put("from", from);
	        map.put("size", size);
	        map.put("type", acc.getType());
	        List<Accessories> accByMap = accessoriesMapper.getAccByMap(map);
	        map.put("count", "count");
	        List<Accessories> total = accessoriesMapper.getAccByMap(map);
	        result.put("rows", accByMap);
	        result.put("total", total.get(0).getAttacNum());
		return result;
	}
	
}
