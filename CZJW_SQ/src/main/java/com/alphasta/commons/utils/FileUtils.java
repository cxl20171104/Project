package com.alphasta.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.alphasta.model.Param;

public class FileUtils {

	public static String uploadFile(MultipartFile uploadFile,String path) {
		if (uploadFile != null) {
			File dir = new File(path);
			if(!dir.exists()){
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

	public static boolean downFile(HttpServletResponse response, String filePath, String fileName) {
		try {
			// 读到流中
			InputStream inStream = new FileInputStream(filePath);// 文件的存放路径
			// 设置输出的格式
			response.reset();
			response.setContentType("bin");
			//response.setContentType("text/javascript; charset=utf-8");
			fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			// 循环取出流中的数据
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	/**
	 * @author guoxk
	 *
	 * 方法描述：根据文件路径获取文件头信息
	 * @param filePath 文件路径
	 * @return 文件头信息
	 */
	public static String getFileHeader(InputStream is) {
		//FileInputStream is = null;
		String value = null;
		try {
			//is = new FileInputStream(filePath);

			byte[] b = new byte[4];
			/*
			 * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
			 * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
			 * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
			 */
			is.read(b, 0, b.length);
			value = bytesToHexString(b);
		} catch (Exception e) {
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return value;
	}

	/**
	 * @author guoxk
	 *
	 * 方法描述：将要读取文件头信息的文件的byte数组转换成string类型表示
	 * @param src 要读取文件头信息的文件的byte数组
	 * @return   文件头信息
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
		//		System.out.println(builder.toString());
		return builder.toString();
	}

	/**
	 * @param sourcePath 删除文件或是文件夹
	 * <br/>例如：
	 *    <br/>1、删除文件<br/>
	 *            deleteFile("C:"+File.separatorChar+"user.txt")
	 *    <br/>2、删除文件夹<br/>
	 *            deleteFile("C:"+File.separatorChar+"user")
	 * **/
	public static void deleteFile(File file){
		if(file.exists()){ 
			if(file.isFile()){
				file.delete(); 
			}else if(file.isDirectory()){ 
				File files[] = file.listFiles(); 
				for(int i=0;i<files.length;i++){ 
					deleteFile(files[i]);
				} 
			} 
			file.delete();
		}	
	}
	
	
	
	public static void main(String[] args) {
	}
}