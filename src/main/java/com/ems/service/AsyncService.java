package com.ems.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
	Logger log = LoggerFactory.getLogger(AsyncService.class);

//	@Async
	public void asyncLog(String message) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("SYSOUT ASYNC");
		log.info("Log Message Async");

	}

}
