package util;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

import model.Param;



public class GetData {
	public static String json(String urla) throws Exception{
		//开始请求
		URL url= new URL(urla);
		URLConnection open = url.openConnection();
		InputStream input = open.getInputStream();
		//这里转换为String，带上包名，怕你们引错包
		String result = org.apache.commons.io.IOUtils.toString(input,"utf-8");
		//
		return result;
		
	}
	
	//"\u4e0a\u6d77"
	public static String changeString(String str) {
		
		try {
			String newStr = URLDecoder.decode(str,"UTF-8");
			return newStr;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(json(Param.path3)+"=========================");
			System.out.println(changeString("\u5b9a\u5dde"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
