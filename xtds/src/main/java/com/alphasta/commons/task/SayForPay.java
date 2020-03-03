package com.alphasta.commons.task;

import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alphasta.commons.utils.MsyToAllUser;
@Component("sayForPay") 
public class SayForPay {
	@Scheduled(cron = "0 0 9 1 * ?") 
	public void sayToAll(){
		Calendar c=Calendar.getInstance();
		int month=c.get(Calendar.MONTH)+1;		
		MsyToAllUser msg=new MsyToAllUser("党费通知","提示： 请在"+month+"月10号前到所在支部缴纳党费。");
		msg.sentOut();
	};
	public static void main(String[] args) {
		SayForPay s=new SayForPay();
		s.sayToAll();
	}
}
