package com.tba.model;

/**
 * 
 * @author ekber
 * 
 * Vehicle's status
 *
 */
public enum VehicleStatus {

	NEW("New"),
	CREATED("Created"),
	MOVED_FORWARD("Moved_Forward"),
	MOVED_BACKWARD("Moved_Backward"),
	MOVED_LEFT("Moved_Left"),
	MOVED_RIGHT("Moved_Right");
     
    private String status;
     
    private VehicleStatus(final String status){
        this.status = status;
    }
     
    public String getStatus(){
        return this.status;
    }
 
    @Override
    public String toString(){
        return this.status;
    }
 
 
    public String getName(){
        return this.name();
    }
}
