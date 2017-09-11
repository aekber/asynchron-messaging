package com.tba.configuration;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

/**
 * 
 * @author ekber
 * 
 * This class is used for message listening on a destination.
 * 
 * The container listens on a destination and when any message arrives on this destination, 
 * it retrieves that message and passes to the bean annotated with @JmsListener.
 * 
 * Also this container is an implementation of JmsListenerContainerFactory.
 *
 */
@Configuration
@EnableJms
public class MessagingListenerConfig {

	@Autowired
	ConnectionFactory connectionFactory;

	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-1");
        return factory;
    }

}

