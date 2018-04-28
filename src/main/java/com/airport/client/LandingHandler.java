
package com.airport.client;

public class LandingHandler extends RunwayRequesttHandler{

	public String processRequest(String... id) {
		if(id.length>1) {
			executor.createLandingRequest(id[1]);
		} else {
			System.out.println("invalid request");
		}
		return "Success";
	}
}
