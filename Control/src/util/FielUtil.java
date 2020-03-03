package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class FielUtil {
    /**
     * 将给定的File表示的文件或目录删除
     * @param file
     */
    public static void delete(File file){
        if(file.isDirectory()){
            //将所有的子项删除
            File[] subs = file.listFiles();
            for(File sub : subs){
                //递归调用
                delete(sub);
            }
                        
        }
        file.delete();
        
    }
    
    
    
    /*
      * 通过递归得到某一路径下所有的目录及其文件
      */
    	public static void getFiles(String filePath,List<File> filelist){
    	   File root = new File(filePath);
    	   File[] files = root.listFiles();
    	   for(File file:files){
    		   if(file.isDirectory()){
    			   getFiles(file.getAbsolutePath(),filelist);
    			   System.out.println("显示1"+filePath+"下所有子目录及其文件"+file.getAbsolutePath());
    		   }else{
    			   
    			   filelist.add(file);
    			   System.out.println("显示2"+filePath+"下所有子目录"+file.getAbsolutePath());
    		   }
    	   }
    	
    	 }
    	  /** 
	     * 复制整个文件夹内容 
	     * @param oldPath String 原文件路径 如：c:/fqf 
	     * @param newPath String 复制后路径 如：f:/fqf/ff 
	     * @return boolean 
	     */ 
	   public static void copyFolder(String oldPath, String newPath) { 

	       try { 
	           (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹 
	           File a=new File(oldPath); 
	           String[] file=a.list(); 
	           File temp=null; 
	           for (int i = 0; i < file.length; i++) { 
	               if(oldPath.endsWith(File.separator)){ 
	                   temp=new File(oldPath+file[i]); 
	               } 
	               else{ 
	                   temp=new File(oldPath+File.separator+file[i]); 
	               } 

	               if(temp.isFile()){ 
	                   FileInputStream input = new FileInputStream(temp); 
	                   FileOutputStream output = new FileOutputStream(newPath + "/" + 
	                           (temp.getName()).toString()); 
	                   byte[] b = new byte[1024 * 5]; 
	                   int len; 
	                   while ( (len = input.read(b)) != -1) { 
	                       output.write(b, 0, len); 
	                   } 
	                   output.flush(); 
	                   output.close(); 
	                   input.close(); 
	               } 
	               if(temp.isDirectory()){//如果是子文件夹 
	                   copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]); 
	               } 
	           } 
	       } 
	       catch (Exception e) { 
	           System.out.println("复制整个文件夹内容操作出错"); 
	           e.printStackTrace(); 

	       } 

	   }
    	public static void main(String[] args) {
    		List<File>filelist=new ArrayList<File>();
    		getFiles("D://开发文档", filelist);
    		System.out.println(filelist);
		}
}


