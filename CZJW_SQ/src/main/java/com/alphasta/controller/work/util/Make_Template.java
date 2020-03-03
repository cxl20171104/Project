package com.alphasta.controller.work.util;

import javax.servlet.ServletContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
@Controller
@RequestMapping("/util")
public class Make_Template extends BaseController{
	@RequestMapping("/make_template")
	public String make_template(){
		return "/util/make_template";
	}
	@RequestMapping("/setDataState")
	@ResponseBody
	public Result setDataState() {
		//使用httpClient发送http请求
		String doGet = doGet("http://localhost:8080/st/views/order?");
		String[] msgs=doGet.split("\n");
		StringBuffer sub=new StringBuffer();
		for(String msg:msgs) {
			System.out.println(msg);
			//1
			if(msg.indexOf("CangXian")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("5,");
			}
			//2
			if(msg.indexOf("XinHuaQu")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("16,");
			}
			//3
			if(msg.indexOf("HeJian")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("3,");
			}
			//4
			if(msg.indexOf("WuQiao")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("14,");
			}
			//5
			if(msg.indexOf("HuangHuaGang")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("17,");
			}
			//6
			if(msg.indexOf("NanPi")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("12,");
			}
			//7
			if(msg.indexOf("RenQiu")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("1,");
			}
			//8
			if(msg.indexOf("HaiXing")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("10,");
			}
			//9
            if(msg.indexOf("YanShan")>=0&&msg.indexOf("ZC")>=0) {
            	sub.append("9,");
			}
            //10
			if(msg.indexOf("SuNing")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("8,");
			}
			
			if(msg.indexOf("XianXian")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("7,");
			}
			//11
			if(msg.indexOf("GaoXinQu")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("19,");
			}
			//12
			if(msg.indexOf("KaiFaQu")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("18,");
			}
			//13
			if(msg.indexOf("YunHeQu")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("15,");
			}
			//14
			if(msg.indexOf("HuangHua")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("2,");
			}
			//15
			if(msg.indexOf("DongGuang")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("13,");
			}
			//16
			if(msg.indexOf("BoTou")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("4,");
			}
			//17
			if(msg.indexOf("QingXian")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("6,");
			}
			//18
			if(msg.indexOf("MengCun")>=0&&msg.indexOf("ZC")>=0) {
				sub.append("11,");
			}
			
			
		}
		
		ServletContext ctx = request.getServletContext();
		ctx.setAttribute("dataSourceControl", ","+sub.toString());
		Result result=new Result();
		result.setSuccess(true);
		result.setMsg("数据连接更新完成");
		return result;
	}
	
	
	public static String doGet(String url) {
		HttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			httpGet = new HttpGet(url);
			// 设置参数
			HttpResponse response = httpClient.execute(httpGet);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
