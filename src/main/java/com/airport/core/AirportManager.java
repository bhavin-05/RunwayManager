package com.airport.core;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import com.airport.entity.AirportStatus;
import com.airport.entity.RunwayRequest;
import com.airport.entity.RunwayRequest.RunwayRequestType;


public class AirportManager {

	public static final long START_TIME=System.currentTimeMillis();
	private static final AirportManager INSTANCE = new AirportManager();
	BlockingQueue<RunwayRequest> pendingRunwayRequests= new PriorityBlockingQueue<RunwayRequest>();
	Map<String,RunwayRequest> flightsInProgress = new HashMap<>();
	public List<String> runways = new ArrayList<>();

	int takeoffs=0;
	int landings=0;
	

	private AirportManager() {
		runways.add("1");
		runways.add("2");
	}

	/**
	 * Create Requests
	 */
	public int createTakeOffRequest(String id) {
		pendingRunwayRequests.add(new RunwayRequest(id,RunwayRequestType.TAKEOFF,System.nanoTime()));
		return pendingRunwayRequests.size();
	}
	public int createLandingRequest(String id) {
		pendingRunwayRequests.add(new RunwayRequest(id,RunwayRequestType.LAND,System.nanoTime()));
		return pendingRunwayRequests.size();
	}
	public int changePriority(String id, RunwayRequestType type, int priority) {
		RunwayRequest req = new RunwayRequest(id,type,System.nanoTime());
		boolean removed = pendingRunwayRequests.remove(req);
		
		if(removed) {
			req.priority=priority;
			pendingRunwayRequests.add(req);
		}
		
		
		return pendingRunwayRequests.size();
	}
	
	/**
	 * Return top priority request
	 */
	public RunwayRequest getNextRunwayRequest() throws InterruptedException {
		return pendingRunwayRequests.take();
	}
	public void requestStart(RunwayRequest request, String runwayId) throws InterruptedException {
		flightsInProgress.put(runwayId, request);
	}
	public void requestComplete(RunwayRequest request,String runwayId) throws InterruptedException {
		flightsInProgress.remove(runwayId);
		if(request.runwayRequestType.equals(RunwayRequestType.TAKEOFF)) {
			takeoffs++;
		} else {
			landings++;
		}
	}

	
	public AirportStatus buildStatus() {
		AirportStatus status = new AirportStatus();
		
		status.time= (int)((System.currentTimeMillis()-START_TIME)/1000);
		
		for(String id:runways) {
			RunwayRequest req = flightsInProgress.get(id);
			if(req != null) {
				if(req.runwayRequestType.equals(RunwayRequestType.TAKEOFF)) {
					status.takeoffInFLight.put(id, req);
				} else {
					status.landingInFlight.put(id, req);
				}
			}
		}
		Iterator<RunwayRequest> queueIterator = pendingRunwayRequests.iterator();
		while(queueIterator.hasNext()) {
			RunwayRequest req = queueIterator.next();
			if(req.runwayRequestType.equals(RunwayRequestType.TAKEOFF)) {
				status.takeOffWaiting.add(req.airplaneId);
			} else {
				status.landingWaiting.add(req.airplaneId);
			}
		}
		status.landings=landings;
		status.takeOffs=takeoffs;
		return status;
	}
	
	
	public static AirportManager getInstance() {
		return INSTANCE;
	}
	
	

	


}
