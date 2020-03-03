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
			//�ϴ��ļ�
			 req.setCharacterEncoding("utf-8");  //���ñ���  
	          
		        //��ô����ļ���Ŀ����  
		        DiskFileItemFactory factory = new DiskFileItemFactory();  
		        //��ȡ�ļ���Ҫ�ϴ�����·��  
		        String path = Param.programPath;  
		          
		        //���û�����������õĻ����ϴ���� �ļ� ��ռ�� �ܶ��ڴ棬  
		        //������ʱ��ŵ� �洢�� , ����洢�ң����Ժ� ���մ洢�ļ� ��Ŀ¼��ͬ  
		        /** 
		         * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ�  
		         * ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem ��ʽ��  
		         * Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ���� 
		         */  
		        factory.setRepository(new File(path));  
		        //���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��  
		        factory.setSizeThreshold(1024*1024) ;  
		          
		        //��ˮƽ��API�ļ��ϴ�����  
		        ServletFileUpload upload = new ServletFileUpload(factory);  
		          
		          
		        try {  
		            //�����ϴ�����ļ�  
		            List<FileItem> list = (List<FileItem>)upload.parseRequest(req);  
		              
		            for(FileItem item : list)  
		            {  
		                //��ȡ������������  
		                String name = item.getFieldName();  
		                  
		                //�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ  
		                if(item.isFormField())  
		                {                     
		                    //��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�  
		                    String value = item.getString() ;  
		                      
		                    req.setAttribute(name, value);  
		                }  
		                //�Դ���ķ� �򵥵��ַ������д��� ������˵�����Ƶ� ͼƬ����Ӱ��Щ  
		                else  
		                {  
		                    /** 
		                     * ������������Ҫ��ȡ �ϴ��ļ������� 
		                     */  
		                    //��ȡ·����  
		                    String value = item.getName() ;  
		                    //���������һ����б��  
		                    int start = value.lastIndexOf("\\");  
		                    //��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�  
		                    String filename = value.substring(start+1);  
		                      
		                    req.setAttribute(name, filename);  
		                      
		                    //����д��������  
		                    //���׳����쳣 ��exception ��׽  
		                      
		                    //item.write( new File(path,filename) );//�������ṩ��  
		                      
		                    //�ֶ�д��  
		                    OutputStream out = new FileOutputStream(new File(path,filename));  
		                      
		                    InputStream in = item.getInputStream() ;  
		                      
		                    int length = 0 ;  
		                    byte [] buf = new byte[1024] ;  
		                      
		                    System.out.println("��ȡ�ϴ��ļ����ܹ���������"+item.getSize());  
		  
		                    // in.read(buf) ÿ�ζ��������ݴ����   buf ������  
		                    while( (length = in.read(buf) ) != -1)  
		                    {  
		                        //��   buf ������ ȡ������ д�� ���������������  
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
				System.out.println("���"+order);
				String filePath = req.getParameter("filePath");
				String tempPath = req.getParameter("tempPath");
				String programName = req.getParameter("programName");
				String startPath = req.getParameter("startPath");
				String endPath = req.getParameter("endPath");
		    	System.out.println(order);
		    	if("0".equals(order)) {
		    		System.out.println("�رշ���");
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
                	System.out.println("ɾ����Ŀ");
                	if(new File(filePath).exists()){
                		FielUtil.delete(new File(filePath));
                	}
                	req.getRequestDispatcher("delete.jsp").forward(req, resp);  
		    	}
                if("2".equals(order)) {
                	System.out.println("��������");
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
                	System.out.println("������Ŀ");
                	File file=new File(programPath);
                	File[] listFiles = file.listFiles();
                	if(listFiles!=null&&listFiles.length>0){
                		for(File f:listFiles){
                    		System.out.println(f.getName());
                    		if(f.getName().equals(programName)&&f.isDirectory()){
                    			//part1:����ʱ�ļ����´���һ���ļ���
                    			File dir=new File(tempPath+File.separator+programName);
                    			if(!dir.exists()){
                    				dir.mkdirs();
                    			}
                    			//part:2��ѯ��Ŀ�µ������ļ�
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
