package com.tba.executor;

import com.google.common.cache.Cache;
import com.tba.model.Vehicle;

/**
 * 
 * @author ekber
 * 
 * This class is responsible to vehicle creation
 *
 */
public class VehicleCreationRunnable implements Runnable {

	private final Cache<String, Vehicle> vehicleCache;
	private final Vehicle vehicle;

	public VehicleCreationRunnable(Cache<String, Vehicle> vehicleCache, Vehicle vehicle) {
		this.vehicleCache = vehicleCache;
		this.vehicle = vehicle;
	}

	@Override
	public void run() {
		//save consumer db
		vehicleCache.put(vehicle.getVehicleId(), vehicle);
	}

}