package com.alphasta.controller.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.app.AppController;
import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.ImgCompress;
import com.alphasta.model.User;

/**
 * 共用的接口
 * 
 * @author 杨亚辉
 * 
 */
@Controller
@RequestMapping("/com")
public class CommonController extends BaseController {
	//上传图片使用
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object upload(
			@RequestParam(value = "upload", required = false) MultipartFile file,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) {
		Result result = new Result();
		try {
			String path = request.getServletContext().getRealPath("files")
					+ "\\";
			String fileName = file.getOriginalFilename();
			String type = fileName.substring(fileName.lastIndexOf("."));

			// String fileName = new Date().getTime()+".jpg";
			String filePath = GetIdUtil.getId() + type;
			String bathPath = path + "upload/";
			File targetFile = new File(bathPath, filePath);

			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			if (targetFile.length() > 2048000) { // 大于2兆压缩
				ImgCompress subimg = new ImgCompress(bathPath + filePath);
				subimg.resizeByHeight(100);
				result.setObj(targetFile.getAbsolutePath()
						.replace(".", "_sub.").replace(path, "")
						.replace("\\", "/"));
			} else {
				result.setObj(targetFile.getAbsolutePath().replace(path, "")
						.replace("\\", "/"));
			}
			result.setSuccess(true);
			result.setMsg(String.valueOf(file.getSize()));

		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);

		}

		return result;
	}
	//大文件上传使用
	@RequestMapping(value = "/uploadLarge")
	@ResponseBody
	public Object uploadLarge(
			@RequestParam(value = "upload", required = false) MultipartFile file,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) {
		Result result = new Result();
		try {
			String path = request.getServletContext().getRealPath("files")
					+ "\\";
			String fileName = file.getOriginalFilename();
			String type = fileName.substring(fileName.lastIndexOf("."));

			// String fileName = new Date().getTime()+".jpg";
			String filePath = GetIdUtil.getId() + type;
			String bathPath = path + "upload/";
			File targetFile = new File(bathPath, filePath);

			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			result.setObj(targetFile.getAbsolutePath().replace(path, "").replace("\\", "/"));
			result.setSuccess(true);
			result.setMsg(String.valueOf(file.getSize()));
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);

		}

		return result;
	}
	
	/**
	 * 更新app版本
	 * 
	 * @param file
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/uploadApp")
	@ResponseBody
	public Object uploadNewApp(
			@RequestParam(value = "upload", required = false) MultipartFile file,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) {
		Result result = new Result();
		try {
			String path = request.getServletContext().getRealPath("files")
					+ "\\";
			String fileName = file.getOriginalFilename();
			String type = fileName.substring(fileName.lastIndexOf("."));

			// String fileName = new Date().getTime()+".jpg";
			File targetFile = new File(path + "upload/", GetIdUtil.getId()
					+ type);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			result.setObj(targetFile.getAbsolutePath().replace(path, "")
					.replace("\\", "/"));
			result.setSuccess(true);
			result.setMsg(String.valueOf(file.getSize()));

		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);

		}

		return result;
	}

	@RequestMapping("/error.json")
	@ResponseBody
	public Object error() {
		return -1;
	}

	/**
	 * 文件上传手机端
	 * 
	 * @param response
	 * @param request
	 * @return //返回文件的绝对路径 包括 服务器IP 及端口号
	 * @throws FileUploadException
	 * @throws IOException
	 */
	@RequestMapping("/load.json")
	@ResponseBody
	@AppLog(remark = "图片上传", openType = "0")
	public Map<String, Object> uploadimage(HttpServletResponse response,
			HttpServletRequest request) throws FileUploadException, IOException {
		// String temppath =
		// request.getServletContext().getRealPath("/static/temp");
		// 上传的文件夹
		String path = request.getServletContext().getRealPath("files")+ "/app";
		Map<String, Object> map = new HashMap<String, Object>();
		if (request.getHeader("content-type") != null
				&& "application/x-www-form-urlencoded".equals(request
						.getHeader("content-type"))) {
			return null;// 不支持断点续传，直接返回null即可
		}
		// 将请求转换成
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Enumeration<String> ps = mRequest.getParameterNames();
		while (ps.hasMoreElements()) {
			String hname = ps.nextElement();
		}
		Iterator<String> fns = mRequest.getFileNames();// 获取上传的文件列表
		while (fns.hasNext()) {
			String s = fns.next();
			MultipartFile mFile = mRequest.getFile(s);
			if (mFile.isEmpty()) {
				map.put("error", "EventAction.picture.failed");
			} else {
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				String originFileName = mFile.getOriginalFilename();
				String suffix = originFileName.split("\\.")[originFileName
						.split("\\.").length - 1];
				String base64Name = GetIdUtil.getId();
				File file = new File(path, base64Name + "." + suffix);
				try {
					FileUtils.copyInputStreamToFile(mFile.getInputStream(),
							file);// 存储文件
					map.put("pic", "app/" + base64Name + "."
							+ suffix);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 返回结果
		return map;

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
	public Result uploadimages(HttpServletResponse response, HttpServletRequest request) throws FileUploadException, IOException{
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

}
