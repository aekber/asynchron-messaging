package com.tba.service;

import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.tba.executor.ExecuterCacheLoader;
import com.tba.executor.VehicleControllerRunnable;
import com.tba.executor.VehicleCreationRunnable;
import com.tba.messaging.MessageSender;
import com.tba.model.Direction;
import com.tba.model.Vehicle;
import com.tba.model.VehicleProcessResponse;

/**
 * 
 * @author ekber
 * 
 * This class is vehicle processor.It processes vehicle from VehicleManager and 
 * then returns response to VehicleManager application.
 * 
 * Also this class works as multi-threaded using ExecutorService and guava lib.
 *
 */
@Service("vehicleProcessService")
public class VehicleProcessServiceImpl implements VehicleProcessService {
	
	@Autowired
	MessageSender messageSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(VehicleProcessServiceImpl.class);
	
	private final static ThreadFactoryBuilder threadFactoryBuilder;
	private final static LoadingCache<Vehicle, ExecutorService> vehicleExecuters;
	private final static Cache<String, Vehicle> vehicleCache;

	
	static {
		threadFactoryBuilder = new ThreadFactoryBuilder();
		vehicleExecuters = CacheBuilder.newBuilder().build(new ExecuterCacheLoader(threadFactoryBuilder));
		vehicleCache = CacheBuilder.newBuilder().build();
	}

	
	public void processVehicle(Vehicle vehicle) throws Exception {
		VehicleProcessResponse response = prepareResponse(vehicle);
		LOG.info("Vehicle : sending vehicle process detail {}", response);
		messageSender.sendMessage(response);
	}

	private VehicleProcessResponse prepareResponse(Vehicle vehicle) throws Exception {
		
		if (vehicle.getDirection() != null) {
			gotoDirection(vehicle);
			return createConsumerResponse(vehicle);
		}

		addVehicle(vehicle);
		return createConsumerResponse(vehicle);

	}
	
	private static void addVehicle(Vehicle vehicle) throws Exception {
		ExecutorService vehicleExecutor = vehicleExecuters.get(vehicle);
		Runnable vehicleCreationRunnable = new VehicleCreationRunnable(vehicleCache, vehicle);
		vehicleExecutor.execute(vehicleCreationRunnable);
	}
	
	private static void gotoDirection(Vehicle vehicle) throws Exception {
		Vehicle v = vehicleCache.getIfPresent(vehicle);
		if (v == null) {
			LOG.info("Check your vehicle with id {}", vehicle.getVehicleId());
		}
		
		ExecutorService vehicleExecutor = vehicleExecuters.get(vehicle);
		Runnable vehicleControllerRunnable = new VehicleControllerRunnable(vehicle);
		vehicleExecutor.execute(vehicleControllerRunnable);
	}

	private VehicleProcessResponse createConsumerResponse(Vehicle vehicle) {
		VehicleProcessResponse response = new VehicleProcessResponse();
		response.setVehicle(vehicle);
		response.setReturnCode(getReturnCodeByDirection(vehicle.getDirection()));
		response.setComment("Vehicle processed successfully");
		return response;
	}

	/**
	 * This method returns process result as result code
	 * 
	 * @param direction
	 * @return
	 */
	private int getReturnCodeByDirection(Direction direction) {
		if (direction == Direction.FORWARD) {
			return 300;
		} else if (direction == Direction.BACKWARD) {
			return 400;
		} else if (direction == Direction.LEFT) {
			return 500;
		} else if (direction == Direction.RIGHT) {
			return 600;
		}
		
		return 200; //vehicle CREATED
	}

}