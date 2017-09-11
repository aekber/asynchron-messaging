package com.tba.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tba.model.Direction;
import com.tba.model.Vehicle;
import com.tba.service.VehicleService;

/**
 * 
 * @author ekber
 *
 * This class is used for Controller.HTTP requests come to these methods according to their mapping.
 * 
 */
@Controller
public class VehicleManagerController {

	@Autowired
	VehicleService vehicleService;
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String prepareProduct(ModelMap model) {
		return "index";
	}

	@RequestMapping(value = { "/newVehicle" }, method = RequestMethod.GET)
	public String prepareVehicle(ModelMap model) {
		Vehicle vehicle = new Vehicle();
		model.addAttribute("vehicle", vehicle);
		return "vehicle";
	}

	@RequestMapping(value = { "/newVehicle" }, method = RequestMethod.POST)
	public String sendVehicle(@Valid Vehicle vehicle, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "vehicle";
		}
		vehicleService.sendVehicle(vehicle);
		model.addAttribute("success", "Vehicle " + vehicle.getVehicleName() + " created.");
		return "vehicleSuccess";
	}
	
	@RequestMapping(value = { "/checkStatus" }, method = RequestMethod.GET)
	public String checkVehicleStatus(ModelMap model) {
		model.addAttribute("vehicles", vehicleService.getAllVehicles());
		return "vehicleStatus";
	}
	
	@RequestMapping(value = { "/forward-{vehicleId}" }, method = RequestMethod.GET)
	public String moveForward(@PathVariable String vehicleId, ModelMap model) {
		vehicleService.updateVehicleDirection(vehicleId, Direction.FORWARD);
		model.addAttribute("message", "The vehicle with " + vehicleId + " id is moved forward.");
		return "moveSuccess";
	}
	
	@RequestMapping(value = { "/backward-{vehicleId}" }, method = RequestMethod.GET)
	public String moveBackward(@PathVariable String vehicleId, ModelMap model) {
		vehicleService.updateVehicleDirection(vehicleId, Direction.BACKWARD);
		model.addAttribute("message", "The vehicle with " + vehicleId + " id is moved backward.");
		return "moveSuccess";
	}
	
	@RequestMapping(value = { "/right-{vehicleId}" }, method = RequestMethod.GET)
	public String moveRight(@PathVariable String vehicleId, ModelMap model) {
		vehicleService.updateVehicleDirection(vehicleId, Direction.RIGHT);
		model.addAttribute("message", "The vehicle with " + vehicleId + " id is turned right.");
		return "moveSuccess";
	}
	
	@RequestMapping(value = { "/left-{vehicleId}" }, method = RequestMethod.GET)
	public String moveLeft(@PathVariable String vehicleId, ModelMap model) {
		vehicleService.updateVehicleDirection(vehicleId, Direction.LEFT);
		model.addAttribute("message", "The vehicle with " + vehicleId + " id is turned left.");
		return "moveSuccess";
	}
	
	@RequestMapping(value = { "/showPlace" }, method = RequestMethod.GET)
	public String showPlace(ModelMap model) {
		model.addAttribute("vehicles", vehicleService.getAllVehicles());
		return "showPlace";
	}
}
