package com.airport.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AirportStatus {

	public int time;
	public Map<String,RunwayRequest> takeoffInFLight = new HashMap<>();
	public Map<String,RunwayRequest> landingInFlight= new HashMap<>();
	public List<String> takeOffWaiting = new ArrayList<>();
	public List<String> landingWaiting = new ArrayList<>();
	public int takeOffs;
	public int landings;
	
	
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		result.append("Time: " + time + " sec\n");
		result.append("Inflight for takeoff: " + getString(takeoffInFLight) + "\n");
		result.append("Waiting for takeoff: " + getString(takeOffWaiting) + "\n");
		result.append("Inflight for landing:: " + getString(landingInFlight) + "\n");
		result.append("Waiting for landing: " + getString(landingWaiting) + "\n");
		result.append("Successful takeoffs: " + getString(takeOffs)  + " \n");
		result.append("Successful landings: " + getString(landings)  + " \n");
		
		return result.toString();
	};
	
	
	private String getString(List<String> takeOffWaiting2) {
		if(takeOffWaiting2 !=null && takeOffWaiting2.size()>0) {
			return String.join(",", takeOffWaiting2);
		}
		return "none";
	}



	private String getString(Map<String, RunwayRequest> inflight) {
		if(inflight != null && inflight.size()>0) {
			String result = "";
			Iterator<String> iter = inflight.keySet().iterator();
			
			while(iter.hasNext()) {
				String id = iter.next();
				RunwayRequest req = inflight.get(id);
				if(req != null) {
					result+="flight " + req.airplaneId + " runway " + id + " ";
				}
			}
			
			return result;
		} else {
			return "none";
		}
	}
	
	private String getString(int takeOffs) {
		
		return 	takeOffs>0?takeOffs+"":"none";
	}


}
