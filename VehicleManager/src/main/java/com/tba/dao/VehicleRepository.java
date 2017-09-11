package com.tba.dao;

import java.util.Map;

import com.tba.model.Vehicle;

/**
 * 
 * @author ekber
 *
 * Data Access Object
 */
public interface VehicleRepository {

	public void putVehicle(Vehicle vehicle);
	
	public Vehicle getVehicle(String vehicleId);
	
	public Map<String, Vehicle> getAllVehicles();
}
