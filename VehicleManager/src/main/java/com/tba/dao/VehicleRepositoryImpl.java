package com.tba.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.tba.model.Vehicle;

/**
 * 
 * @author ekber
 *
 * Data Access Object implementation.
 * 
 * vehicles map is used as database to store objects
 */
@Service("vehicleRepository")
public class VehicleRepositoryImpl implements VehicleRepository{

	private final Map<String, Vehicle> vehicles = new ConcurrentHashMap<String, Vehicle>();
	
	@Override
	public void putVehicle(Vehicle vehicle) {
		vehicles.put(vehicle.getVehicleId(), vehicle);
	}

	@Override
	public Vehicle getVehicle(String vehicleId) {
		return vehicles.get(vehicleId);
	}

	public Map<String, Vehicle> getAllVehicles(){
		return vehicles;
	}
}
