package com.alphasta.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.dataSource.DataSourceContextHolder;
import com.alphasta.mapper.OrganizationMapper;
import com.alphasta.mapper.PageMapper;
import com.alphasta.mapper.SysParamMapper;
import com.alphasta.model.Organization;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.SysParam;
import com.alphasta.service.HoleWeb;
/**
 * 
 * @author cxl
 * 2018年8月24
 */
@Service
public class HoleWebImpl implements HoleWeb{
	@Autowired 
	public PageMapper pageMapper;
	@Autowired
	public OrganizationMapper organizationMapper;
	@Autowired
	public SysParamMapper sysParamMapper;
	/**
	 * 
	 */
    Integer total=0;
    /**
     * 
     */
    Integer rows=0;
    /**
     * 
     */
    String  sort="createTime";
    /**
     * 
     */
    String  order="desc";
    /**
     * 查询条件
     */
    Map<String, Object> advice = new HashMap<>(16);
    /**
     * 需要加载的数据
     */
    List<ProblemClues>dataAll = new ArrayList<>();
    /**
     * 是否可以查询
     */
    boolean canSearch=false;
    /**
     * 异常排查打印
     * 
     */
    StringBuffer errorBuffer=new StringBuffer();
    /**
     *全网查询线索：通过切换数据源  分别查出数据  然后再拼接再一起返回前台  挺简单的
     */
	@Override
	public PageInfo holeSearch(Map<String, Object> condition) {
	    //当前页数
		Integer page = (Integer)condition.get("page");
		//每页显示的数量
		        rows = (Integer)condition.get("rows");
		        //排序
		        sort = (String)condition.get("sort");
		        order = (String)condition.get("order");
		        //查询条件
		        advice = condition;
		//判断是前进还是后退
		boolean march = true;//true是前进
		
		int pageSh = getPage("0");
		if ( page < pageSh ) {
			march = false;
		}
		SysParam shi = new SysParam();
		shi.setId(0L);
		shi.setValue(String.valueOf(page));
		sysParamMapper.update(shi);
		//先查询市区的数据
		PageInfo pageInfo = new PageInfo(page,rows, sort,order );
		condition.put("ip", "master_dataSource");
		pageInfo.setCondition(condition);
		List<ProblemClues> data = pageMapper.holeWeb(pageInfo);
		condition.put("count", "count");
		List<ProblemClues> shiCountr = pageMapper.tonganList(pageInfo);
		//看看有多少县里的数据
		dataAll = data;
		total=Integer.valueOf(shiCountr.get(0).getWhereFrom());
		
		//获取连接数据库的信息
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
		ServletContext servletContext = webApplicationContext.getServletContext();
		String choice=(String)servletContext.getAttribute("dataSourceControl");
		
		//如果当页数据不够显示的总数 查询县里的数据
		//第一站：任丘=============================================================
		//切换数据源
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",1,")>=0) {
		if(data.size()==0) {
				canSearch = true;
		}else {
				canSearch = false;
		}
			data = search(canSearch, march,"1");
		}
		//第二站黄骅
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",2,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"2");
		}
		//第三站河间
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",3,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"3");
		}
		//泊头市
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",4,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"4");
		}
		//沧县
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",5,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"5");
		}
		//青县
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",6,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"6");
		}
		//献县
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",7,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"7");
		}
		//肃宁县
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",8,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"8");
		}
		//盐山县
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",9,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"9");
		}
		//海兴县
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",10,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"10");
		}
		//孟村县
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",11,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"11");
		}
		//南皮县
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",12,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"12");
		}
		//东光县
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",13,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"13");
		}
		//吴桥县
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",14,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"14");
		}
		//运河区
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",15,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"15");
		}
		//新华区
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",16,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"16");
		}
		//渤海新区
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",17,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"17");
		}
		//沧州开发区
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",18,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		data = search(canSearch, march,"18");
		}
		//高新区
		if(StringUtils.isNotEmpty(choice)&&choice.indexOf(",19,")>=0) {
		if(data.size()==0) {
			canSearch=true;
		}else {
			canSearch=false;
		}
		search(canSearch, march,"19");
		}
		
		
	   
		//返回结果
		pageInfo.setTotal(total);
		pageInfo.setRows(dataAll);
		return pageInfo;
		
		
	}
	/**
	 * 
	 */
	@Override
	public int getPage(String id) {
		// TODO Auto-generated method stub
		SysParam now = sysParamMapper.findSysParamById(Long.valueOf(id));
		return Integer.valueOf(now.getValue());
	}
	/**
	 * 
	 */
	@Override
	public String getIp(String id) {
		//当id=4时 now 等于 null
		SysParam now = sysParamMapper.findSysParamById(Long.valueOf(id));
		return now.getRemark();
	}
	/**
	 * 
	 * @param canSearch
	 * @param march
	 * @param whichQx
	 * @return
	 */
    public List<ProblemClues> search(boolean canSearch,boolean march,String whichQx) {
    	//切换数据源  切换到县里的数据库
    	DataSourceContextHolder.setDbType(getIp(whichQx));
    	//查询总数 并 添加到总数中
		List<ProblemClues> rqJu =new ArrayList<ProblemClues>();;
		PageInfo count = new PageInfo();
		count.setCondition(advice);
		count.setSort((String)advice.get("sort"));
		List<ProblemClues> rqCountr = pageMapper.tonganList(count);
		int totalRq=Integer.valueOf(rqCountr.get(0).getWhereFrom());
		total+=totalRq;
		if(canSearch) {
			//从数据库查询该县的当前页数
			DataSourceContextHolder.setDbType("master_dataSource");
			int pageRq = getPage(whichQx);
			//查询数据
			//先查询市区的数据
			if(!march&&pageRq>1) {
				pageRq-=1;
			}
			String ip = getIp(whichQx);
			DataSourceContextHolder.setDbType(ip);
			
			//查询数据
			PageInfo pageInfo = new PageInfo(pageRq,rows, sort, order);
			//把ip嵌入到查询结果中
			advice.put("ip", ip);
			advice.put("count", null);
			pageInfo.setCondition(advice);
		    rqJu = pageMapper.holeWeb(pageInfo);
			if(totalRq>0){
				dataAll=rqJu;
				//准备下一页
				DataSourceContextHolder.setDbType("master_dataSource");
				if(march&&rqJu.size()>0) {
					pageRq+=1;
					SysParam sysParam = new SysParam();
					sysParam.setId(Long.valueOf(whichQx));
					sysParam.setValue( String.valueOf(pageRq) );
					sysParamMapper.update( sysParam );
				}
				
			}
			
		}
		//设置查询数量优先
		advice.put("count", "count");
		//把数据库还原为市里的  否则查询不到ip  
		DataSourceContextHolder.setDbType("master_dataSource");
		return rqJu;
        }
    
    
       //获取异常的完整信息
	   private static String exceptionToString(Exception ex) {
		   StringBuffer sOut=new StringBuffer();
		   StackTraceElement[] trace=ex.getStackTrace();
		   for(StackTraceElement s:trace) {
			   sOut.append("\tat"+s+"\r\n");
		   }
		   return sOut.toString();
	   }
}
