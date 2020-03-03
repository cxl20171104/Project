package com.alphasta.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Data;
/**
 * 压缩多个文件的工具类
 * @author chenxiaoliang
 *
 */
public class ToZipUtils {
    public static  String zipTheFolder(List<String> filePaths,String outPath,String fileName){
    	File used=new File(outPath);
    	if(used!=null&&used.exists()){
    		File[]files=used.listFiles();
    		if(files!=null&&files.length>0){
    			for(File f:files){
        			if(f!=null){
        				f.delete();
        			}
        		}
    		}
    		
    	};
    	String fileIntoFolder = fileIntoFolder(filePaths, outPath);
    	File srcFile=new File(fileIntoFolder);
    	File theStore=new File(outPath+"2");
    	if(!theStore.exists()){
    		theStore.mkdirs();
    	}
    	File destFile=new File(outPath+"2"+File.separator+fileName+".zip");
    	try {
    		ZipUtils z = new ZipUtils();
			z.doCompress(srcFile, destFile);
			return outPath+"2"+File.separator+fileName+".zip";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
    	
    }
    
    
    
    
    
    //将指定文件放到一个文件夹中
    
    public static String  fileIntoFolder(List<String> filePaths,String outPath){
    	File out=new File(outPath);
    	if(!out.exists()){
    		out.mkdirs();
    	}
    	for(String s:filePaths){
    		String newPath=out+File.separator+new File(s).getName();
    		copyFile(s, newPath);
	
    	}
    	
    	return outPath;
    	
    }
    
    
    
    /** 
     * 复制单个文件 
     * @param oldPath String 原文件路径 如：c:/fqf.txt 
     * @param newPath String 复制后路径 如：f:/fqf.txt 
     * @return boolean 
     */ 
   public static void copyFile(String oldPath, String newPath) { 
	   //--------------------------------------------------------//
       try { 
    	   
           int bytesum = 0; 
           int byteread = 0; 
           File oldfile = new File(oldPath); 
           //-------------------------------//
           if (oldfile.exists()) { //文件存在时 
        	   //-----------输入流-----------//
               InputStream inStream = new FileInputStream(oldPath); //读入原文件 
               //---------------输出流----------------------------//
               FileOutputStream fs = new FileOutputStream(newPath); 
               byte[] buffer = new byte[1444];
               //----------------------------//
               int length; 
               while ( (byteread = inStream.read(buffer)) != -1) { 
                   bytesum += byteread; //字节数 文件大小 
                   
                   fs.write(buffer, 0, byteread); 
               } 
               inStream.close(); 
               fs.close();
               
           } 
       } 
       catch (Exception e) { 
           System.out.println("复制单个文件操作出错"); 
           e.printStackTrace(); 

       } 

   } 
}

