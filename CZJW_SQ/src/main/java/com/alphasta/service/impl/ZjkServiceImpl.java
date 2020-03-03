package com.alphasta.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.base.SqlService;
import com.alphasta.commons.exception.ServiceException;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.ZjkMapper;
import com.alphasta.model.CaseClue;
import com.alphasta.model.Dict;
import com.alphasta.model.User;
import com.alphasta.model.Zjk;
import com.alphasta.service.ZjkService;

@Service("zjkService")
public class ZjkServiceImpl implements ZjkService{
	
	private static Logger LOGGER = LoggerFactory.getLogger(ZjkServiceImpl.class);

	@Autowired
	private ZjkMapper zjkMapper;
	//测试修改
	@Autowired
	private SqlService sqlService;
/*	@Override
	public List<Zjk> findZjk1() {
		List<Zjk> list = sqlService.select("select * from czjw_zjk", Zjk.class);
		return list;
	}*/
	
	@Override
	public List<Map<String, Object>> findZjk(PageInfo pageInfo) {
		return zjkMapper.findZjk(pageInfo);
	}
	@Override
	public Zjk findZjkById(Long id) {
		return zjkMapper.findZjkById(id);
	}
	@Override
	public List<Zjk> findDictByDictPid(Zjk zjk) {
		return zjkMapper.findDictByDictPid(zjk);
	}
	
	@Override
	public void findCompanyPageCondition(PageInfo pageInfo) {
		List<Zjk> list = zjkMapper.findCompanyPageCondition(pageInfo);
		int count = zjkMapper.findCompanyPageCount(pageInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(count);
	}
	
	@Override
	public int insert(Zjk zjk) {
		int result = zjkMapper.insert(zjk);
		if (result != 1) {
			LOGGER.warn("新增专家信息失败");
			throw new ServiceException("新增失败");
		}
		return result;
	}
	
	@Override
	public int update(Zjk zjk) {
		int result = zjkMapper.update(zjk);
		if (result != 1) {
			LOGGER.warn("更新职位信息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}

	@Override
	public int deleteByIds(String ids) {
		return zjkMapper.deleteByIds(ids);
	}
	
	@Override
	public int insertData(Zjk zjk) {
		int result = zjkMapper.insertData(zjk);
		if (result != 1) {
			LOGGER.warn("新增专家信息失败");
			throw new ServiceException("新增失败");
		}
		return result;
	}

	@Override
	public int importJzk(List<Object> list,User user) {
		int result = 0;
		for (Object object : list) {
			Zjk zjk = (Zjk)object;
			//处理年龄
			/*int age =Integer.parseInt(zjk.getBirthday());
			if(age<=100){
				Calendar a=Calendar.getInstance();
				int b = a.get(Calendar.YEAR);
				String age2 = String.valueOf(b-age);
				zjk.setBirthday(age2);
			}*/
			String time = zjk.getWorking_time();
			SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-HH-dd");  
			SimpleDateFormat formatter2=new SimpleDateFormat("yyyy年HH月dd日"); 
			if(time !=null){
			 try {
					time=formatter1.format(formatter2.parse(time));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			
				zjk.setWorking_time(time);    
			} 
			
			
			String bir_time=zjk.getBirthday();
			if(bir_time !=null){
			try {
				bir_time=formatter1.format(formatter2.parse(bir_time));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		    
			zjk.setBirthday(bir_time);    
		   } 
			
			
			String party_time=zjk.getParty_time();
			if(party_time !=null){
				try {
					party_time=formatter1.format(formatter2.parse(party_time));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			    
				zjk.setParty_time(party_time);    
			   } 
			
			zjk.setCreaterId((user.getId()).toString());
			result = zjkMapper.insert(zjk);
			if (result != 1) {
				LOGGER.warn("导入案件线索信息失败");
				throw new ServiceException("导入失败");
			}
		}
		return result;
	}
	@Override
	public List<Map<String, Object>> groupCaseByjsAndTime(String js,
			String start, String end) {
		// TODO Auto-generated method stub
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("js", js);
		map.put("start", start);
		map.put("end", end);
		List<Map<String, Object>> groupCaseByjsAndTime = zjkMapper.groupCaseByjsAndTime(map);
		
		return groupCaseByjsAndTime;
	}
    
}
