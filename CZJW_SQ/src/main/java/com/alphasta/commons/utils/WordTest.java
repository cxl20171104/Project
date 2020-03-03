package com.alphasta.commons.utils;
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;
import java.io.OutputStreamWriter;  
import java.io.Writer;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  

import javax.servlet.http.HttpServletRequest;



import Decoder.BASE64Encoder;
  
import freemarker.template.Configuration;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;   
public class WordTest {
	 private static Configuration configuration = null;  
     
	 public static final String FTLS_PATH = "ftls";
	 public static final String File_PATH = "file2";

	  
	    public WordTest(){  
	        configuration = new Configuration();  
	        configuration.setDefaultEncoding("UTF-8");  
	    }  
	      
	    public static void main(String[] args) {  
	    	Map<String,Object> dataMap = new HashMap<String, Object>();
	    	dataMap.put("1", "121312312312312312");
	        String templateName = "probleCluesWord.xml";
	        String id = "123456";
	        String floderPath = "F:/Tomcate/apache-tomcat-7.0.75/webapps/czjw/outExcel";
	        File floder=new File(floderPath);
	        if(!floder.exists()){
		       	floder.mkdirs();
		       }
	    	createWord(dataMap,templateName,id,floderPath);
	   
	    
	    }  
	    
	    public  static void createWord(Map<String,Object> dataMap,String templateName,String id,String floderPath){  
	       
	       
				try {
					
					configuration.setClassForTemplateLoading(WordTest.class, "../ftls"); 
				
	        Template t=null;  
	        
	            t = configuration.getTemplate(templateName); //文件名  
	            File outFile = new File(floderPath+File.separator+id+".doc");  
	            Writer out =  new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));  	       	           	            
	            t.process(dataMap, out);
	            out.close();
	            System.out.println("开始打印。。。");
	            //PrintDemo.printDoc(outFile);
	        } catch (FileNotFoundException e1) {  
	            e1.printStackTrace();  
	        
	        } catch (TemplateException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
				
				
	    }  
	
	    
	    
	    public static String getImageStr(String imgFile) {
			 InputStream in = null;
			 byte[] data = null;
			
				 try {
					in = new FileInputStream(imgFile);
					data = new byte[in.available()];
					 in.read(data);
					 in.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			
			 BASE64Encoder encoder = new BASE64Encoder();
			 return encoder.encode(data);
		 }
}
