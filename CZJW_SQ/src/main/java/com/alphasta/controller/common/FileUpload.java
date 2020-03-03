package com.alphasta.controller.common;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.GetIdUtil;

@Controller
@RequestMapping("/com")
public class FileUpload {
	/**
	 * 上传一个文件
	 * @param file
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object upload(
			@RequestParam(value = "upload", required = false) MultipartFile file,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) {
		Result result = new Result();
		try {
			String path = request.getServletContext().getRealPath("files")+"\\";
			String fileName = file.getOriginalFilename();
			String type = fileName.substring(fileName.lastIndexOf("."));

			// String fileName = new Date().getTime()+".jpg";
			String filePath=new Date().getTime() + type;
			String bathPath=path+"upload/";
			File targetFile = new File(bathPath, filePath);
			
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}            
			file.transferTo(targetFile);
			/*if(targetFile.length()>2048000){  //大于2兆压缩
				ImgCompress subimg=new ImgCompress(bathPath+filePath);
				subimg.resizeByHeight(100);
				result.setObj(targetFile.getAbsolutePath().replace(".", "_sub.").replace(path, "").replace("\\","/"));
			}else{*/
			result.setObj(targetFile.getAbsolutePath().replace(path, "").replace("\\","/"));
			//}		
			result.setSuccess(true);
			result.setMsg(String.valueOf(file.getSize()));

		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);

		}
	  
        return result;
	}
	
	/**
	 * 上传多个附件
	 * @param response
	 * @param request
	 * @return   //返回文件的绝对路径  包括 服务器IP 及端口号
	 * @throws FileUploadException
	 * @throws IOException
	 */
	@RequestMapping("/uploadmany")
	@ResponseBody
	public Result uploadimage(HttpServletResponse response, HttpServletRequest request) throws FileUploadException, IOException{
	        //上传的文件夹
	        String path = request.getServletContext().getRealPath("files")+"/upload";
	        Result result = new Result();
	        String urls="";
        if(request.getHeader("content-type")!=null&&"application/x-www-form-urlencoded".equals(request.getHeader("content-type"))){ 
            return null;//不支持断点续传，直接返回null即可 
        } 
      //将请求转换成 
        MultipartHttpServletRequest mRequest=(MultipartHttpServletRequest)request; 
        Enumeration<String> ps = mRequest.getParameterNames(); 
        while(ps.hasMoreElements()){ 
            String hname = ps.nextElement(); 
        } 
        Iterator<String> fns=mRequest.getFileNames();//获取上传的文件列表 
        
        
        System.out.println(fns);
        while(fns.hasNext()){ 
            String s =fns.next(); 
            MultipartFile mFile = mRequest.getFile(s);   
            if(mFile.isEmpty()){ 
            	result.setSuccess(false);
            	 return result;
            }else{ 
                File dir = new File(path); 
                if(!dir.exists()){ 
                    dir.mkdirs(); 
                } 
                String originFileName=mFile.getOriginalFilename(); 
                String suffix=originFileName.split("\\.")[originFileName.split("\\.").length-1]; 
                Long TimeName=new Date().getTime(); 
                File file =  new File(path,TimeName+"."+suffix); 
                try { 
                    FileUtils.copyInputStreamToFile(mFile.getInputStream(),file);//存储文件 
                    //String path2 =  "http://"+InetAddress.getLocalHost().getHostAddress()+":8083/";
                    urls=urls+ "upload/"+TimeName+"."+suffix+",";
                } catch (IOException e) { 
                    e.printStackTrace(); 
                    result.setSuccess(false);
                    return result;
                }   
            } 
        } 
       //返回结果 
        result.setSuccess(true);
        result.setObj(urls.substring(0, urls.lastIndexOf(",")));
        return result; 
	         
	}   
	public static void main(String[] args) {
		 try {
			String path3 =  "http://"+InetAddress.getLocalHost().getHostAddress()+":8083";
			System.out.println(path3);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
