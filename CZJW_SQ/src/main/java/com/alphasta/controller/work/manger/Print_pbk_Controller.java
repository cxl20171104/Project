package com.alphasta.controller.work.manger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;

import com.alphasta.commons.utils.CreatTwoDecemensionCodeImageUtil;

import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.PrintDemo;
import com.alphasta.commons.utils.WordTest;
import com.alphasta.commons.utils.WordUtils;
import com.alphasta.model.CaseClue;
import com.alphasta.service.CaseClueService;

import freemarker.template.Configuration;
import freemarker.template.Template;
@Controller
@RequestMapping("/print")
public class Print_pbk_Controller extends BaseController{
	@Autowired
	private CaseClueService caseClueService;
	private static final String DETAIL = "/caseclue/caseDetail";
	//生成并打印批办卡
	@RequestMapping("/pbk")
	@ResponseBody
	public void print_pbk(String ids,HttpServletRequest req,HttpServletResponse res){
		  try {
		String[]strs=ids.split(",");
		for(String s:strs){	
			    CaseClue c= caseClueService.findCaseClueById(Long.valueOf(ids));
			    
			    String receive=c.getReceiveDate();
			    c.setReceiveDate(receive.split("-")[0]+"年"+receive.split("-")[1]+"月"+receive.split("-")[2]+"日");
			    String toConpany=c.getToCompany();
			    if(("1").equals(toConpany)){
			    	c.setToCompany("省纪委交办");
			    }
			    if(("2").equals(toConpany)){
			    	c.setToCompany("中纪委交办");
			    }
			    if(("3").equals(toConpany)){
			    	c.setToCompany("省委巡视组交办");
			    }
			    if(("4").equals(toConpany)){
			    	c.setToCompany("上级交办其他");
			    }
			    if(("5").equals(toConpany)){
			    	c.setToCompany("信访部门");
			    }
			    if(("6").equals(toConpany)){
			    	c.setToCompany("执纪监督部门");
			    }
			    if(("7").equals(toConpany)){
			    	c.setToCompany("执纪审查部门");
			    }
			    if(("8").equals(toConpany)){
			    	c.setToCompany("干部监督部门");
			    }
			    if(("9").equals(toConpany)){
			    	c.setToCompany("巡视工作机构");
			    }
			    if(("10").equals(toConpany)){
			    	c.setToCompany("审计机关");
			    }
			    if(("11").equals(toConpany)){
			    	c.setToCompany("行政执法机关");
			    }
			    if(("12").equals(toConpany)){
			    	c.setToCompany("司法机关");
			    }
			    if(("13").equals(toConpany)){
			    	c.setToCompany("普通案件其他");
			    }
			    Map<String,Object> dataMap=new HashMap<String,Object>();
			    dataMap.put("case", c);
		        
		        //存储文件的绝对地址
		        String floderPath=req.getSession().getServletContext().getRealPath("static")+File.separator+"file";
		        File floder=new File(floderPath);
		        if(!floder.exists()){
		        	floder.mkdirs();
		        }
		        CreatTwoDecemensionCodeImageUtil ctdciu = new CreatTwoDecemensionCodeImageUtil();
		        //生成二维码图片
		       // ctdciu.encoderQRCode(ids, floderPath+File.separator+ids+".jpg", "jpg", 6);
		        System.out.println(floderPath+File.separator+ids+"aaa.jpg");
		        ctdciu.encoderQRCode(ids, floderPath+File.separator+ids+"aaa.jpg", "jpg", 6);
		        String ip = req.getRemoteAddr();//返回发出请求的IP地址
		        int port = req.getLocalPort(); 
		        dataMap.put("Qrcode", "http://"+ip+":"+port+File.separator+"czjw"+File.separator+"static"+File.separator+"file"+File.separator+ids+"aaa.jpg");
		        
		        System.out.println("http://"+ip+":"+port+File.separator+"czjw"+File.separator+"static"+File.separator+"file"+File.separator+ids+".jpg");
		        //生成word文件
				//File outFile = WordUtils.exportMillCertificateWord(dataMap,floderPath+File.separator+s+".doc","pbk.ftl");
				
				//前台显示操作打印
		        Configuration configuration = new Configuration();
		        configuration.setClassForTemplateLoading(WordTest.class, "../ftls"); 
				Template t=configuration.getTemplate("pbk.html");
				PrintWriter out=res.getWriter();
				t.process(dataMap, out);
				  //System.out.println("开始打印");
		          // PrintDemo.printDoc(outFile);
		}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//return renderSuccess("生成word失败");
				}catch (Exception e) {
					//return renderSuccess("操作失败");
				}
				
		 // return renderSuccess("开始打印");
		      
		}
		 
		
		
		
	
	
	
	@RequestMapping("/tj")
	public  void print_tj(String start,String end,String js,HttpServletRequest req,HttpServletResponse res){
		try {
	     //分组结果
			
			
	     Map<String,Object> dataMap=new HashMap<String,Object>();	
	     if(("0").equals(js)){
	    	 js="months";
	    	 dataMap.put("js", "日期");  
	    	 
	     }
	     if(("1").equals(js)){
	    	 js="xsState";
	    	 dataMap.put("js", "线索状态"); 
	    	 
	     }
	     if(("5").equals(js)){
	    	 js="beReflectRank";
	    	 dataMap.put("js", "被反应人职级"); 
	     }
	     
		 if(("2").equals(js)){
				js="beReflectName";	
				dataMap.put("js", "被反映人姓名");
		 }
			
	     if(("3").equals(js)){
	    	 js="toCompany";
	    	 dataMap.put("js", "线索来源");
	     }
	     
	     if(("4").equals(js)){
				js="czbm";
				dataMap.put("js", "处置部门");
		 }
		 List<Map<String, Object>> groupCase2 = caseClueService.groupCaseByjsAndTime(js,start,end);
		
		    dataMap.put("time", "从:"+start+"至:"+end);
	        dataMap.put("caseList", groupCase2);  
	        String floderPath=req.getSession().getServletContext().getRealPath("file");
	        File floder=new File(floderPath);
	        if(!floder.exists()){
	        	floder.mkdirs();
	        }
	            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
				File outFile = WordUtils.exportMillCertificateWord(dataMap,floderPath+File.separator+"从："+start+"至："+end+".xls","tj.xml");
				FileUtils.downFile(res, outFile.getAbsolutePath(), sdf.format(new Date())+".xls");
				  // System.out.println("开始打印");
		           //PrintDemo.printDoc(outFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 //return renderSuccess("打印失败");
			}
		
	        //return renderSuccess("开始打印");
		
	}
	
	
	   @RequestMapping("/outExcel")
       public void  outExcel(String ids,HttpServletRequest req,HttpServletResponse res){
	   String[]idm=ids.split(",");	 
	   List<CaseClue>caseClueList=new ArrayList<CaseClue>();
	   for(String id:idm){
		   CaseClue findCaseClueById = caseClueService.findCaseClueById(Long.valueOf(id));
		   caseClueList.add(findCaseClueById);
	   }
		
	   Map<String,Object>dataMap=new HashMap<String,Object>();
	   dataMap.put("caseClueList", caseClueList);
	   String floderPath=req.getSession().getServletContext().getRealPath("outExcel");   
	   File floder=new File(floderPath);
       if(!floder.exists()){
       	floder.mkdirs();
       }
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
       try {
		File exportMillCertificateWord = WordUtils.exportMillCertificateWord(dataMap,floderPath+File.separator+sdf.format(new Date())+".xls","caseOut.xml");
		
		
		
		System.out.println(exportMillCertificateWord.getAbsolutePath());
		//下载
		FileUtils.downFile(res, exportMillCertificateWord.getAbsolutePath(), sdf.format(new Date())+".xls");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(e.getMessage());
		
	}
       
       
       
	   }
	   
	   
	   
	   
	
}
