package com.alphasta.service.impl;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.session.mgt.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;
import com.alphasta.commons.base.SqlService;
import com.alphasta.commons.exception.ServiceException;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.CaseClueMapper;
import com.alphasta.model.CaseClue;
import com.alphasta.model.User;
import com.alphasta.service.CaseClueService;
import com.alphasta.service.ResourceService;
import com.mysql.fabric.xmlrpc.base.Data;

/**
 * 案件线索service
 * 
 * @author LiYunhao
 *
 */

@Service("caseClueService")
public class CaseClueServiceImpl implements CaseClueService {
	private static Logger LOGGER = LoggerFactory.getLogger(CaseClueServiceImpl.class);
	@Autowired
	private CaseClueMapper caseClueMapper;
	@Autowired
	private SqlService sqlService;
	@Autowired
	private ResourceService resourceService;
	
	@Override
	public void findCaseCluePageCondition(PageInfo pageInfo) {
		List<CaseClue> list = caseClueMapper.findCaseCluePageCondition(pageInfo);
		int count = caseClueMapper.findCaseCluePageCount(pageInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(count);
	}

	@Override
	public List<CaseClue> findCaseClue(CaseClue caseClue) {
		return caseClueMapper.findCaseClue(caseClue);
	}

	@Override
	public CaseClue findCaseClueById(Long id) {
		return caseClueMapper.findCaseClueById(id);
	}

	@Override
	public int insert(CaseClue caseClue) {
		caseClue.setIsDel("1");
		caseClue.setXsstate("1");
		int result = caseClueMapper.insert(caseClue);
		if (result != 1) {
			LOGGER.warn("新增案件线索信息失败");
			throw new ServiceException("新增失败");
		}
		return result;
	}

	@Override
	public int update(CaseClue caseClue) {
		int result = caseClueMapper.update(caseClue);
		if (result != 1) {
			LOGGER.warn("更新案件线索信息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}

	@Override
	public int deleteByIds(String ids) {
		return caseClueMapper.deleteByIds(ids);
	}
	
	@Override
	public int importXls(List<Object> list,User user) {
		int result = 0;
		for (Object object : list) {
			CaseClue caseClue = (CaseClue)object;
			caseClue.setCreaterId(user.getId());
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sdf.format(d); 	
			//生成编号
			Date a = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			String time2 = sdf1.format(a);
			int number = (int)(Math.random()*(9999-1000+1))+1;
			//转来单位
			if(caseClue.getToCompany()!=null){
				if(caseClue.getToCompany().equals("1")){ //信访部门
					caseClue.setNum("A"+time2+number);
				}else if(caseClue.getToCompany().equals("2")){//执纪监督部门
					caseClue.setNum("B"+time2+number);
				}else if(caseClue.getToCompany().equals("3")){//执纪审查部门
					caseClue.setNum("C"+time2+number);
				}else if(caseClue.getToCompany().equals("4")){//干部监督部门
					caseClue.setNum("D"+time2+number);
				}else if(caseClue.getToCompany().equals("5")){//巡视工作机构
					caseClue.setNum("E"+time2+number);
				}else if(caseClue.getToCompany().equals("6")){//审计机关
					caseClue.setNum("F"+time2+number);
				}else if(caseClue.getToCompany().equals("7")){//行政执法机关
					caseClue.setNum("H"+time2+number);
				}else if(caseClue.getToCompany().equals("8")){//司法机关
					caseClue.setNum("R"+time2+number);
				}else if(caseClue.getToCompany().equals("9")){//其他
					caseClue.setNum("S"+time2+number);
				}else{
					caseClue.setNum("Z"+time2+number);
				}
			}else{
				caseClue.setNum("Z"+time2+number);
			}
			   String toConpany=caseClue.getToCompany();
			    if(("省纪委交办").equals(toConpany)){
			    	caseClue.setToCompany("1");
			    }
			    if(("中纪委交办").equals(toConpany)){
			    	caseClue.setToCompany("2");
			    }
			    if(("省委巡视组交办").equals(toConpany)){
			    	caseClue.setToCompany("3");
			    }
			    if(("上级交办其他").equals(toConpany)){
			    	caseClue.setToCompany("4");
			    }
			    if(("信访部门").equals(toConpany)){
			    	caseClue.setToCompany("5");
			    }
			    if(("执纪监督部门").equals(toConpany)){
			    	caseClue.setToCompany("6");
			    }
			    if(("执纪审查部门").equals(toConpany)){
			    	caseClue.setToCompany("7");
			    }
			    if(("干部监督部门").equals(toConpany)){
			    	caseClue.setToCompany("8");
			    }
			    if(("巡视工作机构").equals(toConpany)){
			    	caseClue.setToCompany("9");
			    }
			    if(("审计机关").equals(toConpany)){
			    	caseClue.setToCompany("10");
			    }
			    if(("行政执法机关").equals(toConpany)){
			    	caseClue.setToCompany("11");
			    }
			    if(("司法机关").equals(toConpany)){
			    	caseClue.setToCompany("12");
			    }
			    if(("普通案件其他").equals(toConpany)){
			    	caseClue.setToCompany("13");
			    }
			caseClue.setReceiveDate(time);
			caseClue.setIsDel("1");
			caseClue.setXsstate("1");
			result = caseClueMapper.insert(caseClue);
			if (result != 1) {
				LOGGER.warn("导入案件线索信息失败");
				throw new ServiceException("导入失败");
			}
			//处理重复件
			isDouble(caseClue);
		}
		
		return result;
	}


	
    /**
     *添加案件线索 重复件处理
     */
	@Override
	public void isDouble(CaseClue caseClue) {
		
	/*	try {
			CallableStatement c = druiData.getConnection().prepareCall("{call isDouble(?,?,?)}");
			c.setString(1, caseClue.getReflectName());
			c.setString(2, caseClue.getBeReflectName());
			c.setLong(3, caseClue.getId());
			c.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		
		
		  //执行存储过程	
		SqlSessionFactory SqlSessionFactory= sqlService.getSqlSessionFactory();
				         
		 org.apache.ibatis.session.SqlSession openSession = SqlSessionFactory.openSession();
				         String statement = "com.alphasta.mapper.CaseClueMapper.getUserCount";//映射sql的标识字符串
				         Map<String, Object> parameterMap = new HashMap<String,Object>();
				         parameterMap.put("fan_ying_ren", caseClue.getReflectName());
				         parameterMap.put("bei_fan_ying_ren", caseClue.getBeReflectName());
				         parameterMap.put("ida", caseClue.getId());
				         openSession.selectOne(statement, parameterMap);
				         openSession.close();
				
				
			
		
	}
    

	@Override
	public List<CaseClue> findTorepeat(CaseClue caseClue) {
			List<CaseClue> list = sqlService.select("select * from caseclue where isDel='1' and cfState="+"'"+caseClue.getId()+"'", CaseClue.class);
			return list;   
	}
	@Override
	public Long last_insert_id() {
		Long last_insert_id = caseClueMapper.last_insert_id();
		return last_insert_id;
	}
	
	@Override
	public List<Map<String, Object>> groupBymonths(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		List<Map<String,Object>>mapa=caseClueMapper.groupBymonths(map);
		return mapa;
	}

	

	@Override
	public List<Map<String, Object>> groupCaseByjsAndTime(
			String js,String start,String end) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("js", js);
		map.put("start", start);
		map.put("end", end);
		List<Map<String, Object>> groupCaseByjsAndTime = caseClueMapper.groupCaseByjsAndTime(map);
		
		return groupCaseByjsAndTime;
	}

	@Override
	public  List<Map<String,Object>> findCaseClueCountNum(Map<String, Object> map) {
				
		 List<Map<String,Object>> list = caseClueMapper.findCaseClueCountNum(map);
		return list;
	}
	
	
	
}
