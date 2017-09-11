package com.tba.messaging;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.tba.model.VehicleProcessResponse;
import com.tba.service.VehicleService;

/**
 * 
 * @author ekber
 *
 * This class used for receiving(from VehicleProcessor) the message using JmsListener as Spring service.
 * 
 */
@Component
public class MessageReceiver {
	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	private static final String VEHICLE_RESPONSE_QUEUE = "vehicle-processor";
	
	@Autowired
	VehicleService vehicleService;
	
	
	@JmsListener(destination = VEHICLE_RESPONSE_QUEUE)
	public void receiveMessage(final Message<VehicleProcessResponse> message) throws JMSException {
		MessageHeaders headers =  message.getHeaders();
		LOG.info("Vehicle : headers received : {}", headers);
		
		VehicleProcessResponse response = message.getPayload();
		LOG.info("Vehicle : response received : {}",response);
		
		vehicleService.updateVehicle(response);	
	}
}
