package com.tba.executor;

import com.tba.model.Direction;
import com.tba.model.Point;
import com.tba.model.Vehicle;

/**
 * 
 * @author ekber
 * 
 * This class is responsible to vehicle redirecting
 *
 */
public class VehicleControllerRunnable implements Runnable {

	private final Vehicle vehicle;

	public VehicleControllerRunnable(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public void run() {
		Point point = vehicle.getPoint();
		boolean isInXCoordinate = point.getxCoordinate() > 0d && point.getyCoordinate() == 0d;
		if(isInXCoordinate){	
			if (vehicle.getDirection() == Direction.FORWARD) {
				point.incrementxCoordinate();
			} else if (vehicle.getDirection() == Direction.BACKWARD) {
				point.decrementxCoordinate();
			} else if (vehicle.getDirection() == Direction.LEFT) {
				point.incrementyCoordinate();
			} else {
				point.decrementyCoordinate();
			}
		} else{
			if (vehicle.getDirection() == Direction.FORWARD) {
				point.incrementyCoordinate();
			} else if (vehicle.getDirection() == Direction.BACKWARD) {
				point.decrementyCoordinate();
			} else if (vehicle.getDirection() == Direction.LEFT) {
				point.decrementxCoordinate();
			} else {
				point.incrementxCoordinate();
			}
		}
	}

}