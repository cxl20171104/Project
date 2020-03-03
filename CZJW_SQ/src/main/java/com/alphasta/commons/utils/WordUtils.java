package com.alphasta.commons.utils;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordUtils {  
    //配置信息,代码本身写的还是很可读的,就不过多注解了  
    private static Configuration configuration = null;  
    //这里注意的是利用WordUtils的类加载器动态获得模板文件的位置  
    private static final String templateFolder = WordUtils.class.getClassLoader().getResource("../../").getPath() + "asserts/templete/";  
    static {  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
      
            //configuration.setDirectoryForTemplateLoading(new File(templateFolder));  
        	configuration.setClassForTemplateLoading(WordTest.class, "../ftls"); 
       
   }  
  
    private WordUtils() {  
        throw new AssertionError();  
    }  
    //生成文件
    public static File exportMillCertificateWord( Map map,String filePath,String templateName) throws IOException {  
        Template freemarkerTemplate = configuration.getTemplate(templateName);  
            // 调用工具类的createDoc方法生成Word文档  
           File outFile=createDoc(map,freemarkerTemplate,filePath);
           
           return outFile;
          
            
  
         
    }  
  
    //生成html
    public static void exportHtml(Map<String,Object> dataMap,PrintWriter out,String templateName){
    	
    	try {
			Template t=configuration.getTemplate(templateName);
			t.process(dataMap, out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			
		}
    	
    }
    
    
    private static File createDoc(Map<?, ?> dataMap, Template template ,String filePath) {  
        String name =  filePath;  
        File f = new File(name);  
        Template t = template;  
        try {  
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");  
            t.process(dataMap, w);  
            w.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw new RuntimeException(ex);  
        }  
        return f;  
    }  
}  