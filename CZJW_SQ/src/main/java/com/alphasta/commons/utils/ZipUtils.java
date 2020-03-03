package com.alphasta.commons.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
public class ZipUtils {
	
	/**
	 * 
	 * @param inFile    需要压缩的文件所在的文件夹   D:\\cxl
	 * @param destFile  输出文件 D:\\cxl2\\cxl.zip
	 * @throws IOException
	 */
    public  void doCompress(File inFile, File destFile) throws IOException{
    	//生成压缩包
    	ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFile));
    	//添加需要压缩文件
    	File[] file=inFile.listFiles();
    	 FileInputStream fis =null;
    	for(File f:file){
    		if( f.exists() ){
                byte[] buffer = new byte[1024];
                fis = new FileInputStream(f);
                out.putNextEntry(new ZipEntry(f.getName()));
                int len = 0 ;
                // 读取文件的内容,打包到zip文件    
                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                
            }
    	};
        out.flush();
        out.closeEntry();
        out.close();
        fis.close();
    }
    /**
     * 
     * @param zipPath 压缩包路径
     * @param newZipPath 压缩的文件存放路径
     * @throws FileNotFoundException
     */
    public void toCompress(String zipPath,String newZipPath) throws FileNotFoundException{
	    	ZipInputStream Zin=new ZipInputStream(new FileInputStream(  zipPath));//输入源zip路径  
	        BufferedInputStream Bin=new BufferedInputStream(Zin);  
	        String Parent=newZipPath; //输出路径（文件夹目录）  
	        File Fout=null;  
	        ZipEntry entry;  
	        try {  
	            while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
	                Fout=new File(Parent,entry.getName());  
	                if(!Fout.exists()){  
	                    (new File(Fout.getParent())).mkdirs();  
	                }  
	                FileOutputStream out=new FileOutputStream(Fout);  
	                BufferedOutputStream Bout=new BufferedOutputStream(out);  
	                int b;  
	                while((b=Bin.read())!=-1){  
	                    Bout.write(b);  
	                }  
	                Bout.close();  
	                out.close();  
	                System.out.println(Fout+"解压成功");      
	            }  
	            Bin.close();  
	            Zin.close();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
}
    
    //直接读取压缩包内容
    public  void readZipContext(String zipPath) throws IOException{
        ZipFile zf=new ZipFile(zipPath);

        InputStream in=new BufferedInputStream(new FileInputStream(zipPath));
        System.out.println(zipPath);
        ZipInputStream zin=new ZipInputStream(in);
        System.out.println("ZIN:"+zin);
        //ZipEntry 类用于表示 ZIP 文件条目。
        ZipEntry ze;
        while((ze=zin.getNextEntry())!=null){
            if(ze.isDirectory()){
                //为空的文件夹什么都不做
            }else{
                System.out.println("file:"+ze.getName()+"\nsize:"+ze.getSize()+"bytes");
                if(ze.getSize()>0){
                    BufferedReader reader;
                    try {
                        reader = new BufferedReader(new InputStreamReader(zf.getInputStream(ze), "utf-8"));
                        String line=null;
                        while((line=reader.readLine())!=null){
                            System.out.println(line);
                        }
                        reader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }
    }   
    
    public static void main(String[] args) throws IOException {
    	
    	//String name = readfile("F:\\Tomcate\\apache-tomcat-7.0.752\\webapps\\czjw\\JsonData3\\jyFile");
    	//System.out.println(name);
    }
    /**
     * 读取文件内容
     * @param filepath 文件路径
     * @return 返回文件路径
     * @throws FileNotFoundException
     * @throws IOException
     */
    public   String  readfile(String filepath) throws FileNotFoundException, IOException {
    	String name = "";
        File file = new File(filepath);
		if (!file.isDirectory()) {
		        System.out.println("文件");
		        System.out.println("path=" + file.getPath());
		        System.out.println("absolutepath=" + file.getAbsolutePath());
		        System.out.println("name=" + file.getName());

		} else if (file.isDirectory()) {
		        System.out.println("文件夹");
		        String[] filelist = file.list();
		        for (int i = 0; i < filelist.length; i++) {
		                File readfile = new File(filepath + "\\" + filelist[i]);
		                if (!readfile.isDirectory()) {
		                       
		                        name +=readfile.getAbsolutePath()+",";
		                } else if (readfile.isDirectory()) {
		                        return "";
		                }
		        }

		}
        return name;
}
    
}

   
