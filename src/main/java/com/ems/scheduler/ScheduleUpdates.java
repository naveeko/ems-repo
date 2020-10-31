package com.ems.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ems.service.KafkaSenderService;

@Component
public class ScheduleUpdates {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	@Autowired
	KafkaSenderService kafkaSender;

	@Scheduled(fixedRate = 10000)
	public void processEmployeeRecords() {
		kafkaSender.publish( "Schduler published message @ : " + dateFormat.format(new Date()));

	}
}
