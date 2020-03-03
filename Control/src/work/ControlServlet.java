package work;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.BatRun;
import util.FielUtil;
import model.Param;

public class ControlServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer requestURL = req.getRequestURL();
		System.out.println(requestURL.toString());
		if(requestURL.toString().endsWith("page")) {
			resp.setContentType("text/html; charset=gb2312"); 
			resp.sendRedirect("views/page.jsp"); 
		}
		
		if(requestURL.toString().endsWith("upload")) {
			//上传文件
			 req.setCharacterEncoding("utf-8");  //设置编码  
	          
		        //获得磁盘文件条目工厂  
		        DiskFileItemFactory factory = new DiskFileItemFactory();  
		        //获取文件需要上传到的路径  
		        String path = Param.programPath;  
		          
		        //如果没以下两行设置的话，上传大的 文件 会占用 很多内存，  
		        //设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同  
		        /** 
		         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，  
		         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的  
		         * 然后再将其真正写到 对应目录的硬盘上 
		         */  
		        factory.setRepository(new File(path));  
		        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室  
		        factory.setSizeThreshold(1024*1024) ;  
		          
		        //高水平的API文件上传处理  
		        ServletFileUpload upload = new ServletFileUpload(factory);  
		          
		          
		        try {  
		            //可以上传多个文件  
		            List<FileItem> list = (List<FileItem>)upload.parseRequest(req);  
		              
		            for(FileItem item : list)  
		            {  
		                //获取表单的属性名字  
		                String name = item.getFieldName();  
		                  
		                //如果获取的 表单信息是普通的 文本 信息  
		                if(item.isFormField())  
		                {                     
		                    //获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的  
		                    String value = item.getString() ;  
		                      
		                    req.setAttribute(name, value);  
		                }  
		                //对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些  
		                else  
		                {  
		                    /** 
		                     * 以下三步，主要获取 上传文件的名字 
		                     */  
		                    //获取路径名  
		                    String value = item.getName() ;  
		                    //索引到最后一个反斜杠  
		                    int start = value.lastIndexOf("\\");  
		                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠，  
		                    String filename = value.substring(start+1);  
		                      
		                    req.setAttribute(name, filename);  
		                      
		                    //真正写到磁盘上  
		                    //它抛出的异常 用exception 捕捉  
		                      
		                    //item.write( new File(path,filename) );//第三方提供的  
		                      
		                    //手动写的  
		                    OutputStream out = new FileOutputStream(new File(path,filename));  
		                      
		                    InputStream in = item.getInputStream() ;  
		                      
		                    int length = 0 ;  
		                    byte [] buf = new byte[1024] ;  
		                      
		                    System.out.println("获取上传文件的总共的容量："+item.getSize());  
		  
		                    // in.read(buf) 每次读到的数据存放在   buf 数组中  
		                    while( (length = in.read(buf) ) != -1)  
		                    {  
		                        //在   buf 数组中 取出数据 写到 （输出流）磁盘上  
		                        out.write(buf, 0, length);  
		                          
		                    }  
		                      
		                    in.close();  
		                    out.close();  
		                }  
		            }  
		              
		              
		              
		        } catch (FileUploadException e) {  
		            // TODO Auto-generated catch block  
		            e.printStackTrace();  
		        }  
		        catch (Exception e) {  
		            // TODO Auto-generated catch block  
		              
		            //e.printStackTrace();  
		        }  
		          
		          
		        req.getRequestDispatcher("success.jsp").forward(req, resp);  
		          
		  
		    }
		
		    if(requestURL.toString().endsWith("order")) {
		    	String programPath = req.getParameter("programPath");
				Param.programPath=programPath;
				System.out.println(programPath);
				String order = req.getParameter("order");
				System.out.println("命令："+order);
				String filePath = req.getParameter("filePath");
				String tempPath = req.getParameter("tempPath");
				String programName = req.getParameter("programName");
				String startPath = req.getParameter("startPath");
				String endPath = req.getParameter("endPath");
		    	System.out.println(order);
		    	if("0".equals(order)) {
		    		System.out.println("关闭服务");
		    		String realPath = req.getServletContext().getRealPath("bat");
		    		String path=realPath+File.separator+"close_tomcat.bat";
                	boolean runbat = BatRun.runbat(path);
                	if(runbat){
                		req.getRequestDispatcher("shutdown.jsp").forward(req, resp);  
                	}else{
                		req.getRequestDispatcher("error.jsp").forward(req, resp);
                	}
                	
		    	}
                if("1".equals(order)) {
                	System.out.println("删除项目");
                	if(new File(filePath).exists()){
                		FielUtil.delete(new File(filePath));
                	}
                	req.getRequestDispatcher("delete.jsp").forward(req, resp);  
		    	}
                if("2".equals(order)) {
                	System.out.println("启动服务");
                	String realPath = req.getServletContext().getRealPath("bat");
                	System.out.println(realPath);
                	String path=realPath+File.separator+"open_tomcat.bat";
                	boolean runbat = BatRun.runbat(path);
                	if(runbat){
                		req.getRequestDispatcher("startup.jsp").forward(req, resp);  
                	}else{
                		req.getRequestDispatcher("error.jsp").forward(req, resp);
                	}
                	
		    	}
                
                if("3".equals(order)){
                	System.out.println("备份项目");
                	File file=new File(programPath);
                	File[] listFiles = file.listFiles();
                	if(listFiles!=null&&listFiles.length>0){
                		for(File f:listFiles){
                    		System.out.println(f.getName());
                    		if(f.getName().equals(programName)&&f.isDirectory()){
                    			//part1:在临时文件夹下创建一个文件夹
                    			File dir=new File(tempPath+File.separator+programName);
                    			if(!dir.exists()){
                    				dir.mkdirs();
                    			}
                    			//part:2查询项目下的所有文件
                    			FielUtil.copyFolder(filePath, tempPath+File.separator+programName);
                    		}
                    	}
                		req.getRequestDispatcher("ok.jsp").forward(req, resp);  
                	}else{
                		req.getRequestDispatcher("nofile.jsp").forward(req, resp);  
                	}
                	
                }
                
                if("4".equals(order)){
                	try{
                		FielUtil.copyFolder(startPath, endPath);
                	}catch(Exception ex){
                		req.getRequestDispatcher("error.jsp").forward(req, resp);
                	}
                	req.getRequestDispatcher("ok2.jsp").forward(req, resp);
                	
                }
		    }
		}
		
		
}
