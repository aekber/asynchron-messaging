package com.tba.model;

import java.io.Serializable;

/**
 * 
 * @author ekber
 * 
 * This is a wrapper class for Vehicle and used for response from other application.
 *
 */
public class VehicleProcessResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9032086315603687948L;
	
	private Vehicle vehicle;
	private int returnCode;
	private String comment;
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public int getReturnCode() {
		return returnCode;
	}
	
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleProcessResponse other = (VehicleProcessResponse) obj;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InventoryResponse [vehicle=" + vehicle + ", returnCode="
				+ returnCode + ", comment=" + comment + "]";
	}

}
