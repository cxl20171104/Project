package com.alphasta.commons.utils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
public class HttpClientUtil {
   public static boolean send(String json,String ip) {
	   HttpClient  httpclient = HttpClients.createDefault();
       //"http://127.0.0.1:8080/wyc-receive/operate/pay"
       HttpPost httppost = new HttpPost("http://"+ip+":8080/CZJW/probleClues/messageIn");
       httppost.addHeader("Content-type", "application/json; charset=utf-8");
       httppost.addHeader("accept","application/json");  
       StringEntity entity = new StringEntity(json, "utf-8");// 解决中文乱码问题
       entity.setContentEncoding("UTF-8");
       entity.setContentType("application/json");
       httppost.setEntity(entity);
//发送请求
       HttpResponse response;
	try {
		response = httpclient.execute(httppost);
		 if (response.getStatusLine().getStatusCode() == 200) {
             System.out.println("发送成功");
       } else {
             String err = response.getStatusLine().getStatusCode() + "";
             System.out.println("发送失败:" + err);
       }
       return true;
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       //请求结果
       return false;
   }
   public static void main(String[] args) {
	   send(null, "192.168.22.199");
}
}
