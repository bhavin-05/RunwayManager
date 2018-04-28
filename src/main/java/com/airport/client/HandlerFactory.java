package com.airport.client;

import java.util.HashMap;
import java.util.Map;

public class HandlerFactory {

	private static Map<String,RunwayRequesttHandler> handlerMap = new HashMap<>();
	
	static {
		handlerMap.put("takeoff", new TakeoffHandler());
		handlerMap.put("land", new LandingHandler());
		handlerMap.put("status", new StatusHandler());
		handlerMap.put("priority", new PriorityChangeHandler());
	}
	
	public static RunwayRequesttHandler getHandler(String command) {
		return handlerMap.get(command);
	}
}
