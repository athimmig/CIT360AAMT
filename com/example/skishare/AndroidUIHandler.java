package com.example.skishare;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import android.os.Handler;

//This class is for enabling the runnables to have access to UI elements and to be run on the UI thread
public class AndroidUIHandler {

	//the handler
	private static Handler androidHandler;
	//contains weakreferences to all UI elements
	private static HashMap<String, WeakReference> weakReferences = new HashMap<String, WeakReference>();
	
	//gets a WeakReference
	public static WeakReference getWeakReference(String index) {
		return weakReferences.get(index);
	}
	
	//adds a WeakReference
	public static void addWeakReference(String index, WeakReference uiElement) {
		weakReferences.put(index, uiElement);
	}
	
	//sets the new handler
	public static void setHandler(Handler newHandler) {
		androidHandler = newHandler;
	}
	
	//gets the handler
	public static Handler getHandler() {
		return androidHandler;
	}	
}