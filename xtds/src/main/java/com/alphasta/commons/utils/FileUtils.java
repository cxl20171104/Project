package com.alphasta.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
    public static String pic_ip;
	public static String uploadFile(MultipartFile uploadFile, String path) {
		if (uploadFile != null) {
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdir();
			}
			String filename = uploadFile.getOriginalFilename();
			String filePath = path + filename;
			try {
				InputStream ins = uploadFile.getInputStream();
				File file = new File(filePath);
				OutputStream os = new FileOutputStream(file);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.close();
				ins.close();
				return filePath;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void downFile(HttpServletResponse response, String filePath,
			String fileName) {
		try {
			// 读到流中
			InputStream inStream = new FileInputStream(filePath);// 文件的存放路径
			// 设置输出的格式
			response.reset();
			response.setContentType("bin");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			// 循环取出流中的数据
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取请求文件base路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getBathUrl(HttpServletRequest request) {
		
		String bathurl = "http://"+pic_ip+":88/files/";
		return bathurl;
	}
}
