package com.tba.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tba.dao.VehicleRepository;
import com.tba.messaging.MessageSender;
import com.tba.model.Direction;
import com.tba.model.Vehicle;
import com.tba.model.VehicleProcessResponse;
import com.tba.model.VehicleStatus;

/**
 * 
 * @author ekber
 * 
 * Service layer implementation
 * 
 */
@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService{

	static final Logger LOG = LoggerFactory.getLogger(VehicleServiceImpl.class);
	
	@Autowired
	MessageSender messageSender;
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	private static Map<Integer, VehicleStatus> statusMap;
	
	static {
		statusMap = new HashMap<Integer, VehicleStatus>();
		statusMap.put(200, VehicleStatus.CREATED);
		statusMap.put(300, VehicleStatus.MOVED_FORWARD);
		statusMap.put(400, VehicleStatus.MOVED_BACKWARD);
		statusMap.put(500, VehicleStatus.MOVED_LEFT);
		statusMap.put(600, VehicleStatus.MOVED_RIGHT);
	}
	
	public void sendVehicle(Vehicle vehicle) {
		vehicle.setVehicleId(UUID.randomUUID().toString());
		vehicle.setStatus(VehicleStatus.NEW);
		vehicleRepository.putVehicle(vehicle);
		
		LOG.info("Application : sending new vehicle request {}", vehicle);
		
		messageSender.sendMessage(vehicle);
	}

	/**
	 * This methods resolves return codes from other application
	 */
	public void updateVehicle(VehicleProcessResponse response) {
		Vehicle vehicle = response.getVehicle();
		vehicle.setStatus(statusMap.get(response.getReturnCode()));
		vehicleRepository.putVehicle(vehicle);
	}
	
	public Map<String, Vehicle> getAllVehicles(){
		return vehicleRepository.getAllVehicles();
	}

	/**
	 * This method send a message on queue to update vehicle direction.
	 */
	public void updateVehicleDirection(String vehicleId, Direction direction) {
		Vehicle vehicle = vehicleRepository.getVehicle(vehicleId);
		vehicle.setDirection(direction);
		
		LOG.info("Application : sending vehicle move request {}", vehicle);
		
		messageSender.sendMessage(vehicle);
	}

}
