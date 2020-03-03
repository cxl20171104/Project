package com.alphasta.service.scheduled;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alphasta.commons.base.BaseController;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.mapper.PunishmentMapper;
import com.alphasta.model.Dict;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.Punishment;
@Component
public class FirstPageNum extends BaseController{
	 @Autowired
	   private ProblemCluesMapper problemCluesMapper;
	   @Autowired
	   private ProgressMapper   progressMapper;
	   @Autowired
	   private PunishmentMapper  punishmentMapper;
	   //每一个小时统计一次 将结果放到缓存中
	   @Scheduled(fixedRate = 1000*60*60)
	   public void makeDate() {
		   WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
		   SimpleDateFormat yyyy=new SimpleDateFormat("yyyy");
		   //总量
		   int total=0;
		   //接收案件  新案件
		   int jsClues=0;
		   //已经分办的案件//进度数量大于2的为已分办
		   int fbClues=0;
		   //立案的案件 pointValue为21的案件
		   int laClues=0;
		   //处分的案件 punishment里面有值得
		   int cfClues=0;
		   //移送有关党组织处理
		   int ysClues=0;
		   //办结案件
		   int bjClues=0;
		   //专项行动
		   Map<String,Integer>zxClues=new HashMap<String,Integer>();
		   List<String>zx_name=new ArrayList<String>();
		   List<Integer>zx_num=new ArrayList<Integer>();
		   //领域统计
		   List<Map<String,Object>>fieldClues=new ArrayList<Map<String,Object>>();
		   Map<String,Integer>fieldDetail=new HashMap<String,Integer>();
		   //线索来源统计!!!!
		   List<Map<String,Object>>cluesClues=new ArrayList<Map<String,Object>>();
		   Map<String,Integer>cluesDetail=new HashMap<String,Integer>();
		   //当年月收件量统计
		   List<Integer>monthClues=new ArrayList<Integer>();
		   Map<String,Integer>monthDetail=new HashMap<String,Integer>();
		   //问题属地统计
		   List<Map<String,Object>>problemLandClues=new ArrayList<Map<String,Object>>();
		   Map<String,Integer>problemLandDetail=new HashMap<String,Integer>();
		   //异常处理
		   StringBuffer errorMessage=new StringBuffer();
		   //查询出所有线索不包含重复件
		   List<ProblemClues> all = problemCluesMapper.findProblemClues();
		   ServletContext servletContext = webApplicationContext.getServletContext();
		   if(all!=null&&!all.isEmpty()) {
			   total=all.size();
		   }
		   try {
		   for(ProblemClues p:all) {
			   //问题属地统计
			   //领域统计
			  if(StringUtils.isNoneEmpty(p.getProblemLand())) {
				   List<Dict> dList=(List<Dict>)servletContext.getAttribute("problemLand");
				   if(dList!=null&&dList.size()!=0) {
				   for(Dict d:dList){
					   if(p.getProblemLand().equals(d.getValue())) {
						   Integer integer = problemLandDetail.get(d.getName());
						   if(integer==null){
							   problemLandDetail.put(d.getName(), 1);
						   }else {
							   problemLandDetail.put(d.getName(), integer+1);
						   }
					   }
				   }
				   }
			   }
             
			   //当年月收件量统计
			   if(StringUtils.isNotEmpty(p.getReceiveDate())
					   &&p.getReceiveDate().substring(0, 4).equals(yyyy.format(new Date()))) {
				   String month=p.getReceiveDate().substring(5, 7);
				   Integer integer = monthDetail.get(month);
				   if(integer==null) {
					   monthDetail.put(month, 1);
				   }else {
					   monthDetail.put(month, 1+integer);
				   }
			   }

			 //线索来源统计
			 if(StringUtils.isNoneEmpty(p.getClues())) {
				   //获取来源类型
				   List<Dict> dList=(List<Dict>)servletContext.getAttribute("clues");
				   if(dList!=null&&dList.size()!=0) {
				   for(Dict d:dList){
					   if(p.getClues().equals(d.getValue())) {
						   Integer integer = cluesDetail.get(d.getName());
						   if(integer==null){
							   cluesDetail.put(d.getName(), 1);
						   }else {
							   cluesDetail.put(d.getName(), integer+1);
						   }
					   }
				   }
				   }
			   }

			   //领域统计
			 if(StringUtils.isNoneEmpty(p.getFields())) {
				   List<Dict> dList=(List<Dict>)servletContext.getAttribute("fields");
				   if(dList!=null&&dList.size()!=0) {
				   for(Dict d:dList){
					   String field=","+p.getFields()+",";
					   if(field.indexOf(","+d.getValue()+",")!=-1) {
						   Integer integer = fieldDetail.get(d.getName());
						   if(integer==null){
							   fieldDetail.put(d.getName(), 1);
						   }else {
							   fieldDetail.put(d.getName(), integer+1);
						   }
					   }
				   }
				   }
			   }
			  //专项行动统计
			  if(!StringUtils.isEmpty(p.getSpecial())) {
				  List<Dict> dList=(List<Dict>)servletContext.getAttribute("special");
				  if(dList!=null&&dList.size()!=0) {
				  for(Dict d:dList) {
					  if(p.getSpecial().equals(d.getValue())) {
						   Integer integer = zxClues.get(d.getName());
						   if(integer==null){
							   zxClues.put(d.getName(), 1);
						   }else {
							   zxClues.put(d.getName(), integer+1);
						   }
					  }
				  }
				  }
			  }

			  if(StringUtils.isNotEmpty(p.getFinalState())&&p.getFinalState().equals("-1")) {
				  bjClues+=1;
			  }
			  List<Progress> all_progress =new ArrayList<Progress>();
			  if(StringUtils.isNotEmpty(p.getId())) {
				  all_progress = progressMapper.findProgressByCid(p.getId());
			  }
			  if(!all_progress.isEmpty()&&all_progress.size()>2) {
				  fbClues+=1;
			  }
			  if(!all_progress.isEmpty()&&all_progress.size()==1) {
				  jsClues+=1;
			  }
			  //遍历进度
			  for(Progress progress:all_progress) {
				  if(progress.getPointValue().equals(Param.lasc_value)) {
					 laClues+=1;
				  }
				  //移送案件
				  if(progress.getPointValue().equals(Param.scshyjd_value)&&StringUtils.isNotEmpty(progress.getDetail())&&progress.getDetail().equals("5")){
					 ysClues+=1;
				  }
				  //监督室办结
				  if(progress.getPointValue().equals(Param.jdsjd_value)&&StringUtils.isNotEmpty(progress.getDetail())&&progress.getDetail().equals("7")) {
					 bjClues+=1;
				  }
				  //审查室了结
				  if(progress.getPointValue().equals(Param.scshyjd_value)&&StringUtils.isNotEmpty(progress.getDetail())&&"2,3,4,5".indexOf(progress.getDetail())!=-1) {
				     bjClues+=1;
				  }
			  }

			   List<Punishment> all_punishment =new ArrayList<Punishment>();
			  //处分案件
			  if(p.getReflectedPerson()!=null) {
				  String rid=p.getReflectedPerson().getId();
				  all_punishment =punishmentMapper.findPunishmentByRid(rid);
				  if(!all_punishment.isEmpty()) {
					  cfClues+=1;
				  }
				  
			  }
			  
            
		   }
		   
		   //对月线索进行处理
		   System.out.println("对月线索进行处理");
		   for(int i=1;i<=12;i++) {
			   if(i<10){
				   if(monthDetail.get("0"+i)!=null) {
					   monthClues.add((Integer)monthDetail.get("0"+i));
				   }else {
					   monthClues.add(0);
				   }
			   }else{
				   
				   if(monthDetail.get(""+i)!=null) {
					   monthClues.add((Integer)monthDetail.get(""+i));
				   }else {
					   monthClues.add(0);
				   }
				   
			   }
			   
		   }
		   //对问题属地统计的处理
		   Set<String> kset = problemLandDetail.keySet();
		   for(String c:kset){
			    Map<String,Object>data=new HashMap<String,Object>();
			    data.put("name", c);
			    data.put("value", problemLandDetail.get(c));
				problemLandClues.add(data);
		   }
		   //转项行动统计
		   Set<String> kst = zxClues.keySet();
		   for(String b:kst) {
			   zx_name.add(b);
			   zx_num.add(zxClues.get(b));
		   }
		   //对领域统计的处理
		   Set<String> kt = fieldDetail.keySet();   
		   for(String a:kt){
			    Map<String,Object>data=new HashMap<String,Object>();
			    data.put("field_name", a);
			    data.put("field_num", fieldDetail.get(a));
			    fieldClues.add(data);
		   }
		   //线索来源的处理
		   Map<String,Object>clues_total=new HashMap<String,Object>();
		   clues_total.put("clues_total", 0);
		   Set<String> kclues = cluesDetail.keySet();
		   for(String a:kclues){
			    Map<String,Object>data=new HashMap<String,Object>();
			    data.put("clues_name", a);
			    data.put("clues_num", cluesDetail.get(a));
			    Integer i=(Integer)clues_total.get("clues_total");
			    clues_total.put("clues_total", i+Integer.valueOf(cluesDetail.get(a)));
			    cluesClues.add(data);
		   }
		   cluesClues.add(clues_total);
		   }catch(Exception e) {
			   errorMessage.append(exceptionToString(e));
		   }
		   ServletContext ctx = servletContext;
		   ctx.setAttribute("total", total);
		   ctx.setAttribute("jsClues", jsClues);
		   ctx.setAttribute("fbClues", fbClues);
		   ctx.setAttribute("laClues", laClues);
		   ctx.setAttribute("cfClues", cfClues);
		   ctx.setAttribute("ysClues", ysClues);
		   ctx.setAttribute("bjClues", bjClues);
		   ctx.setAttribute("zx_name", zx_name);
		   ctx.setAttribute("zx_num", zx_num);
		   ctx.setAttribute("fieldClues", fieldClues);
		   ctx.setAttribute("cluesClues", cluesClues);
		   ctx.setAttribute("monthClues", monthClues);
		   ctx.setAttribute("problemLandClues", problemLandClues);
		   ctx.setAttribute("errorMessage", errorMessage.toString());
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
	   public static void main(String[] args) {
		try {
			System.out.println(1/0);
		}catch(Exception ex) {
			System.out.println(exceptionToString(ex));
		}
	}
}
