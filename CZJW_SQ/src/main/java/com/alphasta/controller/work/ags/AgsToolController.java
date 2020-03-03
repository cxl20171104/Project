package com.alphasta.controller.work.ags;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.statisticsUtils.CountExcelU;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.model.Accessories;
import com.alphasta.model.Dict;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.service.AccessoriesService;
@Controller
@RequestMapping("/ags_tools")
public class AgsToolController extends BaseController{
	 @Autowired
	 private ProblemCluesMapper problemCluesMapper;
	 @Autowired
	 private AccessoriesService accessoriesService;
	 @Autowired
	 private ProgressMapper prom;
	 SimpleDateFormat yyyy年MM月dd日=new SimpleDateFormat("yyyy年mm月dd日");
	 SimpleDateFormat yyyy_MM_dd=new SimpleDateFormat("yyyy-MM-dd");
     @RequestMapping("/tool0")
     public void tool0(String fileName,String cluesId,String fileTime) {
    	 Map<String,Object>data=new HashMap<String,Object>();
    	 ProblemClues pro = problemCluesMapper.findProblemCluesById(cluesId);
    	 if(pro!=null) {
    		 ServletContext servletContext = request.getServletContext();
    		 //呈批签的标题
    		 //市里的版本直接用沧州市
    		 data.put("where", "沧州市");
    		 data.put("sltime", "");
    		 if(StringUtils.isNotEmpty(pro.getReceiveDate())) {
    			    String[] split = pro.getReceiveDate().split("-");
    			    String month="";
    			    String day="";
    			    if(split[1].startsWith("0")) {
    			    	month=split[1].substring(1);
    			    }else {
    			    	month=split[1];
    			    }
    			    if(split[2].startsWith("0")) {
    			    	day=split[2].substring(1);
    			    }else {
    			    	day=split[2];
    			    }
					data.put("sltime", split[0]+"年"+month+"月"+day+"日");
    		 }
    		 data.put("cluse", "");
    		 List<Dict> cluesList=(List<Dict>)servletContext.getAttribute("clues");
    		 for(Dict d:cluesList) {
    			 if(d.getValue().equals(pro.getClues())){
    				 data.put("cluse", d.getName());
    			 }
    		 }
    		 data.put("describe", "");
    		 if(StringUtils.isNotEmpty(pro.getProblemDescribe())) {
    			 data.put("describe", pro.getProblemDescribe());
    		 }
    		 data.put("cbr", "");
    		 if(StringUtils.isNotEmpty(pro.getCbr_now())) {
    			 data.put("cbr", pro.getCbr_now());
    		 }
    		 //查看该线索是否有分办时间
    		 Progress proms=new Progress();
    		 proms.setCauseId(cluesId);
    		 proms.setPointName("案管室分办");
    		 List<Progress> presult = prom.findProgress(proms);
    		 if(presult!=null&&presult.size()>0) {
    			 int cpq_num=Param.cpq_num+1;
    			 String cpq_num_str="";
    			 if(cpq_num<10) {
    				 cpq_num_str="00"+cpq_num;
    			 }
    			 if(10<=cpq_num&&cpq_num<100) {
    				 cpq_num_str="0"+cpq_num;
    			 }
    			 if(cpq_num>=100) {
    				 cpq_num_str=""+cpq_num;
    			 }
    			 if(cpq_num>999) {
    				 Param.cpq_num=0;
    				 cpq_num_str="001";
    			 }
    			 data.put("cluesNum", Param.xzqh+presult.get(0).getTimeForday().substring(2, 10).replace("-", "")+cpq_num_str);
    			 Param.cpq_num=cpq_num;
    		 }else {
    			 int cpq_num=Param.cpq_num+1;
    			 String cpq_num_str="";
    			 if(cpq_num<10) {
    				 cpq_num_str="00"+cpq_num;
    			 }
    			 if(10<=cpq_num&&cpq_num<100) {
    				 cpq_num_str="0"+cpq_num;
    			 }
    			 if(cpq_num>=100) {
    				 cpq_num_str=""+cpq_num;
    			 }
    			 if(cpq_num>999) {
    				 Param.cpq_num=0;
    				 cpq_num_str="001";
    			 }
    			 data.put("cluesNum", Param.xzqh+fileTime.replace("-", "").substring(2)+cpq_num_str);
    			 Param.cpq_num=cpq_num;
    			 
    		 }
    		 data.put("reflected", "");
    		 if(pro.getReflectedPerson()!=null&&StringUtils.isNoneEmpty( pro.getReflectedPerson().getReflectedName())) {
    			 data.put("reflected", pro.getReflectedPerson().getReflectedName());
    		 }
    		 data.put("duty", "");
    		 if(pro.getReflectedPerson()!=null&&StringUtils.isNoneEmpty( pro.getReflectedPerson().getDuty())) {
    			 data.put("duty", pro.getReflectedPerson().getDuty());
    			 
    		 }
    		 String str="";
    		 //沧州市纪委监委问题线索分办呈批签(处级)
    		 if(fileName.equals("0")) {
    			 fileName="沧州市纪委监委问题线索分办呈批签(处级)";
    			 str=fileName+pro.getCluesNum()+".doc_沧州市纪委监委问题线索分办呈批签(处级).ftl_fbcpq";
    		 }
    		 if(fileName.equals("01")) {
    			 fileName="沧州市纪委监委问题线索分办呈批签(处级)(2)";
    			 str=fileName+pro.getCluesNum()+".doc_沧州市纪委监委问题线索分办呈批签(处级)(2).ftl_fbcpq";
    		 }
    		 //沧州市纪委监委问题线索分办呈批签(科级及以下)
    		 if(fileName.equals("1")) {
    			 fileName="沧州市纪委监委问题线索分办呈批签(科级及以下)";
    			 str=fileName+pro.getCluesNum()+".doc_沧州市纪委监委问题线索分办呈批签(科级及以下).ftl_fbcpq";
    		 }
    		 if(fileName.equals("11")) {
    			 fileName="沧州市纪委监委问题线索分办呈批签(科级及以下)(2)";
    			 str=fileName+pro.getCluesNum()+".doc_沧州市纪委监委问题线索分办呈批签(科级及以下)(2).ftl_fbcpq";
    		 }
    		 if(fileName.equals("12")) {
    			 fileName="沧州市纪委监委问题线索分办呈批签(科级及以下)(3)";
    			 str=fileName+pro.getCluesNum()+".doc_沧州市纪委监委问题线索分办呈批签(科级及以下)(3).ftl_fbcpq";
    		 }
    		 if(fileName.equals("13")) {
    			 fileName="沧州市纪委监委问题线索分办呈批签(科级及以下)(4)";
    			 str=fileName+pro.getCluesNum()+".doc_沧州市纪委监委问题线索分办呈批签(科级及以下)(4).ftl_fbcpq";
    		 }
    		 //创建并生成
        	 CountExcelU.zyCountExcel(data,str,request,response);
    	 }
    	 
    	 
     }
     //方法目的：得到所有的已有呈批签类型 其中  在数据库中摸版存储为  呈批签摸版
     @RequestMapping("/reportList_cpq")
     @ResponseBody
     public Object reportList_cpq(Integer page, Integer rows, String sort, String order,Accessories acc) {
     	//part1:查询数据
    	 Map<String, Object> reportListService = accessoriesService.getReportListService(page,  rows,  sort,  order, acc);
 	     return reportListService;
     }
}


