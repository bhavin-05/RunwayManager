package com.airport.client;

import com.airport.entity.RunwayRequest.RunwayRequestType;

public class PriorityChangeHandler extends RunwayRequesttHandler{
	
	public PriorityChangeHandler() {
		super();
	}
	public String processRequest(String... id) {
		String response="";
		try{
			executor.changePriority(id[2], RunwayRequestType.valueOf(RunwayRequestType.class, id[1].toUpperCase()), Integer.valueOf(id[3]));
			response= "success";
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("invalid request");
			response= "invalid request";
		}
		
		return response;
	}
}
