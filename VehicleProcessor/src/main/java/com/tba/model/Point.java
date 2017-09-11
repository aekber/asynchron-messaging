package com.tba.model;

import java.io.Serializable;

/**
 * 
 * @author ekber
 * 
 * Vehicle location as 2D point
 *
 */
public class Point implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8055227799868019915L;
	
	private double xCoordinate;
	private double yCoordinate;
	

	public Point(double xCoordinate, double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	public double getxCoordinate() {
		return xCoordinate;
	}
	
	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	
	public double getyCoordinate() {
		return yCoordinate;
	}
	
	public void incrementyCoordinate() {
		this.yCoordinate++;
	}
	
	public void incrementxCoordinate() {
		this.xCoordinate++;
	}
	
	public void decrementyCoordinate() {
		this.yCoordinate--;
	}
	
	public void decrementxCoordinate() {
		this.xCoordinate--;
	}
	
	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(xCoordinate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yCoordinate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Point other = (Point) obj;
		if (Double.doubleToLongBits(xCoordinate) != Double
				.doubleToLongBits(other.xCoordinate))
			return false;
		if (Double.doubleToLongBits(yCoordinate) != Double
				.doubleToLongBits(other.yCoordinate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
	}

}
