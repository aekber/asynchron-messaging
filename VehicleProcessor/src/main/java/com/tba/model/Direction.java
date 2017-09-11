package com.tba.model;

/**
 * 
 * @author ekber
 *
 * Vehicle Directions
 */
public enum Direction {

	LEFT("Left"),
    RIGHT("Right"),
    FORWARD("Forward"),
    BACKWARD("Backward");
	
     
    private String direction;
     
    private Direction(final String direction){
        this.direction = direction;
    }
     
    public String getDirection(){
        return this.direction;
    }
 
    @Override
    public String toString(){
        return this.direction;
    }
 
    public String getName(){
        return this.name();
    }
}
