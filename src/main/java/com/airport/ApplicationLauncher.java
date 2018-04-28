package com.airport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.airport.client.HandlerFactory;
import com.airport.client.RunwayRequesttHandler;
import com.airport.core.AirportManager;
import com.airport.core.RunwayListener;
import com.airport.util.JerseyServer;

public class ApplicationLauncher {

	static Scanner input = new Scanner(System.in);
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static List<RunwayListener> runwayListeners ;
	static AirportManager manager = AirportManager.getInstance();

	public static void main(String[] args) throws Exception {

		// rest server
		starthttpserver();
		
		// Start runway listener threads
		launchRunwayListener();

		// Listen to console for commands
		readInput();

		// shutdown executor if terminate received
		shutDown();

		
		
	}
	
	
	private static void starthttpserver() {
		new Thread(new JerseyServer()).start();
		
	}


	public static void launchRunwayListener() {
		
		runwayListeners = new ArrayList<>();
		for(String id:manager.runways) {
			runwayListeners.add(new RunwayListener(id,manager));
		}
		
		for(RunwayListener runwayListener:runwayListeners) {
			new Thread(runwayListener).start();
		}
		
	}



	private static void readInput() throws IOException {

		String input = "";

		while (true) {
			
			// blocking call
			input = in.readLine();

			String[] command = input.split(" ");

			if (command[0].equals("quit")) {
				break;
			}

			RunwayRequesttHandler handler = HandlerFactory.getHandler(command[0]);
			
			if(handler != null) {
				handler.processRequest(command);
			}
		}
		
		in.close();

	}
	
	public static void shutDown() {
		for(RunwayListener runwayListener:runwayListeners) {
			runwayListener.shutdown();
		}
		
	}

	
	
}
