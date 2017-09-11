package com.tba.service;

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
		
		if(response.getReturnCode()==200){
			vehicle.setStatus(VehicleStatus.CREATED);
		}else if(response.getReturnCode()==300){
			vehicle.setStatus(VehicleStatus.MOVED_FORWARD);
		}else if(response.getReturnCode()==400){
			vehicle.setStatus(VehicleStatus.MOVED_BACKWARD);
		}else if(response.getReturnCode()==500){
			vehicle.setStatus(VehicleStatus.MOVED_LEFT);
		}else if(response.getReturnCode()==600){
			vehicle.setStatus(VehicleStatus.MOVED_RIGHT);
		}else{
			vehicle.setStatus(VehicleStatus.NEW);
		}
		
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
