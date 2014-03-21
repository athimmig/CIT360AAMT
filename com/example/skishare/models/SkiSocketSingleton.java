package com.example.skishare.models;

import java.net.Socket;

import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

/*
 * This class contains all of the connections to the server
 * over a socket
 * 
 * Uses singleton pattern
 */

public class SkiSocketSingleton {

	private static SkiSocketSingleton SkiSocket;
	private Socket toServer;
	private JSONInputStream inFromServer;
	private JSONOutputStream outToServer;

	private SkiSocketSingleton() {
		try {
			toServer = new Socket("10.0.2.2", 9292);
			inFromServer = new JSONInputStream(toServer.getInputStream());
			outToServer = new JSONOutputStream(toServer.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SkiSocketSingleton getSkiSocket() {
		if (SkiSocket == null) {
			SkiSocket = new SkiSocketSingleton();
		}
		return SkiSocket;
	}
	
	public JSONInputStream getInFromServer() {
		return inFromServer;
	}

	public JSONOutputStream getOutToServer() {
		return outToServer;
	}

}