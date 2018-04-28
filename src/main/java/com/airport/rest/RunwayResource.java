package com.airport.rest;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.airport.client.RunwayRequesttHandler;
import com.airport.client.HandlerFactory;
import com.airport.core.AirportManager;
import com.airport.core.RunwayListener;

@Path("/v1/api/")
public class RunwayResource {

	AirportManager manager = AirportManager.getInstance();

	/**
	 * @param id
	 * @return
	 */
    @POST
    @Path("landing/airplane/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String landing(@PathParam("id") String id) {
    	RunwayRequesttHandler handler = HandlerFactory.getHandler("land");
    	return handler.processRequest(null,id);
    	
    }

    @POST
    @Path("landing/airplane/{id}/{priority}")
    @Produces(MediaType.TEXT_PLAIN)
    public String landingPriority(@PathParam("id") String id,  @PathParam("priority") String priority) {
    	RunwayRequesttHandler handler = HandlerFactory.getHandler("priority");
    	return handler.processRequest(null,"LAND",id,priority);
    }

    @POST
    @Path("takeoff/airplane/{id}/{priority}")
    @Produces(MediaType.TEXT_PLAIN)
    public String takeoffPriority(@PathParam("id") String id,  @PathParam("priority") String priority) {
    	RunwayRequesttHandler handler = HandlerFactory.getHandler("priority");
    	return handler.processRequest(null,"TAKEOFF",id,priority);
    }

    @POST
    @Path("takeoff/airplane/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String takeoff(@PathParam("id") String id) {
    	RunwayRequesttHandler handler = HandlerFactory.getHandler("takeoff");
    	return handler.processRequest(null,id);
    	
    }

    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public String status() {
    	RunwayRequesttHandler handler = HandlerFactory.getHandler("status");
    	return handler.processRequest();
    }
    
    @POST
    @Path("runway/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String runway(@PathParam("id") String id) {
    	manager.runways.add(id);
    	RunwayListener listener = new RunwayListener(id,manager);
    	new Thread(listener).start();
    	return "Success";
    }
    
}