package com.alphasta.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.PayMapper;


import com.alphasta.mapper.UserMapper;
import com.alphasta.model.Pay;
import com.alphasta.model.User;
import com.alphasta.service.PayService;
@Service("payService")
public class PayServiceImpl implements PayService {
	@Autowired
	private PayMapper payMapper;

    @Autowired
    private UserMapper userMapper;
	@Override
	public Pay selectByID(String id) {
		Pay pay = payMapper.selectByID(id);
		return pay;
	}

	@Override
	public void save(Pay pay) {
		// TODO Auto-generated method stub
		payMapper.save(pay);
	}
	@Override
	public void update(Pay pay) {
		// TODO Auto-generated method stub
		payMapper.update(pay);
	}
	@Override
	public void del(String id) {
		// TODO Auto-generated method stub		
		payMapper.deleteByIds(id);
	}

	@Override
	public void findPayPageCondition(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		List<Pay> list = payMapper.findPayPageCondition(pageInfo);
		int count = payMapper.findPayPageCount(pageInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(count);
	}

	@Override
	public int importXls(List<Object> list) {
		if(list==null||list.size()==0){
			return 1;
		}else{
			for(Object o:list){
				
				try {
					Pay pay=(Pay)o;
					if(pay.getMoney()==null||"".equals(pay.getMoney())||"0".equals(pay.getMoney())){
						continue;
					};
					if(pay.getUserid()!=null&&pay.getYear()!=null&&pay.getMonth()!=null){
						pay.setState("2");
						pay.setPayTime(String.format("%1$tY-%1$tm-%1$te", new Date()));
						payMapper.updatePay(pay);
					};					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}
		return 3;
	}

	@Override
	public List<Pay> findPayByUserId(PageInfo pageInfo) {
		// TODO 自动生成的方法存根		
		return payMapper.findPayByUserId(pageInfo);
	}

	@Override
	public List<Pay> findPayByMonth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return payMapper.getByMonth(map);
	}

	@Override
	public List<Pay> findPayByQuarter(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return payMapper.getByQuarter(map);
	}
}
