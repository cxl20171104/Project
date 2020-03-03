package com.alphasta.service.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alphasta.model.Param;

@Component
public class CpqNumReturn {
	 @Scheduled(cron ="0 0 0 * * ?")
	 public  void retrun() {
		 Param.cpq_num=0;
	 }
}
