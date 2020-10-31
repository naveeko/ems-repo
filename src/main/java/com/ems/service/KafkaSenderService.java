package com.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSenderService {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Value("${app.topic}")
	private String topic;
	
	public void publish(String message) {
		System.out.println("Kafka publishing message ");
		kafkaTemplate.send(topic, message);
	}

}
