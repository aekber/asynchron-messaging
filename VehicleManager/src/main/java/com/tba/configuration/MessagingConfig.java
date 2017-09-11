package com.tba.configuration;

import java.util.Arrays;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/**
 * 
 * @author ekber
 * 
 * Messaging configuration class
 *
 */
@Configuration
public class MessagingConfig {

	private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
	
	private static final String VEHICLE_QUEUE = "vehicle-manager";

	/*
	 * ConnectionFactory is being configured to connect a message broker and send/receive messages.
	 * ActiveMQConnectionFactory is an implementation of ConnectionFactory.
	 */
	@Bean
	public ActiveMQConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		connectionFactory.setTrustedPackages(Arrays.asList("com.tba"));
		return connectionFactory;
	}
	
	/*
	 * JMS messages are sent by an application is addressed with a destination. 
	 * In JMS, there are two types of destination; queue and topic.
	 * In this application i used queue(point to point) as destination.
	 * 
	 * JmsTemplate is being used for abstraction ,through we can avoid the complexities of JMS communication.
	 * Spring manages these complexities.
	 */
	@Bean 
	public JmsTemplate jmsTemplate(){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(VEHICLE_QUEUE);
		return template;
	}
	
}
