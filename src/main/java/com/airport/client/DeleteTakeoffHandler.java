package com.airport.client;

public class DeleteTakeoffHandler extends RunwayRequesttHandler{
	
	public DeleteTakeoffHandler() {
		super();
	}
	public String processRequest(String... id) {
		String result="";
		if(id.length>1) {
			result = executor.deleteTakeOffRequest(id[1]);
		} else {
			result="invalid request";
		}
		
		return result;
	}
}
