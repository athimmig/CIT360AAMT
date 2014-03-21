package com.example.skishare;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.example.skishare.handlers.*;

/*
 * This class's purpose is to map commands to handlers
 * It receives a command and forwards the parameters
 * to the correct handler
 */
public class ApplicationController {

	private static ApplicationController app;
	//contains the mapping between commands and handlers
	private HashMap<String, String> commands = new HashMap<String, String>();

	//instantiates class with command/handler mappings
	private ApplicationController() {
		this.commands.put("search", "com.example.skishare.handlers.SearchHandler");
		this.commands.put("handle_search_results", "com.example.skishare.handlers.HandleSearchResultsHandler");
	}

	public static ApplicationController getApplicationController() {
		if (app == null) {
			app = new ApplicationController();
		}
		return app;
	}

	//takes a command and returns the handler
	private String get(String command) {
		return this.commands.get(command);
	}

	//get the handler and run the handleIt method off of it
	public void handleRequest(String command, ArrayList parameters) {
		try {
			//use reflection to get and instance of the runnable
			Class<?> handler = Class.forName(this.get(command));
			Constructor<?> constructor = handler.getConstructor(ArrayList.class);
			Runnable instance = (Runnable)constructor.newInstance(parameters);
			
			Thread handlerThread = new Thread(instance);
			handlerThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
