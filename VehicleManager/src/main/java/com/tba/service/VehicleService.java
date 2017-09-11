package com.tba.service;

import java.util.Map;

import com.tba.model.Direction;
import com.tba.model.Vehicle;
import com.tba.model.VehicleProcessResponse;

/**
 * 
 * @author ekber
 * 
 * Service layer
 * 
 */
public interface VehicleService {
	public void sendVehicle(Vehicle vehicle);
	
	public void updateVehicle(VehicleProcessResponse response);
	
	public void updateVehicleDirection(String vehicleId, Direction direction);
	
	public Map<String, Vehicle> getAllVehicles();
}
