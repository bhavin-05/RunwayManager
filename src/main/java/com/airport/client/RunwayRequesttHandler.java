package com.airport.client;

import com.airport.core.AirportManager;

public abstract class RunwayRequesttHandler {
	protected AirportManager executor;
	public RunwayRequesttHandler() {
		executor=AirportManager.getInstance();
	}

	public abstract String processRequest(String... id);
}
