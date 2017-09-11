package com.tba.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.tba.model.VehicleProcessResponse;

/**
 * 
 * @author ekber
 *
 * This class used for sending(to VehicleManager) the message using JmsTemplate as Spring service.
 * 
 */
@Component
public class MessageSender {

	@Autowired
	JmsTemplate jmsTemplate;

	public void sendMessage(final VehicleProcessResponse vehicleProcessResponse) {

		jmsTemplate.send(new MessageCreator(){
				@Override
				public Message createMessage(Session session) throws JMSException{
					ObjectMessage objectMessage = session.createObjectMessage(vehicleProcessResponse);
					return objectMessage;
				}
			});
	}

}
