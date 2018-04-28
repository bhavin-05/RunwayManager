package com.airport.client;

import com.airport.entity.AirportStatus;

public class StatusHandler extends RunwayRequesttHandler{

	public StatusHandler() {
		super();
	}

	public String processRequest(String... id) {
		AirportStatus status = executor.buildStatus();
		System.out.println(status.toString());
		return status.toString();
		
	}
}
