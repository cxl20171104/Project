package com.alphasta.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.annotation.ExcelFild;
import com.alphasta.commons.statisticsUtils.CountExcelU;
import com.alphasta.commons.statisticsUtils.PercentFormula;
import com.alphasta.commons.statisticsUtils.SqlMaker0;
import com.alphasta.commons.statisticsUtils.SqlMaker1;
import com.alphasta.commons.statisticsUtils.SqlMaker2;
import com.alphasta.commons.statisticsUtils.SqlMaker3;
import com.alphasta.commons.statisticsUtils.SqlMaker4;
import com.alphasta.commons.statisticsUtils.SqlMaker5;
import com.alphasta.commons.statisticsUtils.SqlMaker6;
import com.alphasta.commons.statisticsUtils.SqlMaker7;
import com.alphasta.commons.statisticsUtils.SqlMaker8;
import com.alphasta.commons.statisticsUtils.SqlMaker9;
import com.alphasta.commons.statisticsUtils.SumUtil;
import com.alphasta.commons.statisticsUtils.UtilFroWord;
import com.alphasta.commons.utils.ListToString;
import com.alphasta.en.StaticticsType;
import com.alphasta.mapper.DictMapper;
import com.alphasta.mapper.ProStaticesMapper;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.mapper.PunishmentMapper;
import com.alphasta.mapper.StaticticsMapper;
import com.alphasta.model.Dict;
import com.alphasta.model.ExcelPojo;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Punishment;
import com.alphasta.service.ProblemCluesService2;
@Service("problemCluesService2")
public class PorblemCluesServiceImp2 implements ProblemCluesService2 {
	@Autowired
	private ProStaticesMapper proStaticesMapper;
	@Autowired
	private StaticticsMapper  st;
	@Autowired
	private ProblemCluesMapper problemCluesMapper;
	@Autowired
	private PunishmentMapper   punishmentMapper;
	@Autowired
	private DictMapper         dictMapper;
	@Override
	public  Map<String, Object> probleClueCount(
			Map<String, Object> map) {
		    Map<String, Object> result = proStaticesMapper.probleClueCount(map);
		    return result;
	}
	
	
	@Override
	public List<Map<String, Object>> probleClueByczMethodCount(
			Map<String, Object> map) {
		List<Map<String, Object>> list = proStaticesMapper.probleClueByczMethodCount(map);
		return list;
	}
    //仅求总数
	@Override
	public Map<String, Object> probleClueByblResultCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		//党纪处分
		map.put("columns", "blResult_djcf");
		List<Map<String,Object>>  blResult_djcf = proStaticesMapper.proClueByCount(map);
		//政纪处分
		map.put("columns", "blResult_zjcf");
		List<Map<String,Object>>  blResult_zjcf = proStaticesMapper.proClueByCount(map);
		//组织处理
		map.put("columns", "blResult_zzcl");
		List<Map<String,Object>>  blResult_zzcl = proStaticesMapper.proClueByCount(map);
		//其他处理
		map.put("columns", "blResult_qtcl");
		List<Map<String,Object>>  blResult_qtcl = proStaticesMapper.proClueByCount(map);
		//移交司法
		map.put("columns", "blResult_yjsf");
		List<Map<String,Object>>  blResult_yjsf = proStaticesMapper.proClueByCount(map);
		//党纪政纪处分
		Long djzj_cf = 0l;
		for(Map<String,Object> bd:blResult_djcf){
	         Long counts=(Long)bd.get("counts");
	         djzj_cf+=Long.valueOf(counts);
		}
		for(Map<String,Object>  bz:blResult_zjcf){
			Long counts=(Long)bz.get("counts");
	         djzj_cf+=Long.valueOf(counts);
		}
		//立案审查，组织处理处理的案件
		Long  zzcl_la = 0l;
		for(Map<String,Object> bzz: blResult_zzcl){
			Long counts=(Long)bzz.get("counts");
			zzcl_la+=Long.valueOf(counts);
		}
		
		
		
		//总处分案件数
		Long zsSum = djzj_cf+zzcl_la;
		//移送司法
		Long yjsf=0l;
		for(Map<String,Object> bq:blResult_qtcl){
			Long counts=(Long)bq.get("counts");
			zsSum+=Long.valueOf(counts);
		}
		for(Map<String,Object> by: blResult_yjsf){
			Long counts=(Long)by.get("counts");
			zsSum+=Long.valueOf(counts);
			yjsf+=Long.valueOf(counts);
		}
		
		//轻处分
		Long qcf =0l;
		//重处分
		Long zcf =0l;
		for(Map<String,Object> djcf :blResult_djcf){
			if(djcf.get("value").equals("11")||djcf.get("value").equals("12")){
				qcf+=(Long)djcf.get("counts");
			}else{
				zcf+=(Long)djcf.get("counts");;
			}
		}
		for(Map<String,Object> zjcf:blResult_zjcf){
			if(zjcf.get("value").equals("21")||zjcf.get("value").equals("22")||zjcf.get("value").equals("23")||zjcf.get("value").equals("24")){
				qcf+=(Long)zjcf.get("counts");
			}else{
				zcf+=(Long)zjcf.get("counts");;
			}
		}
		
		//计算占比
		String qcfzb = PercentFormula.acczb(Integer.valueOf(qcf.toString()),Integer.valueOf(zsSum.toString()),2);
        String zcfzb = PercentFormula.acczb(Integer.valueOf(zcf.toString()),Integer.valueOf(zsSum.toString()),2);
        Map<String, Object> result = new HashMap<String, Object>();
    	//党纪政纪处分
		result.put("djzj_cf", djzj_cf);
		//立案审查，组织处理处理的案件
		result.put("zzcl_la", zzcl_la);
		//总处分案件数
		result.put("zsSum", zsSum);
		//轻处分
		result.put("qcf", qcf);
		//重处分
		result.put("zcf", zcf);
		//移送司法
		result.put("yjsf",yjsf);
		//重处分占比
		
		result.put("zcfzb", zcfzb);//============
		//轻处分占比
		result.put("qcfzb", qcfzb);//============
		return result;
	}
	@Override
	public void createExcelService(String which,Map<String,Object>param,HttpServletRequest req,HttpServletResponse res) {
		// TODO Auto-generated method stub
		ServletContext servletContext = req.getServletContext();
		String start=(String)param.get("start");
		String end=(String)param.get("end");
		String startLastYear=(String)param.get("startLastYear");
		String endLastYear=(String)param.get("endLastYear");
		String special=(String)param.get("special");
		System.out.println((String)param.get("fields"));
		if(!StringUtils.isBlank(special)){
			special=" and LOCATE('"+special+"',p.special)!=0";
		}else{
			special="";
		}
		Map<String,Object>data=new HashMap<String,Object>();
		/*职级违纪行为*/
		if(which.endsWith("zjwjxw")){
			List<Dict> dictList=(List<Dict>)servletContext.getAttribute("fyZyViolation");
			String A1=SqlMaker0.maker(start, end,startLastYear,endLastYear,special);
			String[]zm=new String[]{"A","B","C","D","E","F","G","H"};//单个数量
			List<Map<String,Object>> list  = proStaticesMapper.findCountExcel(A1);
			data.put("list", list);
			List<Integer>l=new ArrayList<Integer>();
			for(int i=0;i<list.size()-1;i++){
				int num=0;
				for(Map<String,Object> m:list){
					Number n =  (Number)m.get(zm[i]);
					if(n!=null){
						
						num+=n.intValue();
						
					}
				}
				l.add(num);
				System.out.println(i);
			}
			data.put("totalList", l);
			data.put("dictList", dictList);
		}
		/*纪律审查情况分析表*/
		if(which.endsWith("jlscqk")){
			String A1=SqlMaker2.make(start, end, startLastYear, endLastYear,special);			
			List<Map<String,Object>> listRight  = proStaticesMapper.findCountExcel(A1);
			String  B1=SqlMaker3.maker(start, end, startLastYear, endLastYear,special);
			List<Map<String,Object>> listLeft  = proStaticesMapper.findCountExcel(B1);
			    int i=0;
				for(Map<String,Object> map:listLeft){
					Map<String, Object> map2 = listRight.get(i);
					Set<String> keySet = map2.keySet();
					for(String key:keySet){
						map.put(key+2, map2.get(key));
					}
				 i++;	 
				}
		    Map<String,Integer>mm=new HashMap<String,Integer>();
		    int l=0;
			for(Map<String,Object> m:listLeft){
				
				Set<String> keySet = m.keySet();
				for(String key: keySet){
					if(!key.equals("name")&&!key.equals("name2")){
						System.out.println(key);
						if(l==0){
							mm.put(key, 0);	
						}
						
						Integer n =(Integer)mm.get(key);
						if(m.get(key) instanceof String){
							String x=(String)m.get(key);
							mm.put(key, Integer.valueOf(n)+Integer.valueOf(x));
						}
						if(m.get(key) instanceof BigDecimal){
							BigDecimal x=(BigDecimal )m.get(key);
							mm.put(key, Integer.valueOf(n)+x.intValue());
						}
						
						
					}
					
				}
				l++;
			}
				
				
			data.put("listLeft", listLeft);
			data.put("mm", mm);
		}
		/*职级反映问题违纪行为分类表*/
		if(which.indexOf("zjfywtwjxw")!=-1){
			/*Map<String, List<Dict>> options = OptionModel.options;
			List<Dict> dictList=options.get("fyZyViolation");*/
			String A1=SqlMaker1.maker(start, end, startLastYear, endLastYear,special);
			String[]zm=new String[]{"A","B","C","D","E","F","G","H"};//单个数量
			List<Map<String,Object>> list  = proStaticesMapper.findCountExcel(A1);
			
			data.put("list", list);
			List<Integer>l=new ArrayList<Integer>();
			for(int i=0;i<list.size();i++){
				int num=0;
				for(Map<String,Object> m:list){
					Number n =  (Number)m.get(zm[i]);
					if(n!=null){
						
						num+=n.intValue();
					}
				}
				l.add(num);
			}
			data.put("totalList", l);
			//data.put("dictList", dictList);
			
		}
		/*案件处理情况表*/
		if(which.endsWith("ajclqk")){
		     String maker2 = SqlMaker4.maker(start, end,special);
		     String maker3=  SqlMaker5.maker(start, end,special);
		     String maker1=  SqlMaker6.maker(start, end,special);
		     List<Map<String,Object>> list1  = proStaticesMapper.findCountExcel(maker1);
		     List<Map<String,Object>> list2  = proStaticesMapper.findCountExcel(maker2);//党纪政纪处理
		     List<Map<String,Object>> list3  = proStaticesMapper.findCountExcel(maker3);
		     int i=0;
		     for(Map<String,Object> map:list1){
		    	 Map<String, Object> map2 = list2.get(i);
		    	 Set<String> keySet = map2.keySet();
		    	 for(String key:keySet){
		    		 map.put(key+2, map2.get(key));
		    	 }
		    	 Map<String, Object> map3 = list3.get(i);
		    	 Set<String> keySet2 = map3.keySet();
		    	 for(String key2:keySet2){
		    		 map.put(key2+3, map3.get(key2));
		    	 }
		    	 i++;
		     }
		     
		    
		     data.put("zyViolation",servletContext.getAttribute("fyZyViolation"));
		     data.put("list",list1);
		}
	    /*违纪行为分类情况表*/
		if(which.endsWith("wjxwflqk")){
			String maker1 = SqlMaker7.maker(start, end,special);
			String maker2 = SqlMaker8.maker(start, end,special);
			List<Map<String,Object>> list1  = proStaticesMapper.findCountExcel(maker1);
			List<Map<String,Object>> list2  = proStaticesMapper.findCountExcel(maker2);
			data.put("list1", list1);
			data.put("list2", list2);
			//小计
		    Map<String, Object> clumonSum1 = SumUtil.clumonSum(list1);
		    Map<String, Object> clumonSum2 = SumUtil.clumonSum(list2);
		    data.put("clumonSum1", clumonSum1);
		    data.put("clumonSum2", clumonSum2);
		    //总计
		    List<Map<String, Object>>total=new ArrayList<Map<String,Object>>();
		    total.add(clumonSum1);
		    total.add(clumonSum2);
		    Map<String, Object> totalSum = SumUtil.clumonSum(total);
		    data.put("totalSum", totalSum);
		
		    data.put("zyViolation",servletContext.getAttribute("fyZyViolation"));
		}
		if(which.endsWith("sslyflqk")){//所属领域分类情况表
			String maker1 = SqlMaker9.maker(start,end);
			List<Map<String,Object>> list1  = proStaticesMapper.findCountExcel(maker1);
			data.put("list", list1);
		    data.put("fields",servletContext.getAttribute("fields"));
		}
if(which.endsWith("template")){
			//线索来源
			data.put("clues",ListToString.make((List<Dict>)servletContext.getAttribute("clues")));
			data.put("rank",ListToString.make((List<Dict>)servletContext.getAttribute("rank")));
			data.put("fyzvviolation",ListToString.make((List<Dict>)servletContext.getAttribute("fyzvviolation")));
			data.put("problemLand",ListToString.make((List<Dict>)servletContext.getAttribute("problemLand")));
			data.put("type",ListToString.make((List<Dict>)servletContext.getAttribute("type")));
			
			data.put("documentType",ListToString.make((List<Dict>)servletContext.getAttribute("documentType")));
			data.put("political",ListToString.make((List<Dict>)servletContext.getAttribute("political")));
			data.put("civilServant",ListToString.make((List<Dict>)servletContext.getAttribute("civilServant")));
			data.put("czCompany",ListToString.make((List<Dict>)servletContext.getAttribute("problemLand")));
			data.put("departmenType",ListToString.make((List<Dict>)servletContext.getAttribute("departmenType")));
			data.put("natureOfenterprise",ListToString.make((List<Dict>)servletContext.getAttribute("natureOfenterprise")));
			data.put("classOfenterprise",ListToString.make((List<Dict>)servletContext.getAttribute("classOfenterprise")));
			data.put("posType",ListToString.make((List<Dict>)servletContext.getAttribute("posType")));
			data.put("post",ListToString.make((List<Dict>)servletContext.getAttribute("post")));
			data.put("topDiscipline",ListToString.make((List<Dict>)servletContext.getAttribute("topDiscipline")));
			data.put("cadre",ListToString.make((List<Dict>)servletContext.getAttribute("cadre")));
			data.put("partyRepresent",ListToString.make((List<Dict>)servletContext.getAttribute("partyRepresent")));
			data.put("np",ListToString.make((List<Dict>)servletContext.getAttribute("np")));
			data.put("dwMember",ListToString.make((List<Dict>)servletContext.getAttribute("dwMember")));
			data.put("jwMember",ListToString.make((List<Dict>)servletContext.getAttribute("jwMember")));
			data.put("zx",ListToString.make((List<Dict>)servletContext.getAttribute("zx")));
			data.put("supervision",ListToString.make((List<Dict>)servletContext.getAttribute("supervision")));
			data.put("pMSupervisoryObject",ListToString.make((List<Dict>)servletContext.getAttribute("pMSupervisoryObject")));
			data.put("is","是,否");
			data.put("superviseCompany",ListToString.make((List<Dict>)servletContext.getAttribute("superviseCompany")));
			data.put("czMethod",ListToString.make((List<Dict>)servletContext.getAttribute("czMethod")));
			data.put("blResult_djcf",ListToString.make((List<Dict>)servletContext.getAttribute("blResult_djcf")));
			data.put("blResult_zjcf",ListToString.make((List<Dict>)servletContext.getAttribute("blResult_zjcf")));
			data.put("blResult_zzcl",ListToString.make((List<Dict>)servletContext.getAttribute("blResult_zzcl")));
			data.put("blResult_yjsf","移送司法");
			data.put("blResult_qtcl",ListToString.make((List<Dict>)servletContext.getAttribute("blResult_qtcl")));
			data.put("fields",ListToString.make((List<Dict>)servletContext.getAttribute("fields")));
			data.put("fourForms",ListToString.make((List<Dict>)servletContext.getAttribute("fourForms")));
			data.put("special",ListToString.make((List<Dict>)servletContext.getAttribute("special")));
		}
		data.put("start", start);
		data.put("end", end);
		CountExcelU.zyCountExcel(data,which,req,res);
	}
	
	
    /**
     * 创建报告word
     */
	@Override
	public void createWordService(String which, Map<String, Object> param,
			HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		String start=(String)param.get("start");
	    String end=(String)param.get("end");
	    String startLastYear=(String)param.get("startLastYear");
	    String endLastYear=(String)param.get("endLastYear");
		Map<String,Object> obj = new HashMap<String, Object>();
		/*总量*/
		Map<String,Object>search_param=new HashMap<String,Object>();
		search_param.put("start", start);
		search_param.put("end", end);
		List<Map<String, Object>> selectTotal = st.selectTotal(search_param);
		double total=0;
		double total_last=0;
		for(Map<String,Object> m:selectTotal) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				total+=(Integer)m.get("total");
			}
		}
		
		obj.put("num", total);
		
		search_param.put("start", startLastYear);
		search_param.put("end", endLastYear);
		List<Map<String, Object>> select_total_last = st.selectTotal(search_param);
		for(Map<String,Object> m:select_total_last) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				total_last+=(Integer)m.get("total");
			}
		}
		
		/*同比*/
		obj.put("tb", PercentFormula.accuracy(total, total_last, 2));
		/*初步核实*/
		int cbhs=0;
		obj.put("time", start);
		obj.put("etime", end);
		search_param.put("option", "2");
		search_param.put("start", start);
		search_param.put("end", end);
		List<Map<String, Object>> selectCzfs = st.selectCzfs(search_param);search_param.remove("option");
		for(Map<String,Object> m:selectCzfs) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				 cbhs+=(Integer)m.get("num");
			}
			
		}
		obj.put("cbhs", cbhs);
		int czfs_total=0;
		search_param.put("option", "-1");
		List<Map<String, Object>> select_czfs_total = st.selectCzfs(search_param);
		for(Map<String,Object> m:select_czfs_total) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				 czfs_total+=(Integer)m.get("num");
			}
			
		}
		
		obj.put("cbhs_zb", PercentFormula.acczb(cbhs, czfs_total, 2));
		double czfs_total_last=0;
		search_param.put("start", startLastYear);
		search_param.put("end", endLastYear);
		List<Map<String, Object>> select_czfs_total_last = st.selectCzfs(search_param);
		for(Map<String,Object> m:select_czfs_total_last) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				 czfs_total_last+=(Integer)m.get("num");
			}
			
		}
		obj.put("czfs_total", czfs_total);
		obj.put("czfs_total_tb", PercentFormula.accuracy(czfs_total, czfs_total_last, 2));
		
		
		
		/*立案情况*/
		double  la_total=0;
		double  la_total_last=0;
		search_param.put("rank", "-1");
		search_param.put("start", start);
		search_param.put("end", end);
		List<Map<String, Object>> selectLa = st.selectLa(search_param);
		for(Map<String,Object> m:selectLa) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				 la_total+=(Integer)m.get("num");
			}
			
		}
		search_param.put("start", startLastYear);
		search_param.put("end", endLastYear);
		List<Map<String, Object>> select_la_last = st.selectLa(search_param);
		for(Map<String,Object> m:select_la_last) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				 la_total_last+=(Integer)m.get("num");
			}
			
		}
		obj.put("la_total",la_total);
		obj.put("la_total_tb",PercentFormula.accuracy(la_total, la_total_last, 2));
		
		
		
		/*结案情况*/
		double ja_total=0;
		double ja_total_last=0;
		search_param.put("start", start);
		search_param.put("end", end);
		search_param.put("rank", "-1");
		List<Map<String, Object>> select_ja = st.select_ja(search_param);
		for(Map<String,Object> m:select_ja) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				ja_total+=(Integer)m.get("num");
			}
			
		}
		search_param.put("start", start);
		search_param.put("end", end);
		List<Map<String, Object>> select_ja_last = st.select_ja(search_param);
		for(Map<String,Object> m:select_ja_last) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				ja_total_last+=(Integer)m.get("num");
			}
			
		}
		obj.put("ja_total",ja_total);
		
		obj.put("ja_total_tb",PercentFormula.accuracy(ja_total, ja_total_last, 2));
		/*党纪政纪处分*/
		search_param.put("start", start);
		search_param.put("end", end);
		int cf_total=0;
		int djzj_total=0;
		int zzcl_total=0;
		int qcf_total=0;
		int zcf_total=0;
		int yssf_total=0;
		List<Map<String, Object>> selectCf = st.selectCf(search_param);
		for(Map<String,Object> m:selectCf) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				if(m.get("option")!=null&&"0118,0119".indexOf(m.get("option").toString())!=-1) {
					djzj_total+=(Integer)m.get("num");
				}
				if(m.get("option")!=null&&m.get("option").toString().equals("0117")) {
					zzcl_total+=(Integer)m.get("num");
				}
				if(m.get("option")!=null&&"011901,011902,011801,011802,011803".indexOf(m.get("option").toString())!=-1) {
					qcf_total+=(Integer)m.get("num");
				}
				if(m.get("option")!=null&&"011903,011904,011905,011804,011805,011806".indexOf(m.get("option").toString())!=-1) {
					zcf_total+=(Integer)m.get("num");
				}
				if(m.get("option")!=null&&"012101".equals(m.get("option"))) {
					yssf_total+=(Integer)m.get("num");
				}
				cf_total+=(Integer)m.get("num");
			}
			
		}
		obj.put("djzj_total", djzj_total);
		obj.put("zzcl_total", zzcl_total);
		obj.put("qcf_total", qcf_total);
		obj.put("zcf_total", zcf_total);
		obj.put("yssf_total", yssf_total);
		double cf_total_last=0;
		double djzj_total_last=0;
		double zzcl_total_last=0;
		double qcf_total_last=0;
		double zcf_total_last=0;
		double yssf_total_last=0;
		
		search_param.put("start", startLastYear);
		search_param.put("end", endLastYear);
		List<Map<String, Object>> select_cf_last = st.selectCf(search_param);
		for(Map<String,Object> m:select_cf_last) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				if(m.get("option")!=null&&"0118,0119".indexOf(m.get("option").toString())!=-1) {
					djzj_total_last+=(Integer)m.get("num");
				}
				if(m.get("option")!=null&&m.get("option").toString().equals("0117")) {
					zzcl_total_last+=(Integer)m.get("num");
				}
				if(m.get("option")!=null&&"011901,011902,011801,011802,011803".indexOf(m.get("option").toString())!=-1) {
					qcf_total_last+=(Integer)m.get("num");
				}
				if(m.get("option")!=null&&"011903,011904,011905,011804,011805,011806".indexOf(m.get("option").toString())!=-1) {
					zcf_total_last+=(Integer)m.get("num");
				}
				if(m.get("option")!=null&&"012101".equals(m.get("option"))) {
					yssf_total_last+=(Integer)m.get("num");
				}
			}
			
		}
		obj.put("djzj_tb", PercentFormula.accuracy(djzj_total, djzj_total_last, 2));//===========
		obj.put("zzcl_tb", PercentFormula.accuracy(zzcl_total, zzcl_total_last, 2));//===========
		obj.put("qcf_tb", PercentFormula.accuracy(qcf_total, qcf_total_last, 2));//=============
		obj.put("zcf_tb", PercentFormula.accuracy(zcf_total, zcf_total_last, 2));//=============
		obj.put("yssf_tb", PercentFormula.accuracy(yssf_total, yssf_total_last, 2));//===========
		obj.put("qcf_zb", PercentFormula.acczb(qcf_total, cf_total, 2));
		obj.put("zcf_zb", PercentFormula.acczb(qcf_total, cf_total,2));
		
		
		
		
		/*线索来源*/
		    int clues_total=0;
		    search_param.put("type", StaticticsType.CLUES);
			search_param.put("start", start);
			search_param.put("end", end); 
		List<Map<String, Object>> basic_list = st.selectBasic(search_param);
		for(Map<String,Object> m:basic_list) {
			if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				clues_total+=(Integer)m.get("num");
			}
		}
		List<Map<String, Object>> selectBasic = st.selectBasic(search_param);
		Map<String, Object>basic_data = UtilFroWord.basic_data(clues_total, "clues", selectBasic, req);
		obj.put("clues", basic_data);
		
		
		/*处置方式*/
		search_param.put("start", start);
		search_param.put("end", end);
		List<Map<String, Object>> select_czfs_list = st.selectCzfs(search_param);
		ServletContext servletContext = req.getServletContext();
		List<Dict>czMethod_list=(List<Dict>)servletContext.getAttribute("czMethod");
		Map<String,Object>czMethod_result=new HashMap<String,Object>();
		for(Dict d:czMethod_list) {
			String name=d.getName();
			String value=d.getValue();
			Map<String,Object>bean=new HashMap<String,Object>();
			StringBuffer buf=new StringBuffer();
			int this_total=0;
			for(Map<String,Object> m:selectBasic) {
				if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1&&!m.get("option").toString().equals("-1")) {
					if(m.get("option").toString().equals(value)) {
						this_total+=(Integer)m.get("num");
					}
					
				}
			}
			buf.append("，数量为："+this_total);
			buf.append("，占比："+PercentFormula.acczb(this_total, czfs_total, 2));
			czMethod_result.put(name, buf.toString());
		   
		}
		czMethod_result.put("，合计：", czfs_total);
		obj.put("czMethod", czMethod_result);
		
		    /*领域统计*/
		    int field_total=0;
		    search_param.put("type", StaticticsType.FIELDS);
			search_param.put("start", start);
			search_param.put("end", end); 
		    List<Map<String, Object>> field_list = st.selectBasic(search_param);
		    for(Map<String,Object> m:basic_list) {
			   if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				    field_total+=(Integer)m.get("num");
			   }
		    }
		List<Map<String, Object>> select_basic_field = st.selectBasic(search_param);
		Map<String, Object> field_data = UtilFroWord.basic_data(clues_total, "fields", select_basic_field, req);
		
		obj.put("fields", field_data);
		
		
		/*职级*/
		 int rank_total=0;
		    search_param.put("type", StaticticsType.RANK);
			search_param.put("start", start);
			search_param.put("end", end); 
		    List<Map<String, Object>> rank_list = st.selectBasic(search_param);
		    for(Map<String,Object> m:basic_list) {
			   if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				   rank_total+=(Integer)m.get("num");
			   }
		    }
		List<Map<String, Object>> select_basic_rank = st.selectBasic(search_param);
		Map<String, Object>rank_data = UtilFroWord.basic_data(rank_total, "fields", select_basic_rank, req);
		
		obj.put("beReflectRank", rank_data);
		
		
		/*归属地*/
		 int problemLand_total=0;
		    search_param.put("type", StaticticsType.RANK);
			search_param.put("start", start);
			search_param.put("end", end); 
		    List<Map<String, Object>> problem_list = st.selectBasic(search_param);
		    for(Map<String,Object> m:basic_list) {
			   if(m.get("time")!=null&&m.get("time").toString().indexOf("-")!=-1) {
				   problemLand_total+=(Integer)m.get("num");
			   }
		    }
		List<Map<String, Object>> select_basic_problem = st.selectBasic(search_param);
		Map<String, Object>problem_data = UtilFroWord.basic_data(problemLand_total, "rank", select_basic_problem, req);
		obj.put("problemLand", problem_data);
        //导出工具类
		CountExcelU.zyCountExcel(obj,which,req,res);
		
		}


	@Override
	public void exportFile(List<ExcelPojo> list,HttpServletRequest req,HttpServletResponse res) {
		    // TODO Auto-generated method stub
		    Field[] declaredFields = ExcelPojo.class.getDeclaredFields();
		    Map<String,Object>result=new HashMap<String,Object>(); 
		    Map<String,Object>data=new HashMap<String,Object>();
		    for(ExcelPojo e:list) {
		    	for(Field  f:declaredFields) {
		    		f.setAccessible(true);
		    		 try {
						if(f.get(e)!=null&&!f.get(e).toString().equals("")){
						    	ExcelFild annotation = f.getAnnotation(ExcelFild.class);
						    	String annotation_title=annotation.title();
						    	String field_value=f.get(e).toString();
						    	data.put(annotation_title, field_value);
						 }
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
		    }
		     result.put("data", data);
			CountExcelU.zyCountExcel(result,"线索导出文档.doc_线索导出文档.xml_xsdc",req,res);
		}
		
	}	
	
	

       
    

