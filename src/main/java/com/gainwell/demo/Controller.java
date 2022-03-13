package com.gainwell.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gainwell.common.AppConstants;
import com.gainwell.common.Message;

@RestController
public class Controller {

	private final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private KafkaTemplate<Object, Object> template;

	@PostMapping(value = "/send/{what}")
	public void sendMessageToKafkaTopic(@PathVariable String what) {
		Message message = new Message(what);
		logger.info("Published: " + message);
		template.send(AppConstants.TOPIC_NAME, message);
	}
}
