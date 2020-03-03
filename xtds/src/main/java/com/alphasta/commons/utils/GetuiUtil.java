package com.alphasta.commons.utils;

import com.alibaba.fastjson.JSONObject;
import com.alphasta.model.MsgPut;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetuiUtil {

	// 定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
	private static String appId = "52KClEgsia8rF5A25ViKW6";
	private static String appKey = "RrGlQYTONO6rGnct8FEcW1";
	private static String masterSecret = "qRh3Z3qlq36vqUWvwPH9V3";
	private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
	/**
	 * AppID： 
52KClEgsia8rF5A25ViKW6
AppSecret： 
sgV0ASPOuF6vgDhqjG5vg5
AppKey： 
RrGlQYTONO6rGnct8FEcW1
MasterSecret： 
qRh3Z3qlq36vqUWvwPH9V3重置
	 */

	/**
	 * 全体发送
	 * 
	 * @param msgPut
	 * @throws IOException
	 */
	public static void putMsgToAll(MsgPut msgPut) throws IOException {

		IGtPush push = new IGtPush(url, appKey, masterSecret);

		// 定义"通知模板"，并设置标题、内容、链接
		NotificationTemplate template = notificationTemplateDemo(msgPut);

		List<String> appIds = new ArrayList<String>();
		appIds.add(appId);

		// 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
		AppMessage message = new AppMessage();
		message.setData(template);
		message.setAppIdList(appIds);
		message.setOffline(true);
		message.setOfflineExpireTime(1000 * 600);

		IPushResult ret = push.pushMessageToApp(message);
		System.out.println(ret.getResponse().toString());
	}

	/**
	 * 单独发送
	 * 
	 * @param msgPut
	 * @throws Exception
	 */
	public static void putMsgToSingle(MsgPut msgPut) throws Exception {
		IGtPush push = new IGtPush(url, appKey, masterSecret);
		// LinkTemplate template = linkTemplateDemo(msgPut);
		NotificationTemplate template = notificationTemplateDemo(msgPut);
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 3600 * 1000);
		message.setData(template);
		// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		message.setPushNetWorkType(0);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(msgPut.getCid());
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			e.printStackTrace();
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
		} else {
			System.out.println("服务器响应异常");
		}
	}

	/**
	 * 发送群消息
	 * 
	 * @param msgput
	 * @param cids
	 * @throws Exception
	 */
	public static void putMsgToLlist(MsgPut msgput, List<String> cids)
			throws Exception {
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin_pushList_needDetails", "true");
		// 配置返回每个别名及其对应cid的用户状态，可选
		// System.setProperty("gexin_pushList_needAliasDetails", "true");
		IGtPush push = new IGtPush(url, appKey, masterSecret);
		// 通知透传模板
		// LinkTemplate template = linkTemplateDemo( msgput);
		NotificationTemplate template = notificationTemplateDemo(msgput);
		ListMessage message = new ListMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 配置推送目标
		List targets = new ArrayList();
		for (String cid : cids) {
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(cid);
			targets.add(target);

		}
		// taskId用于在推送时去查找对应的message
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		System.out.println(ret.getResponse().toString());
	}

	/**
	 * 消息网页版
	 * 
	 * @param msgPut
	 * @return
	 */
	public static LinkTemplate linkTemplateDemo(MsgPut msgPut) {
		LinkTemplate template = new LinkTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);
		// 设置通知栏标题与内容
		template.setTitle(msgPut.getTitle());
		String text = msgPut.getContent();
		text = text.length() > 12 ? text.substring(0, 12) : text;
		template.setText(text);
		// 配置通知栏图标
		// template.setLogo("icon.png");
		// 配置通知栏网络图标，填写图标URL地址
		template.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		template.setIsRing(true);
		template.setIsVibrate(true);
		template.setIsClearable(true);
		// 设置打开的网址地址
		template.setUrl("http://www.baidu.com");
		return template;
	}

	/**
	 * 应用版
	 * 
	 * @param msgPut
	 * @return
	 */
	public static NotificationTemplate notificationTemplateDemo(MsgPut msgPut) {
		NotificationTemplate template = new NotificationTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);
		Style0 style = new Style0();
		// 设置通知栏标题与内容
		style.setTitle(msgPut.getTitle());
		String content = msgPut.getContent();
		style.setText(content);
		// 配置通知栏图标
		style.setLogo("icon.png");
		// 配置通知栏网络图标
		style.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		style.setRing(true);
		style.setVibrate(true);
		style.setClearable(true);
		template.setStyle(style);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(1);
		template.setTransmissionContent(content);
		return template;
	}
	//================================消息透传=====================================
	public static TransmissionTemplate TransmissionTemplateDemo(MsgPut msgPut) {
		TransmissionTemplate template = new TransmissionTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setTransmissionType(2);
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("title", msgPut.getTitle());
		map.put("content", msgPut.getContent());
		Map<String,Object>payload=new HashMap<String,Object>();
		payload.put("RecoredID", "消息透传");
		map.put("payload", payload);
		String obj=JSONObject.toJSONString(map);
		template.setTransmissionContent(obj);
		return template;
	}
    public  static void putMessageTouSingle(MsgPut msgPut) {
    	IGtPush push=new IGtPush(url,appKey, masterSecret);
    	try {
			push.connect();
			TransmissionTemplate template=TransmissionTemplateDemo(msgPut);
			SingleMessage message = new SingleMessage();
			message.setOffline(true);
			// 离线有效时间，单位为毫秒，可选
			message.setOfflineExpireTime(24 * 3600 * 1000);
			message.setData(template);
			// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
			message.setPushNetWorkType(0);
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(msgPut.getCid());
			IPushResult ret = push.pushMessageToSingle(message, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
	 * 全体发送
	 * 
	 * @param msgPut
	 * @throws IOException
	 */
	public static void putMessageTouAll(MsgPut msgPut) throws IOException {

		IGtPush push = new IGtPush(url, appKey, masterSecret);
		try {
			push.connect();
			TransmissionTemplate template=TransmissionTemplateDemo(msgPut);
			List<String> appIds = new ArrayList<String>();
			appIds.add(appId);
			// 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
			AppMessage message = new AppMessage();
			message.setData(template);
			message.setAppIdList(appIds);
			message.setOffline(true);
			message.setOfflineExpireTime(1000 * 600);
			IPushResult ret = push.pushMessageToApp(message);
			System.out.println(ret.getResponse().toString());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 发送群消息
	 * 
	 * @param msgput
	 * @param cids
	 * @throws Exception
	 */
	public static void putMsgToLlistTou(MsgPut msgPut, List<String> cids)
			throws Exception {
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin_pushList_needDetails", "true");
		// 配置返回每个别名及其对应cid的用户状态，可选
		IGtPush push = new IGtPush(url, appKey, masterSecret);
		push.connect();
		// 通知透传模板
		TransmissionTemplate template=TransmissionTemplateDemo(msgPut);
		ListMessage message = new ListMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 配置推送目标
		List targets = new ArrayList();
		for (String cid : cids) {
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(cid);
			targets.add(target);
		}
		// taskId用于在推送时去查找对应的message
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		System.out.println(ret.getResponse().toString());
	}
	public static void main(String[] args) {
		
	}
}
