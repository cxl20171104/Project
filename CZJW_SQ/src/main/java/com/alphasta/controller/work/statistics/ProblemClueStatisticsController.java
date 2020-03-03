package com.alphasta.controller.work.statistics;

import java.io.File;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.statisticsUtils.DateUtilU;
import com.alphasta.model.Dict;
import com.alphasta.service.DictService;
import com.alphasta.service.ProblemCluesService2;
@Controller
@RequestMapping("/statistics")
public class ProblemClueStatisticsController extends BaseController{
	@Autowired
	private  ProblemCluesService2 problemCluesService2;
	private static final String COUNT = "/probleClues/count2";
	private static final String COUNT1 = "/probleClues/count";
	private static final String COUNTBLJG = "/probleClues/countBLJG";	
	/**
	 * 案件线索统计
	 */
	@RequestMapping(value = "/cdd", method = RequestMethod.GET)
	public ModelAndView count() {
		ModelAndView result = new ModelAndView(COUNT);
		return result;
	}
	
	@RequestMapping(value = "/cxl/{id}", method = RequestMethod.GET)
	public ModelAndView manager2(@PathVariable String id,HttpServletRequest req) {
		System.out.println(id);
		ModelAndView result = new ModelAndView(COUNT1);
		result.addObject("tb", id);
		return result;
	}
	
	@RequestMapping("/word")
	public ModelAndView word(){
		ModelAndView mv=new ModelAndView("/probleClues/downLoad");
	/*	Map<String,List<Dict>> ml=OptionModel.options;
	    mv.addObject("reportGs",ml.get("reportGs"));
	    mv.addObject("special",ml.get("special"));*/
		return mv;
	}

	/**
	 * 报表统计分析生成word文档
	 */
	@RequestMapping(value = "/OnImportWord")
	public void OnImportWord(HttpServletRequest req,HttpServletResponse res) {
		String attYear = req.getParameter("attYear");
		String attYearMonth = req.getParameter("attYearMonth");
		String attYearMonth2 = req.getParameter("attYearMonth2");
		String reportGs = req.getParameter("reportGs");
		String special=req.getParameter("special");
		String type=reportGs.split("_")[0];
		if(type.endsWith("doc")){
			 createWorld(attYear, attYearMonth, attYearMonth2, reportGs,special , req, res);
		}
		if(type.endsWith("xls")){
			 createExcle(attYear, attYearMonth, attYearMonth2, reportGs,special, req, res);	
		}
		
	}
	public void   createExcle(String attYear,
			String attYearMonth,
			String attYearMonth2,
			String reportGs,
			String special,
			HttpServletRequest req,
			HttpServletResponse res){
			    String start="";
			    String end="";
			    String startLastYear="";
			    String endLastYear="";
				if(attYear!=null && attYear!=""){
					attYear = attYear.replace("年","");
					start=attYear+"-01-01";
					end=attYear+"-12-31";
					startLastYear=String.valueOf(Integer.valueOf(attYear)-1)+"-01-01";
					endLastYear=String.valueOf(Integer.valueOf(attYear)-1)+"-12-31";
					
				}
                
			
				if(attYearMonth!=null && attYearMonth!=""){
					start=attYearMonth+"01";
					startLastYear=String.valueOf(Integer.valueOf(attYearMonth.split("-")[0])-1)+attYearMonth.split("-")[1]+"01";
					if(StringUtils.isEmpty(attYearMonth2)){
					end=attYearMonth+"31";
					endLastYear=String.valueOf(Integer.valueOf(attYearMonth.split("-")[0])-1)+attYearMonth.split("-")[1]+"31";
					}else{
					end = attYearMonth2 + "-31";	
					endLastYear=String.valueOf(Integer.valueOf(attYearMonth2.split("-")[0])-1)+attYearMonth2.split("-")[1]+"31";	
						
					}
				}
		        Map<String,Object>param=new HashMap<String,Object>();
		        param.put("start", start);
		        param.put("end", end);
		        param.put("startLastYear", startLastYear);
		        param.put("endLastYear", endLastYear);
		        param.put("special", special);
		        problemCluesService2.createExcelService(reportGs,param,req, res);
	}
	
	/**
	 * 
	 * @param attYear
	 * @param attYearMonth
	 * @param attYearMonth2
	 * @param reportGs
	 * @param ny
	 * @param beforeAttYearMonth
	 * @param beforeAttYearMonth2
	 * @param req
	 * @param res
	 */
	
	public void   createWorld(String attYear,
			String attYearMonth,
			String attYearMonth2,
			String reportGs,
			String special,
			HttpServletRequest req,
			HttpServletResponse res){
		   Map<String, Object> map = new HashMap<String, Object>();
		    //按照年统计
		   if(attYear!=null && attYear!=""){
				attYear = attYear.replace("年","-");
				String start=attYear + "01-01";
				String end = attYear + "12-31";
				map.put("start", start);
				map.put("end", end);
				attYear=attYear.replaceAll("-", "");
				map.put("startLastYear", Integer.valueOf(attYear)-1+"-01-01");
				map.put("endLastYear", Integer.valueOf(attYear)-1+"-12-31");
				
			}
		   
			if(attYearMonth!=null && attYearMonth!=""){
				String start=attYearMonth + "-01";
				String end =attYearMonth+"-31";
				//开始日期
				map.put("start", start);
				//去年开始日期
				map.put("startLastYear",  DateUtilU.date_j(start));
				map.put("endLastYear",  DateUtilU.date_j(end));
				if(attYearMonth2!=null && attYearMonth2!=""){
					end = attYearMonth2 + "-31";
					//结束日期
					map.put("end", end);
					//去年结束日期
					map.put("endLastYear", DateUtilU.date_j(end));
				}
			}
			

			map.put("attYear", attYear);
			map.put("special", special);
			problemCluesService2.createWordService(reportGs, map,req, res);
			
	}
}
