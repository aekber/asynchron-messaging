package com.tba.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.tba.model.Vehicle;
import com.tba.service.VehicleProcessService;

/**
 * 
 * @author ekber
 *
 * This class used for receiving(from VehicleManager) the message using JmsListener as Spring service.
 * 
 */
@Component
public class MessageReceiver {

	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
	private static final String VEHICLE_QUEUE = "vehicle-manager";
	
	@Autowired
	VehicleProcessService vehicleProcessService;

	@JmsListener(destination = VEHICLE_QUEUE)
	public void receiveMessage(final Message<Vehicle> message) throws Exception {
		MessageHeaders headers =  message.getHeaders();
		LOG.info("Vehicle : headers received : {}", headers);
		
		Vehicle vehicle = message.getPayload();
		LOG.info("Vehicle : message received : {}",vehicle);	

		vehicleProcessService.processVehicle(vehicle);
	}
	
}
