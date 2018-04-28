package com.airport.core;

import java.util.concurrent.BlockingQueue;

import com.airport.entity.RunwayRequest;

public class RunwayListener implements Runnable {
	String runwayId;
	BlockingQueue<RunwayRequest> workq;
	boolean shutdown=false;
	AirportManager manager;
	
	public RunwayListener(String id, AirportManager manager) {
		this.runwayId=id;
		this.manager=manager;
	}
	
	@Override
	public void run() {
		
		while(true) {
			try {
				// do not process more requests 
				if(shutdown) break;
				
				// blocking call
				RunwayRequest request = manager.getNextRunwayRequest();
				
				manager.requestStart(request, runwayId);
				Thread.sleep(10000);
				manager.requestComplete(request, runwayId);
				
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	public void shutdown() {
		shutdown=true;
	}
	
}