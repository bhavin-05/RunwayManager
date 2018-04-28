package com.airport.entity;

public class RunwayRequest implements Comparable<RunwayRequest> {

	private static final int DEFAULT_PRIORITY = 10;
	public int priority;
	public long requesttime;
	public String airplaneId;
	public RunwayRequestType runwayRequestType;
	
	@Override
	public int compareTo(RunwayRequest o) {
		
		if(this.priority != o.priority) {
			return this.priority-o.priority;
		}
		
		if(!this.runwayRequestType.equals(o.runwayRequestType)) {
			return this.runwayRequestType.ordinal()-o.runwayRequestType.ordinal();
		}
		
		return this.requesttime>o.requesttime?1:-1;
		
	}
	
	public RunwayRequest(int priority, String airplaneId, RunwayRequestType runwayRequestType, long requesttime ) {
		this.priority=priority;
		this.requesttime=requesttime;
		this.airplaneId=airplaneId;
		this.runwayRequestType=runwayRequestType;
		
	}
	
	public RunwayRequest(String airplaneId, RunwayRequestType runwayRequestType, long requesttime) {
		this.priority=DEFAULT_PRIORITY;
		this.requesttime=requesttime;
		this.airplaneId=airplaneId;
		this.runwayRequestType=runwayRequestType;
		
	}
	@Override
	public boolean equals(Object obj) {
		RunwayRequest o = (RunwayRequest) obj;
		
		return (this.airplaneId.equals(o.airplaneId)) && this.runwayRequestType.equals(o.runwayRequestType);
	}
	public enum RunwayRequestType {
		 LAND,TAKEOFF
	}

}

 
