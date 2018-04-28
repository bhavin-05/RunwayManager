package com.airport.client;

public class TakeoffHandler extends RunwayRequesttHandler{
	
	public TakeoffHandler() {
		super();
	}
	public String processRequest(String... id) {
		if(id.length>1) {
			executor.createTakeOffRequest(id[1]);
		} else {
			System.out.println("invalid request");
		}
		
		return "Success";
	}
}
