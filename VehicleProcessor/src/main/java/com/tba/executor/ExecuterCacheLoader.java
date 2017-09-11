package com.tba.executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.tba.model.Vehicle;

/**
 * 
 * @author ekber
 * 
 * This class is used to get ExecutorService from thread factory
 *
 */
public class ExecuterCacheLoader extends CacheLoader<Vehicle, ExecutorService> {

	private final ThreadFactoryBuilder threadfactorybuilder;

	public ExecuterCacheLoader(ThreadFactoryBuilder threadfactorybuilder) {
		this.threadfactorybuilder = threadfactorybuilder;
	}

	@Override
	public ExecutorService load(Vehicle key) throws Exception {
		String threadNameFormat = String.format("Vehicle %s", key.getVehicleName()); 
		ThreadFactory threadFactory = threadfactorybuilder.setNameFormat(threadNameFormat).build();
		ExecutorService executorService = Executors.newSingleThreadExecutor(threadFactory);
		return executorService;
	}

}